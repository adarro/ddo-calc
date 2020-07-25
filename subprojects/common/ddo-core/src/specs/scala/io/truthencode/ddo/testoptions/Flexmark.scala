/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.{DataKey, MutableDataSet}
import com.vladsch.flexmark.util.misc.Extension
import org.concordion.api.option.FlexmarkOptions

import scala.collection.JavaConverters._

trait Flexmark {
  def flexmarkExtensions: Seq[Extension]
  def calls : Seq[() => MutableDataSet]
import Flexmark._
  @FlexmarkOptions  val flexmarkOptions: MutableDataSet = {
    calls.foreach(f => f())
    ds.set(Parser.EXTENSIONS, flexmarkExtensions.toList.toJavaList)

  }
}


object Flexmark {

  implicit class ExtOpts(source: List[Extension]) {

    /**
      * Extension Wrapper because scala is not inferring downcast due to Invariant T
      * @return Downcast T <: Extension
      */
    def toJavaList: java.util.List[Extension] = {
      source.asJava
    }
  }

    implicit class FlexOpts(source: Seq[(DataKey[_],Any)]) {

        /**
         * Extension Wrapper because scala is not inferring downcast due to Invariant T
         * @return Downcast T <: Extension
         */
        def toJavaList: java.util.List[(DataKey[_],Any)] = {
            source.asJava
        }
    }

  implicit class TupOpts(source :Seq[Tuple2[_,_]]) {
      def toJavaList: java.util.List[Tuple2[_,_]] = {
          source.asJava
      }
    }
  implicit class HtmlEscape(source: String) {

    def removeNbsp: String = {
      source.replace("&nbsp;", "&#160;")
    }
  }
  implicit lazy val ds = new MutableDataSet()

}
