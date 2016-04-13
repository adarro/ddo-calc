package org.aos.ddo

import org.aos.ddo.PhysicalDamageType.{ Bludgeon, Pierce, Slash, Value }

trait Damage {
  val defaultDamageType: PhysicalDamageType.Value
}

trait PhysicalDamage extends Damage

/** Damage type resulting in blunt force such as clubs, staves or a partial
  * component of spells such as Ice Storm.
  */
trait Bludgeoning extends PhysicalDamage {
  override val defaultDamageType: Value = Bludgeon
}

trait Slashing extends PhysicalDamage {
  override val defaultDamageType: Value = Slash
}

trait Piercing extends PhysicalDamage {
  override val defaultDamageType: Value = Pierce
}
