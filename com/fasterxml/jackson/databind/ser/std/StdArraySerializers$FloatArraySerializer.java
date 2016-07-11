package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.TypedPrimitiveArraySerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$FloatArraySerializer extends TypedPrimitiveArraySerializer<float[]> {
    private static final JavaType VALUE_TYPE;

    static {
        VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);
    }

    public StdArraySerializers$FloatArraySerializer() {
        super(float[].class);
    }

    public StdArraySerializers$FloatArraySerializer(StdArraySerializers$FloatArraySerializer stdArraySerializers$FloatArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
        super(stdArraySerializers$FloatArraySerializer, beanProperty, typeSerializer);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new StdArraySerializers$FloatArraySerializer(this, this._property, typeSerializer);
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return null;
    }

    public boolean isEmpty(float[] fArr) {
        return fArr == null || fArr.length == 0;
    }

    public boolean hasSingleElement(float[] fArr) {
        return fArr.length == 1;
    }

    public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        int length;
        if (this._valueTypeSerializer != null) {
            length = fArr.length;
            while (i < length) {
                this._valueTypeSerializer.writeTypePrefixForScalar(null, jsonGenerator, Float.TYPE);
                jsonGenerator.writeNumber(fArr[i]);
                this._valueTypeSerializer.writeTypeSuffixForScalar(null, jsonGenerator);
                i++;
            }
            return;
        }
        length = fArr.length;
        while (i < length) {
            jsonGenerator.writeNumber(fArr[i]);
            i++;
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put(ResponseConstants.ITEMS, createSchemaNode("number"));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        if (jsonFormatVisitorWrapper != null) {
            JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (expectArrayFormat != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }
    }
}
