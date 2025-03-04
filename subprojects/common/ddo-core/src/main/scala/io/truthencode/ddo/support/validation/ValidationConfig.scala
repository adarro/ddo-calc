/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ValidationConfig.scala
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

import jakarta.inject.Singleton
import org.eclipse.microprofile.config.inject.ConfigProperty
import zio.prelude.Validation

enum invalidationOptions {
  case RejectAll, FilterValid
}

def validateInt(s: String): Validation[String, Int] =
  Validation(s.toInt).mapError(_.getMessage)

@Singleton
object ValidationConfig {
  @ConfigProperty(name = "core.filtering.global")
  var globalFilterDefault: String = null

}
