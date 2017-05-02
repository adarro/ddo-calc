package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass._
import org.aos.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  RequiresAllOfFeat,
  RequiresAnyOfClass
}

/**
  *  Usage: Passive
  *  Prerequisite: Greater Spell Focus feat of the same spell school and Lv 11 Cleric/Druid/Wizard,
  *    or Lv 12 Sorcerer/Favored Soul, or Lv 15 Artificer, or Lv 16 Bard
  * */
trait EpicSpellFocusBase
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with SpellCastingPassive
    with RequiresAnyOfClass
    with RequiresAllOfFeat { self: EpicFeat =>
  private val lvl11 = List(Cleric, Druid, Wizard).map((_, 11))
  private val lvl12 = List(Sorcerer, FavoredSoul).map((_, 12))
  override def anyOfClass
    : Seq[(CharacterClass with Product with Serializable, Int)] =
    lvl11 ++ lvl12 :+ (Artificer, 15) :+ (Bard, 16)

  override def allOfFeats: Seq[GeneralFeat] = List(GeneralFeat.SpellFocus)
}
