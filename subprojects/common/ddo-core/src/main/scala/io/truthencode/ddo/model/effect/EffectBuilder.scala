/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.model.effect

import io.truthencode.ddo.model.effect.features.Features

abstract class EffectBuilder[T <: EffectStatus]() {
  protected val effect: Effect

  def withProperty(i: Int)(implicit ev: T =:= EffectInComplete): EffectBuilder[EffectInComplete] =
    EffectBuilder.create(effect)

  def build(implicit ev: EffectStatus =:= EffectComplete): Effect = effect
}

object EffectBuilder {

  def apply(): EffectBuilder[EffectInComplete] = create()
  private def create(ef: Effect = new Effect with Features {
    override def features = List.empty
  }) = new EffectBuilder[EffectInComplete] {
    override val effect = ef
  }
}
