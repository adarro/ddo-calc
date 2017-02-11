package org.aos.ddo.support.requisite

import org.aos.ddo.support.requisite.Requirement.BaseAttackBonus

import scala.language.{implicitConversions, postfixOps}


/**
  * Represents the Base Attack Bonus Requirement
  * Created by adarr on 2/3/2017.
  */
sealed trait BaBRequisite {
  self: Requisite =>
  /**
    * The Minimum Required Base Attack Bonus
    * @return Minimum value allowed
    */
  def requiresBaB: Int
}

trait FreeBaB extends BaBRequisite with RequiresNone with RequiredExpression with Requisite

trait RequiresBaB extends BaBRequisite with RequiresOneOf[Requirement] with Requisite {

  abstract override def oneOf: Seq[Requirement] = super.oneOf :+ BaseAttackBonus(requiresBaB)

//  abstract override def prerequisites: Seq[RequisiteExpression] = super.prerequisites :+ this

}


