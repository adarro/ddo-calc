package org.aos.ddo.model.feats

import org.aos.ddo.support.requisite.ClassRequisiteImpl

/**
  * Base trait denoting restrictions for Eberron based Deity feats
  */
trait ForgottenRealmsReligionBase
    extends ClassRequisiteImpl
    with DivineClassBase { self: DeityFeat =>

}
