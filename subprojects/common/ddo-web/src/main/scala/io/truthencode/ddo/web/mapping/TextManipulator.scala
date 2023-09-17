package io.truthencode.ddo.web.mapping

trait TextManipulator[T] {
  def mangle[S](source: Map[String, Any]): Option[S]
}
