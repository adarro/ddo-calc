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
import org.gradle.plugins.ide.idea.model.Module
import org.gradle.plugins.ide.idea.model.ModuleDependency

assert(rootProject == project)

allprojects {
    apply {
        plugin("idea")
        // TODO: editorConfig is also in quality-checks, which is where I believe it belongs.  Need to choose
        //   plugin("org.ec4j.editorconfig")
    }
}

configure<org.gradle.plugins.ide.idea.model.IdeaModel> {
    project {
        vcs = "Git"
        ipr {
            beforeMerged(
                Action<org.gradle.plugins.ide.idea.model.Project> {
                    modulePaths.clear()
                },
            )
        }

        module {
            iml {
                whenMerged(
                    Action<Module> {
                        dependencies.forEach {
                            (it as ModuleDependency).isExported = true
                        }
                    },
                )
            }
        }
    }
}