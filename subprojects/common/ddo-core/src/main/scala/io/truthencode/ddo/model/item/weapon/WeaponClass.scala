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
package io.truthencode.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.naming.DisplayName
import io.truthencode.ddo.support.{Deferred, PhysicalDamage}

import scala.collection.immutable.IndexedSeq

/**
 * This trait is used to map weapons for purposes such as determining weapon specialization and
 * should correspond directly to such Feats.
 */
sealed trait WeaponClass extends EnumEntry with DisplayName {
  self: DefaultDeliveryMethod with PhysicalDamage =>

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  override protected def nameSource: String = entryName
}

object WeaponClass extends Enum[WeaponClass] {

  override def values: IndexedSeq[WeaponClass] = findValues

  case object Bludgeon extends WeaponClassBludgeoning

  case object Piercing extends WeaponClassPiercing

  case object Slashing extends WeaponClassSlashing

  case object Ranged extends WeaponClassRanged

  case object Thrown extends WeaponClassThrown
}

sealed trait WeaponClassBludgeoning
  extends WeaponClass with MeleeDamage with io.truthencode.ddo.support.Bludgeoning

sealed trait WeaponClassPiercing
  extends WeaponClass with MeleeDamage with io.truthencode.ddo.support.Piercing

sealed trait WeaponClassSlashing
  extends WeaponClass with MeleeDamage with io.truthencode.ddo.support.Slashing

sealed trait WeaponClassRanged extends WeaponClass with RangeDamage with Deferred

sealed trait WeaponClassThrown extends WeaponClass with ThrownDamage with Deferred

/* object WeaponClass extends Enum[WeaponClass] {
  case object Bludgeoning extends WeaponClass with MeleeDamage with Bludgeoning
  case object  Piercing extends WeaponClass with MeleeDamage with Piercing
  case class  Ranged () extends WeaponClass with RangeDamage with PhysicalDamage
  case object Slashing extends WeaponClass with MeleeDamage with Slashing
  case object  Thrown extends WeaponClass with RangeDamage with
  override def values: Seq[WeaponClass] = findValues
} */
