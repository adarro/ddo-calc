/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: GfmStrikethroughSubscript.scala
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

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension

/**
 * Configures and enables Flexmarks GFM Strikethroughsubscript extension
 * @see
 *   [[https://github.com/vsch/flexmark-java/wiki/Extensions#gfm-strikethroughsubscript]]
 */
trait GfmStrikethroughSubscript extends Flexmark {

  abstract override def flexmarkExtensions: Seq[Extension] =
    super.flexmarkExtensions :+ StrikethroughSubscriptExtension.create()

  // No options currently specified
  // abstract override def calls: Seq[() => MutableDataSet] = super.calls :+ taskOptions()

  /**
   * see [[https://github.com/vsch/flexmark-java/wiki/Extensions#gfm-strikethroughsubscript]]
   * @param dataSet
   *   used to configure the extension
   * @return
   *   configured dataSet
   */
  private def taskOptions()(implicit dataSet: MutableDataSet): () => MutableDataSet = { () =>
    dataSet
    //   .set(StrikethroughSubscriptExtension.STRIKETHROUGH_STYLE_HTML_CLOSE,"someCssClassLikely")

  }

}
