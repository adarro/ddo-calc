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

import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Monk
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * You have transcended your former race and are now considered a Lawful Outsider.
  * You have gained Damage Reduction 10 / Epic.
  * Warforged retain most living construct traits.
  */
trait PerfectSelf
    extends FeatRequisiteImpl
    with Passive
    with GrantsToClass
    with RequiresAnyOfClass {
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 20))

  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Monk, 20))
}
