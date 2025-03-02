/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: FieldMapper.scala
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
package io.truthencode.ddo.web.mapping

import com.typesafe.scalalogging.LazyLogging
import enumeratum.{Enum => SmartEnum, EnumEntry}
import io.truthencode.ddo.DDOObject
import io.truthencode.ddo.model.item.{PermanentItem, Potion}
import io.truthencode.ddo.model.item.armor.Armor
import io.truthencode.ddo.model.item.clothing.Clothing
import io.truthencode.ddo.support.matching.WordMatchStrategy
import io.truthencode.ddo.model.item.Item

import scala.collection.immutable
import scala.language.{existentials, postfixOps}

/**
 * Utility object for mapping DDOWiki tables and fields to objects
 */
object FieldMapper extends LazyLogging {

  /**
   * Convenience class to hold a list or single value string list.
   */
  sealed abstract class ItemType(words: Either[String, List[String]]) extends EnumEntry {
    val wordList: List[String] = words match {
      case Right(x) => x
      case Left(x) => List(x)
    }
  }

  /**
   * Enumeration to hold the possible item type values along with keywords used to map the fields
   */
  object ItemType extends SmartEnum[ItemType] {
    val values: immutable.IndexedSeq[ItemType] = findValues

    /**
     * A wieldable weapon
     */
    case object Weapon extends ItemType(Left("Proficiency Class"))

    /**
     * An equipable armor
     */
    case object Armor extends ItemType(Left("Armor Type"))

    /**
     * A potion
     */
    case object Potion extends ItemType(Right(List("Acquired from", "Located in")))

    /**
     * Bracers, Boots, Belts etc.
     */
    case object Clothing extends ItemType(Left("Item Type"))

    /**
     * Rings etc.
     */
    case object Jewelery extends ItemType(Left("Item Type"))

    /**
     * Scroll
     */
    case object Scroll extends ItemType(Right(List("No UMD check for", "Spell", "Caster Level")))

  }

  // +3 Enhancement Bonus
  // Vampirism
  // Bloodletter III
  // Risk and Reward
  /**
   * Determines the type of Item from a set up keywords.
   *
   * @param words
   *   list of words pulled from various fields using the mapping provided by
   *   [[FieldMapper.ItemType.values]]
   * @return
   *   an [[ItemType]] if found, otherwise None
   */
  def fieldType(words: Set[String]): Option[ItemType] =
    FieldMapper.ItemType.values.find { x =>
      words.intersect(x.wordList.toSet).nonEmpty
    }

  /**
   * Extracts weapon information from the DDOWiki site.
   *
   * @param source
   *   HTML data from the site
   * @return
   *   A Weapon object scrapped from the site or None
   */
  def weaponFromWiki(source: Map[String, Any])(implicit
    strategy: WordMatchStrategy): Option[DDOObject.Weapon] = {
    val keys = source.keySet
    val types = fieldType(keys)
    logger.info(s"Filtered type: $types")
    types match {
      case Some(x: ItemType) if x == ItemType.Weapon =>
        logger.info("Item type of weapon found")
        val damageAndTypeString = WikiParser.damage(source)
//        val damageDie: Option[String] =
//          WikiParser.damageDie(damageAndTypeString) // replace with damageinfo
        // TODO: damageAppliedType is not mapped to weapon object
//        val damageAppliedType =
//          WikiParser.damageAppliedType(damageAndTypeString) // replace with damageinfo
        //   val weaponModifier = damageAndTypeString
        //   val extraDamage = WikiParser.weaponModifier(damageAndTypeString)
        val weaponCategory = WikiParser.weaponCategoryInfo(source)
        //  val absReqRace = WikiParser.requiredRace(source)
        // val reqTrait = WikiParser.requiredTrait(source)
        // val binding = WikiParser.binding(source)
        //  val loc = WikiParser.location(source)
        // = "Enchantments"
        // TODO: mapping enchantments, as these are nearly any effect.
        // Need to refactor it out to a separate method once we have effects.
        //  val enchantments = WikiParser.enchantments(source)
        // TODO: Need to add Item Sets ,
        // they are included under Enchantments and should be handled / extracted separately

        val weapon = DDOObject.Weapon(
          absoluteMinimumLevel = None,
          baseValue = WikiParser.monetaryValue(WikiParser.baseValue(source)),
          description = WikiParser.description(source),
          durability = WikiParser.durability(source),
          hardness = WikiParser.hardness(source),
          binding = WikiParser.binding(source),
          material = WikiParser.madeFrom(source),
          minimumLevel = WikiParser.minimumLevel(source),
          umd = WikiParser.umdDc(source),
          weight = WikiParser.weight(source), // Members declared in io.truthencode.ddo.Weapon
          attackModifier = WikiParser.attackModifier(source),
          critical = WikiParser.criticalProfile(WikiParser.criticalThreat(source)),
          damage = WikiParser.damage(source),
          damageModifier = WikiParser.damageModifier(source),
          handedness = WikiParser.handedness(source),
          proficiency = WikiParser.proficiency(source),
          upgradeable = WikiParser.upgradable(source),
          weaponCategory = weaponCategory,
          weaponType = WikiParser.weaponType(weaponCategory),
          enchantments = WikiParser.enchantments(source)
        )
        Some(weapon)
      case _ => None
    }
  }

  /**
   * WikiToProperty Reads sourced data from DDOWiki and attempts to mangle it into readable
   * properties.
   *
   * @param source
   *   a collection of properties that should generally be in the form of simple strings or JSoup
   *   Elements.
   * @return
   *   A DDO Item wrapped in an Option
   * @note
   *   Breaking cases: Divine Vengeance has multiple versions based on level and class that equips
   *   it. (RealmsofDespair terms.. a morph) http://ddowiki.com/page/Item:Divine_Vengeance Sun blade
   *   is a slashing short sword, but has 'see note' in the damage type field
   *   http://ddowiki.com/page/Item:Sun_Blade
   */
  def wikiToItem[T <: Item](source: Map[String, Any])(implicit
    strategy: WordMatchStrategy): Option[Item] = {

    val keys = source.keySet
    val types = fieldType(keys)
    logger.info(s"Filtered type: $types")
    types match {
      case Some(x: ItemType) if x == ItemType.Weapon => weaponFromWiki(source)
      case Some(_: Armor) =>
        throw new NotImplementedError
      case Some(_: Potion) =>
        throw new NotImplementedError
      case Some(_: Clothing) =>
        throw new NotImplementedError
      case _ => None

    }
  }
}
