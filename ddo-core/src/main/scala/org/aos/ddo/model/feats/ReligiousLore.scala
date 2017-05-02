package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.support.requisite.{
  FeatRequisiteImpl,
  FreeFeat,
  GrantsToClass
}

/**
  * [[http://ddowiki.com/page/Religious_Lore Religious Lore]]
  * This feat grants represents your knowledge of the Religion.
  * Characters with this feat are granted special quest-specific dialog options/object interactions that classes without this feat otherwise could not perform.
  * It may also allow certain skill checks to learn insight into specific situations.
  *
  * Cleric, Favored Soul, Paladin received this feat once for every level.
  * Bard received this feat at level 1, 3, 5, 7, 9 ,11, 13 ,15, 17, 19.
  *
  * Verbatim from the release notes:
  * Many classes now gain the "Arcane Lore", "Religious Lore", or "Wilderness Lore" feats every level,
  * which may modify certain dialog options or come up in other ways during quests.
  */
protected[feats] trait ReligiousLore
    extends FeatRequisiteImpl
    with Passive
    with Active
    with GrantsToClass
    with StackableFeat
    with FreeFeat { self: ClassFeat =>

  private def bardLevels =
    (1 to 20 by 2).toList.map((CharacterClass.Bard, _))

  private def allLevelsClasses =
    for {
      c <- List(CharacterClass.Cleric,
                CharacterClass.FavoredSoul,
                CharacterClass.Paladin,
                CharacterClass.FavoredSoul)
      l <- 1 to 20
    } yield (c, l)

  override def grantToClass: Seq[(CharacterClass, Int)] =
    allLevelsClasses.sortBy(_._1.entryName) ++ bardLevels
}
