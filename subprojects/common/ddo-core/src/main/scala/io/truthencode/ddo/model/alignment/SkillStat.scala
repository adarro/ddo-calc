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
package io.truthencode.ddo.model.alignment

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.skill.Skill
import io.truthencode.ddo.model.stats.StatItem

trait SkillStat extends StatItem[Skill, Int] {

  /**
   * The starting value for the skill. This should generally be 0.
   */
  val baseValue: Int = 0

  /**
   * Represents the amount of skill points put in during leveling process.
   */
  val trainedRanks: Int

  /**
   * additional points from stats / feats etc.
   * @return
   *   modified bonuses or penalties
   */
  def modValue: Int

  /**
   * Retrieves attributes such as STR that provide bonuses / penalties.
   * @return
   */
  def linkedAttributes: Set[Attribute] = item.linkedAttribute
}
