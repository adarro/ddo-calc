/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ArtificerConstructMastery.scala
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

import io.truthencode.ddo.activation.{AtWillEvent, TriggeredActivationImpl}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.model.spells.{SingleTarget, Spell, SpellBookImpl}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * Created by adarr on 2/16/2017. [[https://ddowiki.com/page/Artificer_Construct_Mastery]] This feat
 * allows an Artificer to spontaneously cast any single-target Repair Damage or Inflict Damage spell
 * they have inscribed in their spellbook. These spells will appear in bonus spell slots once
 * inscribed.
 * @note
 *   Currently set as an Active Feat: AtWillEvent
 */
protected[feats] trait ArtificerConstructMastery
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with GrantsToClass
  with RequiresAllOfClass with SpellBookImpl with AtWillEvent with DefaultCasterCoolDown {
  val fnArtificerInflictRepair: PartialFunction[Spell, Spell & SingleTarget] = {
    case x: SingleTarget =>
      x
  }

  override def allOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Artificer, 1))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((HeroicCharacterClass.Artificer, 1))

  //  def loadSpells: Seq[(String =>Option[Spell])] = {
  //      List("InflictDamage","RepairLightDamage").map{Spell.withNameOption}
  //
  //  }
  override def spellIds: Set[String] =
    super.spellIds ++ Spell.inflictSpells
// Need to retrieve filtered list of spells based on Artificer, Single Target with Repair / Inflict Damage

}
