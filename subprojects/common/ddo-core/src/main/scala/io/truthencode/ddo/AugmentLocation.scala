/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AugmentLocation.scala
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
import io.truthencode.ddo.enumeration.{BitSupport, BitWise}

import scala.collection.immutable

/**
 * Enumerates the places filigrees and augments can be placed.
 */
sealed trait AugmentLocation extends EnumEntry with BitWise {

  override lazy val bitValue: Int = bitValues(this)
  private lazy val bitValues = AugmentLocation.valuesToIndex.map { x =>
    x._1 -> toBitMask(x._2)
  }

}

/**
 * Augments such as Diamonds, Topaz etc
 */
trait ChromaticAugmentLocation extends AugmentLocation

/**
 * Lunar / Solar Augments introduced in update 69
 */
trait CelestialAugmentLocation extends AugmentLocation

/**
 * Legacy Guild augment slots.
 */
trait GuildAugmentLocation extends AugmentLocation

/**
 * Can be slotted into a Sentient Item or Minor Artifact
 */
trait FiligreeLocation extends AugmentLocation

/**
 * Distinct values for location slots.
 */
object AugmentLocation extends Enum[AugmentLocation] with BitSupport {

  // Augments (Ruby / Sapphire etc)
  override type T = AugmentLocation
  override lazy val bitValues: Map[AugmentLocation, Int] = valuesToIndex.map { x =>
    val wl = x._1
    val v = x._2
    wl -> Math.pow(2.0, v).toInt
  }
  val values: immutable.IndexedSeq[AugmentLocation] = findValues

  /**
   * Typically Rubies which add additional damage types, spell power and / or bypass damage
   * reduction
   */
  case object RedAugmentSlot extends ChromaticAugmentLocation

  /**
   * Supports Red, Blue and augments that some named augments which specify a purple slot
   */
  case object PurpleAugmentSlot extends ChromaticAugmentLocation

  /**
   * Supports Sapphires
   */
  case object BlueAugmentSlot extends ChromaticAugmentLocation

  /**
   * Supports Yellow and Blue augments and some named augments which specify a green slot.
   */
  case object GreenAugmentSlot extends ChromaticAugmentLocation

  // Guild Augments

  /**
   * Supports Yellow Augments and Diamonds
   */
  case object YellowAugmentSlot extends ChromaticAugmentLocation

  /**
   * Supports Yellow and Red augments and some named augments which specify an orange slot
   */
  case object OrangeAugmentSlot extends ChromaticAugmentLocation

  /**
   * Supports Diamonds and any named augments which specify a colorless slot.
   * @note
   *   all augment slots support colorless augments.
   */
  case object ColorlessAugmentSlot extends ChromaticAugmentLocation

  /**
   * Can be slotted with a Lunar Augment
   */
  case object MoonAugmentSlot extends CelestialAugmentLocation

  /**
   * Can be slotted with a Solar Augment
   */
  case object SunAugmentSlot extends CelestialAugmentLocation

  /**
   * Supports Tiny Augments and can only be equipped by a character in a guild of a certain size.
   */
  case object TinyGuildAugmentSlot extends GuildAugmentLocation

  /**
   * Supports Small Augments and can only be equipped by a character in a guild of a certain size.
   */
  case object SmallGuildAugmentSlot extends GuildAugmentLocation

  /**
   * Supports Medium Augments and can only be equipped by a character in a guild of a certain size.
   */
  case object MediumGuildAugmentSlot extends GuildAugmentLocation

  /**
   * Supports Large Augments and can only be equipped by a character in a guild of a certain size.
   */
  case object LargeGuildAugmentSlot extends GuildAugmentLocation

  /**
   * Supports filigrees. The amount of slots is based on the level of XP a given sentient item has.
   */
  case object SentientItemSlot extends FiligreeLocation

  /**
   * Supports filigrees. The amount of slots is based on the level of XP a given minor artifact has.
   * @note
   *   Minor Artifacts have a lower maximum number of slots (currently between 1 and 3) possible.
   */
  case object MinorArtifactSlot extends FiligreeLocation
}
