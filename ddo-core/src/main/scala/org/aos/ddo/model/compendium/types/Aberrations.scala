package org.aos.ddo.model.compendium.types

/**
  * Created by adarr on 3/25/2017.
  */
trait Aberrations extends MainType {
  override val mainTypes = Some(MonsterType.Aberration)
}
