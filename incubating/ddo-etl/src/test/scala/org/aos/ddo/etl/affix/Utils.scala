package org.aos.ddo.etl.affix

import com.typesafe.scalalogging.LazyLogging


/**
  * Created by adarr on 5/9/2017.
  */
object Utils extends LazyLogging {
  lazy val cats = RandomLootAffixCategories.prefixValues.toList.sorted

  def DiffEntries(entries: Iterable[RandomLootNamingRule]): List[String] = {
    val eCat = entries.map(_.category.name).toList.distinct
    logger.info(s"Categories : ${entries.map(_.category.name).toList.sorted.toSet.mkString(",")}")
    eCat diff cats
  }
}
