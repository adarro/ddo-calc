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
package io.truthencode.ddo.model.destiny;

import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataSet;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;
import org.concordion.api.option.FlexmarkOptions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(ConcordionRunner.class)
public class EpicDestinies {
    private final transient List<Extension> e = List.of(EmojiExtension.create());
    @FlexmarkOptions
    DataSet flexmarkOptions = new MutableDataSet()
        .set(EmojiExtension.ATTR_IMAGE_SIZE, "24")
        .set(Parser.EXTENSIONS, e);

}
