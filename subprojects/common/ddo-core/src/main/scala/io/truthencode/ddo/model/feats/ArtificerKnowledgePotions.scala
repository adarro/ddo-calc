/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ArtificerKnowledgePotions.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Alchemist, Artificer}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * Created by adarr on 2/16/2017.
 */
protected[feats] trait ArtificerKnowledgePotions
  extends FeatRequisiteImpl with ArtificerKnowledgePrefix with Passive with GrantsToClass
  with RequiresAllOfClass {
  self: ClassFeat =>
  private lazy val levels = List(2, 5, 8, 11, 14)
  private lazy val values = for
    l <- levels
    c <- List(Artificer, Alchemist)
  yield (c, l)
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = values

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = values
}
