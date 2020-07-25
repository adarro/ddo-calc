/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

/**
  * [[https://ddowiki.com/page/Sneak Sneak]]
  * The character becomes invisible to all enemies that fail a Spot and Listen skill check,
  * opposed by Hide and Move Silently skills.
  * @note There have been many changes to stealth and it is worth reading, but currently not deeply invested in this code-base
  */
protected[feats] trait Sneak
    extends FeatRequisiteImpl
    with ActiveFeat
    with Stance
    with DefaultCoolDown
    with FreeFeat {
  self: GeneralFeat =>
}
