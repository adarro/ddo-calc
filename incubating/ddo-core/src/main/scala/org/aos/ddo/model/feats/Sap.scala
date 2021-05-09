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
  * Icon Feat Sap.png
  * Sap
  * Active - Special Attack
  * This feat has a chance to render the target briefly senseless, though it will become active if damaged again.
  * Some creatures may be immune to the sap effect and sap is more effective when performed as a successful sneak attack
  * (whether or not your character can normally perform sneak attacks).
  */
protected trait Sap extends FeatRequisiteImpl
  with Active
  with FreeFeat
  with FighterBonusFeat {
  self: GeneralFeat =>
}
