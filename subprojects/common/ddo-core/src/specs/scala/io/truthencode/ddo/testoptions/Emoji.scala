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
package io.truthencode.ddo.testoptions

import _root_.io.truthencode.ddo.testoptions.Flexmark._
import com.vladsch.flexmark.ext.emoji.{EmojiExtension, EmojiImageType, EmojiShortcutType}
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension

/**
 * Configures and enables Flexmarks Emojis hopefully with the help of
 * [[https://github.com/WebpageFX/emoji-cheat-sheet.com emojii cheat sheet]] extension
 *
 * @see
 *   [[https://github.com/vsch/flexmark-java/wiki/Extensions#emoji]]
 */
trait Emoji extends Flexmark {

  abstract override def flexmarkExtensions: Seq[Extension] =
    super.flexmarkExtensions :+ EmojiExtension.create()

  abstract override def calls: Seq[() => MutableDataSet] = super.calls :+ taskOptions()

  /**
   * see [[https://github.com/vsch/flexmark-java/wiki/Extensions#emoji]]
   * @param dataSet
   * @return
   *   Mutated dataset with specified default options
   */
  private[this] def taskOptions()(implicit dataSet: MutableDataSet): () => MutableDataSet = { () =>
    dataSet
      .set(EmojiExtension.ATTR_IMAGE_SIZE, "24")
      .set(EmojiExtension.USE_IMAGE_TYPE, EmojiImageType.UNICODE_FALLBACK_TO_IMAGE)
      .set(EmojiExtension.USE_SHORTCUT_TYPE, EmojiShortcutType.ANY_EMOJI_CHEAT_SHEET_PREFERRED)
  }

}
