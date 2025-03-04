/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ToughTincture.scala
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
  RequiresAllOfClass
}

/**
 * You can use Intelligence for Will saves. Mutually exclusive with
 * [[https://ddowiki.com/page/Liquid_Courage]]
 *
 * @see
 *   [[https://ddowiki.com/page/Tough_Tincture]]
 */
protected[feats] trait ToughTincture
  extends FeatRequisiteImpl with BonusSelectableToClassFeatImpl with ClassRequisiteImpl
  with RequiresAllOfClass with AlchemistBonusFeat with Passive {
  self: ClassFeat =>
  private val cls = (Alchemist, 8)

  abstract override def allOfClass: Seq[(HeroicCharacterClass, Int)] = super.allOfClass :+ cls
}
