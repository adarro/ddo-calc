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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.activation.{OnMeleeSpecialAttack, OnToggleEvent}
import io.truthencode.ddo.model.effect.ActiveEvent
import io.truthencode.ddo.model.misc.{CoolDown, DefaultCoolDown, DefaultSpellCoolDown}

/**
 * Created by adarr on 1/29/2017.
 * @note
 *   these should be moved out of the Feat package and renamed as they can and should be used for
 *   stances / toggles etc that are granted via enhancements etc
 *
 * Also, perhaps clickies?
 */
sealed trait FeatType

/**
 * This effect can be turned on or off via a toggle.
 */
trait Toggle extends FeatType

/**
 * This effect is a stance, which means it can be on or off, but may also have some stacking
 * restrictions. i.e. only one offensive combat stance may be active at any given time, and
 * automatically toggles off a defensive combat stance, however it may be combined with a
 * non-conflicting stance, such as a wizards undead shroud or the iconic feat Amauntor's Flames.
 */
trait Stance extends FeatType with Toggle with ActiveFeat with OnToggleEvent with DefaultCoolDown

/**
 * This effect must be explicitly activated and is generally a short-term effect such as uncanny
 * dodge and may be subject to a cool-down.
 */
trait ActiveFeat extends FeatType with CoolDown {
  self: ActiveEvent =>
}

/**
 * This stance has effects that increase your defense by increasing your dodge, armor class or other
 * defensive measures.
 *
 * It is exclusive of Offense combat stances.
 */
trait DefensiveCombatStance extends FeatType with Stance

/**
 * This effect increasing your offensive capabilities by increasing damage, critical multiplier,
 * spell dc / penetration etc.
 *
 * It is exclusive of Defensive Combat stances.
 */
trait OffensiveCombatStance extends FeatType with Stance

/**
 * This effect benefits ranged combat such as missile weapons.
 */
trait RangedCombatStance extends FeatType with Stance

/**
 * Listed on Improved Precise Shot, need to see what Archer's focus and any other ranged stance such
 * as Inquisitive uses
 * @todo
 *   is this a duplicate of RangedCombatStance?
 */
trait OffensiveRangedStance extends FeatType with Stance

/**
 * This provides beneficial effects to spells and spell like abilities such as extending length,
 * range or power.
 */
trait MetaMagic extends FeatType with Stance

/**
 * This effect is considered permanent and always on. A given feat or ability may provide both
 * active and passive effects.
 */
trait Passive extends FeatType

trait SpecialAttack extends FeatType

trait Ability extends FeatType

/**
 * This is a tactical Feat such as Trip and Stunning Blow which can generally be affected by things
 * such as Combat Mastery
 */
trait Tactical extends FeatType with OnMeleeSpecialAttack

/**
 * Basic trait to categorize epic feats. Currently used only for better readability and testing.
 */
sealed trait EpicFeatCategory extends FeatType

protected[feats] trait GeneralPassive extends EpicFeatCategory with Passive
protected[feats] trait RangedCombatPassive extends EpicFeatCategory with Passive
protected[feats] trait SpellFeats
  extends EpicFeatCategory with ActiveFeat with DefaultSpellCoolDown {
  self: ActiveEvent =>
}
protected[feats] trait SpellCastingPassive extends EpicFeatCategory with Passive
protected[feats] trait SpellCastingActive extends EpicFeatCategory with ActiveFeat {
  self: ActiveEvent =>
}
protected[feats] trait EpicMetaMagic extends EpicFeatCategory
trait ClassRestricted extends EpicFeatCategory
protected[feats] trait FreePassive extends EpicFeatCategory with Passive
