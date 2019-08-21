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
package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * Icon Feat Slicing Blow.png
  * Slicing Blow
  * Active - Special Attack
  * Using this attack, you deal 1 point of Constitution damage to your target and deal 1d4 additional damage 2 seconds later as the target bleeds.
  * The target suffers an additional round of bleeding for every 3 character levels, up to a max of 6 at level 15.
  * Some creatures may be immune to the bleeding effect.
  *
  * None
  */
protected[feats] trait SlicingBlow extends FeatRequisiteImpl with Active with FreeFeat {
  self: GeneralFeat =>
}
