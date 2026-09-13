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

import javax.persistence.AttributeConverter;

/**
 * JPA converter that strips the "sword:" prefix from a token before storing it in the database.
 */
public class SwordTokenConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String swordToken) {
        if (swordToken == null) {
            return null;
        }
        if (!swordToken.startsWith("sword:")) {
            throw new IllegalStateException("Sword token does not start with 'sword:' prefix: '" + swordToken + "'");
        }
        return swordToken.substring("sword:".length());
    }

    @Override
    public String convertToEntityAttribute(String uuid) {
        if (uuid == null) {
            return null;
        }
        return "sword:" + uuid;
    }
}
