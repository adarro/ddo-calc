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

import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[https://ddowiki.com/page/Dismiss_Charm Dismiss Charm]]
  * Activate this short-ranged ability while targeting a charmed, commanded, controlled,
  * or dominated enemy that is under your control to dispel the controlling effect.
  *
  * @note As of Update 26 all classes now receive this feat at level 1
  */
protected[feats] trait DismissCharm
    extends FeatRequisiteImpl
    with ActiveFeat
    with AtWillEvent
    with DefaultCoolDown
    with FreeFeat
    with ClassRequisiteImpl
    with GrantsToClass { self: GeneralFeat =>

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    HeroicCharacterClass.values.map((_, 1))
}
