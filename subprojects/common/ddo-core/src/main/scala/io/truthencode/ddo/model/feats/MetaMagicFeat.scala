/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: MetaMagicFeat.scala
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

import enumeratum.Enum
import io.truthencode.ddo.activation.TriggeredActivationImpl
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.effect.TriggerEvent
import io.truthencode.ddo.model.effect.features.FeaturesImpl
import io.truthencode.ddo.support.naming.FriendlyDisplay
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  Inclusion,
  Requisite,
  RequisiteType
}

import scala.collection.immutable.IndexedSeq

/**
 * Created by adarr on 2/21/2017.
 */
sealed trait MetaMagicFeat
  extends Feat with BonusSelectableToClassFeatImpl with TriggeredActivationImpl
  with ClassRequisiteImpl with FriendlyDisplay with FeatMatcher with FeaturesImpl {
  self: FeatType & Requisite & Inclusion & RequisiteType & TriggerEvent =>

  val matchFeat: PartialFunction[Feat, MetaMagicFeat] = { case x: MetaMagicFeat =>
    x
  }

  val matchFeatById: PartialFunction[String, MetaMagicFeat] = {
    case x: String if MetaMagicFeat.namesToValuesMap.contains(x) =>
      MetaMagicFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }

}

object MetaMagicFeat extends Enum[MetaMagicFeat] {

  lazy val minimumSpellCastingClass: Seq[(HeroicCharacterClass, Int)] =
    List(
      (HeroicCharacterClass.Alchemist, 1),
      (HeroicCharacterClass.Artificer, 1),
      (HeroicCharacterClass.Bard, 1),
      (HeroicCharacterClass.Cleric, 1),
      (HeroicCharacterClass.Druid, 1),
      (HeroicCharacterClass.FavoredSoul, 1),
      (HeroicCharacterClass.Sorcerer, 1),
      (HeroicCharacterClass.Warlock, 1),
      (HeroicCharacterClass.Wizard, 1),
      (HeroicCharacterClass.Paladin, 4),
      (HeroicCharacterClass.Ranger, 4)
    )

  override def values: IndexedSeq[MetaMagicFeat] = findValues

  case object EmpowerHealingSpell extends MetaMagicFeat with EmpowerHealingSpell

  case object EmpowerSpell extends MetaMagicFeat with EmpowerSpell

  case object EnlargeSpell extends MetaMagicFeat with EnlargeSpell

  case object EschewMaterials extends MetaMagicFeat with EschewMaterials

  case object ExtendSpell extends MetaMagicFeat with ExtendSpell

  case object HeightenSpell extends MetaMagicFeat with HeightenSpell

  case object MaximizeSpell extends MetaMagicFeat with MaximizeSpell

  case object QuickenSpell extends MetaMagicFeat with QuickenSpell
}
