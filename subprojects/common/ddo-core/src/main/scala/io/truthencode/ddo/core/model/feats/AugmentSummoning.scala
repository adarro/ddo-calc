/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AugmentSummoning.scala
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
 * Icon Feat Type Description Prerequisites Icon Feat Augment Summoning.png Augment Summoning
 * Passive Your summoned creatures, charmed minions, and hirelings have +4 to all ability scores,
 * increased health, and increased fortification. * None
 */
protected[feats] trait AugmentSummoning
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with Passive with FreeFeat
  with ArtificerBonusFeat {
  self: GeneralFeat =>
}
