/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.plugins.codecs

import groovy.transform.CompileStatic

import groovy.json.StringEscapeUtils
import org.codehaus.groovy.grails.support.encoding.CodecFactory
import org.codehaus.groovy.grails.support.encoding.CodecIdentifier
import org.codehaus.groovy.grails.support.encoding.Decoder
import org.codehaus.groovy.grails.support.encoding.Encoder


@CompileStatic
class JSONCodecFactory implements CodecFactory {
    Encoder encoder = new BasicJSONEncoder()

    Decoder decoder = new Decoder() {
        def decode(Object obj) {
            obj != null ? StringEscapeUtils.unescapeJavaScript(obj.toString()) : null
        }

        CodecIdentifier getCodecIdentifier() {
            BasicJSONEncoder.JSON_CODEC_IDENTIFIER
        }
    }
}
