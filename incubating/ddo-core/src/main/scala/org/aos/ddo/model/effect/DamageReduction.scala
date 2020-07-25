/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
  * Encompasses all material used for damage.
  *
  * Internally used as convenience trait that is more concise / useful
  * for flagging "DR/-"
  */
trait FullMaterial extends Adamantine with Byeshk with ColdIron with Crystal with Mithral with Silver
trait Magic
trait Light
