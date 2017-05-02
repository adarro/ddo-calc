package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Onatar, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait OnatarFeatBase extends ReligionFeatBase { self: Onatar =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Onatar)
}
