/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * This feat has a chance to trip the target rendering it prone for a short time.
  * Strength or Dexterity save (whichever is higher) is used to oppose a DC of 10 + Strength modifier + related Enhancements + Vertigo.
  * Some creatures may be immune to this effect. Creatures larger or stronger than you are less likely to trip.
  * A successful Balance check negates this effect (DC of 10 + Strength modifier + related Enhancements + Vertigo).
  */
protected[feats] trait Trip extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
