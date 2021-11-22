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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Druid
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Druidic_Oath Druidic Oath]] All druids take an oath that prohibits them from wearing metal
 * armor, using metal shields or orbs, and using rune arms. Doing so suppresses your druidic abilities. Warforged druids
 * are forbidden from taking armor feats other than Composite Plating.
 * @todo
 *   Do we need to add an event trigger to suppress druid abilities?
 */
protected[feats] trait DruidicOath extends FeatRequisiteImpl with Passive with GrantsToClass with RequiresAllOfClass {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

}
