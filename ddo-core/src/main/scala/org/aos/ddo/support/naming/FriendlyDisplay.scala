package org.aos.ddo.support.naming

import org.aos.ddo.support.StringUtils.Extensions

/**
  * Alters Display by adding spaces between words.
  */
trait FriendlyDisplay extends DisplayProperties {
  abstract override def displaySource: String = {
    val d = super.displaySource
    d.lowerCaseNoise
  }
}
