package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Monk, Ranger, Rogue}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * You can avoid even magical and unusual attacks with great agility.
  * When you attempt a Reflex save against an effect that normally does half damage when saved against,
  *     you suffer no damage if you successfully save and half damage even if you fail.
  * Improved Evasion can be used only if the character is wearing light armor or no armor,
  *     is not wielding a heavy shield or tower shield, and is not heavily encumbered.
  * A helpless character does not gain the benefit of improved evasion.
  */
trait ImprovedEvasion
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass with RogueOptionalAbility
    with FreeFeat {
  override def grantToClass: Seq[(CharacterClass, Int)] = rogueOptionMatrix :+ (Monk,9)


}
