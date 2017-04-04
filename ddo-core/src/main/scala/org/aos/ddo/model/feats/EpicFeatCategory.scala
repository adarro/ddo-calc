package org.aos.ddo.model.feats

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Basic trait to categorize epic feats.
  * Currently used only for better readability and testing.
  */
sealed trait EpicFeatCategory extends EnumEntry

protected[feats] trait GeneralPassive extends EpicFeatCategory with Passive
protected[feats] trait RangedCombatPassive
    extends EpicFeatCategory
    with Passive
protected[feats] trait SpellFeats extends EpicFeatCategory with Active
protected[feats] trait SpellCastingPassive
    extends EpicFeatCategory
    with Passive
protected[feats] trait SpellCastingActive extends EpicFeatCategory with Active
protected[feats] trait EpicMetaMagic extends EpicFeatCategory with Passive
protected[feats] trait ClassRestricted extends EpicFeatCategory
protected[feats] trait FreePassive extends EpicFeatCategory with Passive

object EpicFeatCategory extends Enum[EpicFeatCategory] {
  case object GeneralPassive extends GeneralPassive
  case object RangedCombatPassive extends RangedCombatPassive
  case object SpellFeats extends SpellFeats
  case object SpellCastingPassive extends SpellCastingPassive
  case object SpellCastingActive extends SpellCastingActive
  case object MetaMagic extends EpicMetaMagic
  case object ClassRestricted extends ClassRestricted
  case object FreePassive extends FreePassive

  override def values: immutable.IndexedSeq[EpicFeatCategory] = findValues
}
