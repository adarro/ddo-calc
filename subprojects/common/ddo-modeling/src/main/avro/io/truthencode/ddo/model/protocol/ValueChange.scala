/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package io.truthencode.ddo.model.protocol

sealed trait ValueChange

sealed trait ChangeType extends ValueChange

object ChangeType {
  case object INCREASE extends ChangeType
  case object DECREASE extends ChangeType
  case object NOCHANGE extends ChangeType
}

final case class ChangeValueInt(id: String, currentValue: Int, prevValue: Int, changeType: ChangeType) extends ValueChange