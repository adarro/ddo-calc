/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.Ranger
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Transmutes natural matter around you into Goodberries.
  * Goodberries are infused with primal magic, and provide a full meal's nourishment. If eaten in a tavern, they act as tavern food and drink,
  * refilling your health and spell points. When cast, Goodberries appear in your inventory.
  * The higher the caster level, the more nourishing the Goodberries are.
  */
protected[feats] trait HideInPlainSight
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 17))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 17))

}
