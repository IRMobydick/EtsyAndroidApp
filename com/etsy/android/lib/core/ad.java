package com.etsy.android.lib.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

/* compiled from: JsonHelper */
public class ad {
    private static final ad f1413a;
    private final ObjectMapper f1414b;
    private final ObjectMapper f1415c;
    private final JsonFactory f1416d;
    private final ObjectMapper f1417e;

    static {
        f1413a = new ad();
    }

    private ad() {
        this.f1414b = new ObjectMapper();
        this.f1414b.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        this.f1414b.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        this.f1414b.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        this.f1414b.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        this.f1414b.enableDefaultTyping(DefaultTyping.NON_FINAL, As.WRAPPER_OBJECT);
        this.f1415c = new ObjectMapper();
        this.f1416d = this.f1415c.getFactory();
        this.f1416d.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        this.f1417e = new ObjectMapper();
        this.f1417e.setVisibility(PropertyAccessor.FIELD, Visibility.NONE);
        this.f1417e.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    public static ad m1081a() {
        return f1413a;
    }

    public ObjectMapper m1083b() {
        return this.f1415c;
    }

    public JsonParser m1082a(byte[] bArr) {
        return this.f1416d.createParser(bArr);
    }

    public ObjectMapper m1084c() {
        return this.f1414b;
    }
}
