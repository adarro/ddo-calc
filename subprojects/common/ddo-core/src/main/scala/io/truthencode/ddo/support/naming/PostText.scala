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
package io.truthencode.ddo.support.naming

/**
 * Adds optional descriptive text. Used mainly to discriminate objects such as 'Shield Proficiency
 * (General)'
 */
trait PostText extends DisplayProperties {

  /**
   * Surrounds the text with the given characters. By default, this is parenthesis. The default
   * behavior should mimic case classes.
   */
  protected val postTextEnclosure: (String, String) = (" (", ")")

  def postText: Option[String]

  abstract override def displaySource: String = super.displaySource + withPostText.getOrElse("")

  def withPostText: Option[String] = {
    val (x, y) = postTextEnclosure
    postText match {
      case Some(p) => Some(s"$x$p$y")
      case _ => None
    }
  }

  def hasPostText: Boolean = postText.isDefined
}
