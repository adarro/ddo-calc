/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SlowFall.scala
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
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Slow_Fall Slow Fall]] A monk's focus allows him to reduce gravity's
 * effects. This feat can be turned off should he desire normal gravity. The Monk must be centered
 * to use this ability, and its ability to reduce falling damage increases every two levels. In
 * practice, even when this feat is turned off, a monk falls more slowly than he would normally.
 *
 * @note
 *   This class feat does indeed provide a reduced gravity effect as the name suggests, but for the
 *   physics-minded of you it does so exactly as it should, scientifically speaking. I t does not
 *   affect the maximum speed you can reach in a fall due to the effect of gravity, it merely
 *   affects the rate at which gravity will increase the speed of your fall, and thus your damage on
 *   landing, until Perfect Slow Fall at Monk Level 20. What this means in applicable terms is this:
 *   at lower levels of Slow Fall, it will reduce the damage you suffer when falling only on small
 *   to medium falls. On longer falls and at higher levels, even with the reduced rate of
 *   acceleration, you still have time to reach the maximum fall speed for that distance and will
 *   take identical damage whether you use the feat or not. Unlike Feather FallingIcon tooltip.png,
 *   monks do not glide as far with Slow Fall in effect, making a feather falling item still useful.
 *   (Update 8: A known-bug in the game may cause Slow Fall to be operating, even when the feat is
 *   turned off.) Created by adarr on 3/17/2017.
 */
trait SlowFall
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with Stance
  with GrantsToClass with RequiresAllOfClass with DefaultCoolDown {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 4))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 4))
}
