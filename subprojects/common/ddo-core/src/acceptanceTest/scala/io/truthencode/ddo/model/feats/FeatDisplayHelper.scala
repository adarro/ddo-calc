/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: FeatDisplayHelper.scala
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
package io.truthencode.ddo.model.feats

import io.truthencode.ddo.model.DisplayHelper

import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava

/**
 * Created by adarr on 2/17/2017. Adds convenience and display functions for Concordion Acceptance
 * testing against Feats in various contexts.
 */
trait FeatDisplayHelper extends DisplayHelper {

  /**
   * Removes Sub-Feats such as Weapon Proficiencies
   */
  val filterByMainFeat: PartialFunction[Entry, Entry] = {
    case x: (Feat & SubFeatInformation) if !x.isSubFeat => x
  }

  def prettyPrint(): String = {
    listValues("Current Supported Feats", collapse = true)
  }

  def listValues(heading: String, collapse: Boolean = false): String = {

    val data = displayEnum.values.map { a =>
      s"<tr><td>${a.displayText}</td></tr>"
    }.sorted.mkString
    val header = s"<table><tr><th>$heading</th></tr>"
    val footer = "</table>"
    val table = s"$header$data$footer"
    if collapse then {
      s"""<div class=\"collapsible\">$table</div>"""
    } else {
      table
    }
  }

  def verify(): util.List[String] = { displayEnum.values.collect(filterByMainFeat) }.map { x =>
    x.displayText
  }.toList.sorted.asJava

  def withNameAsJavaList(id: String): util.List[String] = withNameAsList(id).asJava

  def withNameAsList(skillId: String*): Seq[String] = {
    for
      id <- skillId
      skill <- withName(id)
    yield skill.displayText
  }

  protected def withName(skillId: String): Option[Entry] =
    displayEnum.withNameOption(skillId)

  def findByName(skillId: String): String = {
    withNameAsList(skillId).headOption.orNull
  }
}
