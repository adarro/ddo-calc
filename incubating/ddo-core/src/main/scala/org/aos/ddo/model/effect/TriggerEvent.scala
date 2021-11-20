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
package io.truthencode.ddo.model.effect

import enumeratum.EnumEntry
import io.truthencode.ddo.support.IndexedEnum

import scala.collection.immutable

/**
  * Determines when or how an effect occurs
  */
sealed trait TriggerEvent extends EnumEntry

/**
  * Empty Interface that denotes a given effect is "Always On"
  */
trait PassiveEvent extends TriggerEvent

/**
  * Event occurs based on some event
  */
trait ActiveEvent extends TriggerEvent

object TriggerEvent extends IndexedEnum[TriggerEvent] {
  @volatile
  override lazy val values: immutable.IndexedSeq[TriggerEvent] = findValues
  case object Passive extends PassiveEvent

  /**
    * Occurs on every attack
    */
  case object OnAttack extends ActiveEvent

  /**
    * Occurs on a specific range of attack rolls
    */
  case object OnAttackRoll extends ActiveEvent

  /**
    * Occurs when you are damaged (hit)
    */
  case object OnDamage extends ActiveEvent

  /**
    * Occurs when you are hit by a spell
    */
  case object OnSpellHit extends ActiveEvent

  /**
    * Occurs when you cast a spell
    */
  case object OnSpellCast extends ActiveEvent

  /**
    * Occurs when you are killed
    */
  case object OnDeath extends ActiveEvent

  /**
    * Occurs when you are incapacited
    *
    * Hit points fall below 0
    */
  case object OnIncapacitated extends ActiveEvent

  /**
    * Occurs upon waking from rest / shrine
    */
  case object OnRest extends ActiveEvent

  /**
    * Can be toggled on / off as desired
    */
  case object OnToggle extends ActiveEvent

  /**
    * Activated At Will / Button press such as Kick / Sunder and may have a cool-down
    */
  case object AtWill extends ActiveEvent
}
