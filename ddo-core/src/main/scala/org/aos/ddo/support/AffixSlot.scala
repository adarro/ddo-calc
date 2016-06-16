package org.aos.ddo.support

trait AffixSlot {
  def addSlot(s: AffixSlot): Unit
}

trait PrimaryPreSlot extends AffixSlot {
  type Pre = PrimaryPreSlot
  val pref: Pre = this
  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref)
}

trait SecondardyPreSlot extends AffixSlot {
  type Pre2 = SecondardyPreSlot
  val pref2: Pre2 = this
  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref2)
}

trait PostSlot extends AffixSlot {
  type Post = PostSlot
  val post: Post = this
  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(post)
}
