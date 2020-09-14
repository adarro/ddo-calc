/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
package io.truthencode.ddo.model.enhancements;

import io.truthencode.ddo.model.enhancement.Enhancement$;
import io.truthencode.ddo.model.enhancement.enhancements.ClassEnhancement;
import io.truthencode.ddo.support.tree.ClassTrees$;
import org.concordion.api.FullOGNL;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;


@FullOGNL
@RunWith(ConcordionRunner.class)
public class VileChemistSpec extends JEnhancementDisplayHelper {
    // override val tree: ClassTrees = ClassTrees.Apothecary
    public VileChemistSpec() {
        setTreeId("VileChemist");
    }

    public String setUpTier(String TEXT) {
        setCurrentTier(TEXT);
        return getCurrentTier();
    }


}
