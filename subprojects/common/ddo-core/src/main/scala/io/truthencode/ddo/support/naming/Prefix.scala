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
 * Mixin Used to prefix a name. Useful for categories to alter 'Dwarf' into 'Race:Dwarf'
 */
trait Prefix extends DisplayProperties {

  /**
   * Delimits the prefix and text. Default value is ": "
   */
  protected val prefixSeparator: String = ":"

  /**
   * Optional Prefix, used to separate sub-items such as Spell Critical Schools and also to
   * disambiguate certain entities such as Feat: precision.
   * @return
   *   The optional prefix.
   */
  def prefix: Option[String]

  abstract override def displaySource: String = withPrefix.getOrElse("") + super.displaySource

  def withPrefix: Option[String] = prefix match {
    case Some(p) => Some(s"$p$prefixSeparator")
    case _ => None
  }

  def hasPrefix: Boolean = prefix.isDefined
}
