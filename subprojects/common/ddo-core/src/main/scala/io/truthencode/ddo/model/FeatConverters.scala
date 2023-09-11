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
package io.truthencode.ddo.model

import io.truthencode.ddo.model.feats.{GeneralFeat, SubFeat}
import io.truthencode.ddo.model.item.weapon.{
  ExoticWeapon,
  MartialWeapon,
  SimpleWeapon,
  WeaponCategory
}

/**
 * Created by adarr on 4/1/2017.
 */
object FeatConverters {

  val featByWeaponProficiency: PartialFunction[WeaponCategory, GeneralFeat with SubFeat] = {
    case x: SimpleWeapon
        if GeneralFeat.SimpleWeaponProficiency.subFeats.exists(p =>
          p.displayText.contains(x.displayText)) =>
      GeneralFeat.SimpleWeaponProficiency.subFeats.find(p =>
        p.displayText.contains(x.displayText)) match {
        case Some(f: GeneralFeat with SubFeat) => f
      }

    case x: MartialWeapon
        if GeneralFeat.MartialWeaponProficiency.subFeats.exists(p =>
          p.displayText.contains(x.displayText)) =>
      GeneralFeat.MartialWeaponProficiency.subFeats.find(p =>
        p.displayText.contains(x.displayText)) match {
        case Some(f: GeneralFeat with SubFeat) => f
      }

    case x: ExoticWeapon
        if GeneralFeat.ExoticWeaponProficiency.subFeats.exists(p =>
          p.displayText.contains(x.displayText)) =>
      GeneralFeat.ExoticWeaponProficiency.subFeats.find(p =>
        p.displayText.contains(x.displayText)) match {
        case Some(f: GeneralFeat with SubFeat) => f
      }

  }

  //  val weaponCategoryToFeat  :PartialFunction[WeaponCategory,GeneralFeat] = {
  //    case x:
  //    override def isDefinedAt(x: WeaponCategory ): Boolean =
  //    GeneralFeat
  //      .withNameOption(s"${Feat.searchPrefix}${x.entryName}")
  //      .isDefined
  //
  //    override def apply(v1: WeaponCategory ): GeneralFeat = ReqFeat(v1.entryName)
  //  }
}
