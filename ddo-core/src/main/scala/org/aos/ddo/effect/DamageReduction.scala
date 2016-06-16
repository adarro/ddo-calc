package org.aos.ddo.effect

/**
  * Reduces damage from a given source by a some amount
  *
  */
/**
  * @author adarr
  *
  */
trait DamageReduction
/**
  * Indicates physical as opposed to magical or other damage type
  */
trait Physical

trait Slashing extends Physical

trait Piercing extends Physical

trait Bludgeoning extends Physical

trait UnTyped

trait Alignment
/**
  * Resists or inflicts all physical types of damage (Slash / Pierce / Bludgeon)
  */
trait FullPhysical extends Slashing with Piercing with Bludgeoning with FullMaterial

trait Good extends Alignment

trait Evil extends Alignment

trait Chaotic extends Alignment

trait Lawful extends Alignment
/**
  * Resists, deals or bypasses all alignments
  */
trait Aligned extends Good with Evil with Chaotic with Lawful

trait Adamantine

trait Byeshk

trait ColdIron
trait Crystal
trait Mithral
trait Silver
/**
  * Encompases all material used for damage.
  *
  * Internally used as convenience trait that is more consice / useful
  * for flagging "DR/-"
  */
trait FullMaterial extends Adamantine with Byeshk with ColdIron with Crystal with Mithral with Silver
trait Magic
trait Light
