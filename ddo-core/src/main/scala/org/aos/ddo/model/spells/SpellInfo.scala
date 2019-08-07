package org.aos.ddo.model.spells

import java.time.Duration

import org.aos.ddo.model.misc.CoolDown
import org.aos.ddo.model.spells.component.ComponentList

/**
  * Encapsulates the cost, duration, range etc of a given spell
  */
trait SpellInfo extends CoolDown {
  // caster level needs to be applied at source site

  // spell failure TBD by existence of somatic / verbal / concentration / armor spell failure etc
  /**
    * Amount of time, usually in seconds that must elapse before a given spell or ability can be used again
    */
  val coolDown: Option[Duration]

  /**
    * Determines if this spell is subject to a Resistance check
    */
  val spellResistance: Boolean

  // TBD spell result
/**
  * List of applicable targets [[SpellTarget]]
 */
  val target : List[SpellTarget]

  /**
    * Available saving throws, if any
    */
  val savingThrow: List[SavingThrow]

  /**
    * Spell Point Cost
    * @return
    */
  def spellPoints: Int

  /**
    * Hit Point Cost
    * @return
    */
  def hitPoints: Option[Int] = None

  /**
    * List of required spell components
    * @return
    */
  def components: List[ComponentList]





}
