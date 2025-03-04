/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WeaponTest.scala
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
package io.truthencode.ddo

import io.truthencode.ddo.MonetaryValue.Coins
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.item.weapon.{
  DeliveryType,
  Handedness,
  ProficiencyClass,
  Weapon,
  WeaponCategory
}
import io.truthencode.ddo.model.misc.Material
import io.truthencode.ddo.support.dice.DamageDice
import io.truthencode.ddo.test.tags.UnitTest
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.tags.Slow
import org.scalatestplus.mockito.MockitoSugar

import scala.reflect.Selectable.reflectiveSelectable
import scala.language.reflectiveCalls

@Slow
class WeaponTest extends AnyFunSpec with Matchers with MockitoSugar {
  private def fixture: Object { val longBow: Weapon } = new {
    val longBow: Weapon = new Weapon {
      val Coinage = 1000
      val NWeight = 4

      val proficiency: Option[ProficiencyClass] = Some(mock[ProficiencyClass])
      val damage: Option[DamageDice] = Some(mock[DamageDice])
      val critical: Option[CriticalProfile] = Some(mock[CriticalProfile])
      val weaponCategory: Option[WeaponCategory] = Some(WeaponCategory.Dagger)
      val baseValue: Option[Coins] = Some(Coins(Coinage))
      val handedness: List[Handedness] = mock[List[Handedness]]
      val weaponType: Option[DeliveryType] = Some(
        mock[io.truthencode.ddo.model.item.weapon.DeliveryType])
      val weight: Option[Int] = Some(NWeight)
      val binding: Option[BindingFlags] = Some(BindingFlags.Unbound)
      val attackModifier: List[Attribute] = mock[List[Attribute]]
      val damageModifier: List[Attribute] = mock[List[Attribute]]
      val durability: Int = 3
      val hardness: Int = 4
      val material: Option[Material] = Some(mock[Material])
      val upgradeInfo: UpgradeInfo = mock[UpgradeInfo]

      val description: Option[String] = Some("A mock Weapon")
      // Members declared in io.truthencode.ddo.Weapon
      val upgradeable: io.truthencode.ddo.UpgradeInfo = mock[UpgradeInfo]
      val absoluteMinimumLevel: Option[Int] = Some(3)
      val minimumLevel: Int = 4
      val umd: Int = 3
      val enchantments: Option[Seq[String]] = mock[Option[Seq[String]]]
    }
  }

  describe("a basic weapon") {
    it("should have a weapon type", UnitTest) {
      //  import scala.language.reflectiveCalls // scalastyle:off import.grouping
      val f = fixture
      f.longBow.weaponCategory should be(Some(WeaponCategory.Dagger))
    }
  }
}
