package org.aos.ddo.model.feats

import org.concordion.api.ConcordionResources
import org.concordion.integration.junit4.ConcordionRunner
import org.junit.runner.RunWith

/**
  * Created by adarr on 2/19/2017.
  */
@ConcordionResources (Array("/images/*.png"))
@RunWith(classOf[ConcordionRunner])
trait ConcordionResourcesBundleSpec
