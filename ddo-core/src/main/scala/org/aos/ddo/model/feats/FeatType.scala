package org.aos.ddo.model.feats

/**
  * Created by adarr on 1/29/2017.
  */
sealed trait FeatType

trait Active extends FeatType

trait DefensiveCombatStance extends FeatType

trait Passive extends FeatType

trait SpecialAttack extends FeatType

trait Ability extends FeatType

trait OffensiveCombatStance extends FeatType

trait RangedCombatStanceStance extends FeatType

trait OffensiveRangedStance extends FeatType



