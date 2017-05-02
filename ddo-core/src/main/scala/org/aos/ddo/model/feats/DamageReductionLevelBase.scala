package org.aos.ddo.model.feats

import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.requisite.{ClassRequisite, FeatRequisite}

/**
  * Created by adarr on 4/10/2017.
  */
trait DamageReductionLevelBase
    extends ReligionFeatBase
    with Passive
    with DamageReductionLevel with DamageReductionPrefix{ self: ClassRequisite with FeatRequisite with DisplayName with FriendlyDisplay =>

}
