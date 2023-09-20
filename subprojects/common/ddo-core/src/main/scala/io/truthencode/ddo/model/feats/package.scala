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
package io.truthencode.ddo.model

import scala.reflect.ClassTag

/**
 * Created by adarr on 2/15/2017.
 */
package object feats {

  /**
   * see
   * [[http://stackoverflow.com/questions/41014979/elegant-way-to-chain-scala-partial-functions SO]]
   * @param pf
   *   Generic Partial Function to chain
   *
   * @tparam A
   *   Inbound type
   * @tparam B
   *   Outbound type
   */
  implicit class PartFuncOps[A: ClassTag, B](pf: PartialFunction[A, B]) {

    /**
     * Chains linked partial functions with an 'Or' operator
     * @param other
     *   next partial function to chain
     * @tparam D
     *   Derived inbound type
     * @tparam C
     *   derived oubound type
     * @return
     *   Partial Function of either A or C
     */
    def or[D >: A, C <: D: ClassTag](other: PartialFunction[C, B]): PartialFunction[D, B] = {
      case a: A if pf.isDefinedAt(a) => pf(a)
      case c: C if other.isDefinedAt(c) => other(c)
    }
  }

}
