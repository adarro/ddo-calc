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
package io.truthencode.ddo.enumeration

/**
 * Created by adarr on 1/22/2017.
 */
trait BitWise {
  def bitValue: Int

  /**
   * Convenience routine to change an Int into the Bit Representation
   * @param i
   *   number to change
   * @return
   *   bit representation
   */
  def toBitMask(i: Int): Int = scala.math.pow(2, i).toInt
}
