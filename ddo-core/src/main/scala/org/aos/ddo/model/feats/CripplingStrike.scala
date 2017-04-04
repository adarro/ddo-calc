package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  ClassRequisite,
  FeatRequisiteImpl,
  GrantsToClass
}

/**
  *A rogue with this ability can sneak attack opponents with such precision that his or her blows weaken and hamper them.
  * An opponent damaged by a sneak attack also takes 2 points of Strength damage.
  */
protected[feats] trait CripplingStrike
    extends FeatRequisiteImpl
    with ClassRequisite
    with Passive
    with GrantsToClass
    with RogueOptionalAbility { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = rogueOptionMatrix

}
