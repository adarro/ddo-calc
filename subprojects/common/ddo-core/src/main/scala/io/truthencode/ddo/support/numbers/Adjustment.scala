/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2026
 *
 * Author: Andre White.
 * FILE: Adjustment.scala
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
package io.truthencode.ddo.support.numbers

import io.truthencode.ddo.enhancement.BonusType

/**
 * Adjustment parameters for attempting to change the value of a given Adjustable Number.
 * @param value
 *   value to add / subtract or set
 * @param kind
 *   determines whether this operation will augment by value, percent or replace existing values
 * @param bonusType
 *   used to determine stacking rules
 * @param sourceId
 *   provides context for some stacking rules in addition to the ability to lookup / add remove
 *   stacks
 */
case class Adjustment(
  value: Int,
  kind: AdjustmentType,
  bonusType: BonusType,
  sourceId: Option[String] = None)
