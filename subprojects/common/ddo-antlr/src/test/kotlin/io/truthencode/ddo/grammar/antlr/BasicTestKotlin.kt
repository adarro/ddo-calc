/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BasicTestKotlin.kt
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
package io.truthencode.ddo.grammar.antlr

import io.truthencode.ddo.visibla.javaclass.PojoDefaultJava
import io.truthencode.ddo.visibla.kotlinclass.PojoDefaultKotlin

/**
 * BasicTestKotlin This is a test class to verify that the Kotlin parser can be used in the same
 * package as the Java parser
 */
open class BasicTestKotlin {
    /**
     * Does something (Not really)
     *
     * @return Okies
     */
    fun doSomething(): String = "Okies"

    /** Reference the parser from Kotlin in the same package */
    fun refParserFromKotlinSamePackage() {
        var ep: EnchantmentsParser
        val jc: PojoDefaultJava
        val kc: PojoDefaultKotlin
    }
}
