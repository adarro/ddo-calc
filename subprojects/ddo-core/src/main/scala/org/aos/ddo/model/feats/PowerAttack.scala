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

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * [[http://ddowiki.com/page/Power_Attack Power Attack]]
  * This feat exchanges part of your attack bonus for extra melee damage.
  * It reduces your hit bonus by 5, or your Base Attack Bonus, whichever is lower.
  * Then your successful attacks will have their damage increased by the same amount.
  * Two-handed weapons get twice that damage bonus. (Unarmed strikes count as one-handed.)
  * Typically, this means one-handed weapons get +5 and two-handed get +10 to damage.
  *
  * This feat is a stance. It may be toggled on and left active indefinitely.
  * When deactivated, there is a 10 second cooldown before it can be used again.
  *
  * @todo add Cooldown 10 seconds
  * @todo add Offensive Combat Stance
  */
protected[feats] trait PowerAttack extends FeatRequisiteImpl with Active with RequiresAttribute {
  self: GeneralFeat =>
  override val requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Strength, 13))
}
