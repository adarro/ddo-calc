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
package io.truthencode.ddo.subscription
import monix.eval.Task
import monix.execution.CancelableFuture

import java.beans.{PropertyChangeListener, PropertyChangeSupport}
import scala.concurrent.stm.Ref

trait AtomicChangeSupport[T] {

  protected val valueNotification = new PropertyChangeSupport(this)

  def addPropertyChangeListener(
    property: String = changeValueKey,
    listener: PropertyChangeListener): Unit = {
    valueNotification.addPropertyChangeListener(property, listener)
  }

  def removePropertyChangeListener(listener: PropertyChangeListener): Unit = {
    valueNotification.removePropertyChangeListener(listener)
  }

  def executeParallel[A](a: Task[A]*): Task[Seq[A]] = {
    val aList: Seq[Task[A]] = a.toIndexedSeq
    Task.parSequence(aList)
  }

  protected val changeValueKey = "IntValue"
  protected val currentValue: Ref[T]
  protected val prevValue: Ref[T]
  protected val hasChanged: Ref[Boolean] = Ref(false)
  def update(newValue: T): CancelableFuture[Task[Seq[Unit]]]
}
