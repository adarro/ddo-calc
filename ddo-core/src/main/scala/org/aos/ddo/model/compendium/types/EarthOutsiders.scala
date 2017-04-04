package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.EarthOutsider

/**
  * Created by adarr on 3/25/2017.
  */
trait EarthOutsiders extends MainType {
  override val mainTypes = Some(EarthOutsider)
}
