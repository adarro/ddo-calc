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
 * A basic trait to provide source information. Useful mostly for tagging Arrays with where they came from. I.e. An AC
 * bonus coming from a specific equipped item and an Enhancement Line that don't stack.
 */
trait SourceInfo {
  val sourceId: String
  val sourceRef: AnyRef
}

object SourceInfo {

  def apply(id: String, ref: AnyRef): SourceInfo = new SourceInfo {
    override val sourceId: String = id
    override val sourceRef: AnyRef = ref
  }
}
