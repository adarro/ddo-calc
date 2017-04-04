package org.aos.ddo.support.requisite

import org.aos.ddo.support.requisite.Requirement.ReqCharacterLevel

/**
  * Represents the character level required to attain a feat, skill etc.
  */
trait LevelRequisite { self: Requisite =>
  val characterLevel: Int
}

trait RequiresCharacterLevel
    extends LevelRequisite
    with RequiresOneOf[Requirement]
    with Requisite {

  abstract override def oneOf: Seq[Requirement] =
    super.oneOf :+ ReqCharacterLevel(characterLevel)
}
