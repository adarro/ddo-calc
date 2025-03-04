/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EfficientMetamagicPrefix.scala
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
 * Created by adarr on 3/5/2017. Display Prefix for meta magic enhancements
 *
 * @example
 *   Alchemist Enhancement Efficient Metagagics in Bombardier line has selectors for Efficient
 *   Empower
 *
 * @note
 *   it expects the specific meta magic name to be set via namesource such that
 *   withPrefix.getOrElse("").namesource would produce 'Efficient Empower' This can be further
 *   suffixed i.e. RomanNumerals
 */
trait EfficientMetamagicPrefix extends Prefix {
  self: DisplayName & DisplayProperties =>
  override protected val prefixSeparator: String = " "

  override def prefix: Option[String] = Some("Efficient")

}
