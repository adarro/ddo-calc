/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package io.truthencode.ddo.model.protocol.parsers

/**
 * @param min Lower bound for critical multiplier
 * @param max Upper bound for critical multiplier
 * @param multiplier Number to multiply the damage roll by
 */
final case class CriticalThreatRange(min: Option[Int], max: Option[Int], multiplier: Option[Int])