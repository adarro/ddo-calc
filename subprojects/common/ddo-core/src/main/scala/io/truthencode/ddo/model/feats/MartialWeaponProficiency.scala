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

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
 * Icon Feat Martial Weapon Proficiency.png Martial Weapon Proficiency Passive This feat negates the
 * penalty from using any of the martial weapons while untrained. This feat must be taken for
 * separate martial weapons. * None
 */
protected[feats] trait MartialWeaponProficiency
  extends FeatRequisiteImpl with Passive with FreeFeat {
  self: GeneralFeat =>
}
