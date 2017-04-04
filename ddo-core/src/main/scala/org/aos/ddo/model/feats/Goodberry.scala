package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Druid
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
  * Transmutes natural matter around you into Goodberries.
  * Goodberries are infused with primal magic, and provide a full meal's nourishment. If eaten in a tavern, they act as tavern food and drink,
  * refilling your health and spell points. When cast, Goodberries appear in your inventory.
  * The higher the caster level, the more nourishing the Goodberries are.
  */
protected[feats] trait Goodberry
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAllOfClass {
  override def allOfClass: Seq[(CharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Druid, 1))

}
