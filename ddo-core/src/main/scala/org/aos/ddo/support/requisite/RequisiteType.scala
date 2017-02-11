package org.aos.ddo.support.requisite

/**
  * Flag used to determine whether a given expression is granting, requiring or prohibiting.
  */
sealed trait RequisiteType

trait Grant extends RequisiteType

trait Prohibit extends RequisiteType

trait Require extends RequisiteType