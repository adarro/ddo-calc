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
package io.truthencode.ddo

import enumeratum.{Enum, EnumEntry}

/**
 * The 'What it does' half of binding status.
 */
sealed trait BindingStatus extends EnumEntry with DefaultValue[BindingStatus] {
  override lazy val default = Some(BindingStatus.Unbound)
}

/**
 * Enum for noting the distinct types of binding.
 *
 * Binding ties an object to a character, preventing them from selling, trading or transferring.
 */
object BindingStatus extends Enum[BindingStatus] with DefaultValue[BindingStatus] {

  /**
   * Item can be transferred between characters on the same account.
   *
   * Generally through the shared bank.
   */
  case object BindsToAccount extends BindingStatus

  /**
   * Item can not be traded or sold.
   */
  case object BindsToCharacter extends BindingStatus

  /**
   * Item does not have any restrictions.
   */
  case object Unbound extends BindingStatus
  val values = findValues // IndexedSeq(BindsToAccount, BindsToCharacter, Unbound)
  override lazy val default = Some(BindingStatus.Unbound)
}

/**
 * The 'When' any binding occurs
 */
sealed trait BindingEvent extends EnumEntry with NoDefault[BindingEvent]

/**
 * Triggers for binding.
 */
object BindingEvent extends Enum[BindingEvent] with DefaultValue[BindingEvent] {

  /**
   * Occurs when you receive the item, such as a quest reward or upon crafting.
   */
  case object OnAcquire extends BindingEvent

  /**
   * Occurs upon first use.
   */
  case object OnEquip extends BindingEvent

  /**
   * No binding occurs.
   */
  case object None extends BindingEvent
  val values = findValues
  override lazy val default = Some(BindingEvent.None)
}
