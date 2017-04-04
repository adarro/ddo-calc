package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.FireOutsider

/**
  * Created by adarr on 3/25/2017.
  */
trait FireOutsiders extends MainType {
  override val mainTypes = Some(FireOutsider)
}
