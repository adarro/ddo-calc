package org.aos.ddo.model.effect

/**
  * Basic flag for Elemental type damage.
  *
  * @note this only indicates there is some type of elemental damage / resist
  *       use [[org.aos.ddo.model.effect.ElementalResistance]] which extends this to indicate
  *       resist for all elemental types.
  */
trait Elemental

trait Acid extends Elemental

trait Fire extends Elemental

trait Cold extends Elemental

trait Electric extends Elemental

trait Sonic

trait Force
/**
  * Reduces damage from Acid, Fire, Cold and Electric
  */
trait ElementalResistance extends Resist with Acid with Fire with Cold with Electric
