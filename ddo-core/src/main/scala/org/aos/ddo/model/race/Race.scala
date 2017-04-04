package org.aos.ddo.model.race

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.attribute._
import org.aos.ddo.model.misc._
import org.aos.ddo.model.worlds.{HomeWorld, World}
import org.aos.ddo.support.SearchPrefix

import scala.collection.immutable.IndexedSeq

/**
  * Represents a DDO Race [http://ddowiki.com/page/Races]
  */
sealed trait Race extends EnumEntry {
  self: Availability with HomeWorld with AttributeModifier =>
}

trait EberronRace extends  HomeWorld { self: Race =>
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
      extends Race with EberronRace
      with AttributeModifierInit
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
      extends Race with ForgottenRealmsRace
      with AttributeModifierInit
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
      extends Race  with EberronRace
      with AttributeModifierInit
      with VIPFeature
      with StrengthModifier
      with CharismaModifier
      with DexterityModifier {
    override protected lazy val intModifierDexterity: Int = -2
    override protected lazy val intModifierCharisma: Int = 2
    override protected lazy val intModifierStrength: Int = 2
  }

  case object DrowElf
      extends Race with EberronRace
      with AttributeModifierInit
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
      extends Race with EberronRace
      with AttributeModifierInit
      with FreeToPlayFeature
      with ConstitutionModifier
      with CharismaModifier {
    override protected lazy val intModifierConstitution: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Elf
      extends Race with EberronRace
      with AttributeModifierInit
      with FreeToPlayFeature
      with DexterityModifier
      with ConstitutionModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object Gnome
      extends Race with EberronRace
      with AttributeModifierInit
      with PremiumFeature
      with IntelligenceModifier
      with StrengthModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object Halfling
      extends Race with EberronRace
      with AttributeModifierInit
      with FreeToPlayFeature
      with DexterityModifier
      with StrengthModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierStrength: Int = -2
  }

  case object HalfElf
      extends Race with EberronRace
      with AttributeModifierInit
      with PremiumFeature
      with DefaultAttributeModifier

  case object HalfOrc
      extends Race with EberronRace
      with AttributeModifierInit
      with PremiumFeature
      with StrengthModifier
      with IntelligenceModifier
      with CharismaModifier {
    override protected lazy val intModifierStrength: Int = 2
    override protected lazy val intModifierIntelligence: Int = -2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Human
      extends Race with EberronRace
      with AttributeModifierInit
      with FreeToPlayFeature
      with DefaultAttributeModifier

  case object Morninglord
      extends Race with ForgottenRealmsRace
      with AttributeModifierInit
      with IconicFeature
      with IntelligenceModifier
      with ConstitutionModifier {
    override protected lazy val intModifierIntelligence: Int = 2
    override protected lazy val intModifierConstitution: Int = -2
  }

  case object PurpleDragonKnight
      extends Race with ForgottenRealmsRace
      with AttributeModifierInit
      with IconicFeature
      with DefaultAttributeModifier

  case object Shadarkai
      extends Race with ForgottenRealmsRace
      with AttributeModifierInit
      with IconicFeature
      with DexterityModifier
      with CharismaModifier {
    override protected lazy val intModifierDexterity: Int = 2
    override protected lazy val intModifierCharisma: Int = -2
  }

  case object Warforged
      extends Race with EberronRace
      with AttributeModifierInit
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
