package org.aos.ddo.support

/**
  * Represents item modifier effects that can be prefixed or suffixed to an item to provide additional effects.
  * Items can generally have up to two prefixes and one suffix, such as Flaming Sword of the Sun's Fury,
  * where Flaming and "Sun's Fury' are affixes.
  */
sealed trait AffixSlot {
  def addSlot(s: AffixSlot): Unit
}

trait PrimaryPreSlot extends AffixSlot {
  type Pre = PrimaryPreSlot
  val pref: Pre = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref)
}

trait SecondaryPreSlot extends AffixSlot {
  type Pre2 = SecondaryPreSlot
  val pref2: Pre2 = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref2)
}

trait PostSlot extends AffixSlot {
  type Post = PostSlot
  val post: Post = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(post)
}
