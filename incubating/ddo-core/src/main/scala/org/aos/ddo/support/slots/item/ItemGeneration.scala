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
package org.aos.ddo.support.slots.item

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Represents the type of slot
  *
  * Created by adarr on 5/5/2017.
  */
sealed trait ItemGeneration extends EnumEntry

object ItemGeneration extends Enum[ItemGeneration] {
  case object Craftable extends Craftable
  case object RandomLoot extends RandomLootGen
  case object NamedLoot extends NamedLoot
  override def values: immutable.IndexedSeq[ItemGeneration] = findValues
}

/**
  * Item created or upgraded via some method of crafting.
  *
  * This usually involves a device / workstation / altar by combinining a base item with various ingredients.
  */
trait Craftable extends ItemGeneration

trait RandomLootGen extends ItemGeneration

trait NamedLoot extends ItemGeneration
