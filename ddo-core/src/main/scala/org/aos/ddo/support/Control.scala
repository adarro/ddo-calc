package org.aos.ddo.support

import scala.language.reflectiveCalls
/**
  * Created by adarr on 8/15/2016.
  */
object Control {
  def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}
