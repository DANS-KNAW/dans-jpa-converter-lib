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

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrnUuidConverterTest {

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        UrnUuidConverter converter = new UrnUuidConverter();
        var uuid = UUID.randomUUID();
        var input = new URI("urn:uuid:" + uuid);

        String dbData = converter.convertToDatabaseColumn(input);
        assertThat(dbData).isEqualTo("" + uuid);
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        UrnUuidConverter converter = new UrnUuidConverter();
        var uuid = UUID.randomUUID();
        var input = "" + uuid;

        URI expected = new URI("urn:uuid:" + uuid);
        URI actual = converter.convertToEntityAttribute(input);

        assertThat(actual).isEqualTo(expected);
    }

}
