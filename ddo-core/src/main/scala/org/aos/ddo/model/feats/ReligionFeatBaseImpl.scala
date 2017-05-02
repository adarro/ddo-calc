package org.aos.ddo.model.feats

import org.aos.ddo.model.religions.Religion

/**
  * Created by adarr on 4/10/2017.
  */
trait ReligionFeatBaseImpl extends ReligionFeatBase {
  override def allowedReligions: List[Religion] = Nil
}
