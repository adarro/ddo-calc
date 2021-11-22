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
package io.truthencode.ddo.model.effect

/**
 * Basic flag for Elemental type damage.
 *
 * @note
 *   this only indicates there is some type of elemental damage / resist use
 *   [[io.truthencode.ddo.model.effect.ElementalResistance]] which extends this to indicate resist for all elemental
 *   types.
 */
trait Elemental

trait Acid extends Elemental

trait Fire extends Elemental

trait Cold extends Elemental

trait Electric extends Elemental

trait Sonic

trait Force
/**
 * Reduces damage from Acid, Fire, Cold and Electric
 */
trait ElementalResistance extends Resist with Acid with Fire with Cold with Electric
