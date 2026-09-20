package io.truthencode.buildlogic

import net.pearx.kasechange.toCamelCase
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.the

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
 * It initially expanded the incubating TestSuiteType enum but was removed in Gradle ~7.13.0+
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
    // Get a handle for the Version Catalogs
    val libs = proj.the<LibrariesForLibs>()

    val applyMockito = { suite: JvmTestSuite ->
        suite.useJUnitJupiter()
        suite.dependencies {
            // TODO: convert to catalog ref and need to add Quarkus vs JUnit vs vanilla Mockito checks?
            // libs.quarkus-mockito
            implementation("org.mockito:mockito-junit-jupiter:4.6.1")
        }
    }

// Kotlin Test Support needs a refresh
    // Plain vs Multi-platform, Quarkus Detection. (JUnit assumed as this is my opinionated build)
    val applyKoTest = { suite: JvmTestSuite ->
        suite.useJUnitJupiter()
        suite.dependencies {

            // implementation(libs.bundles.kotest) // no likey for some reason
            implementation(libs.kotest.assertions.core.jvm)
            implementation(libs.kotest.runner.junit.jvm)
            implementation(libs.kotest.property.jvm)
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
