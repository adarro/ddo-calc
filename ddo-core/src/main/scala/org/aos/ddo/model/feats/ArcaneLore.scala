package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{
  Artificer,
  Bard,
  Sorcerer,
  Wizard
}
import org.aos.ddo.support.requisite.{FeatRequisiteImpl, GrantsToClass}

/**
  * This feat grants represents your knowledge of the Arcane.
  * Characters with this feat are granted special quest-specific dialog options/object interactions that classes without this feat otherwise could not perform.
  * It may also allow certain skill checks to learn insight into specific situations.
  */
protected[feats] trait ArcaneLore
    extends FeatRequisiteImpl
    with Passive
    with StackableFeat
    with GrantsToClass {

  private def grantedClasses =
    for {
      gc <- List(Artificer, Bard, Sorcerer, Wizard)
      l <- 1 to 20
    } yield (gc, l)
  override def grantToClass: Seq[(CharacterClass, Int)] =
    grantedClasses
}
