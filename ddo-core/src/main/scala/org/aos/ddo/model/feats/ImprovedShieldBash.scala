package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/** Icon Feat Improved Shield Bash.png
  * Improved Shield Bash 	Passive 	Enables the character to retain the shield bonus to its Armor Class when using Shield Bash, and grants a 20% chance to make a secondary Shield Bash while attacking with a melee weapon.
  * *
  * Shield Proficiency: General
  * */
protected[feats] trait ImprovedShieldBash extends FeatRequisiteImpl with Passive with RequiresAllOfFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.ShieldProficiency)
}
