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
package io.truthencode.ddo.model.attribute

import io.truthencode.ddo.model.attribute.Attribute._

import scala.collection.immutable.HashSet

/**
 * Created by adarr on 1/28/2017.
 */
trait LinkedAttribute {
  def linkedAttribute: Set[Attribute]
}

trait LinkedAttributeImpl extends LinkedAttribute {
  override def linkedAttribute: Set[Attribute] = HashSet()
}

trait StrengthLinked extends LinkedAttribute {
  abstract override def linkedAttribute: Set[Attribute] = super.linkedAttribute + Strength
}

trait DexterityLinked extends LinkedAttribute {
  abstract override def linkedAttribute: Set[Attribute] = super.linkedAttribute + Dexterity
}

trait ConstitutionLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Constitution
}

trait IntelligenceLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Intelligence
}

trait WisdomLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Wisdom
}

trait CharismaLinked extends LinkedAttribute {
  abstract override val linkedAttribute: Set[Attribute] = super.linkedAttribute + Charisma
}
