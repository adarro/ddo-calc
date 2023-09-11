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

import enumeratum.EnumEntry
import io.truthencode.ddo.NoDefault
import io.truthencode.ddo.model.meta.{MagicDamageType, PhysicalDamageType}

/**
 * Base trait used to flag damage for purposes of dealing / preventing / augmenting.
 */
trait Damage

object Damage {
  def damageTypes: Seq[EnumEntry
    with Damage with NoDefault[
      _ >: PhysicalDamageType with MagicDamageType <: EnumEntry with Damage with NoDefault[
        _ >: PhysicalDamageType with MagicDamageType]]] = {
    MagicDamageType.values ++ PhysicalDamageType.values
  }
}

trait DamageType extends Damage

sealed trait PhysicalDamage extends DamageType

trait TypedPhysicalDamage extends DamageType {
  self: Physical =>
}

trait UntypedPhysicalDamage extends DamageType with UnTyped with Typeless

trait MagicalDamage extends DamageType with Magic

trait UntypedMagicalDamage extends MagicalDamage with UnTyped

/**
 * Bane Damage bypasses Damage Reduction
 */
trait BaneDamage extends DamageType
