package org.aos.ddo

package object ddo {
  implicit class EmpoweredDouble(d: Double) {
    def ~^(e: Double): Double = Math.pow(d, e) // scalastyle:off method.name
  }
}
