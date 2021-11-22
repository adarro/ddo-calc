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

import scala.collection.immutable

/**
 * The Amount at with a charge is recovered. Values range from
 * [[io.truthencode.ddo.support.charges.RechargeAmount.None]] to
 * [[io.truthencode.ddo.support.charges.RechargeAmount.Incremental]] to
 * [[io.truthencode.ddo.support.charges.RechargeAmount.Full]]
 */
sealed trait RechargeAmount extends EnumEntry

object RechargeAmount extends Enum[RechargeAmount] {
  override def values: immutable.IndexedSeq[RechargeAmount] = findValues

  /**
   * Restores this amount of charges
   * @param quantity
   *   the amount of charges restored, up to Max
   */
  case class Incremental(quantity: Int) extends RechargeAmount

  /**
   * All charges are restored after specified event. Generally Onrest
   */
  case object Full extends RechargeAmount

  /**
   * An object with Charges that can not be recharged, such as some wands.
   */
  case object None extends RechargeAmount
}
