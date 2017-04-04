package org.aos.ddo.model.feats

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.support.requisite._

/** Icon Feat Improved Two Weapon Fighting.png
  * Improved Two Weapon Fighting
  * Passive
  *
  * This feat increases the chance to proc an off-hand attack by 20% (includes unarmed Monk), bringing the total chance to 60%.
  *
  * Two Weapon Fighting
  * Dexterity 17, Base Attack Bonus +6
  * */
trait ImprovedTwoWeaponFighting
    extends FeatRequisiteImpl
    with ClassRequisiteImpl
    with Passive
    with RequiresAllOfFeat
    with RequiresAttribute
    with RequiresBaB
    with GrantsToClass { self: GeneralFeat =>
  override def requiresBaB = 6

  override def allOfFeats: Seq[GeneralFeat] =
    List(GeneralFeat.TwoWeaponFighting)

  override def requiresAttribute: Seq[(Attribute, Int)] =
    List((Attribute.Dexterity, 17))

  override def grantToClass: Seq[(CharacterClass, Int)] = List((Ranger, 6))
}
