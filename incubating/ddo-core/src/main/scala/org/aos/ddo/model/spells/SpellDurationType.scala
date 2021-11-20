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
package io.truthencode.ddo.model.spells

import java.time.Duration

/**
  * Represents the duration of a spell
  */
sealed trait SpellDurationType

/**
  * Effect occurs immediately with no lasted or repeated effect.
  */
trait Instant extends SpellDurationType

/**
  * Effect lasts until an external event such as resting at a shrine or quaffing a remove curse removes it.
  */
trait Permanent extends SpellDurationType

/**
  * Effect lasts until the specified time.
  */
trait Expires extends SpellDurationType {
  val duration:Duration
}

case class ExpiringDuration(duration: Duration) extends Expires
object SpellDurationType {
  def apply(duration:Duration): SpellDurationType = ExpiringDuration(duration)

}

