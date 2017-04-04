package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.requisite.GrantsToClass

/**
  * Created by adarr on 3/19/2017.
  */
trait EnergyResistanceFire extends EnergyResistance with FriendlyDisplay with GrantsToClass {
  self: DisplayName =>
  private def fvsMap = (5 to 15 by 5).map((CharacterClass.FavoredSoul,_))

  override def grantToClass: Seq[(CharacterClass, Int)] = fvsMap

  /**
    * @inheritdoc
    */
  override protected def nameSource: String = "Fire"
}
