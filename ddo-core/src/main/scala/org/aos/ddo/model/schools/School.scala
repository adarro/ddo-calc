package org.aos.ddo.model.schools

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.SearchPrefix
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.StringUtils.Extensions

/**
  * Represents one of the eight schools of magic.
  */
sealed trait School extends EnumEntry with DisplayName with FriendlyDisplay {
  /**
    * Sets or maps the source text for the DisplayName.
    *
    * @return Source text.
    */
  override protected def nameSource: String = entryName.splitByCase
}

object School extends Enum[School] with SearchPrefix {

  case object Abjuration extends School

  case object Conjuration extends School

  case object Divination extends School

  case object Enchantment extends School

  case object Evocation extends School

  case object Illusion extends School

  case object Necromancy extends School

  case object Transmutation extends School

  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    *
    * @return A default or applied prefix
    */
  override def searchPrefixSource: String = "School"

  override def values: Seq[School] = findValues
}
