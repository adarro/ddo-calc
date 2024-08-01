package io.truthencode.ddo.model.feats

import io.truthencode.ddo.support.requisite.{FeatRequisiteImpl, FreeFeat}

protected[feats] trait FeatRespecToken extends FeatRequisiteImpl with Passive with FreeFeat {
  self: SpecialFeat =>
}
