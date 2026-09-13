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

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Converts a string representation of a byte size to a long. The string can be a number, optionally followed by a suffix
 * indicating the unit. The following suffixes are supported:
 * <ul>
 *     <li>K or KB for kilobytes</li>
 *     <li>M or MB for megabytes</li>
 *     <li>G or GB for gigabytes</li>
 *     <li>T or TB for terabytes</li>
 * </ul>
 * If no suffix is provided, the number is interpreted as bytes.
 */
public class StringByteSizeConverter extends StdConverter<String, Long> {

    @Override
    public Long convert(String s) {
        var pattern = Pattern.compile("(?<number>\\d+)((?<unit>[a-zA-Z])b?)?", Pattern.CASE_INSENSITIVE);
        var matched = pattern.matcher(s.strip());

        if (matched.matches()) {
            var number = Long.parseLong(matched.group("number"));
            var unit = matched.group("unit");

            if (unit != null) {
                switch (unit.toUpperCase(Locale.ROOT)) {
                    case "T":
                        number *= (1024L * 1024 * 1024 * 1024);
                        break;

                    case "G":
                        number *= (1024L * 1024 * 1024);
                        break;

                    case "M":
                        number *= (1024L * 1024);
                        break;

                    case "K":
                        number *= (1024L);
                        break;

                    default:
                        throw new IllegalArgumentException(String.format("suffix %s is not a valid size, expecting one of T, G, M, or K, or no suffix", unit));
                }
            }

            return number;
        }

        throw new NumberFormatException(String.format("value %s cannot be converted to a number", s));
    }
}
