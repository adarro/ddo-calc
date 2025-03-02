/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Triggers.scala
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
package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.Logger

import zio.prelude.Validation

val logger = Logger("io.truthencode.ddo.support.validation")

def validateTriggers(triggers: Seq[String]): Validation[String, Seq[String]] =
  if triggers.isEmpty then {
    Validation.fail("At least one valid trigger must be specified")
  } else {
    import io.truthencode.ddo.support.TraverseOps._
    val tIn = triggers.mkString(", ")
    logger.debug(s"Evaluating Triggers $tIn")
    val tCommon = triggers.intersect(triggerNames)
    logger.info(s"intersection found ${tCommon.size} Triggers $tCommon ")
    if tCommon.nonEmpty then {
      // Succeed but warn if there are unknown triggers (which we will filter out)
      val unknowns = triggers.nSelect(triggerNames)
      if unknowns.nonEmpty then {
        val badIds = unknowns.mkString(", ")
        logger.warn(s"Filtering out unknown triggers: $badIds")
      }
      Validation.succeed(tCommon)
    } else { // Not a single valid trigger passed in
      val nSel = triggers.nSelect(triggerNames).mkString(", ")
      Validation.fail(s"Invalid Trigger id's $nSel")
    }
  }
