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
package io.truthencode.ddo.model.feats.classes;

import org.concordion.api.FullOGNL;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@FullOGNL
@RunWith(ConcordionRunner.class)
public class MonkClassFeatSpec extends MonkJavaHelperFeat {
    // This wrapper class is no longer needed.
//    MonkJavaHelper helper = new MonkJavaHelper() {
//        @Override
//        public CharacterClass cClass() {
//            return super.cClass();
//        }
//    };
//    public List<String> grantedFeatsByLevel(Integer level) {
//        return  helper.grantedFeatsByLevel(level);
//    }
//
//    public List<String> Simple() {
//        return  java.util.Arrays.asList("Simple"); //helper.grantedFeatsByLevel(level);
//    }
//
//    public List<String> filterByClassBonusFeat() {
//        return  java.util.Arrays.asList("Simple"); //helper.grantedFeatsByLevel(level);
//    }

}
