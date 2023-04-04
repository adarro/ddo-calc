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

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.support.requisite.{
  AttributeRequisiteImpl,
  RequiresAllOfAttribute,
  RequiresAllOfFeat,
  RequiresCharacterLevel
}

/**
 * Epic Feat that can be taken at level 21 or 24.
 */
protected[feats] trait EpicToughness
  extends Passive with AttributeRequisiteImpl with RequiresCharacterLevel
  with RequiresAllOfAttribute with RequiresAllOfFeat {
  self: EpicFeat =>
  override val requireCharacterLevel: Int = 27

  override def allOfFeats: Seq[Feat] = List(GeneralFeat.Toughness)

  override def allOfAttributes: Seq[(Attribute, Int)] = List((Attribute.Constitution, 21))
}
