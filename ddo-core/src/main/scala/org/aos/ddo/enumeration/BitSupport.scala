package org.aos.ddo.enumeration

import enumeratum.{Enum, EnumEntry}

import scala.reflect.{ClassTag, classTag}

/**
  * Created by adarr on 1/22/2017.
  */
trait BitSupport {

  //  self: Enum[_] =>
  type T <: EnumEntry

  def checkVal[C: ClassTag](cls: C): Boolean = classTag[C].runtimeClass.isInstance(cls)

  val bitValues: Map[T, Int]
}
