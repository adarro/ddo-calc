package org.aos.ddo.support.requisite

/**
  * Created by adarr on 1/29/2017.
  */
trait Requisite

object Requisite {


  implicit class RequisiteComputations(source: Requisite) {

    /*  def explode(req: List[Requisite]): List[Requisite] = {
        val inners: List[Requisite] = for {r <- req
                                           pr <- r.prerequisites}
          yield pr
        req ++ explode(inners)
      } */

    def explodeGranted(): List[Requisite] = ???

    def isSatisfiedBy(other: Requisite): Result = ???
  }

  def add(other: RequisiteExpression): RequisiteExpression = ???
}







