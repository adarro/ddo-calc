package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{
  FreeFeat,
  RequiresAllOfFeat,
  RequiresCharacterLevel
}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait GreaterRuin
    extends FreeFeat
    with SpellFeats
    with RequiresCharacterLevel
    with RequiresAllOfFeat { self: EpicFeat =>

  final override val characterLevel: Int = 30

  override def allOfFeats: Seq[Feat] = List(EpicFeat.Ruin)
}
