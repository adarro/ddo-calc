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
package org.aos.ddo.model.item.weapon

/** Base trait to provide a field for weapon proficiency
  */
sealed trait WeaponProficiency {
  /** MustContainAtLeastOne of the [[org.aos.ddo.model.item.weapon.ProficiencyClass]] values.
    */
  val proficiency: ProficiencyClass
}

/** Type generally requires a fighter like class without special training
  */
trait MartialWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass = ProficiencyClass.Martial
}
/** Weapon generally requires no special training to use.
  */
trait SimpleWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass = ProficiencyClass.Simple
}
/** Weapon is extremely rare or requires extensive training.
  */
trait ExoticWeapon extends WeaponProficiency {
  override val proficiency: ProficiencyClass = ProficiencyClass.Exotic
}

