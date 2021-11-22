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

sealed trait TypeCategory

trait MaterialBased extends TypeCategory

/**
 * Can affect health via positive / negative / poison / rust / repair etc
 */
trait Health extends TypeCategory

/**
 * Shape or delivery method (Yes, we need a better name for this) such as Dealing Cold via Piercing Icicles or
 * bludgeoning rocks of ice.
 */
trait Form extends TypeCategory

/**
 * Alignment based effects
 */
trait AlignmentBased extends TypeCategory

/**
 * Typeless is a special flag used to denote all-encompassing or impregnable effects such as DR/ or untyped damage.
 */
trait Typeless extends TypeCategory

/**
 * Currently a placeholder for errata, undefined and or non-categorized types such as magic or light. These may be
 * subject to moving to a new or different category in the future.
 */
trait Other extends TypeCategory
