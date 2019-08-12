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

trait SpellTraits

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

  sealed trait WithSpellEffects extends SpellElement with EffectList {
    val effects: List[Effect]
  }

  sealed trait WithTarget extends SpellElement {
    val targets: List[SpellTarget]
  }

  sealed trait WithCoolDown extends SpellElement with CoolDown

  sealed trait WithCasterClass extends SpellElement with CasterLevels

  sealed trait WithSpellInfo extends SpellElement with SpellInfo

}

final case class UseCoolDown(coolDown: Option[Duration]) extends WithCoolDown

final case class UseCasterClass(override val casterLevels: Seq[CasterWithLevel]) extends WithCasterClass

final case class UseSpellTarget(override val target: List[SpellTarget]) extends WithSpellTarget

final case class UseSpellSavingThrow(override val savingThrow: List[SavingThrow]) extends WithSpellSavingThrow

final case class UseSpellPoints(override val spellPoints: Int) extends WithSpellPoints

final case class UseComponents(override val components: List[ComponentList]) extends WithComponents

final case class UseLevelCap(override val baseLevelCap: Option[Int]) extends WithLevelCap

final case class UseSpellEffects(override val effects: List[Effect]) extends WithSpellEffects

final case class UseSpellInfo(override val coolDown: Option[Duration],
                              override val savingThrow: List[SavingThrow],
                              override val spellResistance: Boolean,
                              override val target: List[SpellTarget],override val components: List[ComponentList],
                              override val spellPoints: Int) extends WithSpellInfo


protected final case class createSpell(
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

sealed trait Spell
  extends SpellLike
    with EnumEntry
    with DisplayName
    with FriendlyDisplay {
  val name: String

  // self: SpellInfo with EffectList =>
  override protected def nameSource: String = entryName.splitByCase.toPascalCase
}

object Spell extends Enum[Spell] {

  override def values: immutable.IndexedSeq[Spell] = findValues
}

trait SpellLikeAbility extends SpellLike

case class SpellDescriptor(elements: Seq[SpellElement]) extends Spell {
  override val name: String = nameSource
}

trait BullsStrength extends Spell with SpellInfo with EffectList

