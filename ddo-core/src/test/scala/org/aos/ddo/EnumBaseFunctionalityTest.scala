/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.mockito.MockitoSugar

@RunWith(classOf[JUnitRunner])
class EnumBaseFunctionalityTest extends FunSpec with BeforeAndAfter with MockitoSugar {

  var item: Item = _

  before {
    //    item = new Weapon with OneHandedWeapon {
    //      /**
    //       * As seen from <$anon: org.aos.ddo.Weapon with org.aos.ddo.OneHandedWeapon>,
    //       *  the missing signatures are as follows.
    //       *
    //       *  * For convenience, these are usable as stub implementations.
    //       */
    //      val critical: org.aos.ddo.CriticalProfile = mock[CriticalProfile]
    //      val damage: org.aos.ddo.DamageInfo = mock[DamageInfo]
    //
    //      val proficiency: org.aos.ddo.ProficiencyClass = mock[ProficiencyClass]
    //      val WeaponCategory: org.aos.ddo.WeaponCategory = mock[WeaponCategory]
    //
    //      val baseValue: Int = 3
    //      val handedness: List[org.aos.ddo.Handedness] = mock[List[org.aos.ddo.Handedness]]
    //      val weaponCategory: org.aos.ddo.WeaponCategory = mock[org.aos.ddo.WeaponCategory]
    //      val weaponType: org.aos.ddo.WeaponType = mock[org.aos.ddo.WeaponType]
    //      val weight: Int = 4
    //      val attackModifier: List[org.aos.ddo.Attribute] = mock[List[org.aos.ddo.Attribute]]
    //      val damageModifier: List[org.aos.ddo.Attribute] = mock[List[org.aos.ddo.Attribute]]
    //      val durability: Int = 3
    //      val hardness: Int = 4
    //      val material: org.aos.ddo.Material = mock[org.aos.ddo.Material]
    //            val description: String = "A Description"
    //       val upgradeable: org.aos.ddo.UpgradeInfo =  mock[UpgradeInfo]
    //
    //
    //    }
  }

  describe("A Pizza") {

    //    it("should start with no toppings") {
    //      assert(pizza.getToppings.size == 0)
    //    }

    it("should allow addition of toppings")(pending)

    it("should allow removal of toppings")(pending)
  }

}
