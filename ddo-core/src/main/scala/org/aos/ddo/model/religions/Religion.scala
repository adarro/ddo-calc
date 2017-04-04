package org.aos.ddo.model.religions

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.worlds.{Eberron, ForgottenRealms, HomeWorld}

import scala.collection.immutable.IndexedSeq

/**
  * Represents the Deities followed by player characters.
  */
sealed trait Religion extends EnumEntry { self: HomeWorld =>
}

sealed trait EberronReligion extends Religion with Eberron
sealed trait ForgottenRealmsReligion extends Religion with ForgottenRealms

object Religion extends Enum[Religion] {
  // Eberron
  case object Aureon extends Religion with Eberron
  case object BloodOfVol extends EberronReligion
  case object LordOfBlades extends EberronReligion
  case object Olladra extends EberronReligion
  case object Onatar extends EberronReligion
  case object SilverFlame extends EberronReligion
  case object SovereignHost extends EberronReligion
  case object UndyingCourt extends EberronReligion
  case object Vulkoor extends EberronReligion
  // Forgotten Realms
  case object Amaunator extends ForgottenRealmsReligion
  case object Helm extends ForgottenRealmsReligion
  case object Silvanus extends ForgottenRealmsReligion

  override def values: IndexedSeq[Religion] = findValues
}
