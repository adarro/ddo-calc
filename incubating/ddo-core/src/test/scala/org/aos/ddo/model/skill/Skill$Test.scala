/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
package io.truthencode.ddo.model.skill

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 2/6/2017.
  */
class Skill$Test extends FunSpec with Matchers {

  describe("Skill$Test") {

    ignore("should withNameInsensitiveOption") {

    }

    it("should values") {
      val v = Skill.values
      noException should be thrownBy Skill
        .values
    }

  }
}
