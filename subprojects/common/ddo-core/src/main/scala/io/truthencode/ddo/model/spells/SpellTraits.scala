/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
package io.truthencode.ddo.model.spells

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.effect.{Effect, EffectList}
import io.truthencode.ddo.model.misc.CoolDown
import io.truthencode.ddo.model.spells.SpellElement._
import io.truthencode.ddo.model.spells.component.ComponentList
import io.truthencode.ddo.support.RomanNumeral
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import java.time.Duration
import scala.collection.{immutable, Iterable}
import scala.reflect.ClassTag
trait SpellTraits

/**
 * Base class for spells and spell like abilities
 */
sealed trait SpellLike

sealed trait SpellElement extends SpellLike

object SpellElement {
  def extract[T <: SpellElement](
    list: Iterable[SpellElement]
  )(implicit tag: ClassTag[T]): Iterable[T] = {
    list.flatMap {
      case element: T => Some(element)
      case _ => None
    }
  }

  implicit class Util(elements: Iterable[SpellElement]) {
    def extract[T <: SpellElement](implicit tag: ClassTag[T]): Iterable[T] = {
      elements.flatMap {
        case element: T => Some(element)
        case _ => None
      }
    }

    def getStuff[T: ClassTag](list: Seq[SpellElement]): Seq[T] = list.collect { case element: T =>
      element
    }

    //    def extract[X: SpellElement](implicit evidence: ClassTag[X]): Option[X] = {
    //      val nameOpt: Seq[Option[X]] = for {e <- elements
    //                                         p = e match {
    //                                           case x: X => Some(x)
    //                                           case _ => None
    //                                         }
    //                                         if p.nonEmpty
    //      } yield p
    //      nameOpt.headOption.flatten
    //    }
  }

  sealed trait EmptySpell extends Spell with SpellElement

  sealed trait WithName extends SpellElement {
    def name: String
  }

  sealed trait WithSpellResistance extends SpellElement {
    self: SpellResistance =>

  }

  sealed trait WithSpellTarget extends SpellElement {
    val target: List[SpellTarget] = List.empty
  }

  sealed trait WithSpellSavingThrow extends SpellElement {
    val savingThrow: List[SavingThrow] = List.empty
  }

  sealed trait WithSpellPoints extends SpellElement {
    val spellPoints: Int = 0
  }

  sealed trait WithComponents extends SpellElement {
    val components: List[ComponentList] = List.empty
  }

  sealed trait WithLevelCap extends SpellElement with CasterLevelCap

  sealed trait WithSpellEffects extends SpellElement with EffectList {
    val effects: List[Effect]
  }

  sealed trait WithTarget extends SpellElement {
    val targets: List[SpellTarget]
  }

  sealed trait WithCoolDown extends SpellElement with CoolDown

  sealed trait WithCasterClass extends SpellElement with CasterLevels

  sealed trait WithSpellInfo extends SpellElement with WithSpellResistance with SpellInfo

}

final case class UseCoolDown(coolDown: Option[Duration]) extends WithCoolDown

final case class UseCasterClass(override val casterLevels: Set[CasterWithLevel])
  extends WithCasterClass

final case class UseSpellTarget(override val target: List[SpellTarget]) extends WithSpellTarget

final case class UseSpellSavingThrow(
  override val savingThrow: List[SavingThrow]
) extends WithSpellSavingThrow

final case class UseSpellPoints(override val spellPoints: Int) extends WithSpellPoints

final case class UseComponents(override val components: List[ComponentList]) extends WithComponents

final case class UseLevelCap(override val baseLevelCap: Option[Int]) extends WithLevelCap

final case class UseSpellEffects(override val effects: List[Effect]) extends WithSpellEffects

final case class UseSpellInfo(
  override val coolDown: Option[Duration],
  override val savingThrow: List[SavingThrow],
  override val sr: Option[Int],
  override val target: List[SpellTarget],
  override val components: List[ComponentList],
  override val spellPoints: Int,
  override val range: Range
) extends WithSpellInfo

final case class UseSpellName(override val name: String) extends WithName

final case class UseSpellResistance(override val sr: Option[Int])
  extends WithSpellResistance with SpellResistance

final protected case class createSpell(
  override val name: String = "new spell",
  coolDown: Option[Duration] = None,
  override val sr: Option[Int],
  target: List[SpellTarget] = List.empty,
  savingThrow: List[SavingThrow] = List.empty,
  spellPoints: Int = 0,
  components: List[ComponentList] = List.empty,
  maxCasterLevel: CasterLevelCap = new CasterLevelCap {
    override val baseLevelCap: Option[Int] = None
  },
  casterLevels: CasterLevels = new CasterLevels {
    override def casterLevels: Set[CasterWithLevel] = Set.empty
  },
  range: Range,
  effects: List[Effect] = List.empty
) extends Spell with SpellInfo with EffectList

sealed trait Spell extends SpellLike with EnumEntry with DisplayName with FriendlyDisplay {
  def name: String

  // self: SpellInfo with EffectList =>
  override protected def nameSource: String = entryName.splitByCase.toPascalCase

  override def entryName: String = name
}

object Spell extends Enum[Spell] {

  // trait EmptySpell extends Spell
  override def values: immutable.IndexedSeq[Spell] = findValues

  /**
   * Creates a list of id's that should correspond to all the 'Summon Natures Ally' spells
   * @return
   */
  def summonNatureAllySpells: immutable.Seq[String] = {
    (1 to 8).map { RomanNumeral.toRoman }.map { rn => s"Summon Nature's Ally $rn".toPascalCase }
  }

  /**
   * Contains the ID's of Construct Repair / Inflict damage spells
   * @return
   *   List of Construct Damage Spells
   * @note
   *   Would prefer to do this in a more type-safe way in the future and needs a unit test.
   */
  def inflictSpells: immutable.Seq[String] = {
    val pre = List("Inflict", "Repair")
    val mid = List("Light", "Moderate", "Serious", "Critical")
    val suf = List("Damage")
    val spells = for {
      p <- pre
      m <- mid
      s <- suf
    } yield s"$p$m$s"
    spells ++ List("Reconstruct", "Deconstruct")
  }

  def ls(
    fn: (String => Option[Spell]),
    names: String*
  ): Seq[String => Option[Spell]] = {
    for { n <- names } yield (id: String) => {
      fn(n)
    }

  }

  protected[spells] case class makeEmptySpell(override val name: String) extends EmptySpell

}

trait SpellLikeAbility extends SpellLike

final protected[spells] case class SpellDescriptor(
  elements: Iterable[SpellElement]
) extends Spell {
  override val name: String = {
    val nameOpt: Iterable[String] = for {
      e <- elements
      p = e match {
        case x: WithName => Some(x)
        case _ => None
      }
      if p.nonEmpty
    } yield p.head.name
    nameOpt.headOption.getOrElse("Unnamed Spell")
  }
}

trait BullsStrength extends Spell with SpellInfo with EffectList {
  override val name: String = nameSource
}
