package io.truthencode.djaxonomy.etc

import net.pearx.kasechange.toCamelCase
import org.gradle.api.Project
import org.gradle.api.attributes.TestSuiteType
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.provideDelegate

enum class KotlinTestKits {
    KoTest, KotlinTest, None
}

enum class TestMode {
    REFLECT, KAPT, KSP
}

interface KotlinTestKitExtension {
    val useKotlinTestKit: Property<KotlinTestKits>
}

class KotlinTestKitClassExtension(val useKotlinTestKit: Property<KotlinTestKits>)

interface KotlinAnnotationProcessingExtension {

    val kotlinTestMode: Property<TestMode>
}


enum class TestTypes {
    Unit(TestSuiteType.UNIT_TEST),
    Integration(TestSuiteType.INTEGRATION_TEST),
    Functional(TestSuiteType.FUNCTIONAL_TEST),
    Performance(TestSuiteType.PERFORMANCE_TEST),
    Acceptance("acceptance-test"),
    Custom("custom-test");

    var id: String? = null
    var defaultName: String? = null
    var testSuiteType: String? = null
    val knownSuiteNames = listOf(
        TestSuiteType.UNIT_TEST,
        TestSuiteType.FUNCTIONAL_TEST,
        TestSuiteType.INTEGRATION_TEST,
        TestSuiteType.PERFORMANCE_TEST
    )

    constructor()

    constructor(id: String) {
        this.id = id
        this.defaultName = when (id) {
            TestSuiteType.UNIT_TEST -> "test"
            else -> id.toCamelCase()
        }
        if (knownSuiteNames.contains(id)) {
            this.testSuiteType = id
        }
    }

    companion object {

        fun fromTestSuiteType(id: String): TestTypes {
            return when (id) {
                TestSuiteType.UNIT_TEST -> Unit
                TestSuiteType.INTEGRATION_TEST -> Integration
                TestSuiteType.FUNCTIONAL_TEST -> Functional
                TestSuiteType.PERFORMANCE_TEST -> Performance
                "acceptance-test" -> Acceptance // Static Duck-typing ATM
                else -> Custom
            }
        }


        fun fromNamingConvention(key: String): TestTypes {
            return TestTypes.values().find { it.defaultName == key } ?: Custom
        }
    }

}



class TestBuildSupport(proj: Project) {
    private val koTestVersion: String by proj
    val applyMockito = { suite: JvmTestSuite ->
        suite.useJUnitJupiter()
        suite.dependencies {
            implementation("org.mockito:mockito-junit-jupiter:4.6.1")
        }
    }

    val applyKoTest = { suite: JvmTestSuite ->
        suite.useJUnitJupiter()
        suite.dependencies {
            implementation("io.kotest:kotest-runner-junit5:$koTestVersion")
            implementation("io.kotest:kotest-assertions-core:$koTestVersion")
            implementation("io.kotest:kotest-property:$koTestVersion")
        }
    }
}