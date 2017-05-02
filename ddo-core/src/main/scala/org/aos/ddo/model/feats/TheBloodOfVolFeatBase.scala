package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{BloodOfVol, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait TheBloodOfVolFeatBase extends ReligionFeatBase { self: BloodOfVol =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.BloodOfVol)
}
