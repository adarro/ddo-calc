plugins {
    id("nl.littlerobots.version-catalog-update") version "1.1.1"
}

versionCatalogUpdate {
    // sort the catalog by key (default is true)
    sortByKey = true
    // Referenced that are pinned are not automatically updated.
    // They are also not automatically kept however (use keep for that).
    pin {
        // pins all libraries and plugins using the given versions
        versions = listOf("scala3-version", "scala2-version")

        // pins specific libraries that are in the version catalog
        // libraries

        // pins specific plugins that are in the version catalog
        // plugins

        // pins all libraries (not plugins) for the given groups
        // groups
    }
    keep {
        // keep has the same options as pin to keep specific entries
        // note that for versions it will ONLY keep the specified version, not all
        // entries that reference it.
        //        versions = ["my-version-name", "other-version"]
        //        libraries = [libs.my.library.reference, libs.my.other.library.reference]
        //        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
        //        groups = ["com.somegroup", "com.someothergroup"]

        // keep versions without any library or plugin reference
        keepUnusedVersions = true

        // keep all libraries that aren't used in the project
        // no longer an option
//            keepUnusedLibraries = true
        // keep all plugins that aren't used in the project
        // no longer an option
//            keepUnusedPlugins = true
    }
}

// LATEST to accept any latest version, STABLE to select only stable versions or
// PREFER_STABLE (default setting) to accept unstable versions only if the catalog already uses an unstable version
// versionSelector(VersionSelectors.STABLE)
