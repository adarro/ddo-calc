package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/** Icon Feat Combat Casting.png
  * Combat Casting 	Passive 	Provides a +4 bonus to the character's Concentration while casting spells in combat.
  * *
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4:Paladin, Ranger
  */
protected[feat] trait CombatCasting extends FeatRequisiteImpl with Passive with RequiresAnyOfClass {
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
}
