/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: HurlThroughHell.scala
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
import io.truthencode.ddo.model.alignment.{AlignmentType, MoralAxis}
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Warlock
import io.truthencode.ddo.support.requisite.*

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Hurl_through_Hell Hurl through Hell]] Exile one target enemy from this
 * existence. An enemy succeeding on a Will save vs DC(10 + Warlock level + Charisma Modifier) is
 * instead paralyzed and helpless with fear for 6 seconds.
 */
protected[feats] trait HurlThroughHell
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with AtWillEvent
  with RequiresAllOfClass with RequiresAllOfFeat with AlignmentRequisiteImpl with RequiresNoneOfAxis
  with GrantsToClass {
  self: ClassFeat =>

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    Seq((Warlock, 15))

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(25))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFiend)

  /**
   * Allowed providing Character posseses NONE of these alignments
   *
   * @return
   *   Restricted alignments
   */
  override def noneOfAlignmentType: Seq[AlignmentType] = Seq(MoralAxis.Good)

  override protected def nameSource: String = "Hurl Through Hell"
}
