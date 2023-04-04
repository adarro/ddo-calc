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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfAttribute
}

/**
 * Icon Feat Type Description Prerequisites Icon Feat Force Of Personality.png Force of Personality
 * Passive This Feat allows Player Character to use Charisma modifier for Will saves, instead of
 * Wisdom. * Charisma 13
 */
trait ForceOfPersonality
  extends FeatRequisiteImpl with Passive with AttributeRequisiteImpl with RequiresAllOfAttribute {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Charisma, 13))
}
