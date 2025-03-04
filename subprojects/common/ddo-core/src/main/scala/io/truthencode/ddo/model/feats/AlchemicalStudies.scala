/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AlchemicalStudies.scala
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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
 * You are able to move while drinking potions
 *
 * @see
 *   [[https://ddowiki.com/page/Alchemy_on_the_Run]]
 */
protected[feats] trait AlchemicalStudies
  extends FeatRequisiteImpl with ClassRequisiteImpl with GrantsToClass with RequiresAllOfClass
  with Passive {
  private val cls = (Alchemist, 4)

  abstract override def grantToClass: Seq[(HeroicCharacterClass, Int)] = super.grantToClass :+ cls

  abstract override def allOfClass: Seq[(HeroicCharacterClass, Int)] = super.allOfClass :+ cls
}
