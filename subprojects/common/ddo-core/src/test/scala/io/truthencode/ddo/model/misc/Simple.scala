/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.misc

import enumeratum.{Enum, EnumEntry}

/**
  * Basic enumeration used for testing / verifying behavior
  */
sealed trait Simple extends EnumEntry

 sealed trait values extends EnumEntry

object Simple extends Enum[Simple] {

  object Basic extends values with Simple

  override def values:  scala.collection.immutable.IndexedSeq[io.truthencode.ddo.model.misc.Simple]= findValues
}