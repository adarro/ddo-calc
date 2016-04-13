package org.aos.ddo.weapon

import org.aos.ddo.{ Enum, NoDefault }
/** Trait to constrain Proficiency values
  */
sealed trait ProficiencyClass extends ProficiencyClass.Value with NoDefault[ProficiencyClass.Value]
/** Enumerates the distinct weapon proficiencies.
  */
object ProficiencyClass extends Enum[ProficiencyClass] {
  /** Longbows, Longswords, Scimitars etc
    */
  case object Martial extends ProficiencyClass
  /** Bastard swords, dwarven axes, kopeshes, great crossbows etc
    */
  case object Exotic extends ProficiencyClass
  /** simple daggers and other basic weapons
    */
  case object Simple extends ProficiencyClass
  override val values = List(Simple, Martial, Exotic)
}
