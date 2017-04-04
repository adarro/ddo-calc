package org.aos.ddo.model.feats.metamagic

import java.util

import org.aos.ddo.model.feats.MetaMagicFeat
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

import scala.collection.JavaConverters._

@RunWith(classOf[ConcordionRunner])
class MetaMagic {
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
