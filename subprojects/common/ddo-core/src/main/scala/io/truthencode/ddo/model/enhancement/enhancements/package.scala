/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: package.scala
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
package io.truthencode.ddo.model.enhancement

import io.truthencode.ddo.support.points.SpendablePoints
import io.truthencode.ddo.support.tree.TreeLike

package object enhancements {

  /**
   * Simple Convinience class to expand and manipulate Tree tuples
   * @param source
   *   Tuple of Tree and cost values
   */
  implicit def expanders(source: (TreeLike, Int)): (TreeLike, SpendablePoints, Int) = {

    (source._1, source._1.pointType, source._2)

  }
}
