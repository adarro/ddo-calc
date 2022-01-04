package io.truthencode.ddo.api.model.effect

trait ValueInfo {
  def intValue: Option[Int]
  def hasValue: Boolean = intValue.isDefined
}
