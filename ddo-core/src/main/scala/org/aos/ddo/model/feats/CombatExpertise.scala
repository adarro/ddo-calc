package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAttribute}

/**
  * Cooldown: 30 seconds
  * Usage: Active, Toggled Stance
  * Prerequisite: Intelligence 13
  * Description
  * Defensive Combat Stance: While using Combat Expertise mode, you suffer -5 to your attack rolls but gain +10% feat bonus to Armor Class.
  * Spells have three times their normal cooldown when this mode is active.
  * Combat Expertise dispels and wards against all Rage effects.
  *
  * @todo Mixin DefensiveCombatStance
  */
protected[feats] trait CombatExpertise extends FeatRequisiteImpl with Active with RequiresAttribute {
  self: GeneralFeat =>
  override val requiresAttribute = List((Attribute.Intelligence, 13))
}
