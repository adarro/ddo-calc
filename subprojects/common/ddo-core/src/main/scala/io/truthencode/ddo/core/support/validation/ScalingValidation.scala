/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: ScalingValidation.scala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.support.validation

import io.truthencode.ddo.api.model.effect.{ScalingEffect, ScalingInfo}
import io.truthencode.ddo.support.TraverseOps.*
import jakarta.inject.Singleton
import org.eclipse.microprofile.config.inject.ConfigProperty
import zio.prelude.{Validation, ZValidation}

// Validates scaling values

@Singleton
object ScalingValidation {
  @ConfigProperty(name = "core.filtering.global", defaultValue = "FilterValid")
  var globalFilterDefault: String = null

  private def filterStrategy = {
    invalidationOptions.values
      .filter(_.toString == globalFilterDefault)
      .map(x => Option(x))
      .headOption
      .flatten
      .getOrElse(invalidationOptions.FilterValid)
  }

  def validateScalingPower(scaling: Option[Seq[ScalingInfo]])(using
    filterStrategy: invalidationOptions): Validation[String, Option[Seq[ScalingInfo]]] = {
    scaling match
      case Some(
            value
          ) => // fail on all or succeed by filtering out invalid values based on config options
        logger.info(s"Some value $value found, validating")
        import zio.*

        val sVal: Seq[Validation[String, ScalingEffect]] =
          for s <- value
          yield Validation.validateWith(
            validateScalingValue(s.value),
            validateScalingType(s.source))(ScalingEffect.apply)

        val f: Seq[Validation[String, ScalingEffect]] = sVal.filter(_.isFailure)
        val s: Seq[Validation[String, ScalingEffect]] = sVal.filter(_.isSuccess)
        logger.info(s"validated ${sVal.size} Pass / fail: ${s.size} / ${f.size} ")
        logger.info(s"matching using filter strategy $filterStrategy")
        filterStrategy match
          // reject if ANY invalid values supplied
          case invalidationOptions.RejectAll =>
            if f.nonEmpty then
              Validation.fail(
                f.map(_.asInstanceOf[ZValidation.Failure[String, ScalingInfo]]).mkString(","))
            else
              Validation.succeed(Some(s.map(suc =>
                suc.asInstanceOf[ZValidation.Success[String, ScalingInfo]].value)))
          case invalidationOptions.FilterValid => // Succeed if we have at least one valid power, else fail
            if s.nonEmpty then
              Validation.succeed(Some(s.map(suc =>
                suc.asInstanceOf[ZValidation.Success[String, ScalingInfo]].value)))
            else
              Validation.fail(
                f.map(_.asInstanceOf[ZValidation.Failure[String, ScalingInfo]]).mkString(","))

      case None => Validation.succeed(scaling)
  }

  def validateScalingValue(sVal: Int): Validation[String, Int] =
    Validation.fromPredicateWith(s"Age $sVal was less than zero")(sVal)(_ >= 0)

  def validateScalingType(sType: String): Validation[String, String] = {
    Validation.fromPredicateWith(s"Invalid scaling type: $sType")(sType)((x: String) =>
      scalingTypes.contains(x))
  }

  def validateScalingTypes(
    scalingType: Option[Seq[String]]): Validation[String, Option[Seq[String]]] = {
    scalingType match {
      case Some(st) =>
        val vIn = st.mkString(", ")
        logger.debug(s"Evaluating Powers $vIn")
        val vCommon = scalingTypes.intersect(st)
        logger.info(s"intersection found ${vCommon.size} Powers $vCommon ")
        if vCommon.nonEmpty then {
          // Succeed but warn if there are unknown triggers (which we will filter out)
          val unknowns = st.nSelect(scalingTypes)
          if unknowns.nonEmpty then {
            val badIds = unknowns.mkString(", ")
            logger.warn(s"Filtering out unknown power: $badIds")
          }
          Validation.succeed(Some(vCommon))
        } else { // Not a single valid trigger passed in
          val nSel = st.nSelect(scalingTypes).mkString(", ")
          Validation.fail(s"Invalid Power id's $nSel")
        }
      case None => Validation.succeed(None)
    }
  }

}
