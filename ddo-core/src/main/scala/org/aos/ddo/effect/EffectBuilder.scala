package org.aos.ddo.effect

abstract class EffectBuilder[T <: EffectStatus]() {
  def withProperty(i: Int)(implicit ev: T =:= EffectInComplete) = EffectBuilder.create(effect)

  protected val effect: Effects
  def build(implicit ev: EffectStatus =:= EffectComplete) = effect
}

object EffectBuilder {

  def apply() = create()
  private def create(ef: Effects = new Effects {}) = new EffectBuilder[EffectInComplete] {
    override val effect = ef
  }
}
