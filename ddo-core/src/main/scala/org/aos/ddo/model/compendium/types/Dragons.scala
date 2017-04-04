package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.Dragon

/**
  * Created by adarr on 3/25/2017.
  */
trait Dragons extends MainType {
   override val mainTypes = Some(Dragon)
}
