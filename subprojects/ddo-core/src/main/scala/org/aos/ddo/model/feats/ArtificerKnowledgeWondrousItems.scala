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
import org.aos.ddo.model.classes.HeroicCharacterClass.Artificer
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  * Created by adarr on 2/16/2017.
  */
protected[feats] trait ArtificerKnowledgeWondrousItems
    extends FeatRequisiteImpl
    with ArtificerKnowledgePrefix
    with Passive
    with GrantsToClass
    with RequiresAllOfClass { self: ClassFeat =>
  private lazy val levels = List(7, 10, 13, 16, 19)
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] = levels.map { x =>
    (Artificer, x)
  }
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    levels.map { x =>
      (Artificer, x)
    }
}
