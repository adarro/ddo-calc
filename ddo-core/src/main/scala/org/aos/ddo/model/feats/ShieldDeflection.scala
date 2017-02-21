package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfFeat, RequiresBaB}

/**
  * Icon Feat Improved Shield Bash.png
  * Shield Deflection
  * Passive
  * When actively blocking with any type of Shield you are proficient with, you gain a Competence bonus based on the type of shield to completly ignore Acid, Cold, Electic and Fire damage.
  *
  * Buckler: 20%, Small Shield: 25%, Large Shield 30%, Tower Shield: 40%
  * *
  *
  * Shield Proficiency: General or
  * Tower Shield Proficiency
  * +8 Base Attack Bonus
  */
protected[feats] trait ShieldDeflection extends FeatRequisiteImpl with Passive with RequiresBaB with RequiresAnyOfFeat {
  self: GeneralFeat =>
  override def requiresBaB = 8

  override def anyOfFeats: Seq[GeneralFeat] = List(GeneralFeat.ShieldProficiency, GeneralFeat.TowerShieldProficiency)
}