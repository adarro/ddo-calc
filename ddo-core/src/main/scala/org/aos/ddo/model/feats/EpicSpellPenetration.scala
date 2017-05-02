package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FreeFeat, RequiresAllOfFeat}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait EpicSpellPenetration
    extends FreeFeat
    with SpellCastingPassive
    with RequiresAllOfFeat { self: EpicFeat =>
  override def allOfFeats: Seq[Feat] = List(GeneralFeat.GreaterSpellPenetration)
}
