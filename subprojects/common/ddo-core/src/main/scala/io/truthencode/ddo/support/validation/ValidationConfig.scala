package io.truthencode.ddo.support.validation

import jakarta.inject.Singleton
import org.eclipse.microprofile.config.inject.ConfigProperty
import zio.prelude.Validation

enum invalidationOptions {
    case RejectAll, FilterValid
}

def validateInt(s: String): Validation[String, Int] =
    Validation(s.toInt).mapError(_.getMessage)
    
@Singleton
object ValidationConfig {
  @ConfigProperty(name="core.filtering.global")
  var globalFilterDefault: String = null

}
