package org.aos.ddo.model.feats

import java.util

import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class MetaMagicFeatSpec {
  def verifyFeats(): util.List[String] = {
    MetaMagicFeat.values
      .map { x =>
        x.displayText
      }
      .toList
      .sorted
      .asJava
  }

}
