/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *       http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import scala.language.reflectiveCalls

import org.aos.ddo.MonetaryValue.Coins
import org.aos.ddo.weapon.{ Handedness, ProficiencyClass, Weapon, WeaponCategory }
import org.junit.runner.RunWith
import org.scalatest.{ FunSpec, Matchers }
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar
import org.scalatest.tags.Slow

@Slow
@RunWith(classOf[JUnitRunner])
class WeaponTest extends FunSpec with Matchers with MockitoSugar {
  def fixture = new {
    val longBow = new Weapon {
      val Coinage = 1000
      val NWeight = 4

      val proficiency = Some(mock[ProficiencyClass])
      val damage = Some(mock[DamageInfo])
      val critical = Some(mock[CriticalProfile])
      val weaponCategory = Some(WeaponCategory.Dagger)
      val baseValue = Some(new Coins(Coinage))
      val handedness: List[Handedness] = mock[List[Handedness]]
      val weaponType = Some(mock[org.aos.ddo.weapon.WeaponType])
      val weight = Some(NWeight)
      val binding = Some(BindingFlags.Unbound)
      val attackModifier: List[Attributes] = mock[List[Attributes]]
      val damageModifier: List[Attributes] = mock[List[Attributes]]
      val durability: Int = 3
      val hardness: Int = 4
      val material = Some(mock[Material])
      val upgradeInfo = mock[UpgradeInfo]

      val description = Some("A mock Weapon")
      // Members declared in org.aos.ddo.Weapon
      val upgradeable: org.aos.ddo.UpgradeInfo = mock[UpgradeInfo]
      val absoluteMinimumLevel: Option[Int] = Some(3)
      val minimumLevel: Int = 4
      val umd: Int = 3
      val enchantments = mock[Option[Seq[String]]]
    }
  }

  describe("a basic weapon") {
    it("should have a weapon type") {
      import scala.language.reflectiveCalls // scalastyle:off import.grouping
      val f = fixture
      f.longBow.weaponCategory should be(Some(WeaponCategory.Dagger))
    }
  }
}
