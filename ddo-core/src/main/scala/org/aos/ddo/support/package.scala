/** Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import scala.util.control.Exception.catching
package object support {
  /** StringUtils
    * Useful Implicits used in mapping
    */
  object StringUtils {
    implicit class StringImprovements(val s: String) {
      def toIntOpt: Option[Int] = catching(classOf[NumberFormatException]) opt s.toInt
    }
    // Constants
    final val Comma: String = ","
    final val Space: String = " "
    final val EmptyString: String = ""
    final val ForwardSlash: String = "/"
    final val UnderScore: String = "_"

    // Random generator
    private[this] val random: scala.util.Random = new scala.util.Random

    /** Attempts to convert a string to an acronym
      *
      * Will use space as a delimiter, falling back on Case else None.
      *
      * @example
      * {{{
      * val wordList = List("I Believe Mom","i borrow money","IBetterMail","oracle")
      * wordList.map(x => wordsToAcronym(x)}
      * res0:List(Some("IBM"),Some("ibm"),Some("IBM"),None)
      * }}}
      */
    def wordsToAcronym(words: String): Option[String] = {
      Option(words) match {
        case Some(x) if (x.contains(Space)) =>
          Some(new String(x.split(" ").map { y => y.charAt(0) }))
        case Some(x) => Some(new String(x.toCharArray().filter { y => y.isLetter && y.isUpper }))
        case _       => None
      }
    }
    /** Generate a random string of length n from the given alphabet
      * @note see excellent source at [[http://www.bindschaedler.com/2012/04/07/elegant-random-string-generation-in-scala/ Laurent BINDSCHAEDLER's blog post]]
      */
    def randomString(alphabet: String)(n: Int): String =
      Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString

    /** Generate a random alphabnumeric string of length n
      */
    def randomAlphanumericString(n: Int): String =
      randomString("abcdefghijklmnopqrstuvwxyz0123456789")(n)
  }
}
