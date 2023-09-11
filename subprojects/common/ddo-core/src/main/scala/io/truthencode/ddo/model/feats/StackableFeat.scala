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
package io.truthencode.ddo.model.feats

/**
 * Used to determine if a given feat can be taken multiple times Feats that do not posses this trait
 * can only be taken once. Feats which have this trait but do not specify a value [Option.None] are
 * considered unlimited. i.e. Toughness can be taken as many times as you wish.
 */
trait StackableFeat {

  /**
   * Convenience method to determine if a value is defined.
   */
  lazy val isLimited: Boolean = maxCount.nonEmpty

  /**
   * Determines the maximum times this feat may be taken.
   */
  val maxCount: Option[Int] = None
}
