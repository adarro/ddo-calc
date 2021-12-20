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
package io.truthencode.ddo.model.spells

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
 * Most spells that affect an area have a particular shape, such as a cone, line, or sphere.
 */
sealed trait SpellArea extends EnumEntry

object SpellArea extends Enum[SpellArea] {

  override def values: immutable.IndexedSeq[SpellArea] = findValues

  /**
   * A cone-shaped spell shoots away from you in a quarter-circle centered on your target. Listed as
   * "Conal AOE" in spell descriptions.
   */
  case object Cone extends SpellArea

  /**
   * A line-shaped spell shoots away from you in a line in the direction you designate. A
   * line-shaped spell affects all valid targets that the line passes through.
   */
  case object Line extends SpellArea

  /**
   * A sphere-shaped spell expands from its point of origin to fill a spherical area. Listed as
   * "Spherical AOE" in spell descriptions.
   */
  case object Sphere extends SpellArea

  /**
   * A circular-shaped spell expands from its point of origin to effect a full 360 degree circle.
   * These spells generally either have no Z-axis (height) limitations, or have very large ones.
   */
  case object Circular extends SpellArea
}
