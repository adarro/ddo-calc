package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.Construct

/**
  * Created by adarr on 3/25/2017.
  */
trait Constructs extends MainType {
  override val mainTypes = Some(Construct)
}
