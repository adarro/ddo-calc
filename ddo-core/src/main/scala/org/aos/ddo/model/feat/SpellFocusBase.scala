package org.aos.ddo.model.feat

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/** Icon Feat Spell Focus.png
  * Spell Focus - Passive
  * This feat makes it harder for enemies to resist the caster's spells of a particular school by adding +1 to the difficulty class of the spell.
  *
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  *
  * @todo create spell focus:X feats
  **/
protected[feat] trait SpellFocusBase extends FeatRequisiteImpl with Passive with RequiresAnyOfClass  {
  self: Feat =>
  override def allOfClass: Seq[(CharacterClass, Int)] =
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
