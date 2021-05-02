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

/**
 * @todo Need to add Hitpoint level (Percent) for events triggered when below 50% health etc.
 */
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
    * Occurs when you activate a SLA (Spell like ability)
    */
  case object OnSpellLikeAbility extends ActiveEvent

  /**
    * Occurs when you play a song (Bard or Epic Destiny)
    */
  case object OnSong extends ActiveEvent

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
    * For lack of a better word, this is a rough translation of "Not Incapacitated" for effects that work unless you're
    * helpless / incapacitated such as Aura of Courage
    */
  case object OnHealthy extends ActiveEvent

  /**
    * Can be toggled on / off as desired
    */
  case object OnToggle extends ActiveEvent

  /**
    * Activated At Will / Button press such as Kick / Sunder and may have a cool-down
    */
  case object AtWill extends ActiveEvent

  /**
    * Activated while in a Tavern, generally only applies to Healing / Recovery such as the Goodberry spell or Broccoli
    */
  case object InTavern extends ActiveEvent

  /**
    * [[https://ddowiki.com/page/Melee_special_attack Special Melee and Tactical attacks]] such as Trip, Sunder, Stunning / Slicing blow
    */
  case object SpecialAttack extends ActiveEvent

    /**
     * Occurs to summon (pet / monster etc)
     */
  case object Summon extends ActiveEvent

    /**
     * Occurs when changing form such as Druid Wild Shape
     */
  case object ShapeChange extends ActiveEvent
}
