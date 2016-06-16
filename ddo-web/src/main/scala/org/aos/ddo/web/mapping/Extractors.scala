package org.aos.ddo.web.mapping

import scala.language.postfixOps

import org.aos.ddo.support.StringUtils.{ Comma, EmptyString, Space }
import org.jsoup.nodes.Element

import com.typesafe.scalalogging.slf4j.LazyLogging

object Extractor extends LazyLogging {
  /**
    * Extracts the critical profile information from a string representation.
    * @param infoText text containing a min - max x: Multiplier, i.e. 19-20 x3
    * @return a [[critProfile]] or None if there was no parsable value found.
    */
  def extractCriticalProfile(infoText: String): Some[critProfile] = {

    /**
      * Regular expression used to translate D &amp; D text to object notation.
      */
    val regCritical = """(?<min>\d+)?-?(?<max>\d+)\s*/\s*x(?<multiplier>\d+)""".r
    infoText match {
      case regCritical(min, max, multiplier) => {
        logger.info(s"critical profile: ${infoText}")
        Some(critProfile(
          if (min == null) max.toInt else min.toInt, // scalastyle:off null
          max toInt,
          multiplier toInt))
      }
      case _ => logger.error(s"argument could not be parsed: ${infoText}"); throw new IllegalArgumentException
    }
  }

  /**
    * Helper object to hold extended damage information
    * @param wMod Weapon Modifier information
    * @param dice D &amp; D Dice
    * @param extra Extra damage beyond dice
    * @param damageType List of Damage types
    */
  case class damageExtractor(wMod: Int, dice: String, extra: Int, damageType: List[String])

  /**
    * Wraps text into an extractor object as an intermeadiate object to extract additional
    * damage information.
    * @param infoText structured text read from the Wiki HTML
    * @return wrapped damageExtractor object with parsed properties.
    */
  def extractDamageInfo(infoText: String): Option[damageExtractor] = {
    val damageInfo = """(?<wDamage>\d(?:\.\d+)?)?\s*(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    // val damageInfo = """(?<wDamage>\d+.\d+)?(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    infoText match {
      case damageInfo(wDamage, dice, extra, damageType) =>

        logger.trace(s"${infoText} matched")
        val msgDiceMultiplier = s"Dice Multiplier: ${wDamage}\nDice: ${dice}\nExtra: ${extra}\nDamageType: ${damageType}"
        logger.info(msgDiceMultiplier)

        Some(damageExtractor(
          Option(wDamage) match {
            case Some(x) => wDamage.toInt
            case _       => 0
          },
          dice,
          Option(extra) match {
            case Some(x) => extra.replaceAll(Space, EmptyString).toInt
            case _       => 0
          },
          damageType.split(Comma) toList))
      case _ => {
        logger.warn(s"Could not match infostring to damage extractor ${infoText}")
        None
      }
    }
  }

  /**
    * Extracts Enchantment data from ddowiki html
    * @param source wiki scrapped html
    * @return sequence of possibly nested Leafs containing any enchantments
    */
  def enchantmentExtractor(source: Map[String, Element]): Seq[org.aos.ddo.web.Leaf] = {
    // FIXME: Need to move enchantmentExtractor to a utility class or we make
    // Warehouse and WikiParser cross dependent
    val maybeTree = for {
      e <- source.get(Field.Enchantments)
    } yield org.aos.ddo.web.Warehouse.readHtmlList(e)

    maybeTree match {
      case Some(tree) => for {
        branches <- tree.branches.toSeq
        branch <- branches
        leaves <- branch.leaves.toSeq
        leaf <- leaves
      } yield leaf
      case _ => Nil
    }
  }
}
