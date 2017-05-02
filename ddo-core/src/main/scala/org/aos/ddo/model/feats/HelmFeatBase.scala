package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Helm, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait HelmFeatBase extends ReligionFeatBase { self: Helm =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Helm)
}
