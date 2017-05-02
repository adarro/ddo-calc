package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Artificer
import org.aos.ddo.model.race.Race
import org.aos.ddo.model.race.Race.Warforged
import org.aos.ddo.support.requisite.{RequiresAllOfClass, RequiresNoneOfRace}

/**
  * Created by adarr on 4/5/2017.
  */
trait ConstructEssence extends Passive with RequiresAllOfClass with RequiresNoneOfRace {
  override def noneOfRace: Seq[(Race, Int)] = List((Warforged,1))

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Artificer,3))
}
