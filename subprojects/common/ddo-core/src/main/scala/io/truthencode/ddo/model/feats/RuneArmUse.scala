/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RuneArmUse.scala
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

import io.truthencode.ddo.activation.{OnToggleEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Artificer
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Rune_Arm_Use Rune Arm Use]] Passively, you are able to equip and use
 * magical relics called Rune Arms. When this feat is toggled on, it will set the rune arm to
 * automatically begin charging whenever it can. While you have a Rune Arm charging, tapping the
 * Rune Arm Key (default: Alt) will fire the Rune Arm. You can freely perform most other actions
 * while charging the Rune Arm. Created by adarr on 2/16/2017.
 */
protected[feats] trait RuneArmUse
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with OnToggleEvent
  with Passive with GrantsToClass with RequiresAllOfClass with DefaultCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 2))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Artificer, 2))
}
