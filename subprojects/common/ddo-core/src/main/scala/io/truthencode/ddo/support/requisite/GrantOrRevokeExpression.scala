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
package io.truthencode.ddo.support.requisite

/**
 * Created by adarr on 1/30/2017.
 */
sealed trait GrantOrRevokeExpression {

  /**
   * Automatically granted this feat / skill / ability when this criteria is met
   */
  def grantedBy: Seq[Requisite] = IndexedSeq.apply()

  /**
   * When this criteria is met, this feat / skill / ability can be purchased.
   */
  def requires: Seq[Requisite] = IndexedSeq.apply()

  /**
   * When this criteria is met, this feat / skill / ability is no longer available.
   */
  def prohibits: Seq[Requisite] = IndexedSeq.apply()
}

trait GrantExpression extends GrantOrRevokeExpression with Grant {
  self: RequisiteExpression =>

  abstract override def grantedBy: Seq[Requisite] = super.grantedBy
}

/**
 * A list of Feats / Skills etc which must *NOT* already be acquired to attain this.
 */
trait ProhibitExpression extends Prohibit {
  self: RequisiteExpression =>
}

/**
 * This expression must be true
 */
trait RequiredExpression extends Require {
  self: RequisiteExpression =>
}
