/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BladeswornTransformation.scala
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
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.model.religions.LordOfBlades
import io.truthencode.ddo.support.requisite.*

/**
 * Created by adarr on 4/7/2017.
 * [[https://ddowiki.com/page/Bladesworn_Transformation Bladesworn Transformation]] You are a
 * devoted follower of the Lord of Blades, and your faith has been rewarded. Activate this ability
 * to attempt to become a juggernaut of destruction for 24 seconds plus 6 seconds per Religious Lore
 * feat you have.
 *
 * While transformed, you have a +4 Profane bonus to Strength, +4 natural armor bonus to AC, a +4
 * profane bonus on damage rolls, +10 enhancement bonus to Will saves against mind affecting magic,
 * and proficiency in all simple and martial weapons. Your base attack bonus equals your level. You
 * lose your spellcasting ability. You have immunity to critical hits and sneak attacks, but cannot
 * be healed by healing spells.
 */
trait BladeswornTransformation
  extends FeatRequisiteImpl with TriggeredActivationImpl with DeityUniqueLevelBase
  with RequiresAllOfFeat with EberronReligionWarforged with LordOfBlades
  with TheLordOfBladesFeatBase with ActiveFeat with AtWillEvent with DefaultCoolDown {
  self: DeityFeat & Requisite & RequisiteType =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfTheLordOfBlades)

}
