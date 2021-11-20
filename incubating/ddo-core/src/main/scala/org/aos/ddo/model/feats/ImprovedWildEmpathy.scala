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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Druid, Ranger}
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAnyOfClass
}

/**
  * This feat allows a ranger to charm an animal (similar to the Charm monster spell) for 5 minutes.
  * It can be used 3 times / rest period with a 60 second cool down.
  *
  * Target: Animal, Magical Beast
  * Save: Will
  */
protected[feats] trait ImprovedWildEmpathy
    extends FeatRequisiteImpl
    with Active
    with GrantsToClass
    with RequiresAnyOfClass {
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 7), (Ranger, 7))

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Druid, 7), (Ranger, 7))
}
