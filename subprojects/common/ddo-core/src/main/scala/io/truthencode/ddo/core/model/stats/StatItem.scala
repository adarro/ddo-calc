/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: StatItem.scala
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
package io.truthencode.ddo.model.stats

import enumeratum.EnumEntry
import io.truthencode.ddo.api.model.effect.DetailedEffect
import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.effect._
import io.truthencode.ddo.support.naming.UsingSearchPrefix

import scala.util.Try

trait StatItem[T <: EnumEntry, V] {
  val item: T
  def value: V
  def modifiers: List[PartModifier[V, T]] = Nil
}

/**
 * Convenience trait used when the type is also the value, i.e. Alignment or Race is itself the
 * value.
 * @tparam T
 *   The basic type
 */
trait SimpleStatItem[T <: EnumEntry] extends StatItem[T, T]

trait AttributeStat extends StatItem[Attribute, Int] {
  val baseValue: Int
//  override def modifiers: List[PartModifier[Int, Attribute]] = Nil
}
// trait AStr extends AttributeStat[Strength]

case class PlayerAttribute(
  override val item: Attribute,
  override val baseValue: Int = 0
) extends AttributeStat {
  override def value: Int = ???
}

case class PlayerStrAttribute(
  override val baseValue: Int = 0
) extends AttributeStat {

  //   override val item: A = Attribute.Strength
  override val item: Attribute = Attribute.Strength

  override def value: Int = ???
}

object ThrowAway {
  type T = Attribute
  type V = Int
  type A = BonusType
  type MyPair = (EffectParameter, V)
  val m: Map[T, List[MyPair]] = Map(
    Attribute.Strength -> List(
      (EffectParameter.BonusType(BonusType.ActionBoost), 5)
    )
  )
  // val a = new AttributeStat[Attribute.Strength] {}
  val f: PartModifier[V, T] & UsingSearchPrefix = new PartModifier[V, T] with UsingSearchPrefix {

    /**
     * The General Description should be just that. This should not include specific values unless
     * all instances will share that value. I.e. a Dodge Effect might state it increases your
     * miss-chance, but omit any value such as 20%. Those values will be displayed in the effectText
     * of a specific implementation such as the Dodge Feat or Uncanny Dodge
     */
    override val generalDescription: String = "Some specifically vague description"

    /**
     * a list of Categories useful for menu / UI placement and also for searching / querying for
     * Miss-Chance or other desired effects.
     *
     * This list might be constrained or filtered by an Enumeration or CSV file. The goal is to
     * enable quick and advanced searching for specific categories from general (Miss-Chance) to
     * specific (evasion). In addition, it may be useful for deep searching such as increasing Spot,
     * which should suggest not only +Spot items, but +Wisdom or eventually include a feat or
     * enhancement that allows the use of some other value as your spot score.
     */
    override def categories: Seq[String] = Seq(EffectCategories.General.toString)

    /**
     * Used when qualifying a search with a prefix. Examples include finding "HalfElf" from
     * qualified "Race:HalfElf"
     *
     * @return
     *   A default or applied prefix
     */
    override def searchPrefixSource: String = Attribute.searchPrefixSource

//    lazy override protected[this] val partToModify: T = Attribute.Strength
//      private val eb = EffectParameterBuilder()
//          .toggleOffValue(triggerOff: _*)
//          .toggleOnValue(triggerOn: _*)
//          .addBonusType(spellCriticalBonusType)
//          .build
//
//      override protected[this] def effectParameters: Seq[ParameterModifier[_]] = eb.modifiers
    override val effectDetail: DetailedEffect = ???
    override val value: V = 3
    override val source: SourceInfo = new SourceInfo {
      override val sourceId: String = "Example"
      override val sourceRef: AnyRef = this
    }
    override protected val partToModify: T = ???
  }
  val str: PlayerAttribute = PlayerAttribute(Attribute.Strength, 6)
  val l = List((EffectParameter.BonusType, 3))

  // val str = PlayerAttribute(item= Strength,baseValue = 6)
  def doStr(): Unit = {
    val mm: Map[Try[EffectPart], List[PartModifier[V, T]]] =
      str.modifiers.filter(f => f.part.isSuccess).groupBy(_.part)
    for g <- mm
    yield g
    for
      part <- str.modifiers.filter(f => f.part.isSuccess).groupBy(_.part)
      mod <- part._2
    yield m
//    val ps = for {
//      group <- str.modifiers.groupBy(_.part)
//      feature <- group._2
//      param <- feature.parameter
//
//    } yield param
//   val g: Map[Try[EffectPart], List[Feature[V]]] = str.features.groupBy(_.part)
//    g.foreach(k => k._2.)
//    str.features.foldLeft(Map[Try[EffectPart]],List[Try[EffectParameter]]()) {(a,b) =>b match {
//
//    }}

  }

  // val strAttribute = StatEnums(Attribute.Strength,)

}
