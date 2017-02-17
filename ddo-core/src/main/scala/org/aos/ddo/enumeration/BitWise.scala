package org.aos.ddo.enumeration

/**
  * Created by adarr on 1/22/2017.
  */
trait BitWise {
  def bitValue: Int

  /**
    * Convenience routine to change an Int into the Bit Representation
    * @param i number to change
    * @return bit representation
    */
  def toBitMask(i:Int): Int = scala.math.pow(2,i).toInt
}
