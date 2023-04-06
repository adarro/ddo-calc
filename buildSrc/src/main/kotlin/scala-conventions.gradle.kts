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
    scala

    id("org.scoverage")
    id("code-quality")
    id("be.vbgn.ci-detect")
}


dependencies {
    val scalaMajorVersion: String by project
    val scoverageMinorVersion: String by project
    val scoveragePluginVersion: String by project
    compileOnly("org.scoverage:scalac-scoverage-plugin_$scalaMajorVersion.$scoverageMinorVersion:$scoveragePluginVersion")

}


configure<org.scoverage.ScoverageExtension> {
    val scoveragePluginVersion: String by project
    scoverageVersion.set(scoveragePluginVersion)
    val cfgs = mapOf(
        Pair(org.scoverage.CoverageType.Branch, 0.5.toBigDecimal()),
        Pair(org.scoverage.CoverageType.Statement, 0.75.toBigDecimal())
    ).map { p ->
        val cfg = org.scoverage.ScoverageExtension.CheckConfig()
        cfg.setProperty("coverageType", p.key)
        cfg.setProperty("minimumRate", p.value)
        cfg
    }
    checks.plusAssign(cfgs)
}

/**
 * scala2CompilerOptions options
 * These are options that will be added only when compiling against Scala 2.x
 */
val scala2CompilerOptions = sequenceOf(
    "-feature", "-deprecation", "-Ywarn-dead-code",
    "-Xsource:3"
)

/**
 * These are the default options added when compiling against Scala 3.x
 */
val scala3CompilerOptions = sequenceOf("-deprecation")

/**
 * Rewrite option should default to '-rewrite' but can be set to '-indent'
 * example ./gradlew ddo-core:scalaCompile -Ps3rewrite=-indent
 * NOTE: this will need to be revisited when not using scala 2.13
 * We should also incorporate the -new-syntax option here instead of below
 */
val rewriteOption = project.findProperty("s3rewrite")?.toString() ?: ""

enum class MigrationDirection {
    FromScala3,
    ToScala3,
    None
}

enum class MigrationPhase {
    Initial,
    Second,
    None
}

fun migrationOptions(): Migration {
    return when (rewriteOption) {
        "toScala3First" -> Migration(MigrationDirection.ToScala3, MigrationPhase.Initial)
        "toScala3Second" -> Migration(MigrationDirection.ToScala3, MigrationPhase.Second)
        "fromScala3First" -> Migration(MigrationDirection.FromScala3, MigrationPhase.Initial)
        "fromScala3Second" -> Migration(MigrationDirection.FromScala3, MigrationPhase.Second)
        else -> Migration(MigrationDirection.None, MigrationPhase.None)
    }

}

data class Migration(val direction: MigrationDirection, val phase: MigrationPhase) {

    // -rewrite or null
    private val action: String? = if (direction != MigrationDirection.None) "-rewrite" else null

    // Braces vs Significant Indentation
    private val syntax = Pair("-noindent", "-indent")

    // Control new vs old control structure
    private val structure = Pair("-old-syntax", "-new-syntax")

    val opt: Sequence<String> = when (direction) {
        MigrationDirection.ToScala3 -> {

            when (phase) {
                MigrationPhase.Initial -> {
                    sequenceOf(action.orEmpty(), structure.second)
                }

                MigrationPhase.Second -> {
                    sequenceOf(action.orEmpty(), syntax.second)
                }

                else -> {
                    emptySequence()
                }
            }
        }

        MigrationDirection.FromScala3 -> {
            when (phase) {
                MigrationPhase.Initial -> {
                    sequenceOf(action.orEmpty(), syntax.first)
                }

                MigrationPhase.Second -> {
                    sequenceOf(action.orEmpty(), structure.first)
                }

                else -> {
                    emptySequence()
                }
            }
        }

        MigrationDirection.None -> emptySequence()
    }

}

