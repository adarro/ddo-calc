/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: VorpalStrikes.scala
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
import io.truthencode.ddo.support.requisite.*

import scala.collection.immutable

/**
 * [[https://ddowiki.com/page/Vorpal_Strikes Vorpal Strikes]] Usage: Active, Toggled Stance
 * Prerequisite: Monk Level 12, Wisdom 23, Improved Critical:Bludgeoning Weapons. Description Your
 * unarmed strikes are now considered vorpal and slashing.
 *
 * Notes Epic Feat available for selection at level 21, 24 or 27. You need at least 12 levels of
 * Monk in your character to qualify for this feat. Vorpal Strikes is a Monk stance, similar to Slow
 * Fall. The feat is switched off on character login. The stance co-exists with general stances such
 * as Power Attack and Monk stances such as Mountain Stance. This ability does not make unarmed
 * strikes considered slashing weapons in regard to Ninja Spy (No poison, no dex-to-damage or hit),
 * it simply adds an additional damage type.
 * @todo
 *   need to verify epic level selection functionality is handled Created by adarr on 4/6/2017.
 */
trait VorpalStrikes
  extends FeatRequisiteImpl with TriggeredActivationImpl with ClassRequisiteImpl
  with RequiresAllOfClass with ActiveFeat with Stance with ClassRestricted {
  override def allOfClass: immutable.Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 12))

}
