package org.aos.ddo.model.feat

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfFeat}

/**
  * Icon Feat Improved Feint.png
  * Improved Feint -- Active - Special Attack
  * A tactical melee attack which Bluffs the enemy, enabling Sneak Attacks.
  *
  * Combat Expertise,
  * MustContainAtLeastOne of : Sneak Attack or Half-Elf Dilettante: Rogue
  *
  * @todo Implement MustContainAtLeastOneOf(Sneak Attack or Half-Elf Dilettante: Rogue)
  */
protected[feat] trait ImprovedFeint extends FeatRequisiteImpl with Active with RequiresAnyOfFeat with RequiresAllOfFeat {
  self: Feat =>
  override def anyOfFeats: Seq[Feat] = List(Feat.SneakAttack)

  override def allOfFeats: Seq[Feat] = List(Feat.CombatExpertise)
}
