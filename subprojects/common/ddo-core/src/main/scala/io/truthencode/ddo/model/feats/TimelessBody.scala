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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Druid, Monk}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

/**
 * Created by adarr on 3/17/2017.
 */
trait TimelessBody
  extends FeatRequisiteImpl with Passive with GrantsToClass with RequiresAnyOfClass {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 15), (Monk, 17))

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 15), (Monk, 17))
}
