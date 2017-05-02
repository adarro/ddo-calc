package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.model.feats.ClassFeat.ImprovedConstructEssence
import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{RequiresAllOfClass, RequiresAllOfFeat, RequiresNoneOfRace}

/**
  * Created by adarr on 4/3/2017.
  */
protected[feats] trait ConstructExemplar
  extends ClassRestricted
    with RequiresAllOfClass
    with RequiresNoneOfRace
    with RequiresAllOfFeat
    with Passive {
  self: EpicFeat =>
  override def allOfFeats: Seq[Feat] = List(ImprovedConstructEssence)

  override def noneOfRace: Seq[(Race, Int)] = List((Warforged, 1))

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer, 12))
}
