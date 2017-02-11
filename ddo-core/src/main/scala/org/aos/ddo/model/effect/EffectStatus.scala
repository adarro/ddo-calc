package org.aos.ddo.model.effect

sealed trait EffectStatus
trait EffectComplete extends EffectStatus
trait EffectInComplete extends EffectStatus
