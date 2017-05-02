package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Aureon, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait AureonFeatBase extends ReligionFeatBase { self: Aureon =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Aureon)
}
