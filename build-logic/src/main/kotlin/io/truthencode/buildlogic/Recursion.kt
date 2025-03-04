package io.truthencode.buildlogic

data class Recursion(
    override val recurseValue: RecurseValue,
    override val depth: Int,
) : RecurseOption {
    operator fun plus(increment: Int): Recursion =
        when (depth) {
            if (depth + increment <= 0) depth else -1 -> RecurseNone
            Int.MAX_VALUE - increment, Int.MAX_VALUE -> RecurseInfinite
            else -> RecurseSome(depth + increment)
        }

    operator fun minus(increment: Int): Recursion =
        when (depth) {
            Int.MAX_VALUE -> RecurseInfinite
            if (depth <= 0 || depth - increment <= 0) depth else -1 -> RecurseNone
            else -> RecurseSome(depth - increment)
        }

    operator fun inc(): Recursion = this.plus(1)

    operator fun dec(): Recursion = this.minus(1)

    companion object {
        val RecurseNone = Recursion(RecurseValue.NONE, 0)

        fun RecurseSome(depth: Int) = Recursion(RecurseValue.FINITE, depth)

        val RecurseInfinite = Recursion(RecurseValue.INFINITE, Int.MAX_VALUE)
    }
}