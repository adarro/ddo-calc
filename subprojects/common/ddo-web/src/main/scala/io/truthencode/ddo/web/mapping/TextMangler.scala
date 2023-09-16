package io.truthencode.ddo.web.mapping

trait TextManipulator[T] {
  def mangle[T](source: Map[String, Any]): Option[T]
}
