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
package io.truthencode.ddo.support.matching

/**
 * Encapsulates word matching strategies for parsing text and acronyms
 */
object WordMatchStrategies {

  /**
   * Matches whole words with lowercase
   */
  case object FullLowerCaseWordStrategy extends WordMatchStrategy with FullLowerCaseMatchStrategy

  /**
   * Matches whole words with uppercase
   */
  case object FullUpperCaseWordStrategy extends WordMatchStrategy with FullUpperCaseMatchStrategy

  /**
   * Matches whole words using TitleCase
   */
  case object TitleCaseWordStrategy extends WordMatchStrategy with FullUpperCaseMatchStrategy

  /**
   * Matches whole words ignoring and implicitly preserving case
   */
  case object IgnoreCaseWordStrategy extends WordMatchStrategy with FullIgnoreCaseMatchStrategy

}
