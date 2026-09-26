/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Goodberry.scala
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

import io.truthencode.ddo.activation.{OnTavern, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Druid
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Goodberry Goodberry]] Transmutes natural matter around you into
 * Goodberries. Goodberries are infused with primal magic, and provide a full meal's nourishment. If
 * eaten in a tavern, they act as tavern food and drink, refilling your health and spell points.
 * When cast, Goodberries appear in your inventory. The higher the caster level, the more nourishing
 * the Goodberries are.
 */
protected[feats] trait Goodberry
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with OnTavern
  with GrantsToClass with RequiresAllOfClass with DefaultCasterCoolDown {
  override def allOfClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] = List((Druid, 1))

}
