package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Barbarian, Rogue}
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Wilderness_Lore Wilderness Lore]]
  * This feat grants represents your knowledge of the wilderness.
  * Characters with this feat are granted special quest-specific dialog options/object interactions that classes without this feat otherwise could not perform.
  * It may also allow certain skill checks to learn insight into specific situations.
  * Barbarian, Druid, Ranger received this feat once for every level.
  * Bard received this feat at level 1, 3, 5, 7, 9 ,11, 13 ,15, 17, 19.
  * Verbatim from the release notes:
  * Many classes now gain the "Arcane Lore", "Religious Lore", or "Wilderness Lore" feats every level,
  * which may modify certain dialog options or come up in other ways during quests.
  */
protected[feats] trait WildernessLore
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with FreeFeat { self: ClassFeat =>
  override def grantToClass: Seq[(CharacterClass, Int)] =
    List((Barbarian, 4), (Rogue, 4))
}
