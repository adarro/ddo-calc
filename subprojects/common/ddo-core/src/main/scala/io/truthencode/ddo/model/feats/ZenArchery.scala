/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ZenArchery.scala
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
  RequiresAllOfAttribute,
  RequiresBaB
}

/**
 * ICON Zen Archery.PNG Zen Archery Passive You can use your Wisdom bonus instead of Dexterity bonus
 * to determine bonus to attack with ranged missile weapons if it is higher (does not apply to
 * thrown weapons). You treat longbows and shortbows as if they were Ki weapons. * Wisdom 13 Base
 * Attack Bonus +1 *
 */
trait ZenArchery
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive
  with AttributeRequisiteImpl with RequiresAllOfAttribute with RequiresBaB with FighterBonusFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Wisdom, 13))

  override def requiresBaB: Int = 1
}
