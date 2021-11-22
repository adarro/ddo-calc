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
/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.truthencode.ddo.model.item.weapon

import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.NoDefault

/**
 * Trait to constrain Proficiency values
 */
sealed trait ProficiencyClass extends EnumEntry with NoDefault[ProficiencyClass]

/**
 * Enumerates the distinct weapon proficiencies.
 */
object ProficiencyClass extends Enum[ProficiencyClass] {

  /**
   * Longbows, Longswords, Scimitars etc
   */
  case object Martial extends ProficiencyClass

  /**
   * Bastard swords, dwarven axes, kopeshes, great crossbows etc
   */
  case object Exotic extends ProficiencyClass

  /**
   * simple daggers and other basic weapons
   */
  case object Simple extends ProficiencyClass

  override val values = findValues
}
