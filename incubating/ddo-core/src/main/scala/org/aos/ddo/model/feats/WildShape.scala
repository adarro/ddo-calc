/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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

import io.truthencode.ddo.support.naming.Prefix
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
  *Activate this ability to lower an animal's or beast's aggression, effectively mesmerizing them.
  *
  * Target: Enemy animals and magical beasts
  * Duration: 5 Min
  * Save: Will
  */
protected[feats] trait WildShape
    extends FeatRequisiteImpl
    with Active
    with Prefix { self: GrantsToClass with RequiresAllOfClass =>

  override def prefix: Option[String] = Some("Wild Shape")

  override protected val prefixSeparator: String = ": "
}
