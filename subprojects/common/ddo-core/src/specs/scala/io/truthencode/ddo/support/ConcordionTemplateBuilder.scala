/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
package io.truthencode.ddo.support

import com.typesafe.scalalogging.LazyLogging
import de.neuland.jade4j.{Jade4J, JadeConfiguration}
import enumeratum.{Enum, EnumEntry}
import io.truthencode.ddo.enumeration.Companionable
import io.truthencode.ddo.support.StringUtils.{randomAlphaString, Extensions}

import scala.jdk.CollectionConverters.IterableHasAsJava
import scala.reflect.ClassTag

/**
 * Created by adarr on 8/14/2016.
 */
class ConcordionTemplateBuilder extends LazyLogging {
  val basePath = "/template"
  val config: JadeConfiguration = {
    val c = new JadeConfiguration
    c.setPrettyPrint(true)
    c.setMode(Jade4J.Mode.HTML)
    c.setCaching(false)
    c
  }

  def renderEnum(fqn: String): Option[String] = {
    val path = s"$basePath/Enum.jade"
    val cpPath = getClass.getResource(path).getFile
    logger.info(s"Attempting to load Template file => $cpPath")

    try {
      // WOW!!! Unsafe
      val cls = Class.forName(fqn).newInstance
      val template = config.getTemplate(cpPath)
      nameToClass(cls) match {
        case Some(x) =>
          val model = modelBuilder(x)

          val output = Jade4J.render(template, model)
          logger.info(s"Pretty printing results? ${config.isPrettyPrint} ")
          Option(output)
        case None =>
          logger.error(s"Failed to convert $fqn, could not build model.")
          None
      }
    } catch {
      case e: InstantiationException =>
        logger.error(s"Could not instantiate $fqn, ${e.getMessage}")
        None
      case e: Exception =>
        logger.error(s"Failed to render file $cpPath", e)
        None
    }
  }

  def nameToClass[T: ClassTag](cls: T): Option[Enum[_ <: EnumEntry]] = {
    val cName = cls.getClass.getName
    cls match {
      case x: Companionable =>
        logger.info(s"Casting $cName from Companionable")
        Some(x.companion.asInstanceOf[Enum[_ <: EnumEntry]])
      case x: Enum[_] =>
        logger.info(s"Returning Enum from base")
        Some(x)
      case _: EnumEntry =>
        logger.info(s"EnumEntry located $cName")
        None
      case _: AnyRef =>
        logger.warn(s"nameToClass resolved $cName")
        None
      case _ => None
    }
  }

  /*def findCompanion[E <: Companion[EnumEntry]](entry: E): Enum[_ <: EnumEntry] = {
    entry.comp
  }*/

  def modelBuilder(source: Enum[_]): java.util.HashMap[String, Object] = {
    val model = new java.util.HashMap[String, Object]()

    val name = source.getClass.getSimpleName.filterAlphaNumeric.splitByCase
    val values = source.values.asJava
    val ciValues = source.values.map { x => x.toString.randomCase }.asJava

    val sLen = 10
    val invalid = (for { x <- 0 to 3 } yield randomAlphaString(sLen).randomCase).asJava
    val singleValue =
      source.values.headOption.map { h => h.toString }
        .getOrElse("Please specify at least one value for this enum")
    model.put("name", name)
    model.put("values", values)
    model.put("invalidValues", invalid)
    model.put("ciValues", ciValues)
    model.put("singleValue", singleValue)

    model
  }
}
