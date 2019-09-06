package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{Inclusion, Requisite, SelectableToClass}

trait FighterBonusFeat extends SelectableToClass with BonusSelectableFeatImpl {
  self: Feat with FeatType with Requisite with Inclusion =>
  private[this] val myCharClass: CharacterClass = CharacterClass.Fighter
  override val levels: Set[Int] = Set(1) ++ (2 to 20 by 2).toSet
  abstract override def bonusCharacterClass: Seq[CharacterClass] = super.bonusCharacterClass :+ myCharClass


  abstract override def bonusSelectableToClass: Seq[(CharacterClass, Int)] = {

    val cc: Set[(CharacterClass, Int)] = for {l <- levels} yield (myCharClass, l)
    super.bonusSelectableToClass ++ cc.toSeq

  }
}
