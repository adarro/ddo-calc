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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresBaB}

/**
 * Icon Feat Improved Critical.png Improved Critical Passive Adds 1, 2, or 3 to critical threat range based on the
 * weapon type's unmodified threat range.
 *
 * This feat is taken for a certain weapon type (Bludgeoning, Piercing, Ranged, Slashing or Thrown), and can be taken
 * multiple times, though each time must be for a different type.
 *
 * Base Attack Bonus +8
 */
protected[feats] trait ImprovedCritical
  extends FeatRequisiteImpl with Passive with RequiresBaB with FighterBonusFeat with ArtificerBonusFeat {
  self: GeneralFeat =>
  override def requiresBaB: Int = 8
}
