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
package io.truthencode.ddo.support

import io.truthencode.ddo.model.effect.Damage
import io.truthencode.ddo.model.meta.PhysicalDamageType
import io.truthencode.ddo.model.meta.PhysicalDamageType.{Bludgeon, Pierce, Slash}
import io.truthencode.ddo.{DefaultValue, NoDefault}

/**
 * Created by adarr on 2/3/2017.
 */
sealed trait PhysicalDamage extends Damage with DefaultValue[PhysicalDamageType]

/**
 * Damage type resulting in blunt force such as clubs, staves or a partial component of spells such
 * as Ice Storm.
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
 * Damage type has no specified default. This is the case of Ranged / Thrown as the damage type
 * depends on the projectile. (I.e. thrown dagger = pierce while thrown hammer = bludgeon)
 */
trait Deferred extends PhysicalDamage with NoDefault[PhysicalDamageType]
