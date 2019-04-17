package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 4/5/2017.
  */
protected[feats] trait InspireGreatness
    extends SkillRequisiteImpl
    with RequiresAllOfSkill
    with ClassRequisiteImpl
    with RequiresAllOfClass
      with GrantsToClass
    with Active {
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Bard, 9))

  override def grantToClass: Seq[(CharacterClass, Int)] =  List((CharacterClass.Bard, 9))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 12))
}