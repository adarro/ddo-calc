/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ShieldDeflection.scala
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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfFeat, RequiresBaB}

/**
 * Icon Feat Improved Shield Bash.png Shield Deflection Passive When actively blocking with any type
 * of Shield you are proficient with, you gain a Competence bonus based on the type of shield to
 * completly ignore Acid, Cold, Electic and Fire damage.
 *
 * Buckler: 20%, Small Shield: 25%, Large Shield 30%, Tower Shield: 40% *
 *
 * Shield Proficiency: General or Tower Shield Proficiency +8 Base Attack Bonus
 */
protected[feats] trait ShieldDeflection
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with RequiresBaB
  with RequiresAnyOfFeat with FighterBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB: Int = 8

  override def anyOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.ShieldProficiency, GeneralFeat.TowerShieldProficiency)
}
