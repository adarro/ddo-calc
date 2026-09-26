/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WeaponCategory.scala
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
package io.truthencode.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.effect.Damage
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.TraverseOps.Joinable
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}
import io.truthencode.ddo.support.{Bludgeoning, Piercing, Slashing}

import scala.collection.immutable.IndexedSeq

/**
 * Enumerates the specific base types of weapons available in DDO, i.e. Kopesh, Dagger etc.
 */
sealed trait WeaponCategory
  extends EnumEntry with Damage with DefaultDeliveryMethod with DisplayName with FriendlyDisplay {

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase

//  lazy val weaponClass =
}

// scalastyle:off number.of.types number.of.methods
/**
 * Holds the basic (Default) weapon types, swords, axes etc.
 *
 * @todo
 *   Handle orbs and rune arms, orbs should be shields, but rune arm is only off-hand only with
 *   non-physical damage
 */
object WeaponCategory extends Enum[WeaponCategory] {

  //  RuneArm,
  lazy val values: IndexedSeq[WeaponCategory] = findValues

  /**
   * These weapons get +3 to threat range with IC
   * @see
   *   [[https://ddowiki.com/page/Improved_Critical]]
   */
  val icPlus3: Seq[WeaponCategory] =
    LazyList(Falchion, GreatCrossbow, Kukris, Rapier, Scimitar)

  /**
   * These weapons get +2 to threat range with IC
   * @see
   *   [[https://ddowiki.com/page/Improved_Critical]]
   */
  val icPlus2: Seq[WeaponCategory] = LazyList(
    BastardSword,
    Dagger,
    Greatsword,
    HeavyCrossbow,
    Khopesh,
    LightCrossbow,
    Longsword,
    RepeatingHeavyCrossbow,
    RepeatingLightCrossbow,
    Shortsword,
    ThrowingDagger
  )

  /**
   * These weapons get +1 to threat range with IC (Everything Not in the plus 2 / 3 lists.)
   * @see
   *   [[https://ddowiki.com/page/Improved_Critical]]
   */
  val icPlus1: Seq[WeaponCategory] = WeaponCategory.values.nSelect(icPlus3.concat(icPlus2))

  /**
   * Filters weapons for Improved Critical Threat modifiers according to source on ddowiki
   * [[https://ddowiki.com/page/Improved_Critical]]
   * @return
   *   Collection of Weapons with appropriate modifiers in a Tuple i.e. Seq((Falchion,3),...)
   */
  def improvedCriticalRangeByWeapon(weaponClass: WeaponClass): Seq[(WeaponCategory, Int)] =
    WeaponClass.values.flatMap { wc =>
      filterByWeaponClass(weaponClass).map { weapon =>
        // The item we are looking for is in one of these lists
        val a1 = icPlus1.filter(_ == weapon).flatMap(optPlus(_, 1))
        val a2 = icPlus2.filter(_ == weapon).flatMap(optPlus(_, 2))
        val a3 = icPlus3.filter(_ == weapon).flatMap(optPlus(_, 3))
        val squish = a1 ++ a2 ++ a3
        val squished = squish.head
        squished
      }
    }

  /**
   * Used by [[improvedCriticalRangeByWeapon]] to safely locate and build an array of weapons with a
   * specific value. This routine may be useful elsewhere (thus parameterized) but essentially a one
   * off.
   * @param t
   *   Possibly null / empty type
   * @param n
   *   Number to add to the Tuple pair if t is not null.
   * @tparam T
   *   Type of t
   * @return
   *   a Option[tuple] of (t,n) or none if t is null / empty.
   */
  def optPlus[T](t: T, n: Int): Option[(T, Int)] = {
    val x = Option(t)
    Option(x) match {
      case Some(_) => Some(t, n)
      case _ => None
    }
  }

  def filterByWeaponClass(weaponClass: WeaponClass): Seq[WeaponCategory] =
    WeaponCategory.values.collect(weaponClassToCategory).filter(_._2 == weaponClass).map(_._1)

