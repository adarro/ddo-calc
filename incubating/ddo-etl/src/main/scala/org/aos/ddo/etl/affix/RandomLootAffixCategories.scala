package org.aos.ddo.etl.affix

import com.typesafe.scalalogging.LazyLogging
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.Document
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{
element,
elements
}
import org.aos.ddo.etl._

import scala.collection.immutable

/**
  * Parses / extracts / validates affixes that may exist for randomly generated loot.
  *
  * Based on table from notes on Updates 29 [http://ddowiki.com/page/Update_29_randomly_generated_loot#Enchantments_by_slot]
  *
  * @note The wikki document is not complete, will have to inject to the rules and categories additional data
  *       Created by adarr on 5/7/2017.
  *
  */
object RandomLootAffixCategories extends LazyLogging {

  case class MyConfig(local: String, remote: String)

  case class AffixCategory(header: String,
                           affixType: String,
                           values: List[String])

  implicit class ErrataOps(source: String) {
    protected def addS() = s"${source}s"

    final val plurals = List("Elemental Absorptions", "Elemental Resistances") //.map(_.addS)

    def pluralize = plurals.find(_.eq(source.addS)).getOrElse(source)

  }

  def findPosition(idx: Int, mod: Int = 3): Int = {
    val seed = 1
    idx % mod + seed
  }

  def findCategory(idx: Int, cat: Int = 12): Int = {
    val seed = 1
    val check = idx - cat

    //    val pos = check match {
    //      case x if x <= 0 => seed
    //      case x if x > 0 && x <= cat => seed + 1
    //      case x if x > cat => (idx - (idx % cat)) / cat
    //    }
    val pos = (idx - (idx % cat)) / cat + seed
    pos
  }

  def doc: Document = loadLocalSourceHtml(local.toString)

  private def table = doc >> element("#mw-content-text > table:nth-child(27)")

  def affixes: immutable.IndexedSeq[String] =
    (for {
      r <- table >> elements("tr")
      data <- r >> elements("td")
    } yield s"${data.text}".trim).toSeq.toIndexedSeq

  private def hRaw = for {h <- table >> elements("th")} yield h.text.trim

  private def headers = hRaw.takeRight(hRaw.size - 4).toSeq.toIndexedSeq

  def resultRaw: immutable.IndexedSeq[(Int, Int, Int)] =
    for {x <- affixes.indices} yield
      (x + 1, findPosition(x), findPosition(x,headers.size)) // (y,x)// (headers(y),affixes(x))

  val affixType: IndexedSeq[String] = IndexedSeq("Prefix", "Suffix", "Extra")

  /* There are inconsistencies such as Elemental Save / Elemental Saves that need to be reconciled in a standard way here.
  These are just arbitrary named categories / affixes which will further be standardized to be consistent with all other affix sources.
  i.e. A Cannith Crafting prefix should have the same category as a Random loot prefix etc.
  For Initial ETL purposes, this list only needs to be consistent within itself (RandomLoot) which can then be further mapped into a proper synchronized standard category.
   */
  lazy val AffixCategories: immutable.IndexedSeq[AffixCategory] = {
    val ar = for {
      r <- resultRaw
      header =  headers(r._3 - 1)
      aType = affixType(r._2 - 1)
      affix = affixes(r._1 - 1).split(",").map(_.trim.pluralize).toList.distinct
    } yield
      (
       header,
        aType,
        affix)
    ar.foreach{v =>
      logger.debug(s"affix category ${v._1} ${v._2} ${v._3}")
    }
    val preMap = ar.map{x => AffixCategory(x._1,x._2,x._3)}
    logger.debug(s"loaded ${preMap.map(_.header).distinct.size} headers")
    val merged = mergeMap(source = preMap, others = missingPrefixes, missingSuffixes)
   // reMap(merged).toIndexedSeq
    reMap(preMap).toIndexedSeq
    //  preMap
  }

