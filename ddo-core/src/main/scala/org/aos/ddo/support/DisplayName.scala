package org.aos.ddo.support

/**
  * Wrapper class to store / convert EnumEntry IDs with their expanded text representation.
  * Mainly needed with special cases like Sub-feats (Weapon Focus: Bludgeon).
  * Can Mix in additional manipulators.
  */
trait DisplayName extends DisplayProperties {
  /**
    * Sets or maps the source text for the DisplayName.
    *
    * @return Source text.
    */
  protected def nameSource: String

  /**
    * @inheritdoc
    */
  override def displaySource: String = nameSource
}
