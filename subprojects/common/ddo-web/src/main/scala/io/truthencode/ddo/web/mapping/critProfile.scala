package io.truthencode.ddo.web.mapping

/**
 * encapsulates the critical damage profile
 *
 * @example
 *   a typical weapon may have a damage range of 1-20 with a bonus for certain high rolls. The
 *   scimatar for example may have 1-20, with 18-20 receiving a bonus damage multiplier of double
 *   damage. This is written in terms of 18-20 x3.
 *
 * programatically, this would be represented as
 * {{{
 * val cp = critProfile(min = 18, max=20, multiplier = 2)
 * }}}
 * @param min
 *   minimum damage roll
 * @param max
 *   maximum damage roll
 * @param multiplier
 *   amount to multiply when a given roll is within the min / max range.
 *
 * @todo
 *   may move this to core package
 */
case class critProfile(min: Int, max: Int, multiplier: Int)
