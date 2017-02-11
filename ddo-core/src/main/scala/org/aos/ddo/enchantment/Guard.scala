package org.aos.ddo.enchantment

import com.wix.accord._
import com.wix.accord.dsl._
import com.wix.accord.transform.ValidationTransform.TransformedValidator
import org.aos.ddo.enchantment.Modifier._
import org.aos.ddo.model.effect.{Passive, _}
import org.aos.ddo.support.Validation._

trait GuardFlag {
  val guard: Guards
}

object Guard extends ((Guards, Option[GuardModifier]) => Guard) {
  implicit val guardValidator: TransformedValidator[Guard] = validator[Guard] { g => {

    g.effects is notEmpty

  }
  }
  type Parameters = (Guards, Option[GuardModifier])

  def apply(guard: Guards, affixes: Option[GuardModifier] = None): Guard = {
    val o = create(guard, affixes)
    validate(o) match {
      case Success => o
      case Failure(x) => throw new IllegalArgumentException(violationToString(x))
    }
  }

  def apply(parameters: Parameters): Guard =
    Guard(parameters._1, parameters._2)

  private def create(guard: Guards, affixes: Option[GuardModifier]): Guard = {
    new Guard(guard, affixes) {
      private def readResolve(): Object =
        Guard(guard, affixes)

      def copy(guard: Guards, affixes: Option[GuardModifier]): Guard =
        Guard(guard, affixes)

      val tuple: Guard.Parameters =
        (guard, affixes)
    }
  }

  def modifier(affixes: Option[GuardModifier]): Int = {
    affixes match {
      case Some(afx) =>
        Modifier.withNameInsensitiveOption(afx.prefix.getOrElse("")) match {
          case Some(Minor) => 1
          case Some(Lesser) => 2
          case Some(Greater) => 3
          case Some(Superior) => 4
          case Some(Epic) => 5
          case _ => 0
        }
      case _ => 0
    }
  }


}

abstract case class Guard private[Guard](
                                          override val guard: Guards, affixes: Option[GuardModifier]) extends Enchantment with GuardFlag with Passive {
  def copy(guard: Guards, affixes: Option[GuardModifier]): Guard

  val effects: List[Effect] = Nil

}
