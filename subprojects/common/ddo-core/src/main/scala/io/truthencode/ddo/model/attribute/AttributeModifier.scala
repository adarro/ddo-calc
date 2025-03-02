/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AttributeModifier.scala
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
package io.truthencode.ddo.model.attribute

import io.truthencode.ddo.model.attribute.Attribute._

import scala.collection.immutable.HashSet

/**
 * Base class for Modifying Attribute
 */
sealed trait AttributeModifier {
  type M = (Attribute, Int)
  def modifiedAttributes: Set[M]
}

trait AttributeModifierInit extends AttributeModifier {
  def modifiedAttributes: Set[M] = new HashSet[M]
}

/**
 * Empty Modifier used to specify nothing special
 */
trait DefaultAttributeModifier extends AttributeModifier

trait StrengthModifier extends AttributeModifier {
  protected val attributeStrength: Attribute = Strength
  private val totalModifier: M = (attributeStrength, intModifierStrength)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierStrength: Int
}

trait DexterityModifier extends AttributeModifier {
  protected val attributeDexterity: Attribute = Dexterity
  private val totalModifier: M = (attributeDexterity, intModifierDexterity)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierDexterity: Int
}

trait ConstitutionModifier extends AttributeModifier {
  protected val attributeConstitution: Attribute = Constitution
  private val totalModifier: M = (attributeConstitution, intModifierConstitution)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierConstitution: Int
}

trait IntelligenceModifier extends AttributeModifier {
  protected val attributeIntelligence: Attribute = Intelligence
  private val totalModifier: M = (attributeIntelligence, intModifierIntelligence)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierIntelligence: Int
}

trait WisdomModifier extends AttributeModifier {
  protected val attributeWisdom: Attribute = Wisdom
  private val totalModifier: M = (attributeWisdom, intModifierWisdom)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierWisdom: Int
}

trait CharismaModifier extends AttributeModifier {
  protected val attributeCharisma: Attribute = Charisma
  private val totalModifier: M = (attributeCharisma, intModifierCharisma)

  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier

  protected def intModifierCharisma: Int
}
