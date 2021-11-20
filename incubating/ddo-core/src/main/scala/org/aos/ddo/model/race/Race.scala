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
package io.truthencode.ddo.model.race

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.model.attribute._
import io.truthencode.ddo.model.misc._
import io.truthencode.ddo.model.worlds.{HomeWorld, World}
import io.truthencode.ddo.support.SearchPrefix

import scala.collection.immutable.IndexedSeq

/**
  * Represents a DDO Race [http://ddowiki.com/page/Races]
  */
sealed trait Race extends EnumEntry with AttributeModifierInit {
  self: Availability with HomeWorld with AttributeModifier =>
}

trait EberronRace extends HomeWorld { self: Race =>
  override def world: World = World.Eberron
}

trait ForgottenRealmsRace extends HomeWorld { self: Race =>
  override def world: World = World.ForgottenRealms
}

object Race extends Enum[Race] with SearchPrefix {
  implicit class FamilyOps(r: Race) {
    def families: Seq[RaceFamily] = {
      for {
        family <- RaceFamily.values
        if family.includedRaces contains r
      } yield family
    }
  }

  case object Bladeforged
      extends Race
      with EberronRace
      with IconicFeature
      with DexterityModifier
      with ConstitutionModifier
      with WisdomModifier {
    override protected lazy val intModifierDexterity: Int = -2
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierWisdom: Int = -2
  }
  // Svirfneblin
  case object DeepGnome
      extends Race
      with ForgottenRealmsRace
      with IconicFeature
      with IntelligenceModifier
      with WisdomModifier
      with StrengthModifier
      with CharismaModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierWisdom: Int = 2
    override protected lazy val intModifierStrength: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object DragonBorn
      extends Race
      with EberronRace
      with VIPFeature
      with StrengthModifier
      with CharismaModifier
      with DexterityModifier {
    override protected lazy val intModifierDexterity: Int = -2
    override protected lazy val intModifierCharisma: Int = 2
    override protected lazy val intModifierStrength: Int = 2
  }

  case object DrowElf
      extends Race
      with EberronRace
      with FavorFeature
      with DexterityModifier
      with IntelligenceModifier
      with CharismaModifier
      with ConstitutionModifier {

    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierCharisma: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object Dwarf
      extends Race
      with EberronRace
      with FreeToPlayFeature
      with ConstitutionModifier
      with CharismaModifier {
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Elf
      extends Race
      with EberronRace
      with FreeToPlayFeature
      with DexterityModifier
      with ConstitutionModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object Gnome
      extends Race
      with EberronRace
      with PremiumFeature
      with IntelligenceModifier
      with StrengthModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object Halfling
      extends Race
      with EberronRace
      with FreeToPlayFeature
      with DexterityModifier
      with StrengthModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object HalfElf
      extends Race
      with EberronRace
      with PremiumFeature
      with DefaultAttributeModifier

  case object HalfOrc
      extends Race
      with EberronRace
      with PremiumFeature
      with StrengthModifier
      with IntelligenceModifier
      with CharismaModifier {
    override protected lazy val intModifierStrength: Int = 2
    override protected lazy val intModifierIntelligence: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Human
      extends Race
      with EberronRace
      with FreeToPlayFeature
      with DefaultAttributeModifier

  case object Morninglord
      extends Race
      with ForgottenRealmsRace
      with IconicFeature
      with IntelligenceModifier
      with ConstitutionModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object PurpleDragonKnight
      extends Race
      with ForgottenRealmsRace
      with IconicFeature
      with DefaultAttributeModifier

  case object Shadarkai
      extends Race
      with ForgottenRealmsRace
      with IconicFeature
      with DexterityModifier
      with CharismaModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Warforged
      extends Race
      with EberronRace
      with PremiumFeature
      with ConstitutionModifier
      with WisdomModifier
      with CharismaModifier {
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierWisdom: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  override def values: IndexedSeq[Race] = findValues

  /**
    * An optional delimiter such as a colon when overridden.
    * By default, this is set to Option.None
    *
    * @return The delimiter, if it exists.
    */
  override def delimiter: Option[String] = Some(":")

  override def searchPrefixSource: String = "Race"
}
