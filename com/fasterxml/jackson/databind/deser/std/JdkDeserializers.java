package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class JdkDeserializers {
    private static final HashSet<String> _classNames;

    static {
        int i = 0;
        _classNames = new HashSet();
        Class[] clsArr = new Class[]{UUID.class, URL.class, URI.class, File.class, Currency.class, Pattern.class, Locale.class, InetAddress.class, Charset.class, AtomicBoolean.class, Class.class, StackTraceElement.class};
        int length = clsArr.length;
        while (i < length) {
            _classNames.add(clsArr[i].getName());
            i++;
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        return new StdDeserializer[]{UUIDDeserializer.instance, URLDeserializer.instance, URIDeserializer.instance, FileDeserializer.instance, CurrencyDeserializer.instance, PatternDeserializer.instance, LocaleDeserializer.instance, InetAddressDeserializer.instance, CharsetDeserializer.instance, AtomicBooleanDeserializer.instance, ClassDeserializer.instance, StackTraceElementDeserializer.instance};
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (!_classNames.contains(str)) {
            return null;
        }
        if (cls == URI.class) {
            return URIDeserializer.instance;
        }
        if (cls == URL.class) {
            return URLDeserializer.instance;
        }
        if (cls == File.class) {
            return FileDeserializer.instance;
        }
        if (cls == UUID.class) {
            return UUIDDeserializer.instance;
        }
        if (cls == Currency.class) {
            return CurrencyDeserializer.instance;
        }
        if (cls == Pattern.class) {
            return PatternDeserializer.instance;
        }
        if (cls == Locale.class) {
            return LocaleDeserializer.instance;
        }
        if (cls == InetAddress.class) {
            return InetAddressDeserializer.instance;
        }
        if (cls == Charset.class) {
            return CharsetDeserializer.instance;
        }
        if (cls == Class.class) {
            return ClassDeserializer.instance;
        }
        if (cls == StackTraceElement.class) {
            return StackTraceElementDeserializer.instance;
        }
        if (cls == AtomicBoolean.class) {
            return AtomicBooleanDeserializer.instance;
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + str);
    }
}
