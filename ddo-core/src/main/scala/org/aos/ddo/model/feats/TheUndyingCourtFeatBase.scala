package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Religion, UndyingCourt}

/**
  * Created by adarr on 5/2/2017.
  */
trait TheUndyingCourtFeatBase extends ReligionFeatBase { self: UndyingCourt =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.UndyingCourt)
}
