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

  val spellResistance: Boolean

  // TBD spell result

  val target : List[SpellTarget]

  val savingThrow: List[SavingThrow]

  def spellPoints: Int

  def hitPoints: Option[Int] = None

  def components: List[ComponentList]





}
