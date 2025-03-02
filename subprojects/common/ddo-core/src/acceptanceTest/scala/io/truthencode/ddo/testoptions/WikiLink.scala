/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: WikiLink.scala
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
package io.truthencode.ddo.testoptions

import _root_.io.truthencode.ddo.testoptions.Flexmark._
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension

/**
 * Configures and enables Flexmarks Emojis hopefully with the help of
 * [[https://github.com/WebpageFX/emoji-cheat-sheet.com emojii cheat sheet]] extension
 *
 * @see
 *   [[https://github.com/vsch/flexmark-java/wiki/Extensions#emoji]]
 */
trait WikiLink extends Flexmark {

  abstract override def flexmarkExtensions: Seq[Extension] =
    super.flexmarkExtensions :+ WikiLinkExtension.create()

  abstract override def calls: Seq[() => MutableDataSet] = super.calls :+ wikiLinkTaskOptions()

  /**
   * see [[https://github.com/vsch/flexmark-java/wiki/Extensions#emoji]]
   * @param dataSet
   *   used to configure the extension
   * @return
   *   the configured dataSet Mutated dataset with specified default options
   */
  private def wikiLinkTaskOptions()(implicit dataSet: MutableDataSet): () => MutableDataSet = {
    () =>
      dataSet
        .set(WikiLinkExtension.IMAGE_LINKS, Boolean.box(true))

  }

}
