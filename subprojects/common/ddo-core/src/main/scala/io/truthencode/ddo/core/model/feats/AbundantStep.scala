/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AbundantStep.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl, TriggeredEventImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

import java.time.Duration

/**
 * At the cost of 10 Ki, a monk can use this ability to make horizontal leaps, closing to targets,
 * traversing chasms, or zipping past enemies with less chance of being detected. There is a
 * cool-down of three seconds on this feat and can only be used on yourself.
 */
trait AbundantStep
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with TriggeredEventImpl
  with AtWillEvent with GrantsToClass with RequiresAllOfClass {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 12))

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 12))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(3))
}
