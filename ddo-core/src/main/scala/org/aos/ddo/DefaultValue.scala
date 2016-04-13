package org.aos.ddo
/** Used to supply some default value or None if there is no default.
  */
trait DefaultValue[T] {
  /** The default value for the object
    */
  lazy val defaultValue: Option[T] = None
  /** True if value is default value, otherwise false.
    * @note will return false if there is no default value is provided.
    */
  def isDefaultValue[T](other: DefaultValue[T]): Boolean = {
    this.defaultValue match {
      case Some(x) ⇒ x == other // this.defaultValue.equals(other.defaultValue)
      case _       ⇒ false
    }
  }
  /** True if there is a default value.
    */
  def hasDefaultValue: Boolean = this.defaultValue match {
    case Some(x) ⇒ true
    case _       ⇒ false
  }
}

/** Specifies there is no default value for this object.
  */
trait NoDefault[T] extends DefaultValue[T] {
  override lazy val defaultValue: Option[T] = None
}
