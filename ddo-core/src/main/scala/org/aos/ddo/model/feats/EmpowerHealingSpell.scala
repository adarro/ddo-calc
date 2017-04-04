package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/21/2017.
  */
protected[feats] trait EmpowerHealingSpell
    extends FeatRequisiteImpl
    with EpicMetaMagic
    with RequiresAnyOfClass { self: MetaMagicFeat =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List(
      (CharacterClass.Bard, 1),
      (CharacterClass.Cleric, 1),
      (CharacterClass.Druid, 1),
      (CharacterClass.FavoredSoul, 1),
      (CharacterClass.Paladin, 4),
      (CharacterClass.Ranger, 8)
    )
}
