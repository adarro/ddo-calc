/*
SPDX-License-Identifier: Apache-2.0

Copyright 2025 ${author}.

FILE: TextSnippets.java

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.truthencode.ddo.grammar.antlr;

public enum TextSnippets {
    FORTIFICATION_PCT("Fortification +94%"),
    PHYSICAL_SHELTERING_NUM("Physical Sheltering +19"),
    ARMOR_ENHANCEMENT_BONUS("+5 Enhancement Bonus");

    @SuppressWarnings("unused")
    private final String rawData;

    TextSnippets(String text) {
        rawData = text;
    }
}
