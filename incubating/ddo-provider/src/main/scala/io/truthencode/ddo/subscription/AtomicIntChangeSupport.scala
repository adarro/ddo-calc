package io.truthencode.ddo.subscription

import com.sksamuel.pulsar4s.Producer
import com.sksamuel.pulsar4s.monixs.MonixAsyncHandler
import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.protocol.{ChangeType, ChangeValueInt}
import monix.eval.Task
import monix.execution.CancelableFuture

trait AtomicIntChangeSupport extends AtomicChangeSupport[Int] with LazyLogging {
  import MonixAsyncHandler._
  import monix.execution.Scheduler.Implicits.global

  import scala.concurrent.stm._

  override protected val currentValue: Ref[Int] = Ref(0)
  override protected val prevValue: Ref[Int] = Ref(0)

  override protected val hasChanged: Ref[Boolean] = Ref(false)

  def readCurrentValue: Int = currentValue.single.get
  def readPreviousValue: Int = prevValue.single.get
  def status: ChangeType = {
    if (hasChanged.single.get) {
      if (currentValue.single.get > prevValue.single.get) { ChangeType.INCREASE }
      else { ChangeType.DECREASE }
    } else { ChangeType.NOCHANGE }
  }
  private def sendThings(change: ChangeValueInt) = {
    logger.info(s"attempting to publish $change send Things")
    val t = producer.sendAsync(change)

    val f = t.runToFuture
    logger.info("inside send Things")
    f
  }
  def producer: Producer[ChangeValueInt]
  def updateValue(newValue: Int): Task[ChangeValueInt] = Task {
    atomic { implicit txn =>
      // Only set / notify on actual change of values
      if (currentValue() != newValue) {
        prevValue() = currentValue()
        currentValue() = newValue
        hasChanged() = true
      } else {
        hasChanged() = false
      }
    }

    ChangeValueInt(changeValueKey, readCurrentValue, readPreviousValue, status)
  }

  def notifyChannel(cv: ChangeValueInt): Task[Unit] = Task {
    cv.changeType match {
      case ChangeType.INCREASE | ChangeType.DECREASE => sendThings(cv)

      case ChangeType.NOCHANGE => ()
    }
  }

  def notifyListener(cv: ChangeValueInt): Task[Unit] = Task {
    cv.changeType match {
      case ChangeType.INCREASE | ChangeType.DECREASE =>
        valueNotification.firePropertyChange(changeValueKey, readPreviousValue, readCurrentValue)

      case ChangeType.NOCHANGE => ()
    }
  }

  def updateL(newValue: Int): Task[Task[Seq[Unit]]] =
    updateValue(newValue)
      .map(c => executeParallel(notifyChannel(c), notifyListener(c)))

  override def update(newValue: Int): CancelableFuture[Task[Seq[Unit]]] =
    updateValue(newValue)
      .map(c => executeParallel(notifyChannel(c), notifyListener(c)))
      .runToFuture
}
