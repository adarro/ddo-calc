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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Rogue
import io.truthencode.ddo.support.requisite._

import scala.collection.immutable

/**
 * Created by adarr on 4/6/2017.
 */
trait ImprovedSneakAttack
  extends FeatRequisiteImpl with ClassRequisiteImpl with RequiresAllOfClass
  with AttributeRequisiteImpl with RequiresAllOfAttribute with Passive with ClassRestricted {
  override def allOfClass: immutable.Seq[(HeroicCharacterClass, Int)] =
    List((Rogue, 12))

  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Dexterity, 21))
}
