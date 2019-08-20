package org.aos.ddo.support

import enumeratum.{Enum, EnumEntry}

import scala.util.{Failure, Success, Try}

object EnumSupport {

  implicit class EnumOpts[U <: enumeratum.Enum[_ <: EnumEntry]](source: U) {
    def tryCast(s: String): Try[EnumEntry] = {
      val g: Any = source.withName(s)
      source.withNameOption(s) match {
        case Some(x) => Success(x)
        case _       => Failure(new NoSuchElementException(s))
      }
    }
  }

  val anyToEnum: PartialFunction[AnyRef, enumeratum.Enum[EnumEntry]] = {
    case x: enumeratum.Enum[EnumEntry] => x
  }

  /**
    * Attempts to locate a valid Singleton given a valid [[enumeratum.Enum]] name as string.
    * @param fqn fully qualified class name
    * @return The Enum Companion object or None
    *
    * @note Internally uses a [[scala.util.Try]] and will safely return [[None]] for any cast exceptions.
    *       Also allows base trait name to invoke Companion. i.e. org.example.baseTrait vs Companion org.example.baseTrait$
    */
  def tryEnumFromString(fqn: String): Option[Enum[EnumEntry]] = {
    // Testing naming pattern for companion object
    val mangle = if (fqn.endsWith("$")) { fqn } else { "%s$".format(fqn) }
    val y: Try[AnyRef] = for {
      c <- Try(Class.forName(mangle))
      c2 <- Try(c.getField("MODULE$"))
      c3 <- Try(c2.get(c2))

    } yield c3
    y match {
      case Success(x) =>
        Some(x) collect anyToEnum
      case _ => None
    }
  }

  /**
    * Attempts to extract a named [[enumeratum.EnumEntry]] from a fully qualified class name
    * @param fqn fully qualified class name of the enumeration
    * @param id string name of the value to extract. (Case Sensitive)
    * @return The EnumEntry value or [[scala.None]] if value can not be found.
    */
  def tryEntryFromString(fqn: String, id: String): Option[EnumEntry] = {
    tryEnumFromString(fqn).flatMap(_.withNameOption(id))
  }

}
