package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.attribute.Attribute.Wisdom
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.model.item.weapon.WeaponClass
import org.aos.ddo.support.requisite._

import scala.collection.immutable

/**
  * Created by adarr on 4/6/2017.
  */
trait ImprovedMartialArts
    extends FeatRequisiteImpl
    with FreeFeat
    with RequiresAllOfFeat
    with ClassRequisiteImpl
    with RequiresAllOfClass
    with RequiresAttribute
    with ClassRestricted { self: EpicFeat =>
  override def allOfClass: immutable.Seq[(CharacterClass, Int)] =
    List((Monk, 12))

  override def requiresAttribute: Seq[(Attribute, Int)] = List((Wisdom, 32))

  override def allOfFeats: Seq[Feat] =
    List(GeneralFeat.ImprovedCritical(WeaponClass.Bludgeon))
}
