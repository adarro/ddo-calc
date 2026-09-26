/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021
 *
 * Author: Andre White.
 * FILE: EfficientMetaMagicsPrefix.scala
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
package io.truthencode.ddo.core.support.naming

import io.truthencode.ddo.core.StringUtils.Extensions

/**
 * Adds Spell Critical: prefix to the item.
 */
trait EfficientMetaMagicsPrefix extends Prefix {
  self: DisplayName =>
  override def prefix: Option[String] = Some("EfficientMetaMagics")
  abstract override def displayText: String = super.displaySource.replaceNumbersWithRomanNumerals

}
