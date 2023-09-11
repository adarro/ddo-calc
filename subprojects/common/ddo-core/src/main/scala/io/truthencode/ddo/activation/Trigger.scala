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
package io.truthencode.ddo.activation

import io.truthencode.ddo.model.effect.TriggerEvent

/**
 * Created by adarr on 2/6/2017.
 */
trait Trigger {

  /**
   * Event Triggers that fire on some condition
   * @return
   *   Set of triggers that can activate it, if any.
   */
  def activatableTriggers: Seq[TriggerEvent]
  def canActivate: Boolean = activatableTriggers.nonEmpty
}

trait TriggerImpl extends Trigger {

  /**
   * Event Triggers that fire on some condition
   *
   * @return
   *   Set of triggers that can activate it, if any.
   */
  override def activatableTriggers: Seq[TriggerEvent] = Nil
}