  def weaponClassToCategory: PartialFunction[WeaponCategory, (WeaponCategory, WeaponClass)] = {
    case x: RangeDamage => (x, WeaponClass.Ranged)
    case x: ThrownDamage => (x, WeaponClass.Thrown)
    case x: Bludgeoning => (x, WeaponClass.Bludgeon)
    case x: Piercing => (x, WeaponClass.Piercing)
    case x: Slashing => (x, WeaponClass.Slashing)
  }

  def exoticWeapons: Seq[WeaponCategory & ExoticWeapon] = {
    for w <- WeaponCategory.values.filter { x =>
        x match {
          case _: ExoticWeapon => true
          case _ => false
        }
      } yield w.asInstanceOf[WeaponCategory & ExoticWeapon]
  }

  def martialWeapons: Seq[WeaponCategory & MartialWeapon] = {
    for w <- WeaponCategory.values.filter { x =>
        x match {
          case _: MartialWeapon => true
          case _ => false
        }
      } yield w.asInstanceOf[WeaponCategory & MartialWeapon]
  }

  def simpleWeapons: Seq[WeaponCategory & SimpleWeapon] = {
    for w <- WeaponCategory.values.filter { x =>
        x match {
          case _: SimpleWeapon => true
          case _ => false
        }
      } yield w.asInstanceOf[WeaponCategory & SimpleWeapon]
  }

  case object BastardSword extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing

  case object BattleAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object Club extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning

  case object Dagger extends WeaponCategory with SimpleWeapon with MeleeDamage with Piercing

  case object Dart extends WeaponCategory with SimpleWeapon with ThrownDamage with Piercing

  case object DwarvenWarAxe
    extends WeaponCategory with ExoticWeapon with MeleeDamage with Piercing {

    /**
     * Sets or maps the source text for the DisplayName.
     *
     * @return
     *   Source text.
     */
    override protected def nameSource: String = "Dwarven Axe".toPascalCase
  }

  case object Falchion extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object GreatAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object GreatClub extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning

  //
  case object GreatCrossbow extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing

  case object Greatsword extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object HandAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object Handwrap extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning

  case object HeavyCrossbow extends WeaponCategory with SimpleWeapon with MeleeDamage with Piercing

  case object HeavyMace extends WeaponCategory with SimpleWeapon with MeleeDamage with Slashing

  case object HeavyPick extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing

  case object Kama extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing

  case object Khopesh extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing

  case object Kukris extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object LightCrossbow extends WeaponCategory with SimpleWeapon with RangeDamage with Piercing

  case object LightHammer
    extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning

  case object LightMace extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning

  case object LightPick extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing

  case object Longbow extends WeaponCategory with MartialWeapon with RangeDamage with Piercing

  case object Longsword extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object Maul extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning

  case object Morningstar extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning

  case object Quarterstaff
    extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning

  case object Rapier extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing

  case object RepeatingHeavyCrossbow
    extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing

  case object RepeatingLightCrossbow
    extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing

  // case object RuneArm extends WeaponCategory
  case object Scimitar extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing

  case object Shortbow extends WeaponCategory with MartialWeapon with RangeDamage with Piercing

  case object Shortsword extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing

  case object Shuriken extends WeaponCategory with ExoticWeapon with ThrownDamage with Piercing

  case object Sickle extends WeaponCategory with SimpleWeapon with MeleeDamage with Slashing

  case object SimpleProjectile
    extends WeaponCategory with SimpleWeapon with ThrownDamage with Bludgeoning

  case object ThrowingAxe extends WeaponCategory with MartialWeapon with ThrownDamage with Slashing

  case object ThrowingDagger
    extends WeaponCategory with SimpleWeapon with ThrownDamage with Piercing

  case object ThrowingHammer
    extends WeaponCategory with MartialWeapon with ThrownDamage with Bludgeoning

  case object Warhammer
    extends WeaponCategory with MartialWeapon with Product with Serializable with MeleeDamage
    with Bludgeoning

}

// scalastyle:on number.of.types number.of.methods
