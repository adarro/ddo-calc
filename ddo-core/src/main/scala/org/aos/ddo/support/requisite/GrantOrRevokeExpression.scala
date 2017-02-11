package org.aos.ddo.support.requisite

/**
  * Created by adarr on 1/30/2017.
  */
sealed trait GrantOrRevokeExpression {
  /**
    * Automatically granted this feat / skill / ability when this criteria is met
    */
  def grantedBy: Seq[Requisite] = IndexedSeq.apply()

  /**
    * When this criteria is met, this feat / skill / ability
    * can be purchased.
    */
  def requires: Seq[Requisite] = IndexedSeq.apply()

  /**
    * When this criteria is met,
    * this feat / skill / ability is no longer available.
    */
  def prohibits: Seq[Requisite] = IndexedSeq.apply()
}

trait GrantExpression extends GrantOrRevokeExpression with Grant {
  self: RequisiteExpression =>

  abstract override def grantedBy: Seq[Requisite] = super.grantedBy
}

/**
  *
  * A list of Feats / Skills etc which must *NOT* already be acquired to attain this.
  *
  */
trait ProhibitExpression extends Prohibit {
  self: RequisiteExpression =>
}

/**
  * This expression must be true
  */
trait RequiredExpression extends Require {
  self: RequisiteExpression =>
}
