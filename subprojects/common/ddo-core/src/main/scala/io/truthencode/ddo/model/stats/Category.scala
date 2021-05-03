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
package io.truthencode.ddo.model.stats

/**
  * Used to generally classify a stat or effect such as Saving Throws or Movement.
  * These should generally correspond to the Game Client Menu although some are not visible from the UI
  */
sealed trait Category

trait AvoidanceDefenses extends Category

trait ElementalDefenses extends Category

trait SavingThrows extends Category

trait Movement extends Category

trait SpellCasting extends Category

trait GeneralCombat extends Category

trait MeleeCombat extends Category

trait RangedCombat extends Category

/**
 * General or Main stats generally appear on the main character sheet such as Base Attack Bonus
 */
trait General extends Category
