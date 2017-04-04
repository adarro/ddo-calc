package org.aos.ddo.web.mapping

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.Element
import org.aos.ddo.support.StringUtils.{Comma, EmptyString, Space}

import scala.language.postfixOps
// import org.jsoup.nodes.Element
import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.web.HtmlTag

object Extractor extends LazyLogging {

  /**
    * parser that extracts the text portion from a possibly HTML wrapped string.
    *
    * @param textOrElement fragment of HTML or Text
    * @param tagSelector   tag to extract. defaults to 'td'
    * @return extracted text or None if tag was not found or input was not of expected type.
    */
  def simpleExtractor(
      textOrElement: Option[Any],
      tagSelector: String = HtmlTag.TableData): Option[String] = {
    textOrElement match {
      case Some(data: String) =>
        logger.info(msgRawStringData)
        Some(data)
      case Some(data: Element) =>
        val rslt = data >> element(s"$tagSelector:first-child")
        lazy val MsgExtraction = s"returning element extraction $rslt.text"
        logger.info(MsgExtraction)
        Some(rslt.text)
      case Some(data) =>
        logger.warn(
          s"Data was not of expected type (text or Element) - found ${data.getClass.toString}")
        None
      case _ => logger.warn(msgNoData); None
    }

  }

  /**
    * Extracts the critical profile information from a string representation.
    *
    * @param infoText text containing a min - max x: Multiplier, i.e. 19-20 x3
    * @return a [[critProfile]] or None if there was no parsable value found.
    */
  def extractCriticalProfile(infoText: String): Some[critProfile] = {

    /**
      * Regular expression used to translate D &amp; D text to object notation.
      */
    val regCritical =
      """(?<min>\d+)?-?(?<max>\d+)\s*/\s*x(?<multiplier>\d+)""".r
    infoText match {
      case regCritical(min, max, multiplier) =>
        logger.info(s"critical profile: $infoText")
        Some(
          critProfile(
            if (min == null) max.toInt else min.toInt, // scalastyle:off null
            max toInt,
            multiplier toInt))
      case _ =>
        logger.error(s"argument could not be parsed: $infoText");
        throw new IllegalArgumentException
    }
  }

  /**
    * Helper object to hold extended damage information
    *
    * @param wMod Weapon Modifier information
    * @param dice D &amp; D Dice
    * @param extra Extra damage beyond dice
    * @param damageType List of Damage types
    */
  case class damageExtractor(wMod: Int,
                             dice: String,
                             extra: Int,
                             damageType: List[String])

  /**
    * Wraps text into an extractor object as an intermeadiate object to extract additional
    * damage information.
    *
    * @param infoText structured text read from the Wiki HTML
    * @return wrapped damageExtractor object with parsed properties.
    */
  def extractDamageInfo(infoText: String): Option[damageExtractor] = {
    val damageInfo =
      """(?<wDamage>\d(?:\.\d+)?)?\s*(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    // val damageInfo = """(?<wDamage>\d+.\d+)?(?<dice>\[\d+d\d+\])(?<damageType>\s\+\s\d+)?\s(.*)""".r
    infoText match {
      case damageInfo(wDamage, dice, extra, damageType) =>
        logger.trace(s"$infoText matched")
        val msgDiceMultiplier =
          s"Dice Multiplier: $wDamage\nDice: $dice\nExtra: $extra\nDamageType: $damageType"
        logger.info(msgDiceMultiplier)

        Some(
          damageExtractor(
            Option(wDamage) match {
              case Some(_) => wDamage.toInt
              case _ => 0
            },
            dice,
            Option(extra) match {
              case Some(_) => extra.replaceAll(Space, EmptyString).toInt
              case _ => 0
            },
            damageType.split(Comma) toList
          ))
      case _ =>
        logger.warn(
          s"Could not match infostring to damage extractor $infoText")
        None
    }
  }

  /**
    * Extracts Enchantment data from ddowiki html
    *
    * @param source wiki scrapped html
    * @return sequence of possibly nested Leafs containing any enchantments
    */
  def enchantmentExtractor(
      source: Map[String, Element]): Seq[org.aos.ddo.web.Leaf] = {
    val maybeTree = for {
      e <- source.get(Field.Enchantments)
    } yield org.aos.ddo.web.Warehouse.readHtmlList(e)

    maybeTree match {
      case Some(tree) =>
        for {
          branches <- tree.branches.toSeq
          branch <- branches
          leaves <- branch.leaves.toSeq
          leaf <- leaves
        } yield leaf
      case _ => Nil
    }
  }
}
