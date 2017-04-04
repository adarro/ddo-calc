package org.aos.ddo.model.compendium.types

import org.aos.ddo.model.compendium.types.MonsterType.Dwarf

/**
  * Created by adarr on 3/25/2017.
  */
trait Dwarves extends MainType {
  override val mainTypes = Some(Dwarf)
}
