package org.aos.ddo.support.requisite

/**
  * Created by adarr on 2/8/2017.
  */
trait DefaultRequirement {
  self: Requisite =>
  lazy val allOf: Seq[Requirement] = Seq.empty
  lazy val anyOf: Seq[Requirement] = Seq.empty
  lazy val noneOf: Seq[Requirement] = Seq.empty
}
