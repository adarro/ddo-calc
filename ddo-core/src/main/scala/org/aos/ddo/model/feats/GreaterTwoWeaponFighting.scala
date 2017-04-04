package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite._

/** Icon Feat Greater Two Weapon Fighting.png
  * Greater Two Weapon Fighting
  * Passive
  * Increases the chance to proc an off-hand attack by 20%, bringing the total chance to 80%.
  *
  * Improved Two Weapon Fighting
  * Dexterity 17, Base Attack Bonus +11
  * *
  */
trait GreaterTwoWeaponFighting
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAttribute
    with RequiresBaB
    with GrantsToClass { self: GeneralFeat =>
  override def requiresBaB = 11

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.ImprovedTwoWeaponFighting)

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 17))

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 11))
}
