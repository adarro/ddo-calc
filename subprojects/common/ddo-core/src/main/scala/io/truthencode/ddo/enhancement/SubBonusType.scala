package io.truthencode.ddo.enhancement

/**
 * Denotes this bonus type is broken down into sub-categories.
 */
trait SubBonusType extends SubBonusInformation {
  override def isSubCategory: Boolean = true
}
