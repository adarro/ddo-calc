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
package io.truthencode.ddo.model.enhancement
import io.truthencode.ddo.support.StringUtils.Extensions
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay}

import scala.collection.immutable

/**
  * An enhancement Tier separates Enhancements into various levels and require increasing pre-requisites such as a certain
  * number of Action points spent
  */
sealed trait Tier extends EnumEntry with DisplayName with FriendlyDisplay {
  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

trait Core extends Tier
trait Tier1 extends Tier
trait Tier2 extends Tier
trait Tier3 extends Tier
trait Tier4 extends Tier
trait Tier5 extends Tier

object Tier extends Enum[Tier] {
  case object Core extends Tier
  case object Tier1 extends Tier
  case object Tier2 extends Tier
  case object Tier3 extends Tier
  case object Tier4 extends Tier
  case object Tier5 extends Tier
  override def values: immutable.IndexedSeq[Tier] = findValues
}
