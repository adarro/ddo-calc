package org.aos.ddo.support

/**
  * Mixin Used to prefix a name.
  * Useful for categories to alter 'Dwarf' into 'Race:Dwarf'
  */
trait Prefix extends DisplayProperties {
  def prefix: Option[String]
  /**
    * Delimits the prefix and text.
    */
  protected val prefixSeparator: String = ":"

  def withPrefix: String = prefix match {
    case Some(p) => s"$p$prefixSeparator"
    case _ => prefixSeparator
  }

  abstract override def displaySource: String = withPrefix + super.displaySource

  def hasPrefix: Boolean = prefix.isDefined
}
