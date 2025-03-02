/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SimpleEnum.scala
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

import enumeratum.{Enum, EnumEntry}
import scala.collection.immutable

sealed trait SimpleEnum extends EnumEntry

sealed trait Other extends SimpleEnum

object SimpleEnum extends Enum[SimpleEnum] {
//    class Derived extends SimpleEnum

  override def values: immutable.IndexedSeq[SimpleEnum] = findValues

  case object E1 extends Other

  case object E2 extends Other
}
