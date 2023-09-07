package io.truthencode.djaxonomy.etc

import org.gradle.api.Project
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