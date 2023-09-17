package io.truthencode.ddo.grammar.antlr

import io.truthencode.ddo.visibla.javaClass.PojoDefaultJava
import io.truthencode.ddo.visibla.kotlinClass.PojoDefaultKotlin

open class BasicTestKotlin {
    fun doSomething(): String {
        return "Okies"
    }

    fun refParserFromKotlinSamePackage() {
        var p: EnchantmentsParser
        val jc: PojoDefaultJava
        val kc: PojoDefaultKotlin
    }
}