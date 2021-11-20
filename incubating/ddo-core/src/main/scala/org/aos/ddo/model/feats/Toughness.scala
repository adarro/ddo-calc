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
  * Icon Feat Toughness.png
  * Toughness 	Passive 	This feat Increases your hit points by +3 at first level, and adds +1 additional hit point for each additional level. This feat can be taken multiple times, so that the effects stack.
  * *
  * None
  **/
protected[feats] trait Toughness extends FeatRequisiteImpl
  with Passive
  with FreeFeat
  with MartialArtsFeat {
  self: GeneralFeat =>
}
