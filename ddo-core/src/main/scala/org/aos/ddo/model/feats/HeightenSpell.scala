package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/21/2017.
  * @todo Need to add requirement to cast 2nd level spells
  */
trait HeightenSpell  extends FeatRequisiteImpl
  with EpicMetaMagic
  with RequiresAnyOfClass { self: MetaMagicFeat =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Artificer, 4),
      (CharacterClass.Bard, 4),
      (CharacterClass.Cleric, 3),
      (CharacterClass.Druid, 1),
      (CharacterClass.FavoredSoul, 4),
      (CharacterClass.Sorcerer, 4),
      (CharacterClass.Warlock, 4),
      (CharacterClass.Wizard, 3),
      (CharacterClass.Paladin, 8),
      (CharacterClass.Ranger, 8))
}