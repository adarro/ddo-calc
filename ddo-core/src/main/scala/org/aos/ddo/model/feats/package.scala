package org.aos.ddo.model

import scala.reflect.ClassTag

/**
  * Created by adarr on 2/15/2017.
  */
package object feats {
  /**
    * see [[http://stackoverflow.com/questions/41014979/elegant-way-to-chain-scala-partial-functions SO]]
    * @param pf Generic Partial Function to chain
    *
    * @tparam A Inbound type
    * @tparam B Outbound type
    */
  implicit class PartFuncOps[A: ClassTag, B](pf: PartialFunction[A, B]) {
    /**
      * Chains linked partial functions with an 'Or' operator
      * @param other next partial function to chain
      * @tparam D Derived inbound type
      * @tparam C derived oubound type
      * @return Partial Function of either A or C
      */
    def or[D >: A, C <: D : ClassTag](other: PartialFunction[C, B]): PartialFunction[D, B] = {
      case a: A if pf.isDefinedAt(a) => pf(a)
      case c: C if other.isDefinedAt(c) => other(c)
    }
  }
}
