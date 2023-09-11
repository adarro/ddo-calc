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
package io.truthencode.ddo.support

trait DefaultType {
  self: DefaultType =>
  type Storage
  type SelfMap = Map[String, Any]

  /**
   * The default value for the object
   */
  lazy val defaultType: Option[Storage] = None

  /**
   * True if value is default value, otherwise false.
   * @note
   *   will return false if there is no default value is provided.
   */
  def isDefaultType[MyDefault](other: MyDefault): Boolean = {
    this.defaultType match {
      case Some(x) => x == other // this.defaultValue.equals(other.defaultValue)
      case _ => false
    }
  }

  /**
   * True if there is a default value.
   */
  def hasDefaultType: Boolean = this.defaultType match {
    case Some(_) => true
    case _ => false
  }
}
