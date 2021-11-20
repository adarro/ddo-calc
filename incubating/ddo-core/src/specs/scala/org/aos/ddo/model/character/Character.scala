package io.truthencode.ddo.model.character

import io.truthencode.ddo.model.attribute.Attribute
import io.truthencode.ddo.model.classes.HeroicCharacterClass
import io.truthencode.ddo.model.race.Race

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


