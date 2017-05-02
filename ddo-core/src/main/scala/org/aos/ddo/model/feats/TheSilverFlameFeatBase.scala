package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Religion, SilverFlame}

/**
  * Created by adarr on 5/2/2017.
  */
trait TheSilverFlameFeatBase extends ReligionFeatBase { self: SilverFlame =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.SilverFlame)
}
