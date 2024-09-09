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

import io.truthencode.ddo.activation.TriggeredActivationImpl
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Druid
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.support.naming.{DisplayName, DisplayProperties}
import io.truthencode.ddo.support.requisite.{GrantsToClass, RequiresAllOfClass}

/**
 * Created by adarr on 3/17/2017.
 */
trait WildShapeBear
  extends WildShape with GrantsToClass with RequiresAllOfClass with DefaultCasterCoolDown {
  self: DisplayName & DisplayProperties =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 2), (Druid, 5))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 2))
}
