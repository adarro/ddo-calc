package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * Created by adarr on 2/21/2017.
  */
trait EnlargeSpell
    extends FeatRequisiteImpl
    with EpicMetaMagic
    with RequiresAnyOfClass { self: MetaMagicFeat =>
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    MetaMagicFeat.minimumSpellCastingClass
}
