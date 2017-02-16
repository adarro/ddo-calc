package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat, RequiresAnyOfClass}

/** Icon Feat Greater Spell Focus.png
  * Greater Spell Focus - Passive
  * This feat makes it harder for enemies to resist the caster's spells of a particular school by adding +1 to the difficulty class of the spell.
  * This stacks with Spell Focus.
  *
  * Spell Focus for the same school
  * Level 1: Artificer, Bard, Cleric, Druid, Favored Soul
  * Level 1: Sorcerer, Wizard; Level 4: Paladin, Ranger
  * */
trait GreaterSpellFocusBase extends FeatRequisiteImpl with Passive with RequiresAnyOfClass with RequiresAllOfFeat  {
  self: GeneralFeat =>


  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.SpellFocus)
}
