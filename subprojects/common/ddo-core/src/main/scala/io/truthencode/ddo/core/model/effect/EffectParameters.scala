/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EffectParameters.scala
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

import enumeratum.{Enum => SmartEnum, EnumEntry}
import io.truthencode.ddo.enhancement.{BonusType => Bonus}
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.repo.Repo
import io.truthencode.ddo.support.ModifierStrategy

import scala.collection.immutable

/**
 * Enumerates the possible parameter types allowed for an effect such as the Trigger, Type of bonus
 * etc
 */
sealed trait EffectParameter extends EnumEntry with SearchPattern {
  override def searchPattern(target: String): String = target
}

trait DifficultyCheck extends EffectParameter {

  /**
   * The base difficulty check value (before modifiers such as Strength or any Feats / buffs etc.
   * I.e. For the Trip Feat, it is a base DC of 10
   * @return
   *   initial base Difficulty value
   */
  def baseDC: Int

  /**
   * List of Attributes to base modifiers. In a general terms, the highest or lowest value will
   * apply.
   * @return
   *   list of attributes used to modify the check value.
   */
  def statModifier: List[Attribute]
  def modifierStrategy: ModifierStrategy
  def DC(implicit repo: Repo): Int = calculateDC
  def calculateDC(implicit repo: Repo): Int
}

object EffectParameter extends SmartEnum[EffectParameter] with Searchable[EffectParameter] {
  val values: IndexedSeq[EffectParameter] = findValues ++ bonusTypes ++ triggerEvents

  def triggerEvents: immutable.IndexedSeq[Trigger] =
    for t <- TriggerEvent.values yield Trigger(t)

  def bonusTypes: immutable.IndexedSeq[BonusType] =
    for b <- Bonus.values yield BonusType(b)

  case class Trigger(triggerEvent: TriggerEvent) extends EffectParameter

  case class BonusType(bonus: Bonus) extends EffectParameter {
    override def searchPattern(target: String): String = s"BonusType($target)"
  }

  case object Magnitude extends EffectParameter

  case object DifficultyCheck extends EffectParameter

  case object Target extends EffectParameter
}
