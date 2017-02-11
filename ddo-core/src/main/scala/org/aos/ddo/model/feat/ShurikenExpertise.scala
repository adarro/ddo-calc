package org.aos.ddo.model.feat

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfFeat, RequiresAttribute}

/** Shuriken Expertise.PNG
  * Shuriken Expertise 	Passive 	You are skilled with the use of the shuriken, and have a chance to throw an additional one per throw. (Percent chance to throw an additional shuriken is equal to your Dexterity.) This is also a racial feat given to all Drow Elf at level 1, regardless of class.
  * *
  * Dexterity 13
  * MustContainAtLeastOne of: Proficiency: Shuriken or
  * Half-Elf Dilettante: Monk
  * */
protected[feat] trait ShurikenExpertise extends FeatRequisiteImpl with Passive with RequiresAttribute with RequiresAnyOfFeat {
  self: Feat =>
  override def requiresAttribute: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 13))

  override def anyOfFeats: Seq[Feat] = List(Feat.ExoticWeaponProficiencyShuriken, Feat.HalfElfDilettanteMonk)
}
