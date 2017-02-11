package org.aos.ddo.support.requisite

/**
  * Flag used to denote inclusion logic
  */
sealed trait Inclusion

trait AllOf extends Inclusion

trait AnyOf extends Inclusion

trait NoneOf extends Inclusion