  /**
    * Prefix categories that were omitted from the source document.
    */
  val missingPrefixes: immutable.Iterable[AffixCategory] = {
    val affixes = Map("Weapons" -> List("Alignment Damages"))
    for {a <- affixes} yield AffixCategory(header = "Prefix", a._1, a._2)

    // for {affix <- affixes}
  }

  /**
    * Suffix categories that were omitted from the source document.
    */
  val missingSuffixes: immutable.Iterable[AffixCategory] = {
    Nil
  }

  /**
    * Extra categories that were omitted from the source document.
    */
  val missingExtra: immutable.Iterable[AffixCategory] = {
    Nil
  }

  /**
    * Filters list of Affixes by prefix / suffix / extra
    *
    * @param a one of Prefix, Affix or Extra (Should make this an enum)
    * @return the filtered list
    */
  private def filterByAffixType(
                                 a: String,
                                 cat: Iterable[AffixCategory] = AffixCategories): Set[String] = {
    cat
      .filter(_.affixType == a)
      .map(_.values)
      .foldLeft(Set[String]()) { (x, y) =>
        x ++ y.toSet
      }
  }

  def appendMap(iterable: Iterable[AffixCategory]): Iterable[AffixCategory] = {
    iterable map { x =>
      val fM = missingPrefixes
        .filter { m =>
          m.header == x.header && m.affixType == x.affixType
        }
        .map { z =>
          x.copy(values = x.values ++ z.values)
        }
      val fS = missingSuffixes
        .filter { m =>
          m.header == x.header && m.affixType == x.affixType
        }
        .map { z =>
          x.copy(values = x.values ++ z.values)
        }
      val fE = missingExtra
        .filter { m =>
          m.header == x.header && m.affixType == x.affixType
        }
        .map { z =>
          x.copy(values = x.values ++ z.values)
        }
      fM.headOption.getOrElse(
        fS.headOption.getOrElse(fE.headOption.getOrElse(x)))
    }

  }

  def mergeMap(source: Iterable[AffixCategory],
               others: Iterable[AffixCategory]*): Iterable[AffixCategory] = {
    val changed: Iterable[AffixCategory] = for {
      s <- source
      other <- others
      o <- other
      if s.header == o.header && s.affixType == o.affixType
    } yield s.copy(values = s.values ++ o.values)
    (source.toList diff changed.toList) ++ changed

  }

  /**
    * Re-maps an existing list of categories using a custom name map to fix inconsistencies.
    *
    * @param iterable Affix Categories
    * @return Tweaked list of categories
    */
  def reMap(iterable: Iterable[AffixCategory]): Iterable[AffixCategory] = {
    val keys = nameMap.keySet.toList
    val changeList = iterable.filter(x => (keys intersect x.values).nonEmpty)
    logger.debug(s"changelist : $changeList")

    def doMap(v: List[String]) = {
      v.map { x => {
        nameMap.find(_._1 == x) match {
          case Some(y) =>
            logger.debug(s"Mapping $x -> ${y._2}")
            y._2
          case _ => x
        }
      }
      }
    }

    val changed = for {x <- changeList} yield
      x.copy(values = doMap(x.values))
    val others = iterable.toList diff changeList.toList
    logger.debug(s"changed: $changed  others: $others ")
    others ++ changed
  }

  /**
    * Mapping used to correct oversights, inconsistent pluralization, typos, alias etc.
    *
    * @return map of renames.
    */
  def nameMap: Map[String, String] =
    Map("UMD" -> "Use Magic Device",
      "Elemental Absorption" -> "Elemental Absorptions",
      "Elemental Resistance" -> "Elemental Resistances")

  lazy val prefixValues: Set[String] = filterByAffixType("Prefix")
  lazy val suffixValues: Set[String] = filterByAffixType("Suffix")
  lazy val extraValues: Set[String] = filterByAffixType("Extra")

}
