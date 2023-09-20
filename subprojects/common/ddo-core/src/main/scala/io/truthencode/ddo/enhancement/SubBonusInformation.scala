package io.truthencode.ddo.enhancement

/**
 * Flag used for determining if this is a Sub-category of Bonus type having a common parent This is
 * mainly useful when looking up odd stacking effects such as Mythic bonuses
 *
 * @return
 *   true if this is a Sub-category otherwise false (the default value).
 */
trait SubBonusInformation {

  def isSubCategory: Boolean = false

}
