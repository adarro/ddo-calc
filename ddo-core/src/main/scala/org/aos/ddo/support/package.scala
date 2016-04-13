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
        case Some(x) if (x.contains(Space)) ⇒
          Some(new String(x.split(" ").map { y ⇒ y.charAt(0) }))
        case Some(x) ⇒ Some(new String(x.toCharArray().filter { y ⇒ y.isLetter && y.isUpper }))
        case _       ⇒ None
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
