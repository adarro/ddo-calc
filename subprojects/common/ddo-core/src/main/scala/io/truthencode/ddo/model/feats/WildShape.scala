/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WildShape.scala
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

import io.truthencode.ddo.activation.{OnShapeShift, TriggeredActivationImpl}
import io.truthencode.ddo.model.misc.DefaultCasterCoolDown
import io.truthencode.ddo.support.naming.{DisplayName, DisplayProperties, WildShapePrefix}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass, RequiresAllOfClass}

/**
 * [[https://ddowiki.com/page/Wild_Shape Wild Shape]] Transform into the shape of and animal,
 * magical beast, or elemental. Each form grants access to special spells that require that form.
 *
 * Duration: Permanent when toggled. Costs 5 sp to activate, free to deactivate. Notes At second
 * level the druid will select an animal form, Wolf or Bear, for their first wild shape, and receive
 * the other at level 5. At level 8 a druid will select a magical beast form, Winter Wolf or Dire
 * Bear, gaining the other at level 11. At level 13 a druid will select an elemental form, Water
 * Elemental or Fire Elemental, obtaining their final wild shape at level 17. Using Wild Shape is
 * subject to Druidic Oath.
 */
protected[feats] trait WildShape
  extends FeatRequisiteImpl with TriggeredActivationImpl with ActiveFeat with OnShapeShift
  with DefaultCasterCoolDown with WildShapePrefix {
  self: GrantsToClass & RequiresAllOfClass & DisplayName & DisplayProperties =>
}
