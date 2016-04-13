package org.aos.ddo

/** Provides information on whether a given object is upgradeable and may contain additional
  * information on how to upgrade it.
  */
trait UpgradeInfo {
  def text: Option[String]
}
