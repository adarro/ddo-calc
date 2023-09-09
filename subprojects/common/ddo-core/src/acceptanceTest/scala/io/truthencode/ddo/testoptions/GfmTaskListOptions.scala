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
import com.vladsch.flexmark.ext.gfm.tasklist.{TaskListExtension, TaskListItemPlacement}
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension

trait GfmTaskListOptions extends Flexmark {
  abstract override def flexmarkExtensions: Seq[Extension] =
    super.flexmarkExtensions :+ TaskListExtension.create()

  abstract override def calls: Seq[() => MutableDataSet] = super.calls :+ gfmTaskOptions()

  def gfmTaskOptions()(implicit dataSet: MutableDataSet): () => MutableDataSet = { () =>
    dataSet
      .set(TaskListExtension.ITEM_DONE_MARKER, safeItemDone)
      .set(TaskListExtension.ITEM_NOT_DONE_MARKER, safeItemNotDone)
      .set(TaskListExtension.FORMAT_LIST_ITEM_PLACEMENT, TaskListItemPlacement.INCOMPLETE_FIRST)

    //    var d = dataSet
    //    oThingsToSet.foreach(e => d = d.set(e._1, e._2))
    //    d

  }

  private def safeItemDone = TaskListExtension.ITEM_DONE_MARKER.getDefaultValue.removeNbsp()

  private def safeItemNotDone = TaskListExtension.ITEM_NOT_DONE_MARKER.getDefaultValue.removeNbsp()

//  def set[T](key: DataKey[T], value: T)(implicit dataSet: MutableDataSet): MutableDataSet = {
//    dataSet.set(key, value)
//  }

}
