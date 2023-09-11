package io.truthencode.ddo.api.model.effect

case class FullEffect(
  override val id: String,
  override val description: String,
  override val triggersOn: Seq[String],
  override val triggersOff: Seq[String],
  override val name: String,
  override val generalDescription: String,
  override val categories: Seq[String],
  override val bonusType: String,
  override val intValue: Option[Int] = None
) extends FullEffectInfo
