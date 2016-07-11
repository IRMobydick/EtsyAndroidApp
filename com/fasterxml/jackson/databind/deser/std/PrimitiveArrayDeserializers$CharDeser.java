package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$CharDeser extends PrimitiveArrayDeserializers<char[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$CharDeser() {
        super(char[].class);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] deserialize(com.fasterxml.jackson.core.JsonParser r6, com.fasterxml.jackson.databind.DeserializationContext r7) {
        /*
        r5 = this;
        r4 = 0;
        r0 = r6.getCurrentToken();
        r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING;
        if (r0 != r1) goto L_0x001b;
    L_0x0009:
        r1 = r6.getTextCharacters();
        r2 = r6.getTextOffset();
        r3 = r6.getTextLength();
        r0 = new char[r3];
        java.lang.System.arraycopy(r1, r2, r0, r4, r3);
    L_0x001a:
        return r0;
    L_0x001b:
        r1 = r6.isExpectedStartArrayToken();
        if (r1 == 0) goto L_0x0079;
    L_0x0021:
        r0 = new java.lang.StringBuilder;
        r1 = 64;
        r0.<init>(r1);
    L_0x0028:
        r1 = r6.nextToken();
        r2 = com.fasterxml.jackson.core.JsonToken.END_ARRAY;
        if (r1 == r2) goto L_0x0070;
    L_0x0030:
        r2 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING;
        if (r1 == r2) goto L_0x003b;
    L_0x0034:
        r0 = java.lang.Character.TYPE;
        r0 = r7.mappingException(r0);
        throw r0;
    L_0x003b:
        r1 = r6.getText();
        r2 = r1.length();
        r3 = 1;
        if (r2 == r3) goto L_0x0068;
    L_0x0046:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "Can not convert a JSON String of length ";
        r0 = r0.append(r2);
        r1 = r1.length();
        r0 = r0.append(r1);
        r1 = " into a char element of char array";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r0 = com.fasterxml.jackson.databind.JsonMappingException.from(r6, r0);
        throw r0;
    L_0x0068:
        r1 = r1.charAt(r4);
        r0.append(r1);
        goto L_0x0028;
    L_0x0070:
        r0 = r0.toString();
        r0 = r0.toCharArray();
        goto L_0x001a;
    L_0x0079:
        r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT;
        if (r0 != r1) goto L_0x00af;
    L_0x007d:
        r0 = r6.getEmbeddedObject();
        if (r0 != 0) goto L_0x0085;
    L_0x0083:
        r0 = 0;
        goto L_0x001a;
    L_0x0085:
        r1 = r0 instanceof char[];
        if (r1 == 0) goto L_0x008e;
    L_0x0089:
        r0 = (char[]) r0;
        r0 = (char[]) r0;
        goto L_0x001a;
    L_0x008e:
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x0099;
    L_0x0092:
        r0 = (java.lang.String) r0;
        r0 = r0.toCharArray();
        goto L_0x001a;
    L_0x0099:
        r1 = r0 instanceof byte[];
        if (r1 == 0) goto L_0x00af;
    L_0x009d:
        r1 = com.fasterxml.jackson.core.Base64Variants.getDefaultVariant();
        r0 = (byte[]) r0;
        r0 = (byte[]) r0;
        r0 = r1.encode(r0, r4);
        r0 = r0.toCharArray();
        goto L_0x001a;
    L_0x00af:
        r0 = r5._valueClass;
        r0 = r7.mappingException(r0);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers$CharDeser.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):char[]");
    }
}
