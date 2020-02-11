package org.aos.ddo.model.character

import org.aos.ddo.model.attribute.Attribute
import org.aos.ddo.model.classes.HeroicCharacterClass
import org.aos.ddo.model.race.Race

trait Character {
  /**
    * Name of character
    */
  val name : String = "Unnamed"
  /**
    * Character Race
    */
  val race: Race
  /**
    * Current Character Level
    */
  val characterLevel : Int

  val heroicLevels : List[(Int,HeroicCharacterClass)]

  val epicLevels :

  /* Stats */
  val baseStrength: Attribute
}


