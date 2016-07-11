package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers.LocaleDeserializer;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
final class StdKeyDeserializer$LocaleKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;
    protected LocaleDeserializer _localeDeserializer;

    StdKeyDeserializer$LocaleKD() {
        super(Locale.class);
        this._localeDeserializer = new LocaleDeserializer();
    }

    protected Locale _parse(String str, DeserializationContext deserializationContext) {
        try {
            return this._localeDeserializer._deserialize(str, deserializationContext);
        } catch (IOException e) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "unable to parse key as locale");
        }
    }
}
