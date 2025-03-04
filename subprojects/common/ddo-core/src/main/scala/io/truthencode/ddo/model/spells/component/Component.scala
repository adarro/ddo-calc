/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Component.scala
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
package io.truthencode.ddo.model.spells.component

import io.truthencode.ddo.model.spells.ComponentType

/**
 * A component required to enact a spell.
 */
sealed trait Component {
  val componentType: ComponentType
}

/**
 * [[http://ddowiki.com/page/Material_component Material Components]] are one or more physical
 * substances or objects that are annihilated by the spell energies in the casting process. In DDO,
 * all the spells of the same level and class which require a material component use the same item.
 * Those items are sold by reagent vendors around Stormreach. They stack together up to 1000, so a
 * typical spellcaster will keep hundreds of components at hand for each of the spell levels he can
 * cast.
 *
 * @note
 *   This is mostly included for completeness to allow the user to see this may require inventory
 *   space but has no other real impact on values except on whether or not they wish to use an
 *   Eschew Material effect.
 */
trait MaterialComponent extends Component {
  override val componentType: ComponentType = ComponentType.MaterialComponent

  /**
   * The name of the component.
   *
   * Some components are only used for spell casting while others are also actual items such as a
   * potion of Bull's Strength for Tenser's Transformation.
   */
  val id: String
}

/**
 * [[http://ddowiki.com/page/Somatic_component Somatic Component]] requires physical gesturing and
 * thus may be affected by [[http://ddowiki.com/page/Arcane_spell_failure arcane spell failure]] or
 * other cases where motion is encumbered.
 */
trait SomaticComponent extends Component {
  override val componentType: ComponentType = ComponentType.SomaticComponent
}

/**
 * [[http://ddowiki.com/page/Verbal_component Verbal Components]] require the ability to speak.
 * Silence / deafness can affect the ability to cast.
 */
trait VerbalComponent extends Component {
  override val componentType: ComponentType = ComponentType.VerbalComponent
}
