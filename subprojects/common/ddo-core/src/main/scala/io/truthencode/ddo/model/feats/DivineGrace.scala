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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Paladin
import io.truthencode.ddo.support.requisite._

/**
 * [[http://ddowiki.com/page/Divine_Grace Divine Grace]] At 2nd level, a Paladin gains a bonus equal
 * to his or her Charisma modifier (if not negative) on all saving throws. Divine Grace is capped at
 * 2+(3*paladin level). For multi-classes this means 2 levels of Paladin only grant up to +8 saves.
 */
protected[feats] trait DivineGrace
  extends FeatRequisiteImpl with Passive with GrantsToClass with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 2))

}
