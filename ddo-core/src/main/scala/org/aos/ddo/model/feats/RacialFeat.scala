package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite.{Inclusion, Requisite}

/**
  * Created by adarr on 2/14/2017.
  */
sealed trait RacialFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation
    with FeatMatcher { self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, RacialFeat] = {
    case x: RacialFeat => x
  }
  val matchFeatById: PartialFunction[String, RacialFeat] = {
    case x: String if RacialFeat.namesToValuesMap.contains(x) =>
      RacialFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

object RacialFeat extends Enum[RacialFeat] with FeatSearchPrefix {

  // Racial Feats
  case object HalfElfDilettanteMonk
      extends RacialFeat
      with HalfElfDilettanteMonk {
    override protected def nameSource: String =
      CharacterClass.Monk.entryName.toPascalCase
  }

  case object HalfElfDilettanteBarbarian
      extends RacialFeat
      with HalfElfDilettanteBarbarian {
    override protected def nameSource: String =
      CharacterClass.Barbarian.entryName.toPascalCase
  }

  case object HalfElfDilettanteBard
      extends RacialFeat
      with HalfElfDilettanteBard {
    override protected def nameSource: String =
      CharacterClass.Bard.entryName.toPascalCase
  }

  case object HalfElfDilettanteCleric
      extends RacialFeat
      with HalfElfDilettanteCleric {
    override protected def nameSource: String =
      CharacterClass.Cleric.entryName.toPascalCase
  }

  case object HalfElfDilettanteDruid
      extends RacialFeat
      with HalfElfDilettanteDruid {
    override protected def nameSource: String =
      CharacterClass.Druid.entryName.toPascalCase
  }

  case object HalfElfDilettanteFavoredSoul
      extends RacialFeat
      with HalfElfDilettanteFavoredSoul {
    override protected def nameSource: String =
      CharacterClass.FavoredSoul.entryName.splitByCase.toPascalCase
  }

  case object HalfElfDilettanteFighter
      extends RacialFeat
      with HalfElfDilettanteFighter {
    override protected def nameSource: String =
      CharacterClass.Fighter.entryName.toPascalCase
  }

  case object HalfElfDilettantePaladin
      extends RacialFeat
      with HalfElfDilettantePaladin {
    override protected def nameSource: String =
      CharacterClass.Paladin.entryName.toPascalCase
  }

  case object HalfElfDilettanteRanger
      extends RacialFeat
      with HalfElfDilettanteRanger {
    override protected def nameSource: String =
      CharacterClass.Ranger.entryName.toPascalCase
  }

  case object HalfElfDilettanteRogue
      extends RacialFeat
      with HalfElfDilettanteRogue {
    override protected def nameSource: String =
      CharacterClass.Rogue.entryName.toPascalCase
  }

  case object HalfElfDilettanteSorcerer
      extends RacialFeat
      with HalfElfDilettanteSorcerer {
    override protected def nameSource: String =
      CharacterClass.Sorcerer.entryName.toPascalCase
  }

  case object HalfElfDilettanteWarlock
      extends RacialFeat
      with HalfElfDilettanteWarlock {
    override protected def nameSource: String =
      CharacterClass.Warlock.entryName.toPascalCase
  }

  case object HalfElfDilettanteWizard
      extends RacialFeat
      with HalfElfDilettanteWizard {
    override protected def nameSource: String =
      CharacterClass.Wizard.entryName.toPascalCase
  }

  override def values: Seq[RacialFeat] = findValues
}
