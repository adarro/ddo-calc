package org.aos.ddo.model.feats

import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  RequiresAnyOfSkill,
  SkillRequisiteImpl
}

/**
  * Icon Feat Single Weapon Fighting.png
  * Single Weapon Fighting
  * Passive
  * While Single Weapon Fighting, you gain +10% Combat Style bonus to attack speed and 2 Melee Power.
  *
  * Single Weapon Fighting: Requires fighting with a single one-handed weapon, and wielding only an orb, runearm, or nothing in your off hand.
  *
  * Note: Actually increases base number of attacks per second from 1.4 to 1.6.[Unverified]
  * 2 ranks of Balance
  */
protected[feats] trait SingleWeaponFighting
    extends FeatRequisiteImpl
    with SkillRequisiteImpl
    with Passive
    with RequiresAnyOfSkill { self: GeneralFeat =>
  override def oneOfSkill: Seq[(Skill, Int)] = List((Skill.Balance, 2))
}
