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
import io.truthencode.ddo.api.model.effect.{BasicEffectInfo, DetailedEffect, FullEffect}
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.attribute.{Attribute, UsingAttributeSearchPrefix}
import io.truthencode.ddo.model.feats.Feat
import io.truthencode.ddo.model.item.weapon.WeaponCategory
import io.truthencode.ddo.model.stats.BasicStat
import io.truthencode.ddo.support.naming.{DisplayName, UsingSearchPrefix}

import scala.util.Try

/**
 * A general Feature with a base type value of V
 * @tparam V
 *   Generally a primitive Type such as Int but may be a more complex object
 */
sealed trait Feature[V] extends BasicEffectInfo {

  /**
   * The main name of the effect.
   *
   * Naming conventions The name should be concisely non-specific. i.e. Prefer "ArmorClass" instead
   * of "Deflection" or "Miss-Chance" Deflection is too specific as there are several stacking and
   * non-stacking types (Natural Armor, Shield) that all contribute to your specific goal of
   * increasing your armor class. Miss-Chance is to vague as it encompasses everything from
   * incorporeal, dodge, armor class, arrow-deflection etc.
   */
  override lazy val name: String = nameOption.getOrElse("Unknown")
  lazy val nameOption: Option[String] = part.toOption match {
    case None => None
    case Some(value) => Some(value.entryName)
  }
  lazy val effectText: Option[String] = None
  val part: Try[EffectPart]
  val value: V
  val source: SourceInfo
  val effectDetail: DetailedEffect

  def parameters: Seq[Try[EffectParameter]]

}

trait DynamicFeature[V] extends Feature[V] {
  def computedValue: V => V
}

object Feature {

  implicit class EffectOps(source: Feature[_]) {
    def asFullEffect: FullEffect = {
      val ed = source.effectDetail
      val iValOpt = source.value match {
        case x: Int => Some(x)
        case _ => None
      }
      FullEffect(
        ed.id,
        source.effectText.getOrElse(ed.description),
        ed.triggersOn,
        ed.triggersOff,
        source.name,
        source.generalDescription,
        source.categories,
        ed.bonusType,
        iValOpt
      )
    }
  }

  def printFeature(f: Feature[_]): String = {
    val categoryInfo = f.categories.mkString(",")
    s"\nFeature:\nName:\t${f.name} \nCategories:$categoryInfo\nvalue:\t${f.value}\nsource:\t${f.source}\nid:\t\t${f.source.sourceId}\ntext:\t${f.effectText
        .getOrElse("")}\nTriggers On:${f.effectDetail.triggersOn.mkString}\nTriggers Off:${f.effectDetail.triggersOff.mkString}"
  }
  // scalastyle:off
  case class CriticalThreatRangeEffect(
    override val value: Seq[(WeaponCategory, Int)],
    basicStat: BasicStat,
    bonusType: BonusType,
    override val source: SourceInfo,
    override val categories: Seq[String],
    override val effectDetail: DetailedEffect)
    extends PartModifier[Seq[(WeaponCategory, Int)], BasicStat] with UsingSearchPrefix {

    //  override protected[this] lazy val parameterToModify: BonusType = bonusType
    override lazy val name: String = "Critical Threat Range"
    override lazy val effectText: Option[String] = Some(
      s"Increased Critical Threat Amount: $value%")
    override protected[this] lazy val partToModify: BasicStat = {
      basicStat

    }
    override val withPrefix: String = s"$searchPrefix:$nameSource"

    /**
     * The General Description should be just that. This should not include specific values unless
     * all instances will share that value. I.e. a Dodge Effect might state it increases your
     * miss-chance, but omit any value such as 20%. Those values will be displayed in the effectText
     * of a specific implementation such as the Dodge Feat or Uncanny Dodge
     */
    override val generalDescription: String = "Increases your chance to make a critical hit"
    private lazy val p = EffectParameterBuilder()
      .toggleOffValue(tO: _*)
      .toggleOnValue(tN: _*)
      .addBonusType(bonusType)
      .build
    private val tO = effectDetail.triggersOff.map(TriggerEvent.withName)
    private val tN = effectDetail.triggersOn.map(TriggerEvent.withName)

    /**
     * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
     * qualified "Race:HalfElf"
     *
     * @return
     *   A default or applied prefix
     */
    override def searchPrefixSource: String = partToModify.searchPrefixSource

  }
  // scalastyle:on

  case class AttributeEffect(
    attribute: Attribute,
    override val value: Int,
    bonusType: BonusType,
    override val source: SourceInfo,
    override val categories: Seq[String],
    effectDetail: DetailedEffect)
    extends PartModifier[Int, Attribute] with UsingAttributeSearchPrefix {
    override lazy val name: String = withPrefix
//    override protected[this] val parameterToModify: BonusType = bonusType
    override protected[this] val partToModify: Attribute = attribute

    /**
     * The General Description should be just that. This should not include specific values unless
     * all instances will share that value. I.e. a Dodge Effect might state it increases your
     * miss-chance, but omit any value such as 20%. Those values will be displayed in the effectText
     * of a specific implementation such as the Dodge Feat or Uncanny Dodge
     */
    override val generalDescription: String = "Increases a particular attribute score"
  }

}
trait AugmentFeatureValue extends Feature[Int]

/**
 * Modification info used to change / affect the value of type V
 * @tparam V
 *   Generally a primitive Type such as Int but may be a more complex object
 * @tparam E
 *   Entry ID / Enumeration used to qualify which part is being modified.
 */
trait PartModifier[V, E <: EnumEntry] extends Feature[V] with DisplayName {
  self: UsingSearchPrefix =>

  /**
   * The current Seaerch-Fu is weak. Override this default function.
   */
  override lazy val part: Try[EffectPart] =
    EffectPart.tryFindByPattern(partToModify.entryName, Option(withPrefix))
  override lazy val parameters: Seq[Try[EffectParameter]] = effectParameters.map(_.parameter)
// FIXME add UsingSearchPrefix to type constraint
  override val withPrefix: String = s"$searchPrefix$nameSource"
  protected[this] val partToModify: E

  protected[this] def effectParameters: Seq[ParameterModifier[_]] = ???

  /**
   * Sets or maps the source text for the DisplayName. // TODO: use the Try[part] instead of the
   * partToModify
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = partToModify.entryName
}

trait FeatModifier[T, E <: Feat] extends PartModifier[T, E] {
  self: UsingSearchPrefix =>

}

/**
 * A parameter that SHOULD be found in EffectParameter used for validation / stacking
 * @tparam E
 *   Enum of the parameter type being used, such as A Bonus Type of 'Action Boost'
 */
trait ParameterModifier[E <: EnumEntry] {

  lazy val parameter: Try[EffectParameter] =
    EffectParameter.tryFindByPattern(parameterToModify.entryName, None)
  protected[this] val parameterToModify: E
}

case class EffectFeature[T](
  parameter: Try[EffectParameter],
  override val part: Try[EffectPart],
  override val source: SourceInfo,
  override val categories: Seq[String],
  override val value: T,
  override val generalDescription: String,
  effectDetail: DetailedEffect)
  extends Feature[T] {
  override def parameters: Seq[Try[EffectParameter]] = ???
}
