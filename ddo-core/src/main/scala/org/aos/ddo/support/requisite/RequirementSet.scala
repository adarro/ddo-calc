package org.aos.ddo.support.requisite

/**
  * Encapsulates A requirement with relevant context for evaluating against a provided set of requirements.
  *
  * @param reqType Whether this provides, requires or prevents
  * @param incl    logic type to apply (AnyOf / AllOf / NoneOf) scenarios
  * @param req     MustContainAtLeastOne or more Requirements.
  */
case class RequirementSet[+T <: RequisiteType, +U <: Inclusion](reqType: T, incl: U, req: Requirement*)


// case class RequirementSet(reqType: RequisiteType, incl: Inclusion, req: Requirement*)

// case class ReqSet[+T <: RequisiteType, +U <: Inclusion](reqType: T, incl: U, req: Requirement*)
