package org.aos.ddo.support.matching

import org.aos.ddo.support.String.MatchStrategy

/**
  * Denotes match is against a String.
  */
trait StringMatch extends MatchStrategy {
  self: StringMatchBounds =>
}
