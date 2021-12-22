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
package io.truthencode.ddo.model.spells

/**
 * Encapsulates spell range [[https://ddowiki.com/page/Spell#Range]]
 */
sealed trait SpellRange

/**
 * Personal range means the spell basically has no range and is a self only spell that you can not
 * cast on anything other than yourself.
 */
trait Personal extends SpellRange

/**
 * Touch range refers to pretty much as it sounds - you have to almost touching your target for the
 * spell to function. Though not quite, as of update 9, the range for touch spells was very slightly
 * extended to be more forgiving. It's roughly 1.5 times the width of a regular human character.
 */
trait Touch extends SpellRange

/**
 * Very short: Very short range refers to a standardized fixed distance of about 5 feet. Roughly a
 * third of what most spells have. This short range is generally reserved only for very low level
 * spells which are later replaced by stronger, longer range and more damaging versions.
 */
trait VeryShort extends SpellRange

/**
 * Standard range refers to a standardized fixed distance. For AOE buff type spells such as bless or
 * haste, you can see the actual area affected by the spells animation
 *   - about 15 feet. Pretty much every AOE buff spell in the game have the same range as these 2
 *     spells, but some do not display a graphic, so use those as the guide. For Offensive spells
 *     and targeted buff spells with standard range, the actual range is about double a standard
 *     buff AOE, so roughly 30 feet.
 */
trait Standard extends SpellRange

/**
 * Double range refers to exactly twice the range that standard spells have. Generally pretty much
 * every ray-type spell in DDO has this range type. It works exactly like having the enlarge feat
 * on, but it's a free benefit for the spell.
 */
trait Double extends SpellRange
