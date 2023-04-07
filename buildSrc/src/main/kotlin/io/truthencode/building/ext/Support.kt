package io.truthencode.building.ext

val semVerPattern = "(?<major>\\d+)\\.(?<minor>\\d+)\\.(?<patch>(?<patchVersion>\\d+)(-?(?<prerelease>[0-9A-Za-z-]+))?)".toRegex()
data class Version(val major: String, val minor: String, val build: BuildVersion) : Comparable<Version> {
    constructor(shortNotation: String) : this(
        readFromShortForm(shortNotation).major,
        readFromShortForm(shortNotation).minor,
        readFromShortForm(shortNotation).build
    )


    override fun compareTo(other: Version): Int =
        when {
            this.major != other.major -> (this.major.toIntOrNull() ?: 0).compareTo(other.major.toIntOrNull() ?: 0)
            this.minor != other.minor -> (this.minor.toIntOrNull() ?: 0).compareTo(other.minor.toIntOrNull() ?: 0)
            else -> this.build.compareTo(other.build)
        }

    companion object {
        /**
         * Extracts name, artifact, version from simple GAV string
         */
        fun readFromShortForm(shortNotation: String): Version {

            val ar = shortNotation.split(":")

            return when {
                //  Handle Proper Semantic version string
                semVerPattern.matches(shortNotation) -> {
                    val m = semVerPattern.matchEntire(shortNotation)
                    val maj = m!!.groups["major"]!!.value
                    val min = m.groups["minor"]!!.value
                    val patch = m.groups["patch"]!!.value
                    Version(maj,min,BuildVersion(patch))
                }
                // Handle Gradle Short Notation
                ar.size == 3 ->Version(ar[0], ar[1], BuildVersion(ar[2]))
                else -> throw IllegalArgumentException("$shortNotation must be either Gradle Short Notation or a valid Semantic version string.")
            }
        }
    }
}

data class BuildVersion(val patch: Int, val preRelease: String?) : Comparable<BuildVersion> {
    override fun compareTo(other: BuildVersion): Int = when {
        this.patch != other.patch -> this.patch - other.patch
        this.preRelease != other.preRelease ->
            (this.preRelease ?: "").compareTo(other.preRelease ?: "")

        listOf(this.preRelease, other.preRelease).all { it == null } -> 0
        else -> (this.preRelease ?: "").compareTo(other.preRelease ?: "")

    }

    constructor(version: Int) : this(version, "")
    constructor(versionString: String) : this(
        readPatch(versionString),
        readPreRelease(versionString)
    )

    override fun toString(): String {
        return "$patch-$preRelease"
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BuildVersion

        if (patch != other.patch) return false
        return preRelease == other.preRelease
    }

    companion object {

        private fun parseVersionString(versionString: String): Pair<Int, String?> {
            val re = "(\\d+)(-(.*))?".toRegex()
            val mr = re.find(versionString)
            val v = mr?.let { r -> r.groups[1] }?.value?.toInt()
                ?: throw IllegalArgumentException("$versionString is not a valid Semantic patch identifier")
            val p = mr.let { g ->
                if (g.groups.size == 4) {
                    g.groups[3]
                } else null
            }?.value
            return Pair(v, p)
        }

        fun readPatch(versionString: String): Int {
            return parseVersionString(versionString).first
        }

        fun readPreRelease(versionString: String): String? {
            return parseVersionString(versionString).second
        }

    }
}

/**
 * Following Semantic sorting guidelings
 */
class BuildComparator {
    companion object : Comparator<BuildVersion> {
        override fun compare(o1: BuildVersion, o2: BuildVersion): Int = when {
            o1.patch != o2.patch -> o1.patch - o2.patch
            o1.preRelease != o2.preRelease ->
                (o1.preRelease ?: "").compareTo(o2.preRelease ?: "")

            listOf(o1.preRelease, o2.preRelease).all { it == null } -> 0
            else -> (o1.preRelease ?: "").compareTo(o2.preRelease ?: "")
        }
    }
}

class VersionComparator {
    companion object : Comparator<Version> {
        override fun compare(o1: Version, o2: Version): Int =
            when {
                o1.major != o2.major -> (o1.major.toIntOrNull() ?: 0).compareTo(o2.major.toIntOrNull() ?: 0)
                o1.minor != o2.minor -> (o1.minor.toIntOrNull() ?: 0).compareTo(o2.minor.toIntOrNull() ?: 0)
                else -> o1.build.compareTo(o2.build)
            }
    }
}


