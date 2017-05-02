package org.aos.ddo.model.feats
import java.util
import scala.collection.JavaConverters._

/**
  * Verifies basic creation of Epic Feats, categorized as per [[http://ddowiki.com/page/Epic_Feats ddo Epic Feats]].
  */
trait EpicFeatDisplayHelper extends DisplayHelper {


  final override val enum: E = Feat
  // val categoryFilter: EpicFeatCategory

  val filterByCategory: PartialFunction[Entry, Entry]

  override def verify(): util.List[String] = {
    val v = enum.values collect filterByCategory
    v.map(_.displayText).asJava
  }
}
