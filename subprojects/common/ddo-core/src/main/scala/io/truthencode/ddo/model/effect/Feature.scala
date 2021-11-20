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
package io.truthencode.ddo.model.effect

import enumeratum.EnumEntry
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.model.skill.Skill

import scala.util.Try

/**
  * A general Feature with a base type value of V
  * @tparam V Generally a primitive Type such as Int but may be a more complex object
  */
sealed trait Feature[V] {
  val parameter: Try[EffectParameter]
  val part: Try[EffectPart]
  val value: V
  val source: SourceInfo

  lazy val name: Option[String] = part.toOption match {
    case None        => None
    case Some(value) => Some(value.entryName)
  }
  val effectText: Option[String] = None
}

object Feature {

  def printFeature(f: Feature[_]): String = {
    s"Feature:\nName:\t${f.name} \nvalue:\t${f.value}\nsource:\t${f.source}\ntext:\t${f.effectText}\n "
  }

  case class SkillEffect(
    skill: Skill,
    override val value: Int,
    bonusType: BonusType,
    override val source: SourceInfo
  ) extends PartModifier[Int, Skill]
      with ParameterModifier[Int, BonusType] {

    def numberToSignedText(int: Int): String = {
      if (int >= 0) {
        s"+$int"
      } else {
        s"-$int"
      }
    }

    lazy override protected[this] val partToModify: Skill =
      skill

    lazy override protected[this] val parameterToModify: BonusType =
      bonusType

    override lazy val name: Option[String] = Some(skill.withPrefix)

    override val effectText: Option[String] = Some(
      s"provides a ${numberToSignedText(value)} ${bonusType.entryName} bonus to ${partToModify.entryName}"
    )

  }

  case class AttributeEffect(
    attribute: Attribute,
    override val value: Int,
    bonusType: BonusType,
    override val source: SourceInfo
  ) extends PartModifier[Int, Attribute]
      with ParameterModifier[Int, BonusType] {
    override protected[this] val partToModify: Attribute = attribute
    override protected[this] val parameterToModify: BonusType = bonusType
  }

}
trait AugmentFeatureValue extends Feature[Int]

/**
  * Modification info used to change / affect the value of type V
  * @tparam V Generally a primitive Type such as Int but may be a more complex object
  * @tparam E Entry ID / Enumeration used to qualify which part is being modified.
  */
trait PartModifier[V, E <: EnumEntry] extends Feature[V] {
  self: ParameterModifier[V, _] =>
  protected[this] val partToModify: E

  override val part: Try[EffectPart] =
    EffectPart.tryFindByPattern(partToModify.entryName)
}

trait FeatModifier[T, E <: Feat] extends PartModifier[T, E] {
  self: ParameterModifier[T, _] =>
}

/**
  * A parameter that SHOULD be found in EffectParameter used for validation / stacking
  * @tparam V Generally a primitive Type such as Int but may be a more complex object
  * @tparam E Enum of the parameter type being used, such as A Bonus Type of 'Action Boost'
  */
trait ParameterModifier[V, E <: EnumEntry] extends Feature[V] {
  self: PartModifier[V, _] =>
  protected[this] val parameterToModify: E

  override lazy val parameter: Try[EffectParameter] =
    EffectParameter.tryFindByPattern(parameterToModify.entryName)
}

case class EffectFeature[T](
  override val parameter: Try[EffectParameter],
  override val part: Try[EffectPart],
  override val source: SourceInfo,
  override val value: T
) extends Feature[T]
