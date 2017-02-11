package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/** Icon Feat Spell Penetration.png
  * Spell Penetration (feat) 	Passive 	Adds +2 to the caster level check for defeating a foe's spell resistance.
  * *
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  * */
trait SpellPenetration extends FeatRequisiteImpl with Passive with RequiresAnyOfClass {
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
