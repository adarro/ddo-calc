package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, RequiresAnyOfClass}

/**
  * This feat grants represents your knowledge of the Arcane.
  * Characters with this feat are granted special quest-specific dialog options/object interactions that classes without this feat otherwise could not perform.
  * It may also allow certain skill checks to learn insight into specific situations.
  */
protected[feats] trait ArcaneLore
    extends FeatRequisiteImpl
    with Passive
    with RequiresAnyOfClass {
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    List((CharacterClass.Artificer, 1),
         (CharacterClass.Bard, 1),
         (CharacterClass.Sorcerer, 1),
         (CharacterClass.Wizard, 1))
}
