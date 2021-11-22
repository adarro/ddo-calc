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
package io.truthencode.ddo

import java.time.Duration

/**
 * Created by adarr on 1/27/2017.
 */
package object model {
  /* Global Defaults */
  final val GlobalMinimumCoolDown = Duration.ofSeconds(1)

  /* icky unknowns as of yet */
  /**
   * Used to provide a default value for under documented / unknown values
   */
  @deprecated(
    message =
      "This is a place holder for cooldowns etc that are undocumented or unknown and should be removed in a later version",
    "ddo-core 0.0.1"
  )
  final val UnknownDuration = Duration.ofSeconds(5)

}
