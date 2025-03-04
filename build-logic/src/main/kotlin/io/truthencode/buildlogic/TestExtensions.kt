package io.truthencode.buildlogic

import net.pearx.kasechange.toCamelCase
import org.gradle.api.Project
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.provideDelegate

enum class KotlinTestKits {
    KoTest,
    KotlinTest,
    None,
}

enum class TestMode {
    REFLECT,
    KAPT,
    KSP,
}

interface KotlinTestKitExtension {
    val useKotlinTestKit: Property<KotlinTestKits>
}

class KotlinTestKitClassExtension(
    val useKotlinTestKit: Property<KotlinTestKits>,
)

interface KotlinAnnotationProcessingExtension {
    val kotlinTestMode: Property<TestMode>
}

/**
 * TestTypes is a general enum for test type names.
 * It initially expanded the incubating TestSuiteType enum but was removed in Gradle 13.0+
 */
enum class TestTypes {
    Unit("unit-test"),
    Integration("integration-test"),
    Functional("functional-test"),
    Performance("performance-test"),
    Acceptance("acceptance-test"),
    Custom("custom-test"),
    ;

    var id: String? = null
    var defaultName: String? = null
    var testSuiteType: String? = null

    constructor(id: String) {
        this.id = id
        this.defaultName =
            when (id) {
                "unit-test" -> "test"
                else -> id.toCamelCase()
            }
    }

    companion object {
        fun fromNamingConvention(key: String): TestTypes = TestTypes.values().find { it.defaultName == key } ?: Custom
    }
}

@Suppress("UnstableApiUsage")
class TestBuildSupport(
    proj: Project,
) {
    // remove this once we Move these functions into kts file and can use version catalog
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

enum class BuildEnvironment {
    DEV,
    TEST,
    PROD,
}

fun getBuildEnvironment(): BuildEnvironment {
    val env = System.getenv("BUILD_ENVIRONMENT")
    return when (env) {
        "DEV" -> BuildEnvironment.DEV
        "TEST" -> BuildEnvironment.TEST
        "PROD" -> BuildEnvironment.PROD
        else -> BuildEnvironment.DEV
    }
}