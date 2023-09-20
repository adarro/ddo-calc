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
package io.truthencode.ddo.support

/**
 * Represents item modifier effects that can be prefixed or suffixed to an item to provide
 * additional effects. Items can generally have up to two prefixes and one suffix, such as Flaming
 * Sword of the Sun's Fury, where Flaming and "Sun's Fury' are affixes.
 */
sealed trait AffixSlot {
  def addSlot(s: AffixSlot): Unit
}

trait PrimaryPreSlot extends AffixSlot {
  type Pre = PrimaryPreSlot
  val pref: Pre = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref)
}

trait SecondaryPreSlot extends AffixSlot {
  type Pre2 = SecondaryPreSlot
  val pref2: Pre2 = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(pref2)
}

trait PostSlot extends AffixSlot {
  type Post = PostSlot
  val post: Post = this

  abstract override def addSlot(s: AffixSlot): Unit = super.addSlot(post)
}
