package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfClass, RequiresAttribute}

/** Icon Feat Mobile Spellcasting.png
  * Mobile Spellcasting 	Passive 	Character while moving cast at half their normal movement speed, however with Mobile Spellcasting, character can move at full speed.
  * *
  * Combat Casting, Dexterity 13,
  * Ability to cast 2nd level spells
  * Level 3: Artificer, Cleric, Druid, Wizard
  * Level 4: Bard, Favored Soul, Sorcerer
  * Level 7: Paladin, Ranger
  *
  * @todo Add ability to cast 2nd level spells req
  **/
protected[feat] trait MobileSpellcasting extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAttribute with RequiresAnyOfClass {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.CombatCasting)

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Artificer, 3),
      (CharacterClass.Bard, 4),
      (CharacterClass.Cleric, 3),
      (CharacterClass.Druid, 3),
      (CharacterClass.FavoredSoul, 4),
      (CharacterClass.Sorcerer, 4),
      (CharacterClass.Wizard, 3),
      (CharacterClass.Paladin, 7),
      (CharacterClass.Ranger, 7))
}
