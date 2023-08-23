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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.activation.OnSpellLikeAbilityEvent
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Paladin
import io.truthencode.ddo.support.requisite._

/**
 * [[http://ddowiki.com/page/Remove_Disease Remove Disease]] At 6th level, a paladin can produce a
 * remove disease effect, as the spell, once per rest period. It can use this ability one additional
 * time per rest period for every three levels after 6th (twice per rest at 9th level).
 * @note
 *   NOTE: Since Update 14, Remove Disease grants immunity to disease for 6 seconds per caster
 *   level. The in-game tooltip incorrectly says the spell does not prevent re-infection.
 * @todo
 *   add Times per rest
 */
protected[feats] trait RemoveDisease
  extends FeatRequisiteImpl with ActiveFeat with OnSpellLikeAbilityEvent with GrantsToClass
  with FreeFeat {
  self: ClassFeat =>
  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    List((Paladin, 6))

}
