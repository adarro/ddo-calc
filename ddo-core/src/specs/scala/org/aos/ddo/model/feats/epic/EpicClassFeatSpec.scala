package org.aos.ddo.model.feats.epic

import java.util

import com.typesafe.scalalogging.LazyLogging
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.feats.{
  ClassDisplayHelper,
  ClassRestricted,
  EpicFeatDisplayHelper
}
import org.aos.ddo.support.requisite.ClassRequisite
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class EpicClassFeatSpec
    extends ClassDisplayHelper
    with EpicFeatDisplayHelper
    with LazyLogging {
  def setUpClass(classId: String): Unit = {
    logger.info(s"classId $classId")
    val result = CharacterClass.namesToValuesMap
      .filterKeys(_ == classId)
      .values
      .headOption
    logger.info(s"classId $classId Set instance to $result")
    instanceClass = result
  }

 private var instanceClass: Option[CharacterClass] = None

  override def cClass: CharacterClass =
    instanceClass.getOrElse(CharacterClass.Artificer)

  val filterByAnyOfs: PartialFunction[Entry, Entry] = {
    case x: ClassRequisite if x.anyOfClass.exists(isDefinedForClass(_)) => x
  }

  override val filterByCategory: PartialFunction[Entry, Entry] = {
    case x: ClassRestricted => x
  }

  override def verify(): util.List[String] = {
    logger.info(s"Verify instance $instanceClass")

    val y: Seq[Entry] = enum.values collect existing
    logger.info(s"count from existing ${y.size}")
    val z = y collect filterByCategory
    logger.info(s"count from filterByCategory ${z.size}")
    z.map(_.displayText).asJava
  }

}
