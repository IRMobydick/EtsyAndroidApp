package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.util.Collection;

public abstract class JsonDeserializer<T> {

    public abstract class None extends JsonDeserializer<Object> {
        private None() {
        }
    }

    public abstract T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext);

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) {
        throw new UnsupportedOperationException("Can not update object of type " + t.getClass().getName() + " (by deserializer of type " + getClass().getName() + ")");
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public JsonDeserializer<T> unwrappingDeserializer(NameTransformer nameTransformer) {
        return this;
    }

    public JsonDeserializer<?> replaceDelegatee(JsonDeserializer<?> jsonDeserializer) {
        throw new UnsupportedOperationException();
    }

    public T getNullValue() {
        return null;
    }

    public T getEmptyValue() {
        return getNullValue();
    }

    public Collection<Object> getKnownPropertyNames() {
        return null;
    }

    public boolean isCachable() {
        return false;
    }

    public ObjectIdReader getObjectIdReader() {
        return null;
    }

    public JsonDeserializer<?> getDelegatee() {
        return null;
    }
}
