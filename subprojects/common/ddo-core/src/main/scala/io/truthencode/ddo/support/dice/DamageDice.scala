/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: DamageDice.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.support.dice

import io.truthencode.ddo.ExtraInfo
import io.truthencode.ddo.model.meta.PhysicalDamageType

import scala.util.matching.Regex

/**
 * Contains Damage information based on Dice
 *
 * @example
 *   Given 3[1d4] + 2 weaponModifier : 3 dice : 1d4 extra : 2
 */
sealed trait DamageDice {

  /**
   * Multiplies the dice result by the specified number
   * @see
   *   [[http://ddowiki.com/page/Weapon_dice_multiplier Weapon Dice Modifier (Wiki)]]
   * @example
   *   weaponModifier 3[1d4] = 1[3d4]
   */
  val weaponModifier: Double

  /**
   * Number of sides of the dice
   *
   * 6 represents a 6 sided die
   */
  val dice: Dice

  /**
   * Adds or subtracts to the value of the dice
   * @example
   *   1d8 + 3, 3 is the extra, yielding (1 to 8) + 3 or 4 to 11 damage
   */
  val extra: ExtraInfo

  /**
   * List of damage types applied to an attack as Slash, Pierce, Magic, Good, Acid etc. This is used
   * for purposes of damage reduction and may further be amplified by spell / melee / ranged power.
   */
  val damageType: List[PhysicalDamageType]
}

/**
 * Encapsulates DnD Dice syntax notation with support for damage type flags (Magic, Silver etc)
 * @note
 *   implementation based on [[https://stackoverflow.com/a/25538287/400729]]
 */
object DamageInfo {
  val ddoDiceRegEx: Regex =
    """(\d+\.?\d*)*?((?:\[(\d+)d(\d+)])|(?:(\d+)d(\d+)))\s*(?:([+\-])\s*(\d+))*""".r

  def apply(diceExp: String): DamageInfo = {

    val nameMap = Map(
      "wMod" -> 1,
      "bracketNumber" -> 3,
      "bracketSides" -> 4,
      "number" -> 5,
      "sides" -> 6,
      "symbol" -> 7,
      "extra" -> 8
    )
    ddoDiceRegEx.findFirstMatchIn(diceExp) match {
      case Some(result) =>
        // Longhand with named values because Regex are easy to misread
        val wMod = Option(result.group(nameMap("wMod"))).getOrElse("")
        val bNumber = Option(result.group(nameMap("bracketNumber"))).getOrElse("")
        val bSides = Option(result.group(nameMap("bracketSides"))).getOrElse("")
        val number = Option(result.group(nameMap("number"))).getOrElse("")
        val sides = result.group(nameMap("sides"))
        val symbol = result.group(nameMap("symbol"))
        val extra = result.group(nameMap("extra"))
        val w: Double = wMod.toDoubleOption.getOrElse(1)
        val n = bNumber.toIntOption
          .orElse(number.toIntOption)
          .orElse(Some(0))
          .get
        val s =
          bSides.toIntOption.orElse(sides.toIntOption).orElse(Some(0)).get
        val e = Option(symbol) match {
          case Some(x) =>
            extra.toIntOption match {
              case Some(y) => (x, y)
              case _ => ("+", 0)
            }
          case _ => ("+", 0)
        }
        val dt: List[PhysicalDamageType] = extractFlags(diceExp)
        apply(
          w = w,
          d = Dice(s, n),
          e = ExtraInfo(e._1, e._2),
          dt
        )
    }
  }

  /**
   * Extracts the extra values / flags like 'Magic Bludgeon' etc.
   * @param expr
   *   inbound Text to parse
   * @return
   *   valid types filtered by values in PhysicalDamageType
   */
  def extractFlags(expr: String): List[PhysicalDamageType] = {
    val template = """(\b(?i)REPLACE\b)+"""
    val tt = PhysicalDamageType.values
      .map(_.entryName)
      .map(template.replace("REPLACE", _))
      .mkString("|")
    tt.r
      .findAllIn(expr)
      .toList
      .flatMap(PhysicalDamageType.withNameInsensitiveOption)

  }

  def apply(
    w: Double,
    d: Dice,
    e: ExtraInfo,
    dt: List[PhysicalDamageType]
  ): DamageInfo =
    new DamageInfo(
      w: Double,
      d: Dice,
      e: ExtraInfo,
      dt: List[PhysicalDamageType]
    ) {} // abstract class implementation intentionally empty
}

abstract case class DamageInfo private[DamageInfo] (
  override val weaponModifier: Double,
  override val dice: Dice,
  override val extra: ExtraInfo,
  override val damageType: List[PhysicalDamageType]
) extends DamageDice {
  def copy(
    w: Double,
    d: Dice,
    e: ExtraInfo,
    dt: List[PhysicalDamageType]
  ): DamageInfo =
    DamageInfo.apply(w, d, e, dt)

  // if (weaponModifier.isWhole()) { weaponModifier.toInt.toString} else {weaponModifier.toString}
  override def toString: String = {
    val diceExp = s"${dice.number}d${dice.sides}"
    val wm = weaponModifier match {
      case 0 | 1 => diceExp
      case _ => s"$doubleToIntFunction[$diceExp]"
    }
    val dt = if damageType.isEmpty then {
      ""
    } else {
      s" ${damageType.mkString(",")}"
    }
    s"$wm$extra$dt"

  }

  private def doubleToIntFunction = weaponModifier match {
    case 0 | 1 => ""
    case x: Double if x.isWhole => x.toInt.toString
    case _ => weaponModifier.toString
  }

  // to ensure validation and possible singleton-ness, must override readResolve to use explicit companion object apply method
  private def readResolve(): Object =
    DamageInfo.apply(weaponModifier, dice, extra, damageType)
}
