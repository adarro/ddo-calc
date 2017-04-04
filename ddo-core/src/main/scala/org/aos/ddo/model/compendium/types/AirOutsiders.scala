package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.AirOutsider

/**
  * Created by adarr on 3/25/2017.
  */
trait AirOutsiders extends MainType {
   override val mainTypes = Some(AirOutsider)
}
