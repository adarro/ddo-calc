/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.model.effect

/**
 * Reduces damage from a given source by a some amount
 */
/**
 * @author
 *   adarr
 */
trait DamageReduction

/**
 * Indicates physical as opposed to magical or other damage type
 */
trait Physical {
  self: TypeCategory =>
}

trait Slashing extends Physical with Form

trait Piercing extends Physical with Form

trait Bludgeoning extends Physical with Form

trait UnTyped {
  self: TypeCategory =>
}

trait Alignment {
  self: TypeCategory =>
}

/**
 * Resists or inflicts all physical types of damage (Slash / Pierce / Bludgeon)
 */
trait FullPhysical extends Slashing with Piercing with Bludgeoning with FullMaterial

trait Good extends Alignment with AlignmentBased

trait Evil extends Alignment with AlignmentBased

trait Chaotic extends Alignment with AlignmentBased

trait Lawful extends Alignment with AlignmentBased

/**
 * Resists, deals or bypasses all alignments
 */
trait Aligned extends Good with Evil with Chaotic with Lawful

/**
 * Pertains to the material substance used for an item which may affect the damage type dealt, such
 * as a Silver Longsword dealing 'Silver' Damage which can harm a Vampire.
 */
trait Material extends MaterialBased {
  self: TypeCategory =>
}

/**
 * Bypasses some constructs, such as Golems
 */
trait Adamantine extends Material

/**
 * Material can generally be used to bypass Aberration DR
 */
trait Byeshk extends Material

/**
 * Some Outsiders such as Devils
 */
trait ColdIron extends Material

/**
 * Immune to Rust Monster / Ooze damage, but may be brittle
 */
trait Crystal extends Material
trait Mithral extends Material

/**
 * Bypasses some undead such as Vampires
 */
trait Silver extends Material

/**
 * Encompasses all material used for damage.
 *
 * Internally used as convenience trait that is more concise / useful for flagging "DR/-"
 */
trait FullMaterial
  extends Adamantine with Byeshk with ColdIron with Crystal with Mithral with Silver

trait Magic extends Other
trait Light extends Other

trait Energy extends Health

/**
 * Damage type suffered by Constructs
 */
trait Rust extends Health

trait Poison extends Health

/**
 * Infuses with Positive Energy. Heals the truly living and harms undead.
 */
trait Positive extends Health

/**
 * Infuses with Negative Energy. Heals the Undead, harms the truly living with no effect on living
 * constructs.
 */
trait Negative extends Health

/**
 * Infuses constructs / living constructs with Healing. No effect on the truly living or undead.
 */
trait Repair extends Health

/**
 * Bane damage is considered untyped and thus can not be bypassed by effects such as Damage
 * Reduction
 */
trait Bane extends UnTyped with Typeless

trait Sonic

trait Force

trait RustRepair

trait Acid extends Elemental

trait Fire extends Elemental

trait Cold extends Elemental

trait Electric extends Elemental

/**
 * Reduces damage from Acid, Fire, Cold and Electric
 */
trait ElementalResistance extends Resist with Acid with Fire with Cold with Electric
