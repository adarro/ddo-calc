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

import io.truthencode.ddo.support.StringUtils.Extensions

/**
  * TODO: Move prefixes to naming package and out of enhancement as they might be used generically for feats / enhancements etc.
  */
trait SpellCriticalPrefix extends Prefix {
  self: DisplayName =>
  override def prefix: Option[String] = Some("Spell Critical")
  abstract override def displayText: String = super.displaySource.replaceNumbersWithRomanNumerals

}
