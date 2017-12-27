package org.aos.ddo.support.requisite

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

/**
  * Created by adarr on 4/11/2017.
  */
sealed trait RequirementOption extends EnumEntry {}

object RequirementOption extends Enum[RequirementOption] {

  /**
    * Automatically granted without the need to acquire additional combinations.
    *
    * @note minimum level still applies.
    *       This is used mainly for quickly determining if a given feat / skill etc are automatically granted
    *       upon character creation or reaching a level as opposed to explicitly needing to purchase.
    */
  case object AutoGrant extends RequirementOption

  /**
    * This Feat / Skill etc can be purchased but contains additional restrictions such as being a certain race AND a certain class.
    */
  case object SelectableWithRestriction extends RequirementOption

  /**
    * This generally applies to Bonus Feats which are generally tied to class or race.
    */
  case object SelectableAsBonus extends RequirementOption

  /**
    * Can be purchased / selected without excess restrictions.
    * Examples include the Toughness Feat.
    */
  case object Available extends RequirementOption

  override def values: immutable.IndexedSeq[RequirementOption] = findValues
}
