package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Religion

/**
  * Created by adarr on 4/10/2017.
  */
trait ReligionFeatBase {
  def allowedReligions: List[Religion]
}
