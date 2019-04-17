package org.aos.ddo.model.spells

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

sealed trait ComponentType extends EnumEntry

object ComponentType extends Enum[ComponentType] {
  case object VerbalComponent extends ComponentType
  case object SomaticComponent extends ComponentType
  case object MaterialComponent extends ComponentType
  override def values: immutable.IndexedSeq[ComponentType] = findValues
}