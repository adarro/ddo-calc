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
package io.truthencode.ddo.model.abilities

import io.truthencode.ddo.support.naming.{DisplayName, UsingSearchPrefix}

trait UsingAbilitySearchPrefix extends UsingSearchPrefix {
  self: DisplayName =>

  /**
   * Adds the search prefix used for uniquely identifying and disambiguating the skill in cases of a
   * name conflict. Also aids object creation when associating the Parent Entity
   */
  override val withPrefix: String = s"$searchPrefix$nameSource"

  override def searchPrefixSource: String = ActiveAbilities.searchPrefix

}
