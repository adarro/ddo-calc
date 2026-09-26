/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: MultitudeOfMissiles.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.model.feats.GeneralFeat.PointBlankShot
import io.truthencode.ddo.model.misc.{CoolDownPool, SharedCoolDown}
import io.truthencode.ddo.support.requisite.*

import java.time.Duration

/**
 * For the next 20 seconds, while wielding a Simple Thrown Weapon add 100 to your Doubleshot and 2x
 * your Base Attack Bonus to Ranged Power. Shares a cooldown with Manyshot.
 *
 * @note
 *   Alchemists get this feat for free at class level 6 (without meeting the prerequisites). Other
 *   classes can take this feat if they meet the prerequisitves.
 * @see
 *   [[https://ddowiki.com/page/Multitude_of_Missiles]]
 */
protected[feats] trait MultitudeOfMissiles
  extends FeatRequisiteImpl with TriggeredActivationImpl with ClassRequisiteImpl with GrantsToClass
  with RequiresAllOfFeat with RequiresBaB with ActiveFeat with AtWillEvent with SharedCoolDown {
  override val coolDownPoolId: String = CoolDownPool.ManyShot.coolDownPoolId

  private val cls = (Alchemist, 6)

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(60))

  abstract override def grantToClass: Seq[(HeroicCharacterClass, Int)] = super.grantToClass :+ cls

  abstract override def allOfFeats: Seq[Feat] = super.allOfFeats :+ PointBlankShot

  /**
   * The Minimum Required Base Attack Bonus
   *
   * @return
   *   Minimum value allowed
   */
  override def requiresBaB: Int = 6
}
