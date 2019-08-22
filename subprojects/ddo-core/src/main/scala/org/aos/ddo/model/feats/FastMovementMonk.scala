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

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Monk
import org.aos.ddo.support.naming.PostText
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * [[http://ddowiki.com/page/Fast_Movement_(monk_class_feat)]]
  * A Monk gains a cumulative 5% bonus to run speed while centered for every three Monk levels.
  * This bonus stacks with other run speed bonuses.
  * If the monk would become uncentered (by equipping armor, or wielding non-monk weapons, etc) then the bonuses are temporarily lost.
  */
protected[feats] trait FastMovementMonk
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with PostText
    with RequiresAllOfClass { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
  override def nameSource: String = "Fast Movement"
  override def postText: Option[String] = Some("Monk Class Feat")

  override def allOfClass: Seq[(CharacterClass, Int)] = List((Monk, 3))
}