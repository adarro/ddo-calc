/**
  * Copyright (C) 2015 Andre White (adarro@gmail.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *         http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.aos.ddo

import org.aos.ddo.PhysicalDamageType.{ Bludgeon, Pierce, Slash, Value }

trait Damage

trait PhysicalDamage extends Damage with DefaultValue[PhysicalDamageType.Value]

/**
  * Damage type resulting in blunt force such as clubs, staves or a partial
  * component of spells such as Ice Storm.
  */
trait Bludgeoning extends PhysicalDamage {
  override lazy val defaultType: Option[Value] = Some(Bludgeon)
}

trait Slashing extends PhysicalDamage {
  override lazy val defaultType: Option[Value] = Some(Slash)
}

trait Piercing extends PhysicalDamage {
  override lazy val defaultType: Option[Value] = Some(Pierce)
}
