package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.util.TokenBuffer;

public class JacksonDeserializers {

    @JacksonStdImpl
    public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
        public static final TokenBufferDeserializer instance;

        static {
            instance = new TokenBufferDeserializer();
        }

        public TokenBufferDeserializer() {
            super(TokenBuffer.class);
        }

        public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            return tokenBuffer;
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        return new StdDeserializer[]{JavaTypeDeserializer.instance, TokenBufferDeserializer.instance};
    }

    public static JsonDeserializer<?> find(Class<?> cls) {
        if (cls == TokenBuffer.class) {
            return TokenBufferDeserializer.instance;
        }
        if (JavaType.class.isAssignableFrom(cls)) {
            return JavaTypeDeserializer.instance;
        }
        return null;
    }

    public static ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        if (beanDescription.getBeanClass() == JsonLocation.class) {
            return JsonLocationInstantiator.instance;
        }
        return null;
    }
}
