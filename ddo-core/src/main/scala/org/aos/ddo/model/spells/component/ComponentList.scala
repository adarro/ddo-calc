package org.aos.ddo.model.spells.component

/**
  * Collection of required components (Material, Somatic, verbal etc.) required to enact a spell
  */
trait ComponentList {
  val components : List[Component]
}
