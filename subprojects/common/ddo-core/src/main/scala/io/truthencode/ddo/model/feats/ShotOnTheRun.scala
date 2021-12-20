/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAttribute, RequiresBaB}

/**
 * Icon Feat Shot On The Run.png Shot on the Run Passive Negates the penalty to your attack roll for
 * firing ranged weapon while moving. Also gives +3 ranged power. * Point Blank Shot, Mobility
 * Dexterity 13, Base Attack Bonus 4+
 */
protected[feats] trait ShotOnTheRun
  extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute
  with RequiresBaB with FighterBonusFeat with ArtificerBonusFeat with AlchemistBonusFeat {
  self: GeneralFeat =>

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 13))

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.PointBlankShot, GeneralFeat.Mobility)

  override def requiresBaB = 4
}
