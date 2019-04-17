package org.aos.ddo.model.spells

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

sealed trait SavingThrowResult extends EnumEntry

object SavingThrowResult extends Enum[SavingThrowResult] {

  override def values: immutable.IndexedSeq[SavingThrowResult] = findValues

  /**
    * No Saving throw is allowed.
    */
  case object None extends SavingThrowResult

  /**
    * On successful save, some partial effect or percentage is avoided.
    */
  case object Partial extends SavingThrowResult

  /**
    * On successful save, half damage or duration is received.
    */
  case object Half extends SavingThrowResult

  /**
    * On successful save, No damage or ill effects occur.
    */
  case object Negates extends SavingThrowResult


}