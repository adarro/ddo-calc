/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.support.requisite

/**
 * Encapsulates A requirement with relevant context for evaluating against a provided set of requirements.
 *
 * @param reqType
 *   Whether this provides, requires or prevents
 * @param incl
 *   logic type to apply (AnyOf / AllOf / NoneOf) scenarios
 * @param req
 *   MustContainAtLeastOne or more Requirements.
 */
case class RequirementSet[+T <: RequisiteType, +U <: Inclusion](reqType: T, incl: U, req: Requirement*)

// case class RequirementSet(reqType: RequisiteType, incl: Inclusion, req: Requirement*)

// case class ReqSet[+T <: RequisiteType, +U <: Inclusion](reqType: T, incl: U, req: Requirement*)
