package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Religion, SovereignHost}

/**
  * Created by adarr on 5/2/2017.
  */
trait TheSovereignHostFeatBase extends ReligionFeatBase {
  self: SovereignHost =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.SovereignHost)
}
