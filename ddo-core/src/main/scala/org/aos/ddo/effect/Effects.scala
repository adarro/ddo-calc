/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *       http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo.effect
/**
  * Base trait representing some effect.
  *
  * Effects can be anything from increasing stats or abilities to
  * granting feats etc.
  */
trait Effects

trait EffectList {
  val effects: List[Effects]
}
/**
  * Adds or reduces a given effect
  *
  * i.e. Adds 3 to STR or 2 to charisma skills etc.
  */
trait AugmentEffect[T] extends Effects {
  val amount: Int
  val effectType: AugmentEffectType
  val effect: T

}

trait NamedEffect extends Effects {
  val name: String
}

trait GrantEffect extends Effects
