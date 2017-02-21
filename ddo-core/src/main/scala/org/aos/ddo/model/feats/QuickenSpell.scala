package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/21/2017.
  * @todo verify ability to cast spells is ~= Class/Level.  Possibly also a naturally aspired min casting stat? I.e. Cleric/1 + WIS 12?
  */
trait QuickenSpell extends FeatRequisiteImpl
  with MetaMagic
  with RequiresAnyOfClass { self: MetaMagicFeat =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    MetaMagicFeat.minimumSpellCastingClass
}