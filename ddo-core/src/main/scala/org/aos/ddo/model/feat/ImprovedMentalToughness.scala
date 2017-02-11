package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfClass}

/** Icon Feat Improved Mental Toughness.png
  * Improved Mental Toughness 	Passive 	This feat increases the character maximum spell points by 10 at level 1, and 5 spell points for each additional level. Also increases your spell critical chance by 1%.
  * Stacks with Mental Toughness.
  *
  * @todo Need to add ability to cast X Level spells as requisite
  *       Mental Toughness, Ability to cast 3rd level spells
  *       Level 5: Cleric, Druid, Wizard
  *       Level 6: Favored Soul, Sorcerer
  *       Level 7: Artificer, Bard; Level 10: Paladin, Ranger
  * */
protected[feat] trait ImprovedMentalToughness extends FeatRequisiteImpl with Passive with RequiresAllOfFeat with RequiresAnyOfClass {
  self: Feat =>
  override def allOfFeats: Seq[Feat] = List(Feat.MentalToughness)

  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Artificer, 7),
      (CharacterClass.Bard, 7),
      (CharacterClass.Cleric, 5),
      (CharacterClass.Druid, 5),
      (CharacterClass.FavoredSoul, 6),
      (CharacterClass.Sorcerer, 6),
      (CharacterClass.Wizard, 5),
      (CharacterClass.Paladin, 10),
      (CharacterClass.Ranger, 10))
}
