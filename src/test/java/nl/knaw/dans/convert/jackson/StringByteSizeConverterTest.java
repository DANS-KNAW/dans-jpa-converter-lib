/*
 * Copyright (C) 2024 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.convert.jackson;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringByteSizeConverterTest {

    @Test
    public void no_unit_leaves_value_unchanged() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert("" + value);

        assertThat(converted).isEqualTo(value);
    }

    @Test
    public void test_kilobytes() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "K");

        assertThat(converted).isEqualTo(value * 1024);
    }

    @Test
    public void test_megabytes() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "M");

        assertThat(converted).isEqualTo(value * 1024 * 1024);
    }

    @Test
    public void test_gigabytes() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "G");

        assertThat(converted).isEqualTo(value * 1024L * 1024 * 1024);
    }

    @Test
    public void test_terabytes() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "T");

        assertThat(converted).isEqualTo(value * 1024L * 1024 * 1024 * 1024);
    }

    @Test
    public void test_invalid_suffix() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        assertThatThrownBy(() -> converter.convert(value + "X"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("suffix X is not a valid size, expecting one of T, G, M, or K, or no suffix");
    }

    @Test
    public void test_invalid_value() {
        var converter = new StringByteSizeConverter();
        var value = "abc";

        assertThatThrownBy(() -> converter.convert(value))
            .isInstanceOf(NumberFormatException.class)
            .hasMessage("value abc cannot be converted to a number");
    }

    @Test
    public void test_kilobytes_with_b() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "KB");

        assertThat(converted).isEqualTo(value * 1024);
    }

    @Test
    public void test_megabytes_with_b() {
        var converter = new StringByteSizeConverter();
        var value = 123;

        var converted = converter.convert(value + "mb");

        assertThat(converted).isEqualTo(value * 1024 * 1024);
    }
}

