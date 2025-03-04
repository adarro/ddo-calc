/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BurstOfGlacialWrath.scala
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
import io.truthencode.ddo.model.spells.SpellBookImpl
import io.truthencode.ddo.support.requisite.{FreeFeat, RequiresCharacterLevel}

/**
 * Created by adarr on 4/3/2017.
 * [[https://ddowiki.com/page/Burst_of_Glacial_Wrath Burst of Glacial Wrath]] Passive: Grants 140
 * spell points. Active: Deals 30d6 cold damage in a cone, chance to freeze.
 */
protected[feats] trait BurstOfGlacialWrath
  extends FreeFeat with TriggeredActivationImpl with SpellFeats with Passive with ActiveFeat
  with AtWillEvent with SpellBookImpl with RequiresCharacterLevel { self: EpicFeat =>
  final override val requireCharacterLevel: Int = 24

  override def spellIds: Set[String] =
    super.spellIds + "BurstOfGlacialWrath"

}
