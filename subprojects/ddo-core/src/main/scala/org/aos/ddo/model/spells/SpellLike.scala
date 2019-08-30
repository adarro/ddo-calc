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
package org.aos.ddo.model.spells

import org.aos.ddo.model.effect.EffectList
import org.aos.ddo.model.spells.component.ComponentList
import org.aos.ddo.model.spells.SpellElement._

import scala.collection.immutable

object SpellBuilder {
  //  def apply[T <: Spell](name: String): SpellBuilder[T with EmptySpell with WithName] = apply[T with EmptySpell with WithName](Seq(UseSpellName(name)))

  def apply(): SpellBuilder[EmptySpell] = apply[EmptySpell](Set()) // new SpellBuilder(elements)

  def apply(name: String): SpellBuilder[EmptySpell with WithName] = apply[EmptySpell with WithName](Set(UseSpellName(name))) // new SpellBuilder(elements)

  def apply[T <: Spell](ingredients: Set[SpellElement]): SpellBuilder[T] = new SpellBuilder[T](ingredients)

  // def apply(): SpellBuilder[Pizza.EmptyPizza] = apply[Pizza.EmptyPizza](Seq())
}

protected abstract class BaseSpellBuilder[T <: Spell] protected(elements: Set[SpellElement]) {

  type CompleteSpell = EmptySpell
    with WithName
    with WithSpellInfo
    with WithSpellEffects
    with WithCasterClass
    with WithTarget
    with WithSpellSavingThrow
    with WithSpellPoints
    with WithComponents
    with WithLevelCap
  //  with CoolDown

  def addName(name: String): BaseSpellBuilder[T with WithName]

  def addSpellInfo(si: SpellInfo): BaseSpellBuilder[T with WithSpellInfo]

  // SpellInfo
  //  def addCoolDown(cd: Option[Duration]): BaseSpellBuilder[T with CoolDown]

  def addCasterClass(cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with WithCasterClass]

  def addSpellTarget(target: List[SpellTarget]): BaseSpellBuilder[T with WithTarget]

  def addSavingThrow(saves: List[SavingThrow]): BaseSpellBuilder[T with WithSpellSavingThrow]

  def addSpellPoints(sp: Int): BaseSpellBuilder[T with WithSpellPoints]

  def addComponents(component: List[ComponentList]): BaseSpellBuilder[T with WithComponents]

  def addLevelCap(cl: CasterLevelCap): BaseSpellBuilder[T with WithLevelCap]

  def addEffect(eff: EffectList): BaseSpellBuilder[T with WithSpellEffects]

  /**
    * Adds Spell Resistance Information
    *
    * @param sr Spell Resistance
    * @return Builder with Spell Resistance information
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

  override def build(implicit ev: T =:= CompleteSpell): Spell = SpellDescriptor(elements)

  // {
  //    val e: Spell =:= CompleteSpell = ev
  //   createSpell
  //    // val s: Spell
  //    null
  //
  // }

  override def addCasterClass(cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with WithCasterClass] = {
    SpellBuilder[T with WithCasterClass](elements + UseCasterClass {
      cl.toSet
    })
  }

  override def addSpellTarget(targets: List[SpellTarget]): BaseSpellBuilder[T with WithTarget] =
    SpellBuilder[T with WithTarget](elements + UseSpellTarget {
      targets
    })

  override def addSavingThrow(saves: List[SavingThrow]): BaseSpellBuilder[T with WithSpellSavingThrow] =
    SpellBuilder[T with WithSpellSavingThrow](elements + UseSpellSavingThrow {
      saves
    })

  override def addSpellPoints(sp: Int): BaseSpellBuilder[T with WithSpellPoints] =
    SpellBuilder[T with WithSpellPoints](elements + UseSpellPoints {
      sp
    })

  override def addComponents(component: List[ComponentList]): BaseSpellBuilder[T with WithComponents] =
    SpellBuilder[T with WithComponents](elements + UseComponents {
      component
    })

  override def addLevelCap(cl: CasterLevelCap): BaseSpellBuilder[T with WithLevelCap] =
    SpellBuilder[T with WithLevelCap](elements + UseLevelCap {
      cl.baseLevelCap
    })

  override def addEffect(eff: EffectList): BaseSpellBuilder[T with WithSpellEffects] =
    SpellBuilder[T with WithSpellEffects](elements + UseSpellEffects {
      eff.effects
    })

  override def addSpellInfo(si: SpellInfo): BaseSpellBuilder[T with WithSpellInfo] =
    SpellBuilder[T with WithSpellInfo](
      elements + UseSpellInfo(
        coolDown = si.coolDown,
        savingThrow = si.savingThrow,
        sr = si.sr,
        target = si.target,
        components = si.components,
        spellPoints = si.spellPoints)
    )

  override def addName(name: String): BaseSpellBuilder[T with WithName] = SpellBuilder[T with WithName](elements + UseSpellName(name)

  )

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
    * @param sr Spell Resistance
    * @return Builder with Spell Resistance information
    */
  override def addSpellResistance(sr: SpellResistance): BaseSpellBuilder[T] = {
    val info: immutable.Set[WithSpellInfo] = elements.extract[WithSpellInfo].toSet
    val wsr: immutable.Set[WithSpellResistance] = elements.extract[WithSpellResistance].toSet
    if (info.nonEmpty) {
      SpellBuilder[T](elements + UseSpellResistance {
        sr.sr
      })
    }
    SpellBuilder[T](elements + UseSpellResistance {
      sr.sr
    })
  }
}





