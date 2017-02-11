package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.RequiresAllOfClass

/** Icon Feat Greater Spell Focus.png
  * Greater Spell Focus 	Passive 	This feat makes it harder for enemies to resist the caster's spells of a particular school by adding +1 to the difficulty class of the spell. This stacks with Spell Focus.
  * *
  * Spell Focus for the same school
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  * */
trait GreaterSpellFocus extends Passive with RequiresAllOfClass {
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
