/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SpellLike.scala
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
package io.truthencode.ddo.model.spells

import io.truthencode.ddo.model.effect.EffectList
import io.truthencode.ddo.model.spells.SpellElement._
import io.truthencode.ddo.model.spells.component.ComponentList

import scala.collection.immutable

object SpellBuilder {
  //  def apply[T <: Spell](name: String): SpellBuilder[T with EmptySpell with WithName] = apply[T with EmptySpell with WithName](Seq(UseSpellName(name)))

  def apply(): SpellBuilder[EmptySpell] =
    apply[EmptySpell](Set()) // new SpellBuilder(elements)

  def apply(name: String): SpellBuilder[EmptySpell & WithName] =
    apply[EmptySpell & WithName](Set(UseSpellName(name))) // new SpellBuilder(elements)

  def apply[T <: Spell](ingredients: Set[SpellElement]): SpellBuilder[T] =
    new SpellBuilder[T](ingredients)

  // def apply(): SpellBuilder[Pizza.EmptyPizza] = apply[Pizza.EmptyPizza](Seq())
}

abstract protected class BaseSpellBuilder[T <: Spell] protected (
  elements: Set[SpellElement]
) {

  type CompleteSpell = EmptySpell & WithName & WithSpellInfo & WithSpellEffects & WithCasterClass &
    WithTarget & WithSpellSavingThrow & WithSpellPoints & WithComponents & WithLevelCap
  //  with CoolDown

  def addName(name: String): BaseSpellBuilder[T & WithName]

  def addSpellInfo(si: SpellInfo): BaseSpellBuilder[T & WithSpellInfo]

  // SpellInfo
  //  def addCoolDown(cd: Option[Duration]): BaseSpellBuilder[T with CoolDown]

  def addCasterClass(
    cl: Seq[CasterWithLevel]
  ): BaseSpellBuilder[T & WithCasterClass]

  def addSpellTarget(
    target: List[SpellTarget]
  ): BaseSpellBuilder[T & WithTarget]

  def addSavingThrow(
    saves: List[SavingThrow]
  ): BaseSpellBuilder[T & WithSpellSavingThrow]

  def addSpellPoints(sp: Int): BaseSpellBuilder[T & WithSpellPoints]

  def addComponents(
    component: List[ComponentList]
  ): BaseSpellBuilder[T & WithComponents]

  def addLevelCap(cl: CasterLevelCap): BaseSpellBuilder[T & WithLevelCap]

  def addEffect(eff: EffectList): BaseSpellBuilder[T & WithSpellEffects]

  /**
   * Adds Spell Resistance Information
   *
   * @param sr
   *   Spell Resistance
   * @return
   *   Builder with Spell Resistance information
   */
  def addSpellResistance(sr: SpellResistance): BaseSpellBuilder[T]

  def build(implicit ev: T =:= CompleteSpell): Spell
}

class SpellBuilder[T <: Spell](elements: Set[SpellElement] = Set.empty)
  extends BaseSpellBuilder[T](elements) {

  //  override def addCoolDown(
  //                            cd: Option[Duration]): BaseSpellBuilder[T with CoolDown] =
  //    SpellBuilder[T with CoolDown](elements :+ UseCoolDown {
  //      cd
  //    })

  override def build(implicit ev: T =:= CompleteSpell): Spell =
    SpellDescriptor(elements)

  // {
  //    val e: Spell =:= CompleteSpell = ev
  //   createSpell
  //    // val s: Spell
  //    null
  //
  // }

  override def addCasterClass(
    cl: Seq[CasterWithLevel]
  ): BaseSpellBuilder[T & WithCasterClass] = {
    SpellBuilder[T & WithCasterClass](elements + UseCasterClass {
      cl.toSet
    })
  }

  override def addSpellTarget(
    targets: List[SpellTarget]
  ): BaseSpellBuilder[T & WithTarget] =
    SpellBuilder[T & WithTarget](elements + UseSpellTarget {
      targets
    })

  override def addSavingThrow(
    saves: List[SavingThrow]
  ): BaseSpellBuilder[T & WithSpellSavingThrow] =
    SpellBuilder[T & WithSpellSavingThrow](elements + UseSpellSavingThrow {
      saves
    })

  override def addSpellPoints(
    sp: Int
  ): BaseSpellBuilder[T & WithSpellPoints] =
    SpellBuilder[T & WithSpellPoints](elements + UseSpellPoints {
      sp
    })

  override def addComponents(
    component: List[ComponentList]
  ): BaseSpellBuilder[T & WithComponents] =
    SpellBuilder[T & WithComponents](elements + UseComponents {
      component
    })

  override def addLevelCap(
    cl: CasterLevelCap
  ): BaseSpellBuilder[T & WithLevelCap] =
    SpellBuilder[T & WithLevelCap](elements + UseLevelCap {
      cl.baseLevelCap
    })

  override def addEffect(
    eff: EffectList
  ): BaseSpellBuilder[T & WithSpellEffects] =
    SpellBuilder[T & WithSpellEffects](elements + UseSpellEffects {
      eff.effects
    })

  override def addSpellInfo(
    si: SpellInfo
  ): BaseSpellBuilder[T & WithSpellInfo] =
    SpellBuilder[T & WithSpellInfo](
      elements + UseSpellInfo(
        coolDown = si.coolDown,
        savingThrow = si.savingThrow,
        sr = si.sr,
        target = si.target,
        components = si.components,
        spellPoints = si.spellPoints,
        range = si.range
      )
    )

  override def addName(name: String): BaseSpellBuilder[T & WithName] =
    SpellBuilder[T & WithName](elements + UseSpellName(name))

  /*
  private[this] def buildFromElements(elements: Seq[SpellElement], name: String) = {

    var s = createSpell(name = name)
    //    elements.reduceLeft {(e,y) => e match {
    //      case x:WithCoolDown => s.copy(coolDown = x.coolDown)
    //    }

    elements.foreach {
      case x: WithSpellInfo => s = s.copy(name = s.name,
        coolDown = x.coolDown,
        spellResistance = x.spellResistance,
        target = x.target,
        savingThrow = x.savingThrow,
        spellPoints = x.spellPoints,
        components = x.components)
      case x: UseCoolDown => s = s.copy(coolDown = x.coolDown)
      case x: WithCasterClass =>
        s = s.copy(casterLevels = new CasterLevels {
          override def casterLevels: Seq[CasterWithLevel] = x.casterLevels
        })
      case x: WithSpellTarget => s = s.copy(target = x.target)
      case x: WithSpellSavingThrow => s = s.copy(savingThrow = x.savingThrow)
      case x: WithSpellPoints => s = s.copy(spellPoints = x.spellPoints)
      case x: WithSpellEffects => s = s.copy(effects = x.effects)
      case x: WithComponents => s = s.copy(components = x.components)
      case x: WithLevelCap => s = s.copy(maxCasterLevel = x)
      case x: WithName => s = s.copy(name = x.name)

    }
    s

  } */

  /**
   * Adds Spell Resistance Information
   *
   * @param sr
   *   Spell Resistance
   * @return
   *   Builder with Spell Resistance information
   */
  override def addSpellResistance(sr: SpellResistance): BaseSpellBuilder[T] = {
    val info: immutable.Set[WithSpellInfo] =
      elements.extract[WithSpellInfo].toSet
    val wsr: immutable.Set[WithSpellResistance] =
      elements.extract[WithSpellResistance].toSet
    if info.nonEmpty then {
      SpellBuilder[T](elements + UseSpellResistance {
        sr.sr
      })
    }
    SpellBuilder[T](elements + UseSpellResistance {
      sr.sr
    })
  }
}
