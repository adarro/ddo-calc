package org.aos.ddo.support.requisite

import org.aos.ddo.model.feat.Feat
import org.aos.ddo.support.requisite.RequirementImplicits.featToReq

import scala.language.{implicitConversions, postfixOps}
import scala.languageFeature.higherKinds

/**
  * Created by adarr on 1/29/2017.
  */
sealed trait FeatRequisite {
  self: Requisite =>
  def anyOfFeats: Seq[Feat] // = IndexedSeq.apply()

  def allOfFeats: Seq[Feat] // = IndexedSeq.apply()

  def noneOfFeats: Seq[Feat] // = IndexedSeq.apply()
}

trait FeatRequisiteImpl extends MustContainImpl[Requirement] with FeatRequisite {
  self: Requisite with RequisiteType =>
  def anyOfFeats: Seq[Feat] = IndexedSeq.apply()

  def allOfFeats: Seq[Feat] = IndexedSeq.apply()

  def noneOfFeats: Seq[Feat] = IndexedSeq.apply()
}

object FeatRequisite {
  def stringToClass(classId: String*): Seq[Feat] = {
    for {cls <- classId
         cOpt <- Feat.withNameInsensitiveOption(cls)
    } yield cOpt
  }
}

trait FreeFeat extends FeatRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresAnyOfFeat extends FeatRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf ++ {
    anyOfFeats collect featToReq
  }
}

trait RequiresAllOfFeat extends FeatRequisite with RequiresAllOf[Requirement] with Requisite {

  abstract override def allOf: Seq[Requirement] = super.allOf ++ {
    allOfFeats collect featToReq
  }
}

trait RequiresNoneOfFeat extends FeatRequisite with RequiresNoneOf[Requirement] with Requisite {

  abstract override def noneOf: Seq[Requirement] = super.noneOf ++ {
    noneOfFeats collect featToReq
  }
}


