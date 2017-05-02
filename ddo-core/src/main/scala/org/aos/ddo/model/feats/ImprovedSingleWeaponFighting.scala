package org.aos.ddo.model.feats

import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite._

/** Icon Feat Single Weapon Fighting.png
  * Improved Single Weapon Fighting
  * Passive
  * Your Single-Weapon Fighting bonus is increased to a +20% Combat Style bonus to attack speed and an additional 2 Melee Power (for a total of 4).
  * You now apply 25% more of your appropriate ability score to your damage instead of just your ability score
  *    (for instance, you add 1.25 times your Strength as damage).
  *
  * Note: Further increases base amount of attacks per second from 1.6 to 1.8.[Unverified]
  *
  * Single Weapon Fighting
  * 4 ranks of Balance
  * Base Attack Bonus +6
  * */
protected[feats] trait ImprovedSingleWeaponFighting
    extends FeatRequisiteImpl
    with SkillRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAnyOfSkill
    with RequiresBaB { self: GeneralFeat =>
  override def requiresBaB = 6

  override def oneOfSkill: Seq[(Skill, Int)] = List((Skill.Balance, 4))

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.SingleWeaponFighting)
}
