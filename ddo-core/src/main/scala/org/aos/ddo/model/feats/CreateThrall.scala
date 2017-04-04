package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
  * Official: Target enemy fights for you 60 seconds.
  *   An enemy succeeding on a Will save is instead confused and may indiscriminately attack friend or foe for 10 seconds.
  *   Does not affect bosses.
  * Display DC = 15 + Warlock Level + Cha Mod. Displayed DC not affected by Enchantment Focus items.
  * Rather quick casting time; no metamagic feats allowed. Affects also Undead.
  */
protected[feats] trait CreateThrall
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(CharacterClass, Int)] = Seq((Warlock, 15))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactGreatOldOne)
}
