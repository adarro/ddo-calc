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

import io.truthencode.ddo.activation.{ActivationTypeImpl, AtWillEvent}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Alchemist
import io.truthencode.ddo.model.misc.DefaultCoolDown
import io.truthencode.ddo.support.requisite.{
  ClassRequisiteImpl,
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass
}

/**
 * You are able to copy alchemical formulae from Recipes into your spellbook. This action will
 * destroy the Recipe.
 *
 * @see
 *   [[https://ddowiki.com/page/Inscribe_Alchemy_Recipe]]
 */
protected[feats] trait InscribeAlchemyRecipe
  extends FeatRequisiteImpl with ClassRequisiteImpl with GrantsToClass with RequiresAllOfClass
  with ActivationTypeImpl with ActiveFeat with AtWillEvent with DefaultCoolDown {
  private[this] val cls = (Alchemist, 1)
  abstract override def grantToClass: Seq[(HeroicCharacterClass, Int)] = super.grantToClass :+ cls

  abstract override def allOfClass: Seq[(HeroicCharacterClass, Int)] = super.allOfClass :+ cls
}
