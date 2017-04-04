package org.aos.ddo.support.naming

/**
  * Mixin Used to prefix a name.
  * Useful for categories to alter 'Dwarf' into 'Race:Dwarf'
  */
trait Prefix extends DisplayProperties {
  def prefix: Option[String]
  /**
    * Delimits the prefix and text.
    */
  protected val prefixSeparator: String = ": "

  def withPrefix: Option[String] = prefix match {
    case Some(p) => Some(s"$p$prefixSeparator")
    case _ => None
  }

  abstract override def displaySource: String = withPrefix.getOrElse("") + super.displaySource

  def hasPrefix: Boolean = prefix.isDefined
}
