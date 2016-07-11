package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class SerializerFactory {
    public abstract JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType);

    public abstract TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType);

    public abstract SerializerFactory withAdditionalKeySerializers(Serializers serializers);

    public abstract SerializerFactory withAdditionalSerializers(Serializers serializers);

    public abstract SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier);

    @Deprecated
    public JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanProperty beanProperty) {
        return createSerializer(serializerProvider, javaType);
    }

    @Deprecated
    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType) {
        return createKeySerializer(serializationConfig, javaType, null);
    }
}
