package org.aos.ddo.model.effect

abstract class EffectBuilder[T <: EffectStatus]() {
  def withProperty(i: Int)(implicit ev: T =:= EffectInComplete): EffectBuilder[EffectInComplete] = EffectBuilder.create(effect)

  protected val effect: Effect
  def build(implicit ev: EffectStatus =:= EffectComplete): Effect = effect
}

object EffectBuilder {

  def apply(): EffectBuilder[EffectInComplete] = create()
  private def create(ef: Effect = new Effect {}) = new EffectBuilder[EffectInComplete] {
    override val effect = ef
  }
}
