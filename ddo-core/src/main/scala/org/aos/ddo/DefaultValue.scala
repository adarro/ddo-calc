/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aos.ddo
/** Used to supply some default value or None if there is no default.
  */
trait DefaultValue[T] {
  /** The default value for the object
    */
  lazy val defaultType: Option[T] = None
  /** True if value is default value, otherwise false.
    * @note will return false if there is no default value is provided.
    */
  def isDefaultValue[T](other: DefaultValue[T]): Boolean = {
    this.defaultType match {
      case Some(x) => other.hasDefaultValue &&  Some(x).equals(other.defaultType) //  == other // this.defaultValue.equals(other.defaultValue)
      case _       => false
    }
  }
  /** True if there is a default value.
    */
  def hasDefaultValue: Boolean = this.defaultType match {
    case Some(x) => true
    case _       => false
  }
}

/** Specifies there is no default value for this object.
  */
trait NoDefault[T] extends DefaultValue[T] {
  override lazy val defaultType: Option[T] = None
}
