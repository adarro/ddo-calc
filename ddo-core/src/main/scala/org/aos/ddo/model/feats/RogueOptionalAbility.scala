package org.aos.ddo.model.feats

import org.aos.ddo.model.classes.CharacterClass.Rogue

/**
  * Created by adarr on 3/26/2017.
  */
trait RogueOptionalAbility {
lazy val rogueOptionLevels = List(10, 13, 16, 19 )
  lazy val rogueOptionMatrix = rogueOptionLevels.map((Rogue,_))

}
