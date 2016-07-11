package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.gcm.Task;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public abstract class JsonParser implements Versioned, Closeable {
    private static final int MAX_BYTE_I = 255;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected int _features;

    public abstract void clearCurrentToken();

    public abstract void close();

    public abstract BigInteger getBigIntegerValue();

    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName();

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue();

    public abstract double getDoubleValue();

    public abstract Object getEmbeddedObject();

    public abstract float getFloatValue();

    public abstract int getIntValue();

    public abstract JsonToken getLastClearedToken();

    public abstract long getLongValue();

    public abstract NumberType getNumberType();

    public abstract Number getNumberValue();

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText();

    public abstract char[] getTextCharacters();

    public abstract int getTextLength();

    public abstract int getTextOffset();

    public abstract JsonLocation getTokenLocation();

    public abstract String getValueAsString(String str);

    public abstract boolean hasCurrentToken();

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    public abstract void overrideCurrentName(String str);

    public abstract void setCodec(ObjectCodec objectCodec);

    public abstract JsonParser skipChildren();

    public abstract Version version();

    protected JsonParser() {
    }

    protected JsonParser(int i) {
        this._features = i;
    }

    public Object getInputSource() {
        return null;
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Parser of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    public FormatSchema getSchema() {
        return null;
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    public int releaseBuffered(OutputStream outputStream) {
        return -1;
    }

    public int releaseBuffered(Writer writer) {
        return -1;
    }

    public JsonParser enable(Feature feature) {
        this._features |= feature.getMask();
        return this;
    }

    public JsonParser disable(Feature feature) {
        this._features &= feature.getMask() ^ -1;
        return this;
    }

    public JsonParser configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public boolean isEnabled(Feature feature) {
        return (this._features & feature.getMask()) != 0;
    }

    public boolean nextFieldName(SerializableString serializableString) {
        return nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(getCurrentName());
    }

    public String nextTextValue() {
        return nextToken() == JsonToken.VALUE_STRING ? getText() : null;
    }

    public int nextIntValue(int i) {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    public long nextLongValue(long j) {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    public Boolean nextBooleanValue() {
        switch (1.a[nextToken().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return Boolean.TRUE;
            case Task.NETWORK_STATE_ANY /*2*/:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public byte getByteValue() {
        int intValue = getIntValue();
        if (intValue >= MIN_BYTE_I && intValue <= MAX_BYTE_I) {
            return (byte) intValue;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
    }

    public short getShortValue() {
        int intValue = getIntValue();
        if (intValue >= MIN_SHORT_I && intValue <= MAX_SHORT_I) {
            return (short) intValue;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
    }

    public boolean getBooleanValue() {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException("Current token (" + currentToken + ") not of boolean type", getCurrentLocation());
    }

    public byte[] getBinaryValue() {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public int readBinaryValue(OutputStream outputStream) {
        return readBinaryValue(Base64Variants.getDefaultVariant(), outputStream);
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        _reportUnsupportedOperation();
        return 0;
    }

    public int getValueAsInt() {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i) {
        return i;
    }

    public long getValueAsLong() {
        return getValueAsLong(0);
    }

    public long getValueAsLong(long j) {
        return j;
    }

    public double getValueAsDouble() {
        return getValueAsDouble(0.0d);
    }

    public double getValueAsDouble(double d) {
        return d;
    }

    public boolean getValueAsBoolean() {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean z) {
        return z;
    }

    public String getValueAsString() {
        return getValueAsString(null);
    }

    public <T> T readValueAs(Class<T> cls) {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValue(this, (Class) cls);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public <T> T readValueAs(TypeReference<?> typeReference) {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValue(this, (TypeReference) typeReference);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public <T> Iterator<T> readValuesAs(Class<T> cls) {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, (Class) cls);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public <T> Iterator<T> readValuesAs(TypeReference<?> typeReference) {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, (TypeReference) typeReference);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
    }

    public <T extends TreeNode> T readValueAsTree() {
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readTree(this);
        }
        throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
    }

    protected JsonParseException _constructError(String str) {
        return new JsonParseException(str, getCurrentLocation());
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + getClass().getName());
    }
}
