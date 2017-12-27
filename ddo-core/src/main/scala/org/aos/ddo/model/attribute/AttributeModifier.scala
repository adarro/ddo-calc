package org.aos.ddo.model.attribute

import org.aos.ddo.model.attribute.Attribute._

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
  protected[this] val attributeStrength: Attribute = Strength
  protected def intModifierStrength: Int
  private[this] val totalModifier: M = (attributeStrength, intModifierStrength)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

trait DexterityModifier extends AttributeModifier {
  protected[this] val attributeDexterity: Attribute = Dexterity
  protected def intModifierDexterity: Int
  private[this] val totalModifier: M = (attributeDexterity, intModifierDexterity)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

trait ConstitutionModifier extends AttributeModifier {
  protected[this] val attributeConstitution: Attribute = Constitution
  protected def intModifierConstitution: Int
  private[this] val totalModifier: M = (attributeConstitution, intModifierConstitution)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

trait IntelligenceModifier extends AttributeModifier {
  protected[this] val attributeIntelligence: Attribute = Intelligence
  protected def intModifierIntelligence: Int
  private[this] val totalModifier: M = (attributeIntelligence, intModifierIntelligence)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

trait WisdomModifier extends AttributeModifier {
  protected[this] val attributeWisdom: Attribute = Wisdom
  protected def intModifierWisdom: Int
  private[this] val totalModifier: M = (attributeWisdom, intModifierWisdom)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

trait CharismaModifier extends AttributeModifier {
  protected[this] val attributeCharisma: Attribute = Charisma
  protected def intModifierCharisma: Int
  private[this] val totalModifier: M = (attributeCharisma, intModifierCharisma)
  abstract override def modifiedAttributes: Set[M] = super.modifiedAttributes + totalModifier
}

