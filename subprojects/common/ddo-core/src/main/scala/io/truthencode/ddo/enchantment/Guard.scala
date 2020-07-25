/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.enchantment

import com.wix.accord._
import com.wix.accord.dsl._
import io.truthencode.ddo.enchantment.Modifier._
import io.truthencode.ddo.model.effect.{Passive, _}

trait GuardFlag {
  val guard: Guards
}

object Guard extends ((Guards, Option[GuardModifier]) => Guard) {
  implicit val guardValidator: Validator[Guard] = validator[Guard] { g => {

    g.effects is notEmpty

  }
  }
  type Parameters = (Guards, Option[GuardModifier])

  def apply(guard: Guards, affixes: Option[GuardModifier] = None): Guard = {
    val o = create(guard, affixes)
    assert(validate(o) == Success)
    o
  }

  def apply(parameters: Parameters): Guard =
    Guard(parameters._1, parameters._2)

  private def create(guard: Guards, affixes: Option[GuardModifier]): Guard = {
    new Guard(guard, affixes) {
      private def readResolve(): Object =
        Guard(guard, affixes)

      def copy(guard: Guards, affixes: Option[GuardModifier]): Guard =
        Guard(guard, affixes)

      val tuple: Guard.Parameters =
        (guard, affixes)
    }
  }

  def modifier(affixes: Option[GuardModifier]): Int = {
    affixes match {
      case Some(afx) =>
        Modifier.withNameInsensitiveOption(afx.prefix.getOrElse("")) match {
          case Some(Minor) => 1
          case Some(Lesser) => 2
          case Some(Greater) => 3
          case Some(Superior) => 4
          case Some(Epic) => 5
          case _ => 0
        }
      case _ => 0
    }
  }


}

abstract case class Guard private[Guard](
                                          override val guard: Guards, affixes: Option[GuardModifier]) extends Enchantment with GuardFlag with Passive {
  def copy(guard: Guards, affixes: Option[GuardModifier]): Guard

  val effects: List[Effect] = Nil

}
