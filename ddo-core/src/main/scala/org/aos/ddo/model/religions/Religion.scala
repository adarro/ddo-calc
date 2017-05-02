package org.aos.ddo.model.religions

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.item.weapon.{FavoredWeapon, WeaponCategory}
import org.aos.ddo.model.worlds.{Eberron, ForgottenRealms, HomeWorld}
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable.IndexedSeq
import org.aos.ddo.support.StringUtils.Extensions

/**
  * Represents the Deities followed by player characters.
  */
sealed trait Religion extends EnumEntry with DisplayName with FriendlyDisplay {
  self: HomeWorld with FavoredWeapon =>
  def hasFavoredWeapon(fw:WeaponCategory): Boolean = fw == favoredWeapon
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

sealed trait EberronReligion extends Religion with Eberron {
  self: FavoredWeapon =>
}
sealed trait ForgottenRealmsReligion extends Religion with ForgottenRealms {
  self: FavoredWeapon =>
}

object Religion extends Enum[Religion] {
  // Eberron
  case object Aureon extends EberronReligion with Aureon
  case object BloodOfVol extends EberronReligion with BloodOfVol
  case object LordOfBlades extends EberronReligion with LordOfBlades
  case object Olladra extends EberronReligion with Olladra
  case object Onatar extends EberronReligion with Onatar
  case object SilverFlame extends EberronReligion with SilverFlame
  case object SovereignHost extends EberronReligion with SovereignHost
  case object
    UndyingCourt extends EberronReligion with UndyingCourt
  case object Vulkoor extends EberronReligion with Vulkoor
  // Forgotten Realms
  case object Amaunator extends ForgottenRealmsReligion with Amaunator
  case object Helm extends ForgottenRealmsReligion with Helm
  case object Silvanus extends ForgottenRealmsReligion with Silvanus

  override def values: IndexedSeq[Religion] = findValues
}
