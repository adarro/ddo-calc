plugins {
                    id("org.unbroken-dome.test-sets")
                }
                
                testSets {
                    "acceptanceTest" {
                        dirName = "specs"
                        sourceSet.java {
                            "acceptanceTest" {
                                exclude(
                                    "**/*Spec.scala",
                                    // "**/*Helper*",
                                    "**/*Builder*"
                                )
                            }
                        }
                
                
                        sourceSet.resources {
                            this.srcDirs.add(File("./src/test/specs"))
                        }
                        configurations[compileClasspathConfigurationName].extendsFrom(configurations["testImplementation"])
                        createArtifact = true
                    }
                }

