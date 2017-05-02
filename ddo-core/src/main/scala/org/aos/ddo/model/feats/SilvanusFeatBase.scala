package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Religion, Silvanus}

/**
  * Created by adarr on 5/2/2017.
  */
trait SilvanusFeatBase extends ReligionFeatBase { self: Silvanus =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Silvanus)
}
