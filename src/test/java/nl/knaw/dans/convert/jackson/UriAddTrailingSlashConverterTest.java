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

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class UriAddTrailingSlashConverterTest {

    @Test
    public void test_end_slash_is_added() {
        var converter = new UriAddTrailingSlashConverter();
        var uri = URI.create("http://example.com/some/path");

        var converted = converter.convert(uri);

        assertThat(converted.getPath()).endsWith("/");
    }
    
    @Test
    public void test_no_extra_slash_added() {
        var converter = new UriAddTrailingSlashConverter();
        var uri = URI.create("http://example.com/some/path/");

        var converted = converter.convert(uri);
        
        assertThat(converted.getPath()).endsWith("/");
        assertThat(converted.getPath().endsWith("//")).isFalse();
    }

    @Test
    public void test_redundant_slashes_removed() {
        var converter = new UriAddTrailingSlashConverter();
        var uri = URI.create("http://example.com/some/path////");

        var converted = converter.convert(uri);

        assertThat(converted.getPath()).endsWith("/");
        assertThat(converted.getPath().endsWith("//")).isFalse();
    }
    
    @Test
    public void test_no_path() {
        var converter = new UriAddTrailingSlashConverter();
        var uri = URI.create("http://example.com");

        var converted = converter.convert(uri);

        assertThat(converted.getPath()).endsWith("/");
    }
}
