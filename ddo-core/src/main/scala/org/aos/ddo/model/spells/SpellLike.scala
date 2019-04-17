package org.aos.ddo.model.spells

import java.time.Duration

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.effect.{Effect, EffectList}
import org.aos.ddo.model.misc.CoolDown
import org.aos.ddo.model.spells.component.ComponentList
import org.aos.ddo.model.spells.SpellElement.{
  WithCasterClass,
  WithComponents,
  WithCoolDown,
  WithLevelCap,
  WithSpellEffects,
  WithSpellPoints,
  WithSpellSavingThrow,
  WithSpellTarget
}
import org.aos.ddo.support.naming.{DisplayName, FriendlyDisplay}
import org.aos.ddo.support.StringUtils.Extensions

import scala.collection.immutable

/**
  * Base class for spells and spell like abilities
  */
sealed trait SpellLike

sealed trait SpellElement extends SpellLike
object SpellElement {
  sealed trait EmptySpell extends SpellElement
  sealed trait WithSpellResistance extends SpellElement {
    val spellResistance: Boolean = false
  }
  sealed trait WithSpellTarget extends SpellElement {
    val target: List[SpellTarget] = List.empty
  }
  sealed trait WithSpellSavingThrow extends SpellElement {
    val savingThrow: List[SavingThrow] = List.empty
  }

  sealed trait WithSpellPoints extends SpellElement {
    val spellPoints: Int = 0
  }

  sealed trait WithComponents extends SpellElement {
    val components: List[ComponentList] = List.empty
  }
  sealed trait WithLevelCap extends SpellElement with CasterLevelCap

  sealed trait WithSpellEffects extends SpellElement {
    val effects: List[Effect]
  }

  sealed trait WithCoolDown extends SpellElement with CoolDown
  sealed trait WithCasterClass extends SpellElement with CasterLevels
}

sealed trait Spell
    extends SpellLike
    with EnumEntry
    with DisplayName
    with FriendlyDisplay {
  // self: SpellInfo with EffectList =>
  override protected def nameSource: String = entryName.splitByCase.toPascalCase
  val name: String
}

abstract class BaseSpellBuilder[T <: Spell](elements: Seq[SpellElement]) {

  type CompleteSpell = Spell
    with SpellInfo
    with EffectList
    with CasterLevels
    with CoolDown
  def addCoolDown(cd: Option[Duration]): BaseSpellBuilder[T with CoolDown]
  def addCasterClass(
      cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with CasterLevels]

  def build(implicit ev: Spell =:= CompleteSpell): Spell
}

class SpellBuilder[T <: Spell](elements: Seq[SpellElement])
    extends BaseSpellBuilder[T](elements) {

  override def addCoolDown(
      cd: Option[Duration]): BaseSpellBuilder[T with CoolDown] =
    new SpellBuilder[T with CoolDown](elements :+ new WithCoolDown {
      override def coolDown: Option[Duration] = cd
    })

  override def build(implicit ev: Spell =:= CompleteSpell): Spell = ???

  override def addCasterClass(
      cl: Seq[CasterWithLevel]): BaseSpellBuilder[T with CasterLevels] =
    new SpellBuilder[T with CasterLevels](elements :+ new WithCasterClass {
      override def casterLevels: Seq[CasterWithLevel] = cl
    })

  private def buildFromElements(elements: Seq[SpellElement]) = {
    import org.aos.ddo.model.classes.CharacterClass

    var s = createSpell()
    //    elements.reduceLeft {(e,y) => e match {
    //      case x:WithCoolDown => s.copy(coolDown = x.coolDown)
    //    }

    elements.foreach {
      case x: WithCoolDown => s = s.copy(coolDown = x.coolDown)
      case x: WithCasterClass =>
        s = s.copy(casterLevels = new CasterLevels {
          override def casterLevels: Seq[CasterWithLevel] = x.casterLevels
        })
      case x: WithSpellTarget      => s = s.copy(target = x.target)
      case x: WithSpellSavingThrow => s = s.copy(savingThrow = x.savingThrow)
      case x: WithSpellPoints      => s = s.copy(spellPoints = x.spellPoints)
      case x: WithComponents       => s = s.copy(components = x.components)
      case x: WithLevelCap         => s = s.copy(maxCasterLevel = x)
      case x: WithSpellEffects     => s = s.copy(effects = x.effects)
    }
    s

  }
}

private final case class createSpell(
    name: String = "new spell",
    coolDown: Option[Duration] = None,
    spellResistance: Boolean = false,
    target: List[SpellTarget] = List.empty,
    savingThrow: List[SavingThrow] = List.empty,
    spellPoints: Int = 0,
    components: List[ComponentList] = List.empty,
    maxCasterLevel: CasterLevelCap = new CasterLevelCap {
      override val baseLevelCap: Option[Int] = None
    },
    casterLevels: CasterLevels = new CasterLevels {
      override def casterLevels: Seq[CasterWithLevel] = Seq.empty
    },
    effects: List[Effect] = List.empty)
    extends Spell
    with SpellInfo
    with EffectList

trait BullsStrength extends Spell with SpellInfo with EffectList

object Spell extends Enum[Spell] {

  override def values: immutable.IndexedSeq[Spell] = findValues
}

trait SpellLikeAbility extends SpellLike
