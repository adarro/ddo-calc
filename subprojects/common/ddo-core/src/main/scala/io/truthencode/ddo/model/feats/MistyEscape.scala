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
import io.truthencode.ddo.model.classes.HeroicCharacterClass.Warlock
import io.truthencode.ddo.support.requisite.{
  FeatRequisiteImpl,
  GrantsToClass,
  RequiresAllOfClass,
  RequiresAllOfFeat
}

import java.time.Duration

/**
 * [[https://ddowiki.com/page/Misty_Escape Misty Escape]] You become invisible and charge forward.
 * During your escape, you move through monsters as if you were ethereal. These effects last for six
 * seconds.
 */
protected[feats] trait MistyEscape
  extends FeatRequisiteImpl with ActiveFeat with OnSpellLikeAbilityEvent with RequiresAllOfClass
  with RequiresAllOfFeat with GrantsToClass { self: ClassFeat =>

  override def grantToClass: Seq[(HeroicCharacterClass, Int)] =
    Seq((Warlock, 15))

  override def allOfFeats: Seq[Feat] = Seq(ClassFeat.PactFey)

  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(25))
}
