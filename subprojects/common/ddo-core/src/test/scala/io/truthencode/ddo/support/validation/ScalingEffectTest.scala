package io.truthencode.ddo.support.validation

import org.junit.runner.RunWith
import zio._
import zio.test.{test, _}
import zio.test.junit.ZTestJUnitRunner

@RunWith(classOf[ZTestJUnitRunner])
class ScalingEffectTest  extends ZIOSpecDefault {
    def spec: Spec[TestEnvironment & Scope, Any] = suite("Zio runner")(
        test("test env") {
            for
                _ <- ZIO.unit
            yield assertCompletes
        }
    )

}
