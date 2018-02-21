package org.aos.ddo.model.feats

/**
  * A Parent Feat is a Feat which acts as a container for linked sub-feats.
  * A basic example would be Spell Focus, which contains sub-feats for each school of magic.
  * i.e. Spell Focus: Enchantment, Spell Focus: Necromancy etc.
  */
trait ParentFeat {
  self: Feat =>
  val subFeats: Seq[Feat with SubFeat]

}
