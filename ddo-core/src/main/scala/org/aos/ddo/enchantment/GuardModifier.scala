package org.aos.ddo.enchantment

import org.aos.ddo.effect.{ Prefix, SecondaryPrefix, Suffix }
import org.aos.ddo.enchantment.Modifier.{ Greater, Lesser, Minor }
import org.aos.ddo.support.RomanNumeral.fromRoman
import org.aos.ddo.support.Validation.violationToString

import com.typesafe.scalalogging.slf4j.LazyLogging
import com.wix.accord.{ Failure, Success }
import com.wix.accord.dsl.{ Contextualizer, ValidatorBooleanOps, empty, notEmpty, validator }
import com.wix.accord.validate

/**
  * Valid prefixes and suffixes for Guard Enchantments
  */
object GuardModifier extends LazyLogging {
  type Parameters = (Option[String], Option[String], Option[String])
  def apply(prefix: Option[String] = None, sPrefix: Option[String] = None, suffix: Option[String] = None): GuardModifier = {
    val o = create(prefix, sPrefix, suffix)
    validate(o) match {
      case Success    => o
      case Failure(x) => throw new IllegalArgumentException(violationToString(x))
    }
  }
  def apply(parameters: Parameters): GuardModifier =
    GuardModifier(parameters._1, parameters._2, parameters._3)

  private def create(prefix: Option[String], sPrefix: Option[String], suffix: Option[String]): GuardModifier = {
    new GuardModifier(prefix, sPrefix, suffix) {
      private def readResolve(): Object =
        GuardModifier(prefix, sPrefix, suffix)

      def copy(prefix: Option[String], sPrefix: Option[String], suffix: Option[String]): GuardModifier =
        GuardModifier(prefix, sPrefix, suffix)

      val tuple: GuardModifier.Parameters =
        (prefix, sPrefix, suffix)
    }
  }
  /**
    * Array of allowed Guard Modifiers, may occasionally need to be updated
    * if the game adds new ones.
    */
  lazy val allowedModifiers = List(Minor, Lesser, Greater).map { x => x.entryName }
  /**
    * Restricts Modifiers to allowed current modifiers.
    */
  def filterModifiers(mod: Option[String]): Option[String] = {
    val r = for { m <- mod } yield {
      allowedModifiers.collectFirst({
        case x if x.equals(m) => x
      })
    }
    r.flatten
  }
  /**
    * Filters to allow supported suffixes for the guards.
    * Currently, this is represented by a Roman Numeral 1 - 10.
    */
  def allowedRoman(rn: Option[String]): Option[Int] = {
    rn.flatMap { y =>
      fromRoman(y) match {
        case x if 1 until 11 contains x =>
          logger.info(s"AllowedRoman ${y}->${x}"); Some(x)
        case _ => logger.info(s"AllowedRoman ${y} -> None"); None
      }
    }
  }
  implicit val guardModifierValidator = validator[GuardModifier] { g =>
    {
      // Guards can have nothing, a prefix or a suffix
      // No Modifiers
      (((g.prefix is empty) and (g.sPrefix is empty) and (g.suffix is empty))
        or
        // Just a (valid) prefix
        (((g.prefix is notEmpty) and (g.sPrefix is empty) and (g.suffix is empty))
          and (filterModifiers(g.prefix) is notEmpty))
          or
          // Just a valid suffix
          (((g.prefix is empty) and (g.sPrefix is empty) and (g.suffix is notEmpty))
            and (allowedRoman(g.suffix) is notEmpty)))

    }
  }
}

abstract case class GuardModifier private[GuardModifier] (val prefix: Option[String], val sPrefix: Option[String] = None, val suffix: Option[String]) extends Prefix with SecondaryPrefix with Suffix {
  def copy(prefix: Option[String], sPrefix: Option[String], suffix: Option[String]): GuardModifier
}
