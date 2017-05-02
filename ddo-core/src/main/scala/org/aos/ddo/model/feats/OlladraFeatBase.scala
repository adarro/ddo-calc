package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.{Olladra, Religion}

/**
  * Created by adarr on 5/2/2017.
  */
trait OlladraFeatBase extends ReligionFeatBase { self: Olladra =>
  abstract override def allowedReligions: List[Religion] =
    super.allowedReligions ++ List(Religion.Olladra)
}
