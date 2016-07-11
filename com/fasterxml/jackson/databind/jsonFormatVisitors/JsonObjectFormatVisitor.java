package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface JsonObjectFormatVisitor extends JsonFormatVisitorWithSerializerProvider {

    public class Base implements JsonObjectFormatVisitor {
        protected SerializerProvider _provider;

        public Base(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }

        public SerializerProvider getProvider() {
            return this._provider;
        }

        public void setProvider(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }

        public void property(BeanProperty beanProperty) {
        }

        public void property(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) {
        }

        @Deprecated
        public void property(String str) {
        }

        public void optionalProperty(BeanProperty beanProperty) {
        }

        public void optionalProperty(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) {
        }

        @Deprecated
        public void optionalProperty(String str) {
        }
    }

    void optionalProperty(BeanProperty beanProperty);

    @Deprecated
    void optionalProperty(String str);

    void optionalProperty(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType);

    void property(BeanProperty beanProperty);

    @Deprecated
    void property(String str);

    void property(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType);
}
