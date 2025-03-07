/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Elvis.scala
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
package io.truthencode.ddo.support

import scala.language.implicitConversions

case class Elvis(b: Boolean) {
  def ?[X](t: => X) = new {
    def |(f: => X) = if (b) then t else f
  }
}

/**
 * Wholly unnecessary Utility function to bring Elvis Operator into scala Appropriated by
 * [[https://stackoverflow.com/a/4949295/400729]]
 */
object Elvis {
  implicit def booleanToElvis(b: Boolean): Elvis = Elvis(b)
}
