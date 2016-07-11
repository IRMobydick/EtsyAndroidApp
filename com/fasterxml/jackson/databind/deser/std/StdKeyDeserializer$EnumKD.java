package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;

@JacksonStdImpl
final class StdKeyDeserializer$EnumKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _factory;
    protected final EnumResolver<?> _resolver;

    protected StdKeyDeserializer$EnumKD(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
        super(enumResolver.getEnumClass());
        this._resolver = enumResolver;
        this._factory = annotatedMethod;
    }

    public Object _parse(String str, DeserializationContext deserializationContext) {
        Object call1;
        if (this._factory != null) {
            try {
                call1 = this._factory.call1(str);
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
            return call1;
        }
        call1 = this._resolver.findEnum(str);
        if (call1 == null && !deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
        }
        return call1;
    }
}
