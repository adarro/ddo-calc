/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: TriggerEvent.scala
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

import java.util
import scala.jdk.CollectionConverters.*
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
 * Enumerates all valid trigger event typse
 */
// scalastyle:off number.of.methods
object TriggerEvent extends IndexedEnum[TriggerEvent] {
  @volatile
  override lazy val values: immutable.IndexedSeq[TriggerEvent] = findValues

  def asJava: util.List[TriggerEvent] = values.asJava
  case object Passive extends PassiveEvent, TriggerEvent

  /**
   * Used to denote that this never happens. Not useful for triggering ON events, but should be used
   * with Passive, always on effects.
   */
  case object Never extends PassiveEvent, TriggerEvent

  /**
   * Occurs on every attack
   */
  case object OnAttack extends ActiveEvent, TriggerEvent

  /**
   * Occurs on a specific range of attack rolls
   */
  case object OnAttackRoll extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are damaged (hit)
   */
  case object OnDamage extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are hit by a spell
   */
  case object OnSpellHit extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you cast a spell
   */
  case object OnSpellCast extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you activate a SLA (Spell like ability)
   */
  case object OnSpellLikeAbility extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you play a song (Bard or Epic Destiny)
   */
  case object OnSong extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are killed
   */
  case object OnDeath extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are incapacited
   *
   * Hit points fall below 0
   */
  case object OnIncapacitated extends ActiveEvent, TriggerEvent

  /**
   * Occurs upon waking from rest / shrine
   */
  case object OnRest extends ActiveEvent, TriggerEvent

  /**
   * For lack of a better word, this is a rough translation of "Not Incapacitated" for effects that
   * work unless you're helpless / incapacitated such as Aura of Courage, the opposite of
   * [[OnUnconscious]] trigger
   */
  case object OnHealthy extends ActiveEvent, TriggerEvent

  /**
   * Triggers when a certain percentage of health falls below the threshold
   */
  case object OnHealthLevelBelow extends ActiveEvent, TriggerEvent

  /**
   * Triggers on a ceratin percentage of health raises above the threshold
   */
  case object OnHealthLevelAbove extends ActiveEvent, TriggerEvent

  /**
   * Can be toggled on / off as desired
   */
  case object OnToggle extends ActiveEvent, TriggerEvent

  /**
   * Activated At Will / Button press such as Kick / Sunder and may have a cool-down
   */
  case object AtWill extends ActiveEvent, TriggerEvent

  /**
   * Activates or deactivates on a cooldown
   */
  case object OnCoolDown extends ActiveEvent, TriggerEvent

  /**
   * Activated while in a Tavern, generally only applies to Healing / Recovery such as the Goodberry
   * spell or Broccoli
   */
  case object InTavern extends ActiveEvent, TriggerEvent

  /**
   * [[https://ddowiki.com/page/Melee_special_attack Special Melee and Tactical attacks]] such as
   * Trip, Sunder, Stunning / Slicing blow
   */
  case object SpecialAttack extends ActiveEvent, TriggerEvent

  /**
   * Occurs to summon (pet / monster etc)
   */
  case object Summon extends ActiveEvent, TriggerEvent

  /**
   * Occurs when changing form such as Druid Wild Shape
   */
  case object ShapeChange extends ActiveEvent, TriggerEvent

  /**
   * Occurs when equipping an item
   */
  case object OnEquip extends ActiveEvent, TriggerEvent

  /**
   * Occurs when un-equipping an item
   */
  case object OnUnEquip extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are centered.
   */
  case object OnCentered extends ActiveEvent, TriggerEvent

  /**
   * Occurs when you are no longer / not centered.
   */
  case object OnOffCentered extends ActiveEvent, TriggerEvent

  /**
   * Activated Stance Events (Implies Toggle off / on)
   */
  case object OnStance extends ActiveEvent, TriggerEvent

  /**
   * Triggers when rendered unconscious
   */
  case object OnUnconscious extends ActiveEvent, TriggerEvent

  /**
   * Activates / Cycles based on some timer. i.e. Deflect Arrows
   */
  case object OnTimer extends ActiveEvent, TriggerEvent

  /**
   * Takes effect when you Tumble
   */
  case object OnTumble extends ActiveEvent, TriggerEvent

  /**
   * Special End event. Denotes the effect ends at the end of whatever the TriggerOn event was. i.e.
   * End of Tumble for the Mobility Feat
   * @note
   *   need a better name for this
   */
  case object WhileOn extends ActiveEvent, TriggerEvent

  /**
   * Activates when you level up This will be a special case such as applying increased Ability
   * points
   */
  case object OnLevelUp extends TriggerEvent
}
// scalastyle:on
