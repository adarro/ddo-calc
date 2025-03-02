/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: package.scala
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
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.skill.{Skill, UsingSkillSearchPrefix}
import io.truthencode.ddo.support.StringUtils.IntExtensions

package object features {

  case class SkillEffect(
    skill: Skill,
    override val value: Int,
    bonusType: BonusType,
    override val source: SourceInfo,
    override val categories: Seq[String],
    override val effectParameters: Seq[ParameterModifier[?]],
    effectDetail: DetailedEffect)
    extends PartModifier[Int, Skill] with UsingSkillSearchPrefix {

    /**
     * The main name of the effect.
     *
     * Naming conventions The name should be concisely non-specific. i.e. Prefer "ArmorClass"
     * instead of "Deflection" or "Miss-Chance" Deflection is too specific as there are several
     * stacking and non-stacking types (Natural Armor, Shield) that all contribute to your specific
     * goal of increasing your armor class. Miss-Chance is to vague as it encompasses everything
     * from incorporeal, dodge, armor class, arrow-deflection etc.
     */
    override lazy val name: String = withPrefix.replace("::", ":")
    override lazy val effectText: Option[String] = Some(
      s"provides a ${value.numberToSignedText} ${bonusType.entryName} bonus to ${partToModify.entryName}"
    )
    //    override lazy val name: Option[String] = Some(skill.withPrefix)
    override protected lazy val partToModify: Skill =
      skill

    /**
     * The General Description should be just that. This should not include specific values unless
     * all instances will share that value. I.e. a Dodge Effect might state it increases your
     * miss-chance, but omit any value such as 20%. Those values will be displayed in the effectText
     * of a specific implementation such as the Dodge Feat or Uncanny Dodge
     */
    override val generalDescription: String = "Increases a particular skill"

    //    override protected[this] def parameters: Seq[ParameterModifier[Int, _]] = effectParameters
  }
}
