package io.truthencode.ddo.etl.affix

import com.typesafe.scalalogging.LazyLogging
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.Document
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{element, elements}
import io.truthencode.ddo.etl._

import scala.collection.immutable

/**
  * Parses / extracts / validates affixes that may exist for randomly generated loot.
  *
  * Based on table from notes on Updates 29 [http://ddowiki.com/page/Update_29_randomly_generated_loot#Enchantments_by_slot]
  * @note The wikki document is not complete, will have to inject to the rules and categories additional data
  * Created by adarr on 5/7/2017.
  *
  */
object CannithCraftingAffixCategories extends LazyLogging {

  case class MyConfig(local: String, remote: String)
  case class AffixCategory(header: String,
                           affixType: String,
                           values: List[String])

  implicit class ErrataOps(source: String) {
    protected def addS = s"${source}s"


    final val plurals = List("Elemental Absorptions", "Elemental Resistances") // .map(_.addS)

    /**
      * An extremely basic pluralizer that adds an 'S' to the word based on a dictionary list.
      */
    def pluralize: String = plurals.find(_.eq(source.addS)).getOrElse(source)

  }

  def findPosition(idx: Int, mod: Int = 3): Int = {
    idx % mod match {
      case 0 => 3
      case x => x
    }
  }

  def findCategory(idx: Int, cat: Int = 12): Int = {
    val seed = 1
    val check = idx - cat

    val pos = check match {
      case x if x <= 0 => seed
      case x if x > 0 && x <= cat => seed + 1
      case x if x > cat => (idx - (idx % cat)) / cat
    }
    pos
  }

  def doc: Document = loadLocalSourceHtml(local.toString)
  private def table = doc >> element("#mw-content-text > table:nth-child(27)")

  private def affixes =
    (for {
      r <- table >> elements("tr")
      data <- r >> elements("td")
    } yield s"${data.text}".trim).toSeq.toIndexedSeq

  private def hRaw = for { h <- table >> elements("th") } yield h.text.trim
  private def headers = hRaw.takeRight(hRaw.size - 4).toSeq.toIndexedSeq

  private def resultRaw =
    for { x <- 1 to affixes.size } yield
      (x, findPosition(x), findCategory(x)) // (y,x)// (headers(y),affixes(x))

  val affixType: IndexedSeq[String] = IndexedSeq("Prefix", "Suffix", "Extra")

  /* There are inconsistencies such as Elemental Save / Elemental Saves that need to be reconciled in a standard way here.
  These are just arbitrary named categories / affixes which will further be standardized to be consistent with all other affix sources.
  i.e. A Cannith Crafting prefix should have the same category as a Random loot prefix etc.
  For Initial ETL purposes, this list only needs to be consistent within itself (RandomLoot) which can then be further mapped into a proper synchronized standard category.
   */
  lazy val AffixCategories: immutable.IndexedSeq[AffixCategory] = {
    val preMap = for {
      r <- resultRaw
    } yield
      AffixCategory(
        headers(r._3 - 1),
        affixType(r._2 - 1),
        affixes(r._1 - 1).split(",").map(_.trim.pluralize).toList.distinct)
    reMap(preMap).toIndexedSeq
    //  preMap
  }

  private def filterByAffixType(a: String): Set[String] = {
    AffixCategories
      .filter(_.affixType == a)
      .map(_.values)
      .foldLeft(Set[String]()) { (x, y) =>
        x ++ y.toSet
      }
  }

  def reMap(iterable: Iterable[AffixCategory]): Iterable[AffixCategory] = {
    val keys = nameMap.keySet.toList
    val changeList = iterable.filter(x => (keys intersect x.values).nonEmpty)
    logger.debug(s"changelist : $changeList")
    def doMap(v: List[String]) = {
      v.map { x =>
        {
          nameMap.find(_._1 == x) match {
            case Some(y) =>
              logger.debug(s"Mapping $x -> ${y._2}")
              y._2
            case _ => x
          }
        }
      }
    }
    val changed = for { x <- changeList } yield
      x.copy(values = doMap(x.values))
    val others = iterable.toList diff changeList.toList
    logger.debug(s"changed: $changed  others: $others ")
    others ++ changed
  }

  /**
    * Mapping used to correct oversights, inconsistent pluralization, typos, alias etc.
    * @return map of renames.
    */
  def nameMap =
    Map("UMD" -> "Use Magic Device",
        "Elemental Absorption" -> "Elemental Absorptions",
        "Elemental Resistance" -> "Elemental Resistances")

  lazy val prefixValues: Set[String] = filterByAffixType("Prefix")
  lazy val suffixValues: Set[String] = filterByAffixType("Suffix")
  lazy val extraValues: Set[String] = filterByAffixType("Extra")

}
