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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.{Cleric, FavoredSoul, Paladin}
import io.truthencode.ddo.support.requisite.{ClassRequisite, RequiresAnyOfClass}

/**
  * Created by adarr on 4/18/2017.
  */
trait DivineClassBase extends ClassRequisite with RequiresAnyOfClass {
  override def anyOfClass: Seq[(HeroicCharacterClass, Int)] =
    allowedClasses.map((_, minLevel))
  protected val allowedClasses = List(Cleric, FavoredSoul, Paladin)
  protected val minLevel: Int = 1
}