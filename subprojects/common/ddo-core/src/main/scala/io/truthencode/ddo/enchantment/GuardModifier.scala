/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GuardModifier.scala
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
package io.truthencode.ddo.enchantment

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.enchantment.Modifier.{Greater, Lesser, Minor}
import io.truthencode.ddo.model.effect.{Prefix, SecondaryPrefix, Suffix}
import io.truthencode.ddo.support.RomanNumeral.fromRoman
import zio.prelude.Validation

import scala.language.postfixOps

/**
 * Valid prefixes and suffixes for Guard Enchantments
 */
object GuardModifier extends LazyLogging {
  type Parameters = (Option[String], Option[String], Option[String])

  /**
   * Array of allowed Guard Modifiers, may occasionally need to be updated if the game adds new
   * ones.
   */
  lazy val allowedModifiers: List[String] = List(Minor, Lesser, Greater).map { x =>
    x.entryName
  }

  def apply(
    prefix: Option[String] = None,
    sPrefix: Option[String] = None,
    suffix: Option[String] = None): GuardModifier = {
    val o = create(prefix, sPrefix, suffix)
    // Validate here
    o

  }

  object Validation {

    def validatePrefix(prefix: Option[String]): Validation[String, Option[String]] = {
      if prefix.isDefined && !allowedModifiers.contains(prefix.get) then {
//        val noPrefix = ()
        zio.prelude.Validation.fail("Prefix is not a valid modifier")
      } else {
        zio.prelude.Validation.succeed(prefix)
      }

//      implicit val guardModifierValidator: TransformedValidator[GuardModifier] =
//          validator[GuardModifier] { g => {
//              // Guards can have nothing, a prefix or a suffix
//              // No Modifiers
//              (((g.prefix
//                  .is(empty))
//                  .and(g.sPrefix.is(empty))
//                  .and(g.suffix.is(empty)))
//                  .or(
//                      // Just a (valid) prefix
//                      ((g.prefix
//                          .is(notEmpty))
//                          .and(g.sPrefix.is(empty))
//                          .and(g.suffix.is(empty)))
//                          .and(filterModifiers(g.prefix).is(notEmpty)))
//                  .or(
//                      // Just a valid suffix
//                      ((g.prefix
//                          .is(empty))
//                          .and(g.sPrefix.is(empty))
//                          .and(g.suffix.is(notEmpty)))
//                          .and(allowedRoman(g.suffix).is(notEmpty))))
//
//          }
    }
  }

  def apply(parameters: Parameters): GuardModifier =
    GuardModifier(parameters._1, parameters._2, parameters._3)

  /**
   * Filters to allow supported suffixes for the guards. Currently, this is represented by a Roman
   * Numeral 1 - 10.
   */
  def allowedRoman(rn: Option[String]): Option[Int] = {
    rn.flatMap { y =>
      fromRoman(y) match {
        case x: Int if 1 until 11 contains x =>
          logger.info(s"AllowedRoman $y->$x"); Some(x)
        case _ => logger.info(s"AllowedRoman $y -> None"); None
      }
    }
  }

  /**
   * Restricts Modifiers to allowed current modifiers.
   */
  protected[enchantment] def filterModifiers(mod: Option[String]): Option[String] =
    (for m <- mod yield {
      allowedModifiers.collectFirst({
        case x: String if x.equals(m) => x
      })
    }).flatten

  private def create(
    prefix: Option[String],
    sPrefix: Option[String],
    suffix: Option[String]): GuardModifier = {
    new GuardModifier(prefix, sPrefix, suffix) {
      private def readResolve(): Object =
        GuardModifier(this.prefix, this.sPrefix, this.suffix)

      def copy(
        prefix: Option[String],
        sPrefix: Option[String],
        suffix: Option[String]): GuardModifier =
        GuardModifier(prefix, sPrefix, suffix)

      val tuple: GuardModifier.Parameters =
        (this.prefix, this.sPrefix, this.suffix)
    }
  }

}

abstract case class GuardModifier private[GuardModifier] (
  prefix: Option[String],
  sPrefix: Option[String] = None,
  suffix: Option[String])
  extends Prefix with SecondaryPrefix with Suffix {
  def copy(prefix: Option[String], sPrefix: Option[String], suffix: Option[String]): GuardModifier
}
