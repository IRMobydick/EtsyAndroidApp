package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$CharArraySerializer extends StdSerializer<char[]> {
    public StdArraySerializers$CharArraySerializer() {
        super(char[].class);
    }

    public boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            jsonGenerator.writeStartArray();
            _writeArrayContents(jsonGenerator, cArr);
            jsonGenerator.writeEndArray();
            return;
        }
        jsonGenerator.writeString(cArr, 0, cArr.length);
    }

    public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
            _writeArrayContents(jsonGenerator, cArr);
            typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
            return;
        }
        typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
        jsonGenerator.writeString(cArr, 0, cArr.length);
        typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
    }

    private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) {
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            jsonGenerator.writeString(cArr, i, 1);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        ObjectNode createSchemaNode2 = createSchemaNode("string");
        createSchemaNode2.put(FindsModule.FIELD_TYPE, "string");
        createSchemaNode.put(ResponseConstants.ITEMS, createSchemaNode2);
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        if (jsonFormatVisitorWrapper != null) {
            JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (expectArrayFormat != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.STRING);
            }
        }
    }
}
