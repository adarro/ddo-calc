package org.aos.ddo.support.naming

/**
  * Adds optional descriptive text.
  * Used mainly to discriminate objects such as 'Shield Proficiency (General)'
  */
trait PostText extends DisplayProperties {
  def postText: Option[String]

  /**
    * Surrounds the text with the given characters.
    * By default, this is parenthesis.
    */
  protected val postTextEnclosure: (String, String) = (" (", ")")

  def withPostText: Option[String] = {
    val (x, y) = postTextEnclosure
    postText match {
      case Some(p) => Some(s"$x$p$y")
      case _ => None
    }
  }

  abstract override def displaySource: String = super.displaySource + withPostText.getOrElse("")

  def hasPostText: Boolean = postText.isDefined
}
