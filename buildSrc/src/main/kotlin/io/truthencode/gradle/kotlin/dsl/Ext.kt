package io.truthencode.gradle.kotlin.dsl

import org.gradle.api.Action
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope


fun NamedDomainObjectContainerScope<SourceSet>.getOrCreate(
    name: String,
    configureAction: Action<in SourceSet>
): SourceSet {

    return try {
        getByName(name)
    } catch (e: UnknownDomainObjectException) {
        create(name)
    }
}
