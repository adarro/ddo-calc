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
package io.truthencode.ddo.model.effect.features

import io.truthencode.ddo.model.effect.Feature

trait Features {
  def features: List[Feature[_]]

  def namedFeatures: Map[String, List[Feature[_]]] = features.groupBy(_.name) collect {
    case (Some(x), list) => (x, list)
  }

}

object Features {
  implicit class FeatureOpt(source: Features) {
    def hasFeature(features: Features): Boolean = {
      source.features.contains(features)
    }

      /**
        * Keys are generally expected to be prefixed (i.e. Skill:Listen or Feat:Alertness) to avoid ambiguity.
        * @param name (prefixed) id of the feature
        * @return true if found within the Features container.
        */
    def hasNamedFeature(name:String): Boolean = {
      source.namedFeatures.contains(name)
    }
  }
}

/**
  * Default convenience implementation which initializes the features list to Nil
  */
trait FeaturesImpl extends Features {
  override def features: List[Feature[_]] = Nil
}
