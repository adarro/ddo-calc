package org.aos.ddo.model.feats.classes

import java.util

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.model.feats.{ClassDisplayHelper, Feat}

abstract class MonkJavaHelper extends ClassDisplayHelper{
  override val cClass: CharacterClass = Monk
  override val enum: E = Feat
  def allBonusFeats(): util.List[String] =  bonusFeats

}
