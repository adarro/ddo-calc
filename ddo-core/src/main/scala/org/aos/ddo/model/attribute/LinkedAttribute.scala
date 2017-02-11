package org.aos.ddo.model.attribute

import org.aos.ddo.model.attribute.Attribute._

import scala.collection.immutable.HashSet

/**
  * Created by adarr on 1/28/2017.
  */
trait LinkedAttribute {
  def linkedAttribute: Set[Attribute] = new HashSet[Attribute]()
}

trait StrengthLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Strength
}

trait DexterityLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Dexterity
}

trait ConstitutionLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Constitution
}

trait IntelligenceLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Intelligence
}

trait WisdomLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Wisdom
}

trait CharismaLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Charisma
}
