package org.aos.ddo.model.feat

import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresBaB, RequiresSkill}

/** Icon Feat Single Weapon Fighting.png
  * Greater Single Weapon Fighting
  * Passive
  * Your Single-Weapon Fighting bonus is increased to a +30% Combat Style bonus to attack speed and 2 additional Melee Power (for a total of 6)
  * and 50% more of your appropriate ability score to your damage (similar to Two-Handed Fighting).
  *
  * Note: Further increases base amount of attacks per second from 1.8 to 2.0.[Unverified]
  * Improved Single Weapon Fighting
  * 7 ranks of Balance
  * Base Attack Bonus +11
  */
trait GreaterSingleWeaponFighting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresSkill with RequiresBaB {
  self: Feat =>
  override def requiresBaB = 11

  override def requiresSkill: Seq[(Skill, Int)] = List((Skill.Balance, 7))

  override def allOfFeats: Seq[Feat] = List(Feat.ImprovedSingleWeaponFighting)
}
