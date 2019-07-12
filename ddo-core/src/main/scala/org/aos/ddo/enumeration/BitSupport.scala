package org.aos.ddo.enumeration

import enumeratum.EnumEntry

import scala.reflect.{classTag, ClassTag}

/**
  * Created by adarr on 1/22/2017.
  */
trait BitSupport {

  //  self: Enum[_] =>
  type T <: EnumEntry

  def checkVal[C: ClassTag](cls: C): Boolean = classTag[C].runtimeClass.isInstance(cls)

  val bitValues: Map[T, Int]
}
