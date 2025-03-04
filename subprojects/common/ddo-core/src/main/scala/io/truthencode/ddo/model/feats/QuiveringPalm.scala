/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: QuiveringPalm.scala
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

import io.truthencode.ddo.activation.TriggeredActivationImpl
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Quivering_Palm Quivering Palm]] Starting at 15th level, a monk can set
 * up fatal vibrations within the body of a living creature. Constructs, oozes, plants, undeads,
 * incorporeal creatures such as wraiths, and creatures immune to critical hits cannot be affected,
 * but other creatures must make a Fortitude Save after being struck by the Quivering Palm or die
 * instantly. The saving throw is equal to DC of 10 + (monk level)/2 + wisdom modifier.
 *
 * @note
 *   Update 19 changes:
 *
 * Quivering Palm may now be used with Monk weapons. Quivering Palm now gains DC bonuses from all
 * enhancements, feats, items, and Epic Destiny abilities that increase Sunder DC. Update 21
 * changes:
 *
 * Quivering Palm gains DC bonuses from general tactical combat boosts, but not Sundering items.
 * Whenever an enemy saves vs. Quivering Palm, you get a stacking +4 bonus to your Quivering Palm
 * DC. This stack is dispelled when you successfully kill someone with Quivering Palm (or 3 minutes
 * pass).
 * @todo
 *   Some Tactical bonuses are confirmed not working So not sure if flagging this tactical is
 *   accurate programmatically
 */
trait QuiveringPalm
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with Tactical
  with GrantsToClass with RequiresAllOfClass {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 15))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 15))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))
}
