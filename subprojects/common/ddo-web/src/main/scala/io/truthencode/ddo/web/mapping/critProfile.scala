/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: critProfile.scala
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
package io.truthencode.ddo.web.mapping

/**
 * encapsulates the critical damage profile
 *
 * @example
 *   a typical weapon may have a damage range of 1-20 with a bonus for certain high rolls. The
 *   scimatar for example may have 1-20, with 18-20 receiving a bonus damage multiplier of double
 *   damage. This is written in terms of 18-20 x3.
 *
 * programatically, this would be represented as
 * {{{
 * val cp = critProfile(min = 18, max=20, multiplier = 2)
 * }}}
 * @param min
 *   minimum damage roll
 * @param max
 *   maximum damage roll
 * @param multiplier
 *   amount to multiply when a given roll is within the min / max range.
 *
 * @todo
 *   may move this to core package
 */
case class critProfile(min: Int, max: Int, multiplier: Int)
