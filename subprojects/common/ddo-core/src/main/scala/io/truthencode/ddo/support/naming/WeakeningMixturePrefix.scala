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
package io.truthencode.ddo.support.naming

/**
  * Created by adarr on 3/5/2017.
  * Display Prefix for Spell Like Abilities when they are included in wiki description
  *
  * @example Alchemist Enhancement Rapid Condensation appears as SLA: Rapid Condensation
  */
trait WeakeningMixturePrefix extends Prefix {
  self: DisplayName with DisplayProperties =>
  override def prefix: Option[String] = Some("Weakening Mixture")

    override protected val prefixSeparator: String = ""

}
