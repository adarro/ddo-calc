package io.truthencode.ddo.api.model.effect

/**
 * Used to amplify the value of an effect.
 */
trait ScalingInfo {

  /**
   * The value of the scaling. This is generally a percentage.
   */
  def value: Int

  /**
   * The source of the scaling. This is generally tied to a power such as spell, melee or ranged.
   */
  def source: String
}
