package org.aos.ddo.support

/**
  * Simple Trait used to add Full Text when names may not reflect display values
  */
trait Descriptive {
  /**
    * Text as seen by user or in text.
    *
    * i.e. MySkillSpecialGreater might display "My Special Skill (Greater)"
    */
  val display: String
}
