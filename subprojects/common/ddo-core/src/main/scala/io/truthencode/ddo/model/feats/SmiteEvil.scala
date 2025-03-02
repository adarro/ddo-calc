/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SmiteEvil.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Paladin
import io.truthencode.ddo.support.requisite.*

import java.time.Duration

/**
 * [[http://ddowiki.com/page/Smite_Evil Smite Evil]] Using this attack, you call upon the paladin's
 * ability to strike down evil creatures, gaining twice your Charisma bonus and a damage bonus based
 * on your paladin level. Smites return at the rate of one every 90 seconds.
 * @todo
 *   Need to change Requirements to Either PAL 1 or Level 20 Enhancement
 *   [[https://ddowiki.com/page/Unyielding_Sentinel#Fanaticism Fanaticism]]
 * @todo
 *   Shared Cooldown with Exalted Smite
 */
protected[feats] trait SmiteEvil
  extends FeatRequisiteImpl with TriggeredActivationImpl with Passive with ActiveFeat
  with AtWillEvent with GrantsToClass with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 1))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))
}
