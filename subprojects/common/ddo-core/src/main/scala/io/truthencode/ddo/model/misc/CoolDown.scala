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
package io.truthencode.ddo.model.misc

import java.time.Duration

trait CoolDown {

  /**
   * Some duration until this action / spell / ability can be used again.
   * @return
   *   Some Time span which must elapse before re-activation.
   */
  def coolDown: Option[Duration]
}

/**
 * A default CoolDown currently used for unknown / inconsequential values such as toggling Auto
 * Attack
 */
trait DefaultCoolDown extends CoolDown {

  /**
   * This is a default cool down which is currently using
   * [[io.truthencode.ddo.model.GlobalMinimumCoolDown]] for a value. This may be adjusted or changed
   * as knowledge increases. However, it is also hopeful to deprecate this for a specified value.
   * @return
   *   Some Time span which must elapse before re-activation.
   */
  override def coolDown: Option[Duration] =
    Some(io.truthencode.ddo.model.GlobalMinimumCoolDown)
}

/**
 * Default spell cool downs for undocumented / unknown spells.
 */
trait DefaultSpellCoolDown extends CoolDown {

  /**
   * @todo
   *   this needs to be a class based filter as the default depends on it.
   * @return
   */
  override def coolDown: Option[Duration] = Some(Duration.ofSeconds(3))
}

/**
 * The standard cooldown for Artificer, Druid, Cleric, Favored Soul, Wizard, and Bard spells is 1.5
 * + 0.5*level seconds
 * @todo
 *   Need to see if Warlocks are in this category
 */
trait DefaultCasterCoolDown extends DefaultSpellCoolDown

/**
 * Sorcerers have reduced cooldown as a class feature, and their standard delay is 1.25 + 0.25*level
 */
trait DefaultSorcererCoolDown extends DefaultSpellCoolDown

/**
 * Rangers and paladins have longer cooldowns of 1 + 1*level seconds.
 */
trait DefaultMagicCoolDown extends DefaultSpellCoolDown

trait BardSongCoolDown extends CoolDown {

  /**
   * This is a temporary and arbitrary cooldown assuming aura bard songs have a timer. Typically,
   * aside from instant songs, the cool down will expire well before the song finishes.
   */
  final val BardSongDefaultCoolDown = Duration.ofSeconds(3)
  override def coolDown: Option[Duration] = Some(BardSongDefaultCoolDown)
}
trait SharedCoolDown extends CoolDown with PoolId
