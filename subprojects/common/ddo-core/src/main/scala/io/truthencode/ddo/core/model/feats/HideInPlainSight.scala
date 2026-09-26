/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: HideInPlainSight.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Ranger
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Hide_in_Plain_Sight Hide in Plain Sight]] You are a master when it
 * comes to hiding in places others think impossible. You gain +1 Hide and +1 Move Silently for
 * every three seconds you stand still, up to +5. While you are sneaking and standing still (for 3
 * seconds), enemies don't gain Spot bonuses until they get much closer to you, and the bonuses are
 * smaller. [Update 19, not tested]
 *
 * Also, available in Shadowdancer epic destiny.
 */
protected[feats] trait HideInPlainSight
  extends FeatRequisiteImpl with TriggeredActivationImpl with Passive with AtWillEvent
  with GrantsToClass with RequiresAllOfClass {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Ranger, 17))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Ranger, 17))

}
