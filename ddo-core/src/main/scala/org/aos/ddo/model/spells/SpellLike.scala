package org.aos.ddo.model.spells

import java.time.Duration

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.effect.{Effect, EffectList}
import org.aos.ddo.model.misc.CoolDown
import org.aos.ddo.model.spells.component.ComponentList
import org.aos.ddo.model.spells.SpellElement._
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.StringUtils.Extensions

import scala.collection.immutable

object SpellBuilder {
  def apply[T <: Spell](ingredients: Seq[SpellElement]): SpellBuilder[T] = new SpellBuilder[T](ingredients)

  // def apply(): SpellBuilder[Pizza.EmptyPizza] = apply[Pizza.EmptyPizza](Seq())
}

protected abstract class BaseSpellBuilder[T <: Spell](elements: Seq[SpellElement]) {

  type CompleteSpell = Spell
    with SpellInfo
    with EffectList
    with CasterLevels
    with CoolDown

  def addSpellInfo(si: SpellInfo): BaseSpellBuilder[T with SpellInfo]

  // SpellInfo
  //  def addCoolDown(cd: Option[Duration]): BaseSpellBuilder[T with CoolDown]

  def addCasterClass(
                      cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with CasterLevels]

  def addSpellTarget(target: List[SpellTarget]): BaseSpellBuilder[T with SpellTarget]

  def addSavingThrow(saves: List[SavingThrow]): BaseSpellBuilder[T with WithSpellSavingThrow]

  def addSpellPoints(sp: Int): BaseSpellBuilder[T with WithSpellPoints]

  def addComponents(component: List[ComponentList]): BaseSpellBuilder[T with WithComponents]

  def addLevelCap(cl: CasterLevelCap): BaseSpellBuilder[T with WithLevelCap]

  def addEffect(eff: EffectList): BaseSpellBuilder[T with WithSpellEffects]

  def build(implicit ev: Spell =:= CompleteSpell): Spell
}

class SpellBuilder[T <: Spell](elements: Seq[SpellElement] = Seq.empty)
  extends BaseSpellBuilder[T](elements) {

  //  override def addCoolDown(
  //                            cd: Option[Duration]): BaseSpellBuilder[T with CoolDown] =
  //    SpellBuilder[T with CoolDown](elements :+ UseCoolDown {
  //      cd
  //    })

  override def build(implicit ev: Spell =:= CompleteSpell): Spell = SpellDescriptor(elements)

  // {
  //    val e: Spell =:= CompleteSpell = ev
  //   createSpell
  //    // val s: Spell
  //    null
  //
  // }

  override def addCasterClass(
                               cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with CasterLevels] =
    SpellBuilder[T with CasterLevels](elements :+ UseCasterClass {
      cl
    })

  override def addSpellTarget(targets: List[SpellTarget]): BaseSpellBuilder[T with SpellTarget] =
    SpellBuilder[T with SpellTarget](elements :+ UseSpellTarget {
      targets
    })

  override def addSavingThrow(saves: List[SavingThrow]): BaseSpellBuilder[T with WithSpellSavingThrow] =
    SpellBuilder[T with WithSpellSavingThrow](elements :+ UseSpellSavingThrow {
      saves
    })

  override def addSpellPoints(sp: Int): BaseSpellBuilder[T with WithSpellPoints] =
    SpellBuilder[T with WithSpellPoints](elements :+ UseSpellPoints {
      sp
    })

  override def addComponents(component: List[ComponentList]): BaseSpellBuilder[T with WithComponents] =
    SpellBuilder[T with WithComponents](elements :+ UseComponents {
      component
    })

  override def addLevelCap(cl: CasterLevelCap): BaseSpellBuilder[T with WithLevelCap] =
    SpellBuilder[T with WithLevelCap](elements :+ UseLevelCap {
      cl.baseLevelCap
    })

  override def addEffect(eff: EffectList): BaseSpellBuilder[T with WithSpellEffects] =
    SpellBuilder[T with WithSpellEffects](elements :+ UseSpellEffects {
      eff.effects
    })

  override def addSpellInfo(si: SpellInfo): BaseSpellBuilder[T with SpellInfo] =
    SpellBuilder[T with SpellInfo](
      elements :+ UseSpellInfo(
        coolDown = si.coolDown,
        savingThrow = si.savingThrow,
        spellResistance = si.spellResistance,
        target = si.target,
        components = si.components,
        spellPoints = si.spellPoints)
    )

  private def buildFromElements(elements: Seq[SpellElement]) = {

    var s = createSpell()
    //    elements.reduceLeft {(e,y) => e match {
    //      case x:WithCoolDown => s.copy(coolDown = x.coolDown)
    //    }

    elements.foreach {
      case x: WithSpellInfo => s = s.copy(name = "new spell",
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

    }
    s

  }
}





