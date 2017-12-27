package org.aos.ddo.etl.affix

/**
  * Created by adarr on 5/23/2017.
  */
case class Affix(slot: EquipmentSlot,
                 affixType: AffixType,
                 baseName: String,
                 modifier: Option[Modifier] = None,
                 category: Option[Category] = None)
