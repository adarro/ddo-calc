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
plugins {
//    id("org.unbroken-dome.test-sets")
    java apply(false)
    `jvm-test-suite`
}

testing {
    suites {
        val applyMockito = { suite: JvmTestSuite ->
            suite.useJUnitJupiter()
            suite.dependencies {
                implementation("org.mockito:mockito-junit-jupiter:4.6.1")
            }
        }

        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register<JvmTestSuite>("acceptanceTest") {
            dependencies {
                implementation(project())
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("acceptanceTest"))
}

//testing {
//    suites {
//        test {
//            useJUnitJupiter()
//        }
//
//        register<JvmTestSuite>("acceptanceTest") {
//            dependencies {
//                implementation(project())
//            }
//
//            targets {
//                all {
//                    testTask.configure {
//                        shouldRunAfter(test)
//                    }
//                }
//            }
//        }
//    }
//
////        acceptanceTest(JvmTestSuite) {
////            sources.java.srcDirs = ['src/acceptanceTest/java']
////            sources.resources.srcDirs = ['src/acceptanceTest/resources']
////            dependencies {
////                implementation project()
////                implementation sourceSets.test.output
////            }
////            targets {
////                all {
////                    testTask.configure {
////                        shouldRunAfter(test)
////                    }
////                }
////            }
////        }
//
//}

//configurations {
//    integTestImplementation.extendsFrom testImplementation
//}

tasks.named("check") {
dependsOn(testing.suites.named("acceptanceTest"))
}

//tasks.named("sourcesJar") {
//    dependsOn("compileJava")
//    dependsOn("compileTestJava")
//    dependsOn("compileIntegTestJava")
//}
//tasks.named("javadoc") {
//    dependsOn("compileJava")
//    dependsOn("compileTestJava")
//    dependsOn("compileIntegTestJava")
//}

//testSets {
//    "acceptanceTest" {
//        dirName = "specs"
//        sourceSet.java {
//            "acceptanceTest" {
//                exclude(
//                    "**/*Spec.scala",
//                    // "**/*Helper.java",
//                    "**/*Builder*"
//                )
//            }
//        }
//
//
//        sourceSet.resources {
//            this.srcDirs.add(File("./src/test/specs"))
//        }
//        configurations[compileClasspathConfigurationName].extendsFrom(configurations["testImplementation"])
//        createArtifact = true
//    }
//}

