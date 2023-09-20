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
package io.truthencode.ddo

/**
 * Encapsulates the stacking rules for purposes of adding / combining bonuses from multiple sources.
 */
sealed trait StackingRule

/**
 * Benefits from this stack from Any source, including itself.
 * @example
 *   Mythic bonuses
 */
trait StacksWithAny extends StackingRule

/**
 * The most common rule. Multiple effects will not stack and only the highest applies.
 */
trait NonStacking extends StackingRule

/**
 * Miscellaneous bonuses coming from the same source don't stack (ie - 2 paladins' aura).
 */
trait StacksWithUnique extends StackingRule