fun tryFindScalaVersionDependencies(proj: Project, timing: String): List<Dependency> {
    logger.warn("${proj.name.toUpperCase()} $timing +++++++++++++(FROM CONVENTION)++++++++++++++++++++++++++}")
    val cfg = proj.configurations["implementation"].dependencies

    // TODO: Add org check for scala-lang
    val sz = cfg.size
    if (sz > 0) {
        val ncRegx = """scala\d?-library.*""".toRegex()
        val sl = cfg.filter { it.name.matches(ncRegx) }
        if (sl.isEmpty()) {
            logger.warn("\t$sz not found")
            cfg.forEach { d -> logger.warn("\t$d.name}") }

        } else {
            logger.warn("found matches")
            sl.forEach { d -> logger.warn("\t${d.name}") }
            cfg.forEach { d ->
                logger.warn(d.name)
            }
            return sl
        }
    }
    return emptyList()

}

fun tryReadScalaVersion(proj: Project, timing: String): Int {
    val dep = tryFindScalaVersionDependencies(proj, timing)
    val s3 = dep.filter { it.name.startsWith("scala3") }
    return if (s3.isEmpty()) 2 else 3
}

/**
 * Make options
 * Conditionally adds compiler options based on if you're migrating between versions, using Scala 2 or Scala 3
 * @note Dependencies may or not yet be resolved / available depending on when (doLast / afterEvaluate) or buildSrc vs Root vs subproject
 * @param proj current project being compiled.
 * @param timing testing only parameter to display when running.
 *
 * @return values from [scala2CompilerOptions], [scala3CompilerOptions] or [rewrite options](https://docs.scala-lang.org/scala3/guides/migration/tooling-migration-mode.html) (if specified)
 * returns an empty list when no scala-library is found on path.
 */
fun makeOptions(proj: Project, timing: String): Sequence<String> {
    val sv = tryReadScalaVersion(proj, timing)
    val mo = migrationOptions()
    logger.debug("Detected ScalaVersion {} using options {}", sv, mo)
    // Current 'safe' option is not to attempt a migration from 2 to 3 if using scala3
    // Scala 2x lib may be on path regardless of S3 / S2 due to legacy / BOM enforcedPlatform import etc. ::frown:: (I'm looking at you Quarkus!)
    val compilerOpt = when (sv) {
        3 -> {
            when (mo.direction) {
                MigrationDirection.ToScala3, MigrationDirection.None -> {
                    if (mo.direction == MigrationDirection.ToScala3)
                        logger.warn("Migrating code to Scala3 skipped re-write options as it appears Scala3 is already on your classpath")
                    scala3CompilerOptions
                }

                MigrationDirection.FromScala3 -> {
                    // just add re-write options
                    mo.opt
                }
            }
        }

        2 -> {
            // Scala 2 options
            when (mo.direction) {
                MigrationDirection.None -> {
                    // Just add S2
                    scala2CompilerOptions
                }

                MigrationDirection.FromScala3, MigrationDirection.ToScala3 -> {
                    // we're migrating, so just use mo
                    mo.opt
                }
            }
        }

        else -> {
            logger.warn("Could not determine scala library on path")
            emptySequence()
        }
    }
    return compilerOpt
}



tasks.withType<ScalaCompile>().configureEach {

    doLast {

        val co = makeOptions(project, "last")
        val msg = co.joinToString { it }
        logger.info("derived compiler options\n $msg")
    }

    tryFindScalaVersionDependencies(project, "inConfigure")
    this.project // scala-library
    scalaCompileOptions.apply {
        val scalaCoptions = listOf(
            "-feature", "-deprecation", "-Ywarn-dead-code",
            "-Xsource:3"
        )

        additionalParameters?.plusAssign(
            scalaCoptions
        )
        val mo = migrationOptions()

        logger.warn(" Migration options ${mo.opt}")
        logger.debug("executing scala compile with options\n {}",scalaCoptions)
        // Need to add -Ypartial-unification for Tapir
    }
}

tasks.withType<Javadoc> {
    val ss: FileTree = sourceSets["main"].allJava
    val ft = fileTree("$buildDir/generated/java")
    source = ss.plus(ft)

    dependsOn("compileScala")
    // source = listOf(sourceSets.main.allJava, "$buildDir/generated/java")
}


tasks {
    withType(Test::class.java) {
        systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
    }
    logger.info("concordion output directory: *************************************")
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest", "vintage")
            if (ci.isCi)
                excludeTags = setOf(
                    "Integration",
                    "io.truthencode.tags.Integration",
                    "FunctionOnly",
                    "io.truthencode.tags.FunctionOnly"
                )
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        outputs.upToDateWhen { false }
    }
}