package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Religion, Vulkoor}

/**
  * Created by adarr on 5/2/2017.
  */
trait TheVulkoorFeatBase extends ReligionFeatBase { self: Vulkoor =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Vulkoor)
}
