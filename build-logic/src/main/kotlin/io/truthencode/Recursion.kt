package io.truthencode.djaxonomy.etc

data class Recursion(override val recurseValue: RecurseValue, override val depth: Int) : RecurseOption {
    operator fun plus(increment: Int): Recursion {
        return when (depth) {
            if (depth + increment <= 0) depth else -1 -> RecurseNone
            Int.MAX_VALUE - increment, Int.MAX_VALUE -> RecurseInfinite
            else -> RecurseSome(depth + increment)
        }
    }

    operator fun minus(increment: Int): Recursion {
        return when (depth) {
            Int.MAX_VALUE -> RecurseInfinite
            if (depth <= 0 || depth - increment <= 0) depth else -1 -> RecurseNone
            else -> RecurseSome(depth - increment)
        }
    }

    operator fun inc(): Recursion {
        return this.plus(1)
    }

    operator fun dec(): Recursion {
        return this.minus(1)
    }

    companion object {
        val RecurseNone = Recursion(RecurseValue.NONE, 0)

        fun RecurseSome(depth: Int) = Recursion(RecurseValue.FINITE, depth)

        val RecurseInfinite = Recursion(RecurseValue.INFINITE, Int.MAX_VALUE)
    }
}