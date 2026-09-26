/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: UncannyDodge.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Barbarian, Rogue}
import io.truthencode.ddo.support.TraverseOps.Crossable
import io.truthencode.ddo.support.requisite.*

import java.time.Duration

/**
 * [[http://ddowiki.com/page/Uncanny_Dodge Uncanny Dodge]] This feat grants you a 1% passive bonus
 * to Dodge at levels 4, 6, 8, 12, 16, and 20. Also, you can activate this ability to gain a
 * temporary 50% dodge bonus and a +6 reflex save bonus. As of Update 14, using this ability is no
 * longer restricted by number of uses per rest. However, it is restricted by cooldown.
 *
 * @note
 *   This feat is replaced by [[ImprovedUncannyDodge]] if character has both. also available via
 *   Swashbuckler Enhancements
 */
protected[feats] trait UncannyDodge
  extends FeatRequisiteImpl with TriggeredActivationImpl with Passive with ActiveFeat
  with AtWillEvent with GrantsToClass with RequiresAnyOfClass {
  self: ClassFeat =>
  private lazy val grantedClasses = classMatrix.map((_, 4))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = grantedClasses

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = grantedClasses

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(180))

  private def rogueAndBarbMatrix = (classMatrix.cross(rogueAndBarbLevels)).toSeq

  private def rogueAndBarbLevels = (4 to 20 by 4) :+ 6

  private def classMatrix = List(Rogue, Barbarian)
}
