package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite._

/**
  * @note may need to change name to 'Suggestion'
  */
protected[feats] trait SuggestionSong
    extends SkillRequisiteImpl
    with RequiresAllOfSkill
    with ClassRequisiteImpl
    with RequiresAllOfClass
      with GrantsToClass
    with Active {
  override def allOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Bard, 6))

  override def grantToClass: Seq[(CharacterClass, Int)] =  List((CharacterClass.Bard, 6))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform, 9))
}
