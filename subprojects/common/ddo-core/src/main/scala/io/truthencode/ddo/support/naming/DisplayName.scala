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
 * Wrapper class to store / convert EnumEntry IDs with their expanded text representation. Mainly
 * needed with special cases like Sub-feats (Weapon Focus: Bludgeon). Can Mix in additional
 * manipulators.
 */
trait DisplayName extends DisplayProperties {

  /**
   * @inheritdoc
   */
  override def displaySource: String = nameSource

  /**
   * Sets or maps the source text for the DisplayName.
   *
   * @return
   *   Source text.
   */
  protected def nameSource: String
}
