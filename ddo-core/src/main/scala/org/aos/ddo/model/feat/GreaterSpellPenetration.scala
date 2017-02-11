package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfClass}

/** Icon Feat Greater Spell Penetration.png
  * Greater Spell Penetration 	Passive 	Adds +2 to the caster level check for defeating a foe's spell resistance. (This stacks with Spell Penetration for a grand total of +4.)
  * *
  * Spell Penetration
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  * */
protected[feat] trait GreaterSpellPenetration extends FeatRequisiteImpl with Passive with RequiresAnyOfClass with RequiresAllOfFeat {
  self: Feat =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Artificer, 1),
      (CharacterClass.Bard, 1),
      (CharacterClass.Cleric, 1),
      (CharacterClass.Druid, 1),
      (CharacterClass.FavoredSoul, 1),
      (CharacterClass.Sorcerer, 1),
      (CharacterClass.Wizard, 1),
      (CharacterClass.Paladin, 4),
      (CharacterClass.Ranger, 4))

  override def allOfFeats: Seq[Feat] = List(Feat.SpellPenetration)
}
