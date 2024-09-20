package io.truthencode.ddo.support.validation

import zio._
import zio.test.{test, _}
import zio.test.junit.JUnitRunnableSpec


object ScalingInfoTest extends JUnitRunnableSpec {
    def spec = suite("MySpec")(
        test("test") {
            for
                _ <- ZIO.unit
            yield assertCompletes
        }
    )
}
