package org.aos.ddo.effect

sealed trait EffectStatus
trait EffectComplete extends EffectStatus
trait EffectInComplete extends EffectStatus
