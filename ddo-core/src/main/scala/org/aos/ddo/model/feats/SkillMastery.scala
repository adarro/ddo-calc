package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Barbarian, Bard, Rogue}
import org.aos.ddo.support.requisite.{ClassRequisite, FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
  * A rogue with this ability gains +1 to all skills. This ability may be taken multiple times.
  * Notes
  * This ability may be taken multiple times.
  */
protected[feats] trait SkillMastery
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = rogueOptionMatrix

  override def anyOfClass: Seq[(CharacterClass, Int)] = List((Rogue,10),(Bard,18),(Barbarian,12))
}
