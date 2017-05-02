package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Bard
import org.aos.ddo.model.skill.Skill
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 4/5/2017.
  */
trait InspireExcellence
    extends RequiresAllOfFeat
    with RequiresAllOfClass
    with RequiresAllOfSkill
    with RequiresCharacterLevel
    with ClassRestricted {
  override def allOfFeats: Seq[Feat] = List(ClassFeat.InspireHeroics)

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Bard,15))

  override def allOfSkill: Seq[(Skill, Int)] = List((Skill.Perform,20))

  override val characterLevel: Int = 21
}
