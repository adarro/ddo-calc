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

import io.truthencode.ddo.activation.AtWillEvent
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Warlock
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.model.spells.SpellLikeAbility
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

/**
 * [[https://ddowiki.com/page/Create_Thrall Create Thrall]] Official: Target enemy fights for you 60
 * seconds. An enemy succeeding on a Will save is instead confused and may indiscriminately attack
 * friend or foe for 10 seconds. Does not affect bosses. Display DC = 15 + Warlock Level + Cha Mod.
 * Displayed DC not affected by Enchantment Focus items. Rather quick casting time; no metamagic
 * feats allowed. Affects also Undead.
 */
protected[feats] trait CreateThrall
  extends FeatRequisiteImpl with ActiveFeat with AtWillEvent with SpellLikeAbility
  with RequiresAllOfClass with RequiresAllOfFeat with GrantsToClass with DefaultCasterCoolDown {
  self: ClassFeat =>

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    Seq((Warlock, 15))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactGreatOldOne)
}
