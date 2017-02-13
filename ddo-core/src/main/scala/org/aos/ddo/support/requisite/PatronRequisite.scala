package org.aos.ddo.support.requisite
import org.aos.ddo.model.favor.FavorPatron
import org.aos.ddo.support.requisite.RequirementImplicits.patronToReq
/**
  * Created by adarr on 2/11/2017.
  */
trait PatronRequisite {

  def anyOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()

  def allOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()

  def noneOfPatron: Seq[(FavorPatron, Int)] = IndexedSeq.apply()
}


trait FreePatron extends PatronRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfPatron extends PatronRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfPatron collect patronToReq
  }
}

trait RequiresAllOfPatron extends PatronRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfPatron collect patronToReq
  }
}


trait RequiresNoneOfPatron extends PatronRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfPatron collect patronToReq
  }
}
