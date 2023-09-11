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
package io.truthencode.ddo.modeling

import io.truthencode.ddo.api.model.effect.BasicEffectInfo

/**
 * A Base effect.
 *
 * @param name
 *   The main name of the effect.
 * @param generalDescription
 *   The general description of the effect.
 * @param categories
 *   a list of Categories useful for menu / UI placement and also for searching / querying for
 *   Miss-Chance or other desired effects.
 */
case class Effect(name: String, generalDescription: String, categories: Seq[String])
  extends BasicEffectInfo
