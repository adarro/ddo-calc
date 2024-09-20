package io.truthencode.ddo.support.validation

import com.typesafe.scalalogging.Logger

import zio.prelude.Validation

val logger = Logger("io.truthencode.ddo.support.validation")

def validateTriggers(triggers: Seq[String]): Validation[String, Seq[String]] =
    if triggers.isEmpty then {
        Validation.fail("At least one valid trigger must be specified")
    } else {
        import io.truthencode.ddo.support.TraverseOps._
        val tIn = triggers.mkString(", ")
        logger.debug(s"Evaluating Triggers $tIn")
        val tCommon = triggers.intersect(triggerNames)
        logger.info(s"intersection found ${tCommon.size} Triggers $tCommon ")
        if tCommon.nonEmpty then {
            // Succeed but warn if there are unknown triggers (which we will filter out)
            val unknowns = triggers.nSelect(triggerNames)
            if unknowns.nonEmpty then {
                val badIds = unknowns.mkString(", ")
                logger.warn(s"Filtering out unknown triggers: $badIds")
            }
            Validation.succeed(tCommon)
        } else { // Not a single valid trigger passed in
            val nSel = triggers.nSelect(triggerNames).mkString(", ")
            Validation.fail(s"Invalid Trigger id's $nSel")
        }
    }