package io.truthencode.ddo.support.validation

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.effect.{EffectCategories, TriggerEvent}
import io.truthencode.ddo.model.spells.SpellPower
import io.truthencode.ddo.model.stats.BasicStat
import zio.prelude.Validation

def validateName(name: String): Validation[String, String] =
  if name.isEmpty then {
    Validation.fail("Name was empty")
  } else {
    Validation.succeed(name)
  }

def validateDescription(description: String): Validation[String, String] =
  if description.isEmpty then {
    Validation.fail("Name was empty")
  } else {
    Validation.succeed(description)
  }

def validateBonusType(bonusType: String): Validation[String, String] =
  if bonusType.isEmpty then {
    Validation.fail("Bonus Type was empty")
  } else if bonusTypeNames.contains(bonusType) then {
    Validation.succeed(bonusType)
  } else {
    Validation.fail("Unknown or Invalid Bonus Type")
  }

lazy val triggerNames: Seq[String] = TriggerEvent.values.map(_.entryName)
lazy val bonusTypeNames = BonusType.values.map(_.entryName)
lazy val categories: Seq[String] = EffectCategories.values.map(_.toString).toSeq
lazy val physicalScaling: Seq[String] =
  Seq(BasicStat.MeleePower, BasicStat.RangedPower).map(_.entryName)

lazy val spellScaling: Seq[String] = SpellPower.values.map(_.entryName) ++ Seq("SpellPower")

lazy val scalingTypes: Seq[String] = physicalScaling ++ spellScaling
