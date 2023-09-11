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

import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
 * Base Trait for Half-Elf Racial Feat family (Half-Elf Dilettante: X)
 */
trait HalfElfDilettantePreFix extends Prefix {
  self: DisplayName with FriendlyDisplay =>

  /**
   * Delimits the prefix and text.
   */
  override protected val prefixSeparator: String = ": "

  override def prefix: Option[String] = Some("Half-Elf Dilettante")
}
