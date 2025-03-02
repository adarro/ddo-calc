/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ActiveAbilities.scala
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
package io.truthencode.ddo.model.abilities

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.activation.OnToggleEvent
import io.truthencode.ddo.model.feats.DefensiveCombatStance
import io.truthencode.ddo.support.SearchPrefix
import io.truthencode.ddo.support.StringUtils.Extensions
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

/**
 * Represents an ability that must be granted and activated. This may be as simple as the Attack, a
 * toggle, Ability to Sing bard songs but also Cleave et al.
 */
sealed trait ActiveAbilities extends EnumEntry with DisplayName with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

trait Cleave extends ActiveAbilities

// scalastyle:off number.of.methods
object ActiveAbilities extends Enum[ActiveAbilities] with SearchPrefix {
  override lazy val values: IndexedSeq[ActiveAbilities] = findValues

  /**
   * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from qualified
   * "Race:HalfElf"
   *
   * @return
   *   A default or applied prefix
   */
  override def searchPrefixSource: String = "Ability"

  override def searchDelimiter: Option[String] = Some(":")

  case object Attack extends ActiveAbilities

  case object BardSongs extends ActiveAbilities

  case object TurnUndead extends ActiveAbilities

  case object Cleave extends Cleave

  case object GreatCleave extends Cleave

  case object SupremeCleave extends Cleave

  case object ExaltedCleave extends Cleave

  case object Trip extends ActiveAbilities

  case object ImprovedTrip extends ActiveAbilities

  case object Sunder extends ActiveAbilities

  case object ImprovedSunder extends ActiveAbilities

  case object Sap extends ActiveAbilities

  case object Hamstring extends ActiveAbilities

  case object ImprovedFeint extends ActiveAbilities

  case object SlicingBlow extends ActiveAbilities

  case object WhirlingSteelStrike extends ActiveAbilities

  case object WhirlwindAttack extends ActiveAbilities

  case object CombatExpertise extends ActiveAbilities

  case object Manyshot extends ActiveAbilities

  case object TenThousandStars extends ActiveAbilities

  case object PowerAttack extends ActiveAbilities

  case object ImprovedPreciseShot extends ActiveAbilities

  case object Resilience extends ActiveAbilities

  case object StunningFist extends ActiveAbilities

  case object StunningBlow extends ActiveAbilities

  case object SpringAttack extends ActiveAbilities

  case object DefensiveFighting
    extends ActiveAbilities with DefensiveCombatStance with OnToggleEvent {
    // Members declared in io. truthencode. ddo. activation. ActivationType
    override def activations: Seq[io.truthencode.ddo.model.effect.TriggerEvent] = super.activations
    // Members declared in io. truthencode. ddo. activation. Trigger
    override def activatableTriggers: Seq[io.truthencode.ddo.model.effect.TriggerEvent] =
      super.activatableTriggers
  }

  /**
   * Allows one to dismiss some charmed creatures.
   */
  case object DismissCharm extends ActiveAbilities

  case object Sneak extends ActiveAbilities

  case object Precision extends ActiveAbilities
}
// scalastyle:on
