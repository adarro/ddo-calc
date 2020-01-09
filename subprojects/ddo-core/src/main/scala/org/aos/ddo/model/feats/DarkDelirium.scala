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

import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.classes.HeroicCharacterClass.Warlock
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
  * Plunge one enemy's mind into an illusory realm. That enemy is dazed and cannot act for 20 seconds.
  * Each time this enemy is damaged, there's a 10% chance they break free.
  */
protected[feats] trait DarkDelirium
    extends FeatRequisiteImpl
    with Active
    with RequiresAllOfClass
    with RequiresAllOfFeat
    with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = Seq((Warlock, 6))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFey)
}
