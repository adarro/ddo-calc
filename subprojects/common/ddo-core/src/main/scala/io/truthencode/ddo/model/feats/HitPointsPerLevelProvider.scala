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

trait HitPointsPerLevelProvider {

  /**
   * 3 at first level, and adds +1 for each other level. Technically
   *
   * @example
   *   val currentLevel = 3 calculateHitPointsPerLevel(3) // 5 // Officially this would be Seq(3) ++ (2 to
   *   levelCap).map(_ => 1).sum // Effectively this is currentLevel + 2
   * @param currentLevel
   *   Current Character Level
   * @return
   *   Additional Hit Points
   */
  def calculateHitPointsPerLevel(currentLevel: Int): Int = {
    currentLevel + 2
  }
}
