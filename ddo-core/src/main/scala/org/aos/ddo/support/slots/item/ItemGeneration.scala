package org.aos.ddo.support.slots.item

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Represents the type of slot
  *
  * Created by adarr on 5/5/2017.
  */
sealed trait ItemGeneration extends EnumEntry

object ItemGeneration extends Enum[ItemGeneration] {
  case object Craftable extends Craftable
  case object RandomLoot extends RandomLootGen
  case object NamedLoot extends NamedLoot
  override def values: immutable.IndexedSeq[ItemGeneration] = findValues
}

/**
  * Item created or upgraded via some method of crafting.
  *
  * This usually involves a device / workstation / altar by combinining a base item with various ingredients.
  */
trait Craftable extends ItemGeneration

trait RandomLootGen extends ItemGeneration

trait NamedLoot extends ItemGeneration
