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

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import com.wix.accord.Descriptions.Description
import com.wix.accord.Violation

import scala.collection.immutable
import scala.language.postfixOps
import scala.util.Random
import scala.util.control.Exception.catching

package object support extends LazyLogging {
  /**
    * The Current Max Level achievable by a player.
    */
  val LevelCap = 30

  /**
    * Current Valid range of levels considered [[http://ddowiki.com/page/Heroic Heroic Levels]]
    */
  final val HeroicLevels: Range.Inclusive = 1 to 20
  /**
    * Current valid range of levels considered [[http://ddowiki.com/page/Epic_levels Epic Levels]]
    */
  final val EpicLevels: Range.Inclusive = 21 to LevelCap
  final val CharacterLevels: _root_.scala.collection.immutable.Range.Inclusive = HeroicLevels.min to EpicLevels.max

  object TraverseOps {

    // succinctly pooled from SO [[http://stackoverflow.com/a/14740340/400729]]
    implicit class Crossable[X](xs: Traversable[X]) {
      def cross[Y](ys: Traversable[Y]) = for { x <- xs; y <- ys } yield (x, y)
    }

  }

  /**
    * StringUtils
    * Useful Implicits used in mapping
    */
  object StringUtils {

    def path = "org.aos.ddo.support"

    implicit lazy val config: com.typesafe.config.Config = {
      val cfg = ConfigFactory.load
      if (!cfg.hasPath(path)) {
        logger.warn(
          "Failed to load a valid configuration file, using hard-coded defaults... Please verify your configuration")
        val data =
          """
            |org.aos.ddo {
            |  support {
            |    noiseWords = [
            |      "On",
            |      "The",
            |      "Of",
            |      "And",
            |      "To",
            |      "In"
            |    ]
            |  }
            |}
          """.stripMargin
        ConfigFactory.parseString(data)
      } else {
        cfg
      }
    }

    implicit class StringImprovements(val s: String) {
      def toIntOpt: Option[Int] =
        catching(classOf[NumberFormatException]) opt s.toInt
    }

    implicit class Extensions(val s: String) {
      def lowerCaseNoise: String = {
        val noise = config.getStringList("org.aos.ddo.support.noiseWords")
        val words = s.splitByCase
        (for {
          w <- words.split(Space).map { x =>
            if (noise.contains(x)) x.toLowerCase else x
          }
        } yield w + Space).mkString.trim
      }

      /**
        * Formats string into [[http://wiki.c2.com/?PascalCase PascalCase]] or 'UpperCamelCase'
        *
        * @return formatted string
        * @note this also removes space characters but does not check for characters that may be illegal
        *       for a specific language. (i.e. underscores, commas etc)
        */
      def toPascalCase: String = {
        {
          for {
            w <- s.split(Space)
            c = w.charAt(0).toString.toLowerCase
          } yield w.toLowerCase.replaceFirst(c, c.toUpperCase)
        } mkString
      }

      /**
        * Splits a string using uppercase
        *
        * @return the string with spaces between words.
        * @note This is currently a very simple parser that assumes input is camel-cased.  Non-camel case
        *       may have undesired results. i.e.  TEST will return T E S T. where getFoo returns get Foo.
        */
      def splitByCase: String = {
        val b = new StringBuilder
        s.toCharArray.foreach { c =>
          if (c.isLetter && c.isUpper) b.append(s"$Space$c")
          else b.append(c.toString)
        }
        b.mkString.trim
      }

      /**
        * Sanitizes strings by removing unwanted characters
        *
        * @return Sanitized string.
        */
      def sanitize: String = {
        s.trim.filterAlphaNumeric
      }

      def filterAlphaNumeric: String =
        s.toCharArray.filter { x =>
          x.isLetterOrDigit
        }.mkString

      def randomCase: String = {
        val r = new Random
        s.toCharArray
          .map { x =>
            if (r.nextInt > 0) x.toUpper else x.toLower
          }
          .foldLeft("")((r, c) => r + c)
      }

      /**
        * Attempts to convert a string to an acronym
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
      def wordsToAcronym: Option[String] = Option(s) match {
        case Some(x) if x.contains(Space) =>
          Some(new String(x.split(" ").map { y =>
            y.charAt(0)
          }))
        case Some(x) =>
          Some(new String(x.toCharArray.filter { y =>
            y.isLetter && y.isUpper
          }))
        case _ => None
      }
    }

    // Constants
    final val Comma = ","
    final val Space = " "
    final val EmptyString = ""
    final val ForwardSlash = "/"
    final val UnderScore = "_"
    final val LineSep = sys.props("line.separator")

    // Random generator
    private[this] val random: scala.util.Random = new scala.util.Random

    /**
      * Generate a random string of length n from the given alphabet
      *
      * @note see excellent source at [[http://www.bindschaedler.com/2012/04/07/elegant-random-string-generation-in-scala/ Laurent BINDSCHAEDLER's blog post]]
      */
    def randomString(alphabet: String)(n: Int): String =
      Stream
        .continually(random.nextInt(alphabet.length))
        .map(alphabet)
        .take(n)
        .mkString

    /**
      * Generate a random alphabnumeric string of length n
      */
    def randomAlphanumericString(n: Int): String =
      randomString({
        ('a' to 'z') ++ (0 to 9)
      }.mkString)(n)

    def randomAlphaString(n: Int): String =
      randomString(('a' to 'z').mkString)(n)
  }

  /**
    * Validation utilities and convenience routines
    */
  object Validation {

    import StringUtils.LineSep

    // scalastyle:off import.group
    /**
      * Extracts violation description and message text
      *
      * @param v Volations
      * @return Printable list of violations.
      * @deprecated
      */
    def violationToString(v: Set[Violation]): String = {
      //      val buf = new StringBuilder
      //      v.foreach { x =>
      //        x.description match {
      //          case Some(d) => buf.append(s"$d -> ${x.constraint}")
      //          case _ => buf.append(x.constraint)
      //        }
      //        buf.append(LineSep)
      //      }
      //      buf.toString()
      //    }
      "This function is no longer supported, please see Wix Documentation"
    }
  }

}
