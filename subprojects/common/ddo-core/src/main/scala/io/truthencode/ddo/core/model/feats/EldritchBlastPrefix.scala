/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EldritchBlastPrefix.scala
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

import io.truthencode.ddo.support.naming.{DisplayName, FriendlyDisplay, Prefix}

/**
 * Created by adarr on 3/26/2017.
 */
trait EldritchBlastPrefix extends Prefix {
  self: DisplayName & FriendlyDisplay =>
  override protected val prefixSeparator: String = ": "

  override def prefix: Option[String] = Some("Eldritch Blast")
}
