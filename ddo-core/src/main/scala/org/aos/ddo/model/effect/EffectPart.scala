package org.aos.ddo.model.effect

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.NoDefault
sealed trait EffectPart extends EnumEntry
object EffectPart extends Enum[EffectPart] with NoDefault[EffectPart] {
  val values = findValues
  case object Attribute extends EffectPart
  case object Skill extends EffectPart
  case object Health extends EffectPart
  case object Spellpoints extends EffectPart
  case object Feat extends EffectPart
  case object Spell extends EffectPart
}

/**
  *
  * {{{
  * val excCombatMastery5 = Eff(
  *     TriggerEvent.Passive,bonusType = Incite, Magnitude = 5,DifficultyCheck,List(Skill.Trip,Sunder,StunningBlow))
  * //Tendon Slice 10%
  * val tSlice = Eff(OnAttack,Enhancement,10%,???,Hamstring(slow enemy movement))
  * // +6 Enhancement Bonus
  * val eb6 = Eff(Passive,Enhancement,100/6,???Atk / Dmg Bonus)
  * }}}
  * Maiming: This weapon has a twisted haft or grip and spikes
  * along its blade, head, or point. Whenever you score a
  * critical hit with this weapon it deals an amount of
  *  extra untyped damage depending on its
  *  critical multiplier: x2 - 1 to 6, x3 - 2 to 12, x4 - 3 to 18.
  *  {{{
  *  val maiming = Eff(Critical,100,NA,100 / Multiplier(2->1d6,3->2d6,4->3d6),???,Melee/Ranged Attack)
  *  }}}
  */
case class Eff(
  // When this event activates
  trigger: TriggerEvent,
  // Incite / Feat / Competence used for stacking rules
  bonusType: AnyRef,
  // +5 or 4d3+1 etc
  magnitude: Magnitude,
  // DC
  difficultyCheck: Int,
  // target - skill or attribute or sub effect(s)?
  target: Any)

case class EffectFeature(parm: EffectParameter, part: EffectPart)
