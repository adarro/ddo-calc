package org.aos.ddo.support

import org.aos.ddo.PhysicalDamageType.{Bludgeon, Pierce, Slash}
import org.aos.ddo.{Damage, DefaultValue, NoDefault, PhysicalDamageType}

/**
  * Created by adarr on 2/3/2017.
  */
sealed trait PhysicalDamage extends Damage with DefaultValue[PhysicalDamageType]

/**
  * Damage type resulting in blunt force such as clubs, staves or a partial
  * component of spells such as Ice Storm.
  */
trait Bludgeoning extends PhysicalDamage {
  override lazy val default: Option[PhysicalDamageType] = Some(Bludgeon)
}

trait Slashing extends PhysicalDamage {
  override lazy val default: Option[PhysicalDamageType] = Some(Slash)
}

trait Piercing extends PhysicalDamage {
  override lazy val default: Option[PhysicalDamageType] = Some(Pierce)
}

/**
  * Damage type has no specified default. This is the case of Ranged / Thrown as the damage
  * type depends on the projectile.  (I.e. thrown dagger = pierce while thrown hammer = bludgeon)
  */
trait Deferred extends PhysicalDamage with NoDefault[PhysicalDamageType]
