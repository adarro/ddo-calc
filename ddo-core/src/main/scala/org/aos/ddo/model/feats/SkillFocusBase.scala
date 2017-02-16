package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/** Icon Feat Skill Focus.png
  * Skill Focus 	Passive 	Provides a +3 bonus to the chosen skill check roll. This feat can only be taken once per skill. Available Skills: Balance, Bluff, Concentration, Diplomacy, Disable Device, Haggle, Heal, Hide, Intimidate, Jump, Listen, Move Silently, Open Lock, Perform, Repair, Search, Spellcraft, Spot, Swim, Tumble, Use Magic Device.
  * *
  * None
  */
protected[feats] trait SkillFocusBase extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
