package io.truthencode.ddo.subscription

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.protocol.{ChangeType, ChangeValueInt}
import monix.eval.Task
import org.scalatest.funspec.AsyncFunSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration._
import scala.util.Success

class MultiUpdateTest extends AsyncFunSpec with Matchers with LazyLogging {
  import monix.execution.Scheduler.Implicits.global

  def doNoop(): Unit = {
    ()
  }
  val newVal = 41
  describe("Multiple Async Actions") {
    they("should work") {
      def updateValue(newValue: Int): Task[ChangeValueInt] =
        Task.eval(ChangeValueInt("SomeId", newValue, 0, ChangeType.INCREASE))
      def notifyChannel(cv: ChangeValueInt): Task[Unit] = Task { doNoop() }
      def notifyListener(cv: ChangeValueInt): Task[Unit] = Task { doNoop() }

      def massUpA[A](a: Task[A]*): Task[Seq[A]] = {
        val aList: Seq[Task[A]] = a.toIndexedSeq
        Task.parSequence(aList)
      }

      val vm = for {
        c <- updateValue(newVal)
        mu <- massUpA(notifyChannel(c), notifyListener(c))

      } yield Task.now(mu)

      val cm = updateValue(newVal)
        .map(c => massUpA(notifyChannel(c), notifyListener(c)))
        .flatMap(_ => Task(succeed))
      cm.runToFuture

    }

    it("Can be a most basic task") {
      val hiTask = Task { "Hello" }
      val hr = hiTask.runToFuture
      hr.value shouldBe Some(Success("Hello"))
    }

    it("Can be a most basic task after IO side effects") {
      val hiTask = Task { println("side effect!"); "Hello" }
      val hr = hiTask.runToFuture
      hr.value shouldBe Some(Success("Hello"))
    }

    it("Can't have a tick") {
      val tTask: Task[Unit] = Task
        .sleep(3.seconds)
        .flatMap(_ => Task { System.out.println("Tick has someting to return") })
      val f = tTask.runToFuture
      f.value shouldBe empty
    }

    it("can be a really simple for comprehension") {
      val task: Task[String] =
        for {
          t <- Task { System.out.println("Unit Type task") }
          i <- Task { 42 }
          r <- Task { println("Executing..."); "Hello!" }
        } yield r
      val f = task.runToFuture
      f.value match {
        case Some(value) => System.out.println(value)
        case None => System.out.println("Nada")
      }
      f.value shouldBe Some(Success("Hello!"))
    }

    it("can be a simple for comprehension") {
      val task: Task[String] =
        for {
          _ <- Task.sleep(3.seconds)
          r <- Task { println("Executing..."); "Hello!" }
        } yield r
      val f = task.runToFuture
      f.value match {
        case Some(value) => System.out.println(value)
        case None => System.out.println("Nada")
      }
      f.value shouldBe empty
    }
    it("has issues") {
      val items = 0 until 1000

      // The list of all tasks needed for execution
      val tasks: Seq[Task[Int]] = items.map(i => Task(i * 2))
      val aggregate: Task[List[Int]] = Task.parSequence(tasks).map(_.toList)
      val f = aggregate.runToFuture
      f.value shouldBe defined

    }
  }

}
