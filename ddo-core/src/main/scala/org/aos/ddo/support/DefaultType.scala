package org.aos.ddo.support

trait DefaultType {
  self : DefaultType =>
    type Storage
  type SelfMap = Map[String, Any]
  /**
    * The default value for the object
    */
  lazy val defaultType: Option[Storage] = None
  /**
    * True if value is default value, otherwise false.
    * @note will return false if there is no default value is provided.
    */
  def isDefaultType[MyDefault](other: MyDefault): Boolean = {
    this.defaultType match {
      case Some(x) => x == other // this.defaultValue.equals(other.defaultValue)
      case _       => false
    }
  }
  /**
    * True if there is a default value.
    */
  def hasDefaultType: Boolean = this.defaultType match {
    case Some(x) => true
    case _       => false
  }
}
