package io.truthencode.ddo.model.feats

import io.truthencode.ddo.enhancement.BonusType
import io.truthencode.ddo.enhancement.BonusType.Feat
import io.truthencode.ddo.model.effect
import io.truthencode.ddo.model.effect.TriggerEvent.{Never, Passive}
import io.truthencode.ddo.model.effect.features.{
  Features,
  FeaturesImpl,
  TurnUndeadCasterLevelFeature
}
import io.truthencode.ddo.model.effect.{EffectCategories, Feature, SourceInfo, TriggerEvent}
import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, RequiresAllOfFeat}

trait ImprovedTurning extends FeatRequisiteImpl with Passive with Features {
  self: ClassFeat with RequiresAllOfFeat =>
  // prerequisite: Cleric, Paladin, Bane Of Undead[20]

  private val si = this
  private val undeadTurnsFeature = new FeaturesImpl
    with TurnUndeadCasterLevelFeature with SourceInfo {

    override val sourceId: String = si.sourceId
    override val sourceRef: AnyRef = si.sourceRef

    override protected[this] lazy val triggerOn: Seq[TriggerEvent] = Seq(Passive)
    override protected[this] lazy val triggerOff: Seq[TriggerEvent] = Seq(Never)

    override lazy val undeadCasterLevelBonusType: BonusType = Feat
    override val undeadCasterLevelBonusAmount: Int = 1
    override protected[this] val undeadCasterLevelCategories: Seq[effect.EffectCategories.Value] =
      Seq(EffectCategories.TurnUndead)
  }
  override lazy val features: Seq[Feature[_]] = undeadTurnsFeature.features

}
