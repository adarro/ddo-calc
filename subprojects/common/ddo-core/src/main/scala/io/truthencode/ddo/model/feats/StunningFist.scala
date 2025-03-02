/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StunningFist.scala
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
import io.truthencode.ddo.model.feats.ClassFeat.FlurryOfBlows
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

import java.time.Duration

/**
 * ![Feat stunningfist.png](/images/Feat_stunningfist.png)
 *
 * Stunning Fist
 *
 * * **Cost:** 15 [Ki](/page/Ki "Ki") * **[Cooldown](/page/Cooldown "Cooldown"):** 6 seconds *
 * **Usage:** [Active](/page/Active_feat "Active feat") * **Prerequisite:** [Flurry of
 * Blows](/page/Flurry_of_Blows "Flurry of Blows")
 *
 * A swift unarmed attack to vulnerable areas that cause your target to be [stunned](/page/Stunned
 * "Stunned") for 6 seconds if it fails a ([DC](/page/DC "DC") = 10 + half character levels +
 * [Wis](/page/Wis "Wis") mod + other modifiers) [Fortitude save](/page/Fortitude_save "Fortitude
 * save").
 *
 * ### Notes
 *
 * For many players, Stunning Fist is often the first, if not the preferred, initial attack that a
 * Monk will make. It can be used over twice as often as [Stunning Blow](/page/Stunning_Blow
 * "Stunning Blow"), which has a 15 second cooldown. With enough ki, a monk could keep an enemy in a
 * state of permanent stun. The [DC](/page/Difficulty_Class "Difficulty Class") is 10 + half
 * Character level + Wisdom modifier + [Stunning](/page/Stunning "Stunning") item bonus. Stunning
 * Fist's effectiveness is augmented by a Monk's WIS modifier as well as any items with
 * [Stunning](/page/Stunning "Stunning") enhancements. Any item, [Epic Destiny](/page/Epic_Destiny
 * "Epic Destiny") ability, or [Epic Destiny Feats](/page/Epic_Destiny_Feats "Epic Destiny Feats")
 * which boosts [Tactics DC](/page/Tactical_feat "Tactical feat") will also increase the
 * effectiveness of Stunning Fist. Stunning Fist does work on living constructs (such as
 * [Warforged](/page/Warforged "Warforged")). It does not work on constructs, elementals or undead.
 * Stunning Fist can only be used while wielding [Handwraps](/page/Handwraps "Handwraps"). Stunning
 * Fist does not work in Druid [Wild Shape](/page/Wild_Shape "Wild Shape") even while wearing
 * handwraps or unarmed. A [Monk](/page/Monk "Monk") may select this feat as one of his [martial
 * arts feats](/page/Martial_arts_feats "Martial arts feats").
 * @todo
 *   Likely need to split tactical feats into True Tactical such as Trip vs Monk (Stunning Fist) to
 *   catch Tatical DC bonuses accurately
 */
protected[feats] trait StunningFist
  extends FeatRequisiteImpl with TriggeredActivationImpl with BonusSelectableToClassFeatImpl
  with ActiveFeat with Tactical with RequiresAllOfFeat with MartialArtsFeat {
  self: GeneralFeat =>
  override def allOfFeats: Seq[Feat] = Seq(FlurryOfBlows)

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(6))
}
