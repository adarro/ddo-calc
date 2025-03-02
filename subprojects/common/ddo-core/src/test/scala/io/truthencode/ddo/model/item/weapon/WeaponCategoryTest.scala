/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WeaponCategoryTest.scala
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

import io.truthencode.ddo.model.effect.Damage
import io.truthencode.ddo.model.item.weapon.WeaponCategory._
import io.truthencode.ddo.support.TraverseOps._
import io.truthencode.ddo.support.{Bludgeoning, Piercing, Slashing}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.slf4j.LoggerFactory

class WeaponCategoryTest extends AnyFunSpec with Matchers {
  private val logger = LoggerFactory.getLogger(getClass)
  def categoryToClass: PartialFunction[WeaponCategory, WeaponClass] = { case x: RangeDamage =>
    WeaponClass.Ranged
  }

  def typedToWeaponClass: PartialFunction[
    DefaultDeliveryMethod & Damage,
    (DefaultDeliveryMethod & Damage, WeaponClass)] = {
    case x: RangeDamage => (x, WeaponClass.Ranged)
    case x: ThrownDamage => (x, WeaponClass.Thrown)
    case x: Bludgeoning => (x, WeaponClass.Bludgeon)
    case x: Piercing => (x, WeaponClass.Piercing)
    case x: Slashing => (x, WeaponClass.Slashing)
  }

  def filterByWeaponClass(weaponClass: WeaponClass): Seq[WeaponCategory] =
    WeaponCategory.values.collect(weaponClassToCategory).filter(_._2 == weaponClass).map(_._1)

//    def weaponClassToCategory: PartialFunction[WeaponClass, WeaponCategory] = {
//      case x:WeaponClassBludgeoning => x.
//      case x:    WeaponClassPiercing =>
//
//      case x:  WeaponClassSlashing =>
//
//      case x: WeaponClassRanged =>
//
//      case x:WeaponClassThrown =>
//  }

  def weaponClassToCategory: PartialFunction[WeaponCategory, (WeaponCategory, WeaponClass)] = {
    case x: RangeDamage => (x, WeaponClass.Ranged)
    case x: ThrownDamage => (x, WeaponClass.Thrown)
    case x: Bludgeoning => (x, WeaponClass.Bludgeon)
    case x: Piercing => (x, WeaponClass.Piercing)
    case x: Slashing => (x, WeaponClass.Slashing)
  }

  describe("Weapon Categories") {
    they("should derive associated Weapon Class") {
      // Testing with Improved Critical: Slash filter
      val wcFilter = WeaponClass.Slashing

      val filtered = WeaponCategory.filterByWeaponClass(wcFilter)
      logger.info(filtered.mkString)
      val plus3 =
        LazyList(
          WeaponCategory.Falchion,
          WeaponCategory.GreatCrossbow,
          WeaponCategory.Kukris,
          Rapier,
          Scimitar)
      val plus2 = LazyList(
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
      val plus1 = WeaponCategory.values.filterNot(plus3.concat(plus2).contains)
      val plus1N = WeaponCategory.values.nSelect(plus3.concat(plus2))
      plus1 shouldEqual plus1N

      val expectedPlus2 = List(BastardSword, Greatsword, Khopesh, Longsword)
      val expectedPlus3 = List(Falchion, Kukris, Scimitar)
      // "All others requires a bit more of a manual dig to verify long-hand"
      plus2.intersect(filtered) shouldEqual expectedPlus2
      plus3.intersect(filtered) shouldEqual expectedPlus3

    }
  }

}
