package org.aos.ddo.model

import org.aos.ddo.model.feats.{GeneralFeat, SubFeat}
import org.aos.ddo.model.item.weapon.{
  ExoticWeapon,
  MartialWeapon,
  SimpleWeapon,
  WeaponCategory
}

/**
  * Created by adarr on 4/1/2017.
  */
object FeatConverters {

  val featByWeaponProficiency
    : PartialFunction[WeaponCategory, GeneralFeat with SubFeat] = {
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
