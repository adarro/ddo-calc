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
package io.truthencode.ddo.subscription.service

import monix.eval.Task
import monix.execution.Cancelable
import wvlet.log.LogSupport

import java.io.{File, IOException}
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.{SelectionKey, Selector, ServerSocketChannel, SocketChannel}
import java.util
import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object StatusService extends LogSupport {

  private val POISON_PILL: String = "WwiBblE"
  private val bbDefaults = 256
  private val serviceMap: mutable.Map[String, AnyRef] = new mutable.HashMap[String, AnyRef]()
  def registerServer[T <: AnyRef](id: String, service: T): Unit = {
    serviceMap.put(id, service)
  }
  def getService(id: String): Option[AnyRef] = {
    serviceMap.get(id)
  }
  private val defaultSocketAddress = 5454
  private val defaultCallBackDelay = 15
  @throws[IOException]
  def main(): Unit = {
    val selector: Selector = Selector.open
    val serverSocket: ServerSocketChannel = ServerSocketChannel.open
    serverSocket.bind(new InetSocketAddress("localhost", defaultSocketAddress))
    serverSocket.configureBlocking(false)
    serverSocket.register(selector, SelectionKey.OP_ACCEPT)
    val buffer: ByteBuffer = ByteBuffer.allocate(bbDefaults)
    while ({
      true
    }) {
      selector.select
      val selectedKeys: util.Set[SelectionKey] = selector.selectedKeys
      val iter: util.Iterator[SelectionKey] = selectedKeys.iterator
      while ({
        iter.hasNext
      }) {
        val key: SelectionKey = iter.next
        if (key.isAcceptable) {
          register(selector, serverSocket)
        }
        if (key.isReadable) {
          answerWithEcho(buffer, key)
        }
        iter.remove()
      }
    }
  }

  private var Status: DaemonStatus = DaemonStatus.STOPPED
  private var cmd: ServerCommand = ServerCommand.START

  def serverDaemon(): Unit = {}
  @throws[IOException]
  def echoDaemon(): Unit = {
    Status = DaemonStatus.STARTING

    evalDelayed(FiniteDuration.apply(defaultCallBackDelay, TimeUnit.SECONDS)) {
      warn("Sending poison pill")
      stop()
    }

    val selector: Selector = Selector.open
    val serverSocket: ServerSocketChannel = ServerSocketChannel.open
    serverSocket.bind(new InetSocketAddress("localhost", defaultSocketAddress))
    serverSocket.configureBlocking(false)
    serverSocket.register(selector, SelectionKey.OP_ACCEPT)
    val buffer: ByteBuffer = ByteBuffer.allocate(bbDefaults)
    Status = DaemonStatus.RUNNING
    cmd = ServerCommand.CONTINUE
    while ({
      cmd ne ServerCommand.STOP
    }) {
      selector.select
      val selectedKeys: util.Set[SelectionKey] = selector.selectedKeys
      val iter: util.Iterator[SelectionKey] = selectedKeys.iterator
      while ({
        iter.hasNext
      }) {
        val key: SelectionKey = iter.next
        if (key.isAcceptable) {
          register(selector, serverSocket)
        }
        if (key.isReadable) {
          answerWithEcho(buffer, key)
        }
        iter.remove()
      }
    }
  }

  def stop(): Unit = {
    EchoClient.start.sendMessage(POISON_PILL)
    cmd = ServerCommand.STOP
  }

  @throws[IOException]
  private def answerWithEcho(buffer: ByteBuffer, key: SelectionKey): Unit = {
    info("answerWithEcho Called")
    val client: SocketChannel = key.channel.asInstanceOf[SocketChannel]
    client.read(buffer)
    if (new String(buffer.array).trim == POISON_PILL) {
      warn("Poison Pill received, shutting down")
      client.close()
      info("Not accepting client messages anymore")
    } else {
      info("Echoing answer")
      buffer.flip
      client.write(buffer)
      buffer.clear
    }
  }

  @throws[IOException]
  private def register(selector: Selector, serverSocket: ServerSocketChannel): Unit = {
    info("Registering socket client")
    val client: SocketChannel = serverSocket.accept
    client.configureBlocking(false)
    client.register(selector, SelectionKey.OP_READ)
  }

  @throws[IOException]
  @throws[InterruptedException]
  def start: Process = {
    val javaHome: String = System.getProperty("java.home")
    val javaBin: String = javaHome + File.separator + "bin" + File.separator + "java"
    val classpath: String = System.getProperty("java.class.path")
    val className: String = classOf[EchoServer].getCanonicalName
    val builder: ProcessBuilder = new ProcessBuilder(javaBin, "-cp", classpath, className)
    builder.start
  }

  def evalDelayed[A](delay: FiniteDuration)(f: => A): Task[A] = {
    info(s"Starting delayed task ${delay.toSeconds} seconds")
    // On execution, we have the scheduler and
    // the callback injected ;-)
    Task.create { (scheduler, callback) =>
      val cancelable =
        scheduler.scheduleOnce(delay) {
          callback(Try(f))
        }

      // We must return something that can
      // cancel the async computation
      cancelable
    }
  }
  def fromFuture[A](f: Future[A]): Task[A] =
    Task.create { (scheduler, callback) =>
      f.onComplete({
        case Success(value) =>
          callback.onSuccess(value)
        case Failure(ex) =>
          callback.onError(ex)
      })(scheduler)

      // Scala Futures are not cancelable, so
      // we shouldn't pretend that they are!
      Cancelable.empty
    }

}
