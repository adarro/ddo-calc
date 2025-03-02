/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EmptyBody.scala
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

import io.truthencode.ddo.activation.{OnSpellLikeAbilityEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAnyOfClass}

import java.time.Duration

/**
 * Created by adarr on 3/17/2017. [[https://ddowiki.com/page/Empty_Body Empty Body]] You are able to
 * focus your ki and walk the edge of the Plane of Shadow, mimicking the effects of a Shadow Walk
 * spell. While this effect is active, you move much faster than normal and your outline appears
 * faint, and you are harder to hit. Attacking another creature or otherwise interacting with
 * objects shunts you back to the Material Plane.
 */
trait EmptyBody
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat
  with OnSpellLikeAbilityEvent with GrantsToClass with RequiresAnyOfClass {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 19))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(10))

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 19))
}
