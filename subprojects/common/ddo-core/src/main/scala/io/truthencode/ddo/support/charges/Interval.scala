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
package io.truthencode.ddo.support.charges

import enumeratum.{Enum, EnumEntry}

import java.time.Duration
import scala.collection.immutable

/**
 * Represents the timespan or interval which triggers a charge to be recovered such as Rest or
 * Duration
 */
sealed trait Interval extends EnumEntry

trait ResetOnRest extends Interval

trait TimeDuration extends Interval {
  val duration: Duration
}

trait ResetOnDuration extends Interval

object Interval extends Enum[Interval] {
  override def values: immutable.IndexedSeq[Interval] = findValues

  /**
   * Replenished by Resting
   */
  case object PerRest extends ResetOnRest

  case class OnTimer(override val duration: Duration) extends TimeDuration

  /**
   * Replenished after time elapses
   */
  case object PerInterval extends ResetOnDuration
}
