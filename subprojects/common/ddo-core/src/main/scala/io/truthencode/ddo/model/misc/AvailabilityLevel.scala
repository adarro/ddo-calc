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
package io.truthencode.ddo.model.misc

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.Abbreviation

import scala.collection.immutable.IndexedSeq

/**
 * Created by adarr on 1/28/2017.
 */
sealed trait AvailabilityLevel extends EnumEntry

object AvailabilityLevel extends Enum[AvailabilityLevel] {
  override def values: IndexedSeq[AvailabilityLevel] = findValues

  case object FreeToPlay extends AvailabilityLevel with Abbreviation {

    /**
     * The short form of the word
     */
    override val abbr: String = "F2P"

    /**
     * Expands the abbr to its full value
     */
    override def toFullWord: String = this.toString
  }

  case object Favor extends AvailabilityLevel

  case object Premium extends AvailabilityLevel

  case object VIP extends AvailabilityLevel

  case object Iconic extends AvailabilityLevel
}
