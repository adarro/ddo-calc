/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package org.aos.ddo

import scala.language.implicitConversions
object EnumImplicits {
  import scala.language.implicitConversions

}
/**
  * @author adarro
  *         A generic Enumeration with built-in support for flags / BitWise Fields, positional values
  *         to provide C# like [[https://msdn.microsoft.com/en-us/library/system.flagsattribute(v=vs.110).aspx C Sharp Flags]] functionality.
  *
  * @tparam A Base type of the class.
  *
  * @example
  * {{{
  * sealed trait DayOfWeek extends DayOfWeek.Value
  * object DayOfWeek extends Enum[DayOfWeek] {
  * case object MON extends DayOfWeek
  * case object TUE extends DayOfWeek
  * case object WED extends DayOfWeek
  * case object THU extends DayOfWeek
  * case object FRI extends DayOfWeek
  * case object SAT extends DayOfWeek
  * case object SUN extends DayOfWeek
  * val values = List(MON,TUE,WED,THU,FRI,SAT,SUN)
  * }
  * }}}
  *
  * Syntactic sugar for bitwise manipulations can be realized
  * via implicits.
  *
  * In the above example:
  * {{{
  * import DayOfWeek.e2i
  * val monday = DayOfWeek.MON
  * val tuesday = DayOfWeek.values(1)
  * assert(tuesday == DayOfWeek.TUE)
  * //Cast monday to Int
  * monday: Int
  * //Add monday (as int) to another Int
  * 2 + monday
  *
  * monday + 2
  * val i = monday.bitValue
  * assert(i == DayOfWeek.MON.bitValue)
  *
  * //cast Int to Day Of Week (Monday)
  * i: DayOfWeek.Value \\yields DayOfWeek.MON
  *
  * //BitWise Manulpulations
  * import DayOfWeek._
  * val b = MON | TUE \\res: 3:INT
  * val tuesdayOrBust = b & TUE \\ res: 2
  * val tuesdayOrBustNative : DayOfWeek.Value =b & TUE \\res TUE
  * }}}
  * NOTE: the last example, tuesdayOrBustNative may throw an exception for no match.
  */
trait EnumOld[A] {

  trait Value {
    self: A =>
    def index: Int = values indexOf this

    def bitValue: Int = Math.pow(2.0, index).toInt

    def all: List[Value] = values
  }

  def valsOfType[T: Manifest]: Array[T] = {
    val c = implicitly[Manifest[T]].runtimeClass
    for {
      m <- getClass.getMethods
      if m.getParameterTypes.isEmpty && c.isAssignableFrom(m.getReturnType)
    } yield m.invoke(this).asInstanceOf[T]
  }

  /**
    * Supplies a default Enum value or should return None if no default applies.
    */
  val defaultValue: Option[Value] = None

  /**
    * Collection of all Enum Values
    */
  val values: List[Value]

  /**
    * Locates an enum value by name.
    *
    * @param name       String name of value i.e. "Red"
    * @param ignoreCase toggles case sensitivity
    *
    * @return Named value or None
    */
  def withName(name: String, ignoreCase: Boolean = false): Option[Value] = values.find {
    x => if (ignoreCase) x.toString.equalsIgnoreCase(name) else x.toString.equals(name)
  }

  /**
    * Attempts to locate a matching enumeration based on a list of potential values.
    *
    * @param names      List of string values to try. i.e. Red, blue, bLaCk for a color.
    * @param ignoreCase toggles case sensitivity in search.
    *
    * @return Returns the first Enum value found matching any of the given supplied names.
    */
  def withNames(names: List[String], ignoreCase: Boolean = false): Option[List[Value]] = {
    for {
      sc <- Some(this.values.filter { x =>
        (ignoreCase && names.exists(n => n.equalsIgnoreCase(x.toString))) || names.contains(x.toString)
      }) if sc.nonEmpty
    } yield sc
  }

  /**
    * A list of enum values matching the BitMask
    *
    * @param flag Bit value to compare
    *
    * @return All matching values
    */
  def fromMask(flag: Int): Option[List[Value]] = {
    for {
      sc <- Some(this.values.filter { x => (x.bitValue & flag) != 0 }) if sc.nonEmpty
    } yield sc
  }

  /**
    * Converts Int to Enum
    */
  implicit def i2e(i: Int): Value = {
    values.find { x => x.bitValue == i } match {
      case Some(x) => x
      case _ => throw new IndexOutOfBoundsException(s"No value found with index $i")
    }
  }


  /**
    * Converts enum to Int
    */
  implicit def e2i[T <: Value](t: T): Int = {
    t.bitValue
  }
}

object EnumOld {
  implicit def companion[T](implicit comp: EnumOld[T]): EnumOld[T] = comp

 // implicit def strToX[T](s: String)(implicit comp: Enum[T]): Option[comp.Value] = comp.withName(s, ignoreCase = true)



}
