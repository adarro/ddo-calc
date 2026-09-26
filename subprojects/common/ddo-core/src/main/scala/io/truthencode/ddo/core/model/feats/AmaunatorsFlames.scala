/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AmaunatorsFlames.scala
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
import io.truthencode.ddo.model.religions.Amaunator
import io.truthencode.ddo.support.naming.DisplayProperties
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

/**
 * Created by adarr on 4/7/2017.
 */
trait AmaunatorsFlames
  extends FeatRequisiteImpl with TriggeredActivationImpl with ForgottenRealmsReligionNonWarforged
  with DeityUniqueLevelBase with RequiresAllOfFeat with Amaunator with AmaunatorFeatBase with Stance
  with DisplayProperties {
  self: DeityFeat =>

  override def allOfFeats: Seq[Feat] = List(DeityFeat.ChildOfAmaunator)

  override def displayText: String = "Amaunator's Flames"
}
