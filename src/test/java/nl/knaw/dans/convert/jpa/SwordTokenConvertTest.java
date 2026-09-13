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
package nl.knaw.dans.convert.jpa;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SwordTokenConvertTest {

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        SwordTokenConverter converter = new SwordTokenConverter();
        var input = "sword:1234";

        String dbData = converter.convertToDatabaseColumn(input);
        assertThat(dbData).isEqualTo("1234");
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        SwordTokenConverter converter = new SwordTokenConverter();
        var input = "1234";

        String expected = "sword:1234";
        String actual = converter.convertToEntityAttribute(input);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void throws_IllegalStateExeption_if_not_a_sword_token() throws Exception {
        SwordTokenConverter converter = new SwordTokenConverter();
        var input = "1234";

        assertThatThrownBy(() -> converter.convertToDatabaseColumn(input))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Sword token does not start with 'sword:' prefix: '1234'");
    }

    @Test
    public void convertToEntityAttribute_returns_null_if_input_is_null() throws Exception {
        SwordTokenConverter converter = new SwordTokenConverter();
        String input = null;

        String actual = converter.convertToEntityAttribute(input);

        assertThat(actual).isNull();
    }
    
}
