/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

/** The 'What it does' half of binding status.
  */
sealed trait BindingStatus extends BindingStatus.Value with DefaultValue[BindingStatus.Value] {
  override lazy val defaultValue = Some(BindingStatus.Unbound)
}
/** Enum for noting the distinct types of binding.
  *
  * Binding ties an object to a character, preventing them from selling, trading or transferring.
  */
object BindingStatus extends Enum[BindingStatus] {
  /** Item can be transfered between characters on the same account.
    *
    * Generally through the shared bank.
    */
  case object BindsToAccount extends BindingStatus
  /** Item can not be traded or sold.
    */
  case object BindsToCharacter extends BindingStatus
  /** Item does not have any restrictions.
    */
  case object Unbound extends BindingStatus
  override val values = List(BindsToAccount, BindsToCharacter, Unbound)
  override val defaultValue = Some(BindingStatus.Unbound)
}

/** The 'When' any binding occurs
  */
sealed trait BindingEvent extends BindingEvent.Value with NoDefault[BindingEvent.Value]
/** Triggers for binding.
  */
object BindingEvent extends Enum[BindingEvent] {
  /** Occurs when you receive the item, such as a quest reward or upon crafting.
    */
  case object OnAcquire extends BindingEvent
  /** Occurs upon first use.
    */
  case object OnEquip extends BindingEvent
  /** No binding occurs.
    */
  case object None extends BindingEvent
  override val values = List(OnAcquire, OnEquip, None)
  override val defaultValue = Some(BindingEvent.None)
}
