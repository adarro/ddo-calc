package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Artificer, Rogue}
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass, RequiresAnyOfFeat}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait Trapmaking extends FeatRequisiteImpl with Passive with RequiresAnyOfClass with RequiresAnyOfFeat  {
  override def anyOfClass: Seq[(CharacterClass, Int)] = List((Artificer,4),(Rogue,4))
//@todo Add Least DM of Making
  override def anyOfFeats: Seq[Feat] = List(GeneralFeat.NimbleFingers,GeneralFeat.SkillFocus(Skill.DisableDevice))
}
