package org.aos.ddo.model.alignment

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.NoDefault
import org.aos.ddo.enumeration._

import scala.language.implicitConversions

/**
  * Created by adarr on 8/12/2016.
  */
sealed trait AlignmentType

// extends BitWise

sealed trait LawAxis
    extends EnumEntry
    with AlignmentType
    with NoDefault[LawAxis] {

  //  implicit val v = LawAxis
  protected val c = LawAxis

  // SuperEnum.companion[LawAxis]
  /* implicit def enumToSpecific(x: Enum[_ <: EnumEntry]): LawAxis = {
     x.asInstanceOf[LawAxis]
   }*/

  // def eToS[A,B](implicit A : LawAxis,B:Enum[_]) = LawAxis
  // def sToE[B,A](implicit A : LawAxis,B:Enum[_]) = B.asInstanceOf[A]

  // def makeWork[E] = implicitly[LawAxis]

  //  override type E = LawAxis.type
  // C <: Enum[_ <: enumeratum.EnumEntry]
  //  override val companion = LawAxis

  lazy val bitValue: Int = {
    c.bitValues
      .filter { x =>
        c.checkVal(x._1)
      }
      .values
      .headOption
      .getOrElse(0)

  }
}

object LawAxis extends Enum[LawAxis] with BitSupport {
  /*  implicit def comp = new SuperEnum[LawAxis] {
    type C = LawAxis.type

    def apply(): LawAxis.type = LawAxis
  }*/

  case object Chaotic extends LawAxis

  case object Neutral extends LawAxis

  case object Lawful extends LawAxis

  val values = findValues
  type T = LawAxis
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }
}

sealed trait MoralAxis
    extends EnumEntry
    with AlignmentType
    with NoDefault[MoralAxis] {

  val c = MoralAxis

  /* val bitValue: Int = c.bitValues
    .filter { x =>
      c.checkVal(x._1)
    }.values.head*/
  val bitValue: Int = 0
}

object MoralAxis extends Enum[MoralAxis] with BitSupport {

  case object Good extends MoralAxis

  case object Neutral extends MoralAxis

  case object Evil extends MoralAxis

  val values = findValues
  type T = MoralAxis
  val bitValues: Map[T, Int] = valuesToIndex.map { x =>
    x._1 -> Math.pow(2.0, x._2).toInt
  }
}
