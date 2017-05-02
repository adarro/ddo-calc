package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Amaunator, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait AmaunatorFeatBase extends ReligionFeatBase { self: Amaunator =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Amaunator)
}
