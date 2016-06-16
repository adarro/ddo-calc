/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *         http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo.weapon

import org.aos.ddo.{ Bludgeoning, Damage, Enum, NoDefault, Piercing, Slashing }
import org.aos.ddo.support.DefaultType

/**
  * Enumerates the specific base types of weapons available in DDO, i.e. Kopesh, Dagger etc.
  */
sealed trait WeaponCategory extends WeaponCategory.Value with Damage with WeaponClass

// scalastyle:off number.of.types number.of.methods
/**
  * Holds the basic (Default) weapon types, swords, axes etc.
  * TODO: Handle orbs and rune arms, orbs should be shields,
  * but rune arm is only off-hand only with non-physical damage
  */
object WeaponCategory extends Enum[WeaponCategory] {
  case object BastardSword extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing
  case object BattleAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object Club extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning
  case object Dagger extends WeaponCategory with SimpleWeapon with MeleeDamage with Piercing
  case object Dart extends WeaponCategory with SimpleWeapon with MeleeDamage with Piercing
  case object DwarvenWarAxe extends WeaponCategory with ExoticWeapon with MeleeDamage with Piercing
  case object Falchion extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object GreatAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object GreatClub extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning
  //
  case object GreatCrossbow extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing
  case object GreatSword extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object HandAxe extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object Handwrap extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning
  case object HeavyCrossbow extends WeaponCategory with SimpleWeapon with MeleeDamage with Piercing
  case object HeavyMace extends WeaponCategory with SimpleWeapon with MeleeDamage with Slashing
  case object HeavyPick extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing
  case object Kama extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing
  case object Khopesh extends WeaponCategory with ExoticWeapon with MeleeDamage with Slashing
  case object Kukris extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object LightCrossbow extends WeaponCategory with SimpleWeapon with RangeDamage with Piercing
  case object LightHammer extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning
  case object LightMace extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning
  case object LightPick extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing
  case object Longbow extends WeaponCategory with MartialWeapon with RangeDamage with Piercing
  case object Longsword extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object Maul extends WeaponCategory with MartialWeapon with MeleeDamage with Bludgeoning
  case object Morningstar extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning
  case object Quarterstaff extends WeaponCategory with SimpleWeapon with MeleeDamage with Bludgeoning
  case object Rapier extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing
  case object RepeatingHeavyCrossbow extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing
  case object RepeatingLightCrossbow extends WeaponCategory with ExoticWeapon with RangeDamage with Piercing
  // case object RuneArm extends WeaponCategory
  case object Scimitar extends WeaponCategory with MartialWeapon with MeleeDamage with Slashing
  case object Shortbow extends WeaponCategory with MartialWeapon with RangeDamage with Piercing
  case object ShortSword extends WeaponCategory with MartialWeapon with MeleeDamage with Piercing
  case object Shuriken extends WeaponCategory with ExoticWeapon with ThrownDamage with Piercing
  case object Sickle extends WeaponCategory with SimpleWeapon with MeleeDamage with Slashing
  case object SimpleProjectile extends WeaponCategory with SimpleWeapon with ThrownDamage with Bludgeoning
  case object ThrowingAxe extends WeaponCategory with MartialWeapon with ThrownDamage with Slashing
  case object ThrowingDagger extends WeaponCategory with SimpleWeapon with ThrownDamage with Piercing
  case object ThrowingHammer extends WeaponCategory with MartialWeapon with ThrownDamage with Bludgeoning
  case object WarHammer extends WeaponCategory with MartialWeapon with Product with Serializable with MeleeDamage with Bludgeoning
  //  RuneArm,
  val values = List(
    BastardSword, BattleAxe, Club, Dagger, Dart, DwarvenWarAxe, Falchion, GreatAxe, GreatClub, GreatCrossbow,
    GreatSword, HandAxe, Handwrap, HeavyCrossbow, HeavyMace, HeavyPick, Kama, Khopesh, Kukris, LightCrossbow, LightHammer, LightMace, LightPick,
    Longbow, Longsword, Maul, Morningstar, Quarterstaff, Rapier, RepeatingHeavyCrossbow, RepeatingLightCrossbow, Scimitar,
    Shortbow, ShortSword, Shuriken, Sickle, SimpleProjectile, ThrowingAxe, ThrowingDagger, ThrowingHammer, WarHammer)

}
// scalastyle:on number.of.types number.of.methods
