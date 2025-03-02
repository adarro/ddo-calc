/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: package.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.support.matching.{WordMatchStrategies, WordMatchStrategy}

import java.security.SecureRandom
import scala.collection.immutable.HashMap
import scala.language.postfixOps
import scala.util.control.Exception.catching

package object support extends LazyLogging {

  /**
   * Current Valid range of levels considered [[http://ddowiki.com/page/Heroic Heroic Levels]]
   */
  final val HeroicLevels: Range.Inclusive = 1 to 20

  /**
   * The Current Max Level achievable by a player.
   */
  final val LevelCap = 34

  /**
   * Current valid range of levels considered [[http://ddowiki.com/page/Epic_levels Epic Levels]]
   */
  final val EpicLevels: Range.Inclusive = 21 to 30

  final val LegendaryLevels: Range.Inclusive = 31 to LevelCap

  final val CharacterLevels: _root_.scala.collection.immutable.Range.Inclusive =
    HeroicLevels.min to LegendaryLevels.max

  /**
   * Implicit functions for manipulating collections such as finding the Cartesian Product, Left and
   * Right Joins
   */
  object TraverseOps {

    // succinctly pooled originally from SO [[http://stackoverflow.com/a/14740340/400729]]
    implicit class Crossable[X](xs: Iterable[X]) {
      def cross[Y](ys: Iterable[Y]): Iterable[(X, Y)] = for x <- xs; y <- ys yield (x, y)
    }

    implicit class Joinable[X](xs: Seq[X]) {

      def leftJoin[Y >: X](ys: Seq[Y]): Seq[X] = {
        val yy: Seq[X] = xs.intersect(ys)
        yy.concat(xs.diff(yy))
      }

      def rightJoin[Y >: X](ys: Seq[Y]): Seq[Y] = {
        val yy: Seq[Y] = ys.intersect(xs)
        yy.concat(ys.diff(yy))
      }

      def nSelect[Y >: X](ys: Seq[Y]): Seq[X] = {
        xs.filterNot(ys.contains)
      }
    }

    implicit class MapOps[K, V](xs: Map[K, V]) {

      /**
       * Performs the traditional "Left Join" on a Map
       * @param ys
       *   Map to Join
       * @param joinOnKeys
       *   Join based on Keys Only, otherwise matches based on key AND value
       * @tparam Y
       *   Key
       * @tparam Z
       *   Value
       * @return
       *   Subset of source consisting of unique LHS + common LHS
       */
      def leftJoin[Y >: K, Z >: V](
        ys: Map[Y, Z])(implicit joinOnKeys: Boolean = true): Map[K, V] = {
        if joinOnKeys then {
          val lk = xs.keys.toSeq.leftJoin(ys.keys.toSeq)
          xs.filter { k =>
            lk.contains(k._1)
          }
        } else {
          xs.toList.leftJoin(ys.toList).toList.toMap
        }
      }

      /**
       * Performs the traditional "Right Join" on a Map
       * @param ys
       *   Map to Join
       * @param joinOnKeys
       *   Join based on Keys Only, otherwise matches based on key AND value
       * @tparam Y
       *   Key
       * @tparam Z
       *   Value
       * @return
       *   Subset of source consisting of unique RHS + common RHS
       */
      def rightJoin[Y >: K, Z >: V](
        ys: Map[Y, Z])(implicit joinOnKeys: Boolean = true): Map[Y, Z] = {
        if joinOnKeys then {
          val lk = ys.keys.toSeq.leftJoin(xs.keys.toSeq)
          ys.filter { k =>
            lk.contains(k._1)
          }
        } else {
          ys.toList.leftJoin(xs.toList).toList.toMap
        }
      }
    }
  }

  /**
   * StringUtils Useful Implicits used in mapping
   */
  object StringUtils {

    // Constants
    final val Comma = ","

    implicit lazy val config: com.typesafe.config.Config = {
      val cfg = ConfigFactory.load
      if !cfg.hasPath(path) then {
        logger.warn(
          "Failed to load a valid configuration file, using hard-coded defaults... Please verify your configuration"
        )
        val data =
          """
            |io.truthencode.ddo {
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

      /**
       * Converts a String to an Optional Int.
       *
       * @return
       *   Some(Int) upon successful conversion or None for a failed attempt.
       */
      def toIntOpt: Option[Int] =
        catching(classOf[NumberFormatException]).opt(s.toInt)
    }

    implicit class IntExtensions(val source: Int) {

      /**
       * Convenience toString to add +/- to number for text fields
       * @return
       *   returns the number as a string prefixed with either + or -
       */
      def numberToSignedText: String = {
        if source >= 0 then {
          s"+$source"
        } else {
          s"-$source"
        }
      }
    }

    /**
     * Mostly String manipulation utility methods. Most methods are fluent if possible.
     *
     * @param s
     *   source string
     */
    implicit class Extensions(val s: String) {

      lazy val specialSymbols: Map[Char, String] = {
        HashMap {
          '&' -> "And"
        }
      }

      def lowerCaseNoise: String = {
        val noise = config.getStringList("io.truthencode.ddo.support.noiseWords")
        val words = s.splitByCase
        (for w <- words.split(Space).map { x =>
            if noise.contains(x) then x.toLowerCase else x
          } yield w + Space).mkString.trim
      }

      /**
       * Splits a string using uppercase
       *
       * @return
       *   the string with spaces between words.
       * @note
       *   This is currently a very simple parser that assumes input is camel-cased. Non-camel case
       *   may have undesired results. i.e. TEST will return T E S T. where getFoo returns get Foo.
       */
      def splitByCase(implicit replaceSymbols: Boolean = false): String = {
        val b = new StringBuilder
        s.toCharArray.foreach {
          case c: Char if (c.isLetter && c.isUpper) || c.isDigit =>
            b.append(s"$Space$c")
          case c: Char if c.isSpaceChar =>
          case c: Char if replaceSymbols && specialSymbols.keys.exists(sc => sc.equals(c)) =>
            b.append(s"$Space${c.toString.symbolsToWords}")
          case c: Char if !replaceSymbols && specialSymbols.keys.exists(sc => sc.equals(c)) =>
            b.append(s"$Space$c")
          case c: Char =>
            b.append(c.toString)
        }
        b.mkString.trim
      }

      /**
       * Replaces special characters such as ampersand to "And"
       * @return
       */
      def symbolsToWords: String = {
        val b = new StringBuilder
        s.toCharArray.foreach { (e: Char) =>
          if specialSymbols.keys.exists(_.equals(e)) then {
            val r = specialSymbols(e)
            b.append(s"$r")
          } else {
            b.append(e)
          }
        }
        b.mkString.trim
      }

      /**
       * Formats string into [[http://wiki.c2.com/?PascalCase PascalCase]] or 'UpperCamelCase'
       *
       * @return
       *   formatted string
       * @note
       *   this also removes space characters but does not check for characters that may be illegal
       *   for a specific language. (i.e. underscores, commas etc)
       */
      def toPascalCase: String = {
        {
          for
            w <- s.split(Space)
            c = w.charAt(0).toString.toLowerCase
          yield w.toLowerCase.replaceFirst(c, c.toUpperCase)
        }.mkString
      }

      /**
       * Replaces Roman Numeral Representation with Numerical value. i.e. IV -> 4
       * @note
       *   This utility parses using Space as a delimiter. It is generally advised to use this
       *   before other manipulations such as removing spaces or changing cases.
       * @return
       *   the string with all Roman Numerals replaced. No changes are made if no valid numerals are
       *   found.
       */
      def replaceRomanNumerals: String = {
        s.split(Space)
          .map {
            case s: String if RomanNumeral.fnRomanNumeralToNumber.isDefinedAt(s) =>
              RomanNumeral.fnRomanNumeralToNumber(s).toString
            case s: String => s
          }
          .mkString(Space)
      }

      def replaceNumbersWithRomanNumerals: String = {
        s.split(Space)
          .map {
            case s: String if RomanNumeral.fnNumberToRomanNumeral.isDefinedAt(s) =>
              RomanNumeral.fnNumberToRomanNumeral(s)
            case s: String => s
          }
          .mkString(Space)
      }

      /**
       * Filters out non alphanumeric values.
       *
       * @return
       *   alphanumeric values.
       */
      def filterAlphaNumeric: String =
        s.toCharArray.filter { x =>
          x.isLetterOrDigit
        }.mkString

      /**
       * Randomizes the case of the source string.
       *
       * @return
       *   source string with randomized upper and lower case characters.
       */
      def randomCase: String = {
        val r = new SecureRandom()
        s.toCharArray.map { x =>
          if r.nextInt() > 0 then x.toUpper else x.toLower
        }
          .foldLeft("")((r, c) => r + c)
      }

      /**
       * Attempts to convert a string to an acronym
       *
       * Will use space as a delimiter, falling back on Case else None.
       *
       * @example
       *   {{{
       * val wordList = List("I Believe Mom","i borrow money","IBetterMail","oracle")
       * wordList.map(x => wordsToAcronym(x)}
       * res0:List(Some("IBM"),Some("IBM"),Some("IBM"),None)
       *   }}}
       */
      def wordsToAcronym(implicit
        wordMatchStrategy: WordMatchStrategy = WordMatchStrategies.IgnoreCaseWordStrategy
      ): Option[String] =
        s.toSanitizeOption match {
          case Some(x) if x.contains(Space) =>
            Some(new String(x.split(Space).map { y =>
              characterToStrategy(y.charAt(0), wordMatchStrategy)
            }))
          case Some(x) =>
            Some(
              new String(
                x.toCharArray.filter { y =>
                  y.isLetter && y.isUpper
                }
                  .map(characterToStrategy(_, wordMatchStrategy))
              )
            )
          case _ => None
        }

      /**
       * Applies the sanitize filter while safely wrapping value in an [[Option[String]]]
       *
       * @return
       */
      def toSanitizeOption: Option[String] = {
        Option(s) match {
          case Some(x) =>
            Option(x.sanitize) match {
              case Some(y) if y.nonEmpty => Some(y)
              case _ => None
            }
          case _ => None
        }
      }

      /**
       * Sanitizes strings by removing unwanted characters
       *
       * @return
       *   Sanitized string.
       */
      def sanitize: String = {
        s.trim.toCharArray.filter { x =>
          x.isLetterOrDigit || x.isSpaceChar
        }.mkString
      }
    }

    final val Space = " "
    final val EmptyString = ""
    final val ForwardSlash = "/"
    final val UnderScore = "_"
    final val LineSep = sys.props("line.separator")
    private val path = "io.truthencode.ddo.support"
    // Random generator
    private val random: SecureRandom = new SecureRandom()

    /**
     * Generate a random alphanumeric string of length n
     */
    def randomAlphanumericString(n: Int): String =
      randomString({
        ('a' to 'z') ++ (0 to 9)
      }.mkString)(n)

    /**
     * Generate a random string of length n from the given alphabet
     *
     * @note
     *   see excellent source at
     *   [[http://www.bindschaedler.com/2012/04/07/elegant-random-string-generation-in-scala/ Laurent BINDSCHAEDLER's blog post]]
     */
    def randomString(alphabet: String)(n: Int): String =
      LazyList
        .continually(random.nextInt(alphabet.length))
        .map(alphabet)
        .take(n)
        .mkString

    def randomAlphaString(n: Int): String =
      randomString(('a' to 'z').mkString)(n)

    private def characterToStrategy(c: Char, wordMatchStrategy: WordMatchStrategy): Char = {
      wordMatchStrategy match {
        case WordMatchStrategies.IgnoreCaseWordStrategy => c
        case WordMatchStrategies.FullUpperCaseWordStrategy |
            WordMatchStrategies.TitleCaseWordStrategy =>
          c.toUpper
        case WordMatchStrategies.FullLowerCaseWordStrategy => c.toLower
      }
    }
  }
}
