/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EffectParameterBuilder.scala
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
import io.truthencode.ddo.support.numbers.Numbers

import scala.util.{Success, Try}

case class EffectParameterList(modifiers: Seq[ParameterModifier[?]])

class EffectParameterBuilder[T <: EffectParameterBuilder.EffectParams] protected (
  ingredients: Seq[ParameterModifier[?]]) {
  import EffectParameterBuilder.EffectParams._

  /**
   * Determines when the given effect is triggered on. Required
   * @param toggleOn
   *   A valid Trigger Event
   * @return
   *   applied trigger modifier to builder
   */
  def toggleOnValue(toggleOn: TriggerEvent*): EffectParameterBuilder[T & ToggleOnParam] = {

    val npm = toggleOn.map { t =>
      {
        new ParameterModifier[TriggerEvent] {
          override protected val parameterToModify: TriggerEvent = t
          override lazy val parameter: Try[EffectParameter] = Success(EffectParameter.Trigger(t))
        }
      }
    }
    val allGoodStuff: Seq[ParameterModifier[TriggerEvent]] =
      ingredients.collect(filterByType[TriggerEvent]) ++ npm
    EffectParameterBuilder(ingredients ++ allGoodStuff.toSet.toSeq)
  }

  def filterByType[U <: EnumEntry]: PartialFunction[ParameterModifier[?], ParameterModifier[U]] =
    new PartialFunction[ParameterModifier[?], ParameterModifier[U]] {
      override def isDefinedAt(x: ParameterModifier[?]): Boolean = x match {
        case x: ParameterModifier[U] => true
      }

      override def apply(v1: ParameterModifier[?]): ParameterModifier[U] =
        v1.asInstanceOf[ParameterModifier[U]]
    }

  /**
   * Determines when the given effect is triggered off. Required
   * @param toggleOff
   *   A valid Trigger Event
   * @return
   *   applied trigger modifier to builder
   */
  def toggleOffValue(toggleOff: TriggerEvent*): EffectParameterBuilder[T & ToggleOffParam] = {
    val npm = toggleOff.map { t =>
      {
        new ParameterModifier[TriggerEvent] {
          override protected val parameterToModify: TriggerEvent = t
          override lazy val parameter: Try[EffectParameter] = Success(EffectParameter.Trigger(t))
        }
      }
    }
    val pp: EffectParameterBuilder[T & ToggleOffParam] = EffectParameterBuilder(ingredients ++ npm)
    EffectParameterBuilder(ingredients ++ npm)
  }

  /**
   * Optional value that flags this effect as using magnitude
   * @return
   *   applied magnitude to builder.
   */
  def addMagnitude(): EffectParameterBuilder[T] = {
    val npm = new ParameterModifier[Numbers] {
      override protected val parameterToModify: Numbers = Numbers.Magnitude
      override lazy val parameter: Try[EffectParameter] = Success(EffectParameter.Magnitude)
    }
    EffectParameterBuilder(ingredients :+ npm)
  }

  /**
   * Denotes this is a DC check, effect or condition
   * @return
   *   applied DC to the builder
   */
  def addDifficultyCheck(): EffectParameterBuilder[T] = {
    val npm = new ParameterModifier[Numbers] {
      override protected val parameterToModify: Numbers = Numbers.DifficultyCheck
      override lazy val parameter: Try[EffectParameter] = Success(EffectParameter.DifficultyCheck)
    }

    EffectParameterBuilder(ingredients :+ npm)
  }

  /**
   * Required. Sets the BonusType of the effect for purposes of stacking.
   * @param bt
   *   A valid BonusType
   * @return
   *   applied BonusType to the builder
   */
  def addBonusType(bt: BonusType): EffectParameterBuilder[T & BonusTypeParam] = {
    val npm = new ParameterModifier[BonusType] {
      override protected val parameterToModify: BonusType = bt
      override lazy val parameter: Try[EffectParameter] = Success(EffectParameter.BonusType(bt))
    }
    EffectParameterBuilder(ingredients :+ npm)
  }

  /**
   * Builds the ParameterModifier set if all required parameters are set.
   * @param ev
   *   validation evidence for the builder.
   * @return
   *   A valid EffectParameterList containing all ParameterModifiers.
   */
  def build(implicit ev: T =:= FullPizza): EffectParameterList = EffectParameterList(ingredients)
}

object EffectParameterBuilder {

  def apply(): EffectParameterBuilder[EffectParams.EmptyParameters] =
    apply[EffectParams.EmptyParameters](Seq())

  def apply[T <: EffectParams](ingredients: Seq[ParameterModifier[?]]): EffectParameterBuilder[T] =
    new EffectParameterBuilder[T](ingredients)

  sealed trait EffectParams

  object EffectParams {
    type FullPizza = EmptyParameters & ToggleOnParam & ToggleOffParam & BonusTypeParam

    sealed trait EmptyParameters extends EffectParams

    sealed trait ToggleOnParam extends EffectParams

    sealed trait ToggleOffParam extends EffectParams

    sealed trait BonusTypeParam extends EffectParams
  }

//  def makePizzaWithOptional: EffectParameterList[Int, EffectParameter] =
//    EffectParameterBuilder[Int, EffectParameter]()
//      .toggleOnValue("mozzarella")
//      .addBonusType
//      .addMagnitude("Some Option")
//      .toggleOffValue("olives")
//      .build
}
