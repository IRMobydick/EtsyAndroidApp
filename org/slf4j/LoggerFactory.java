package org.slf4j;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

/* renamed from: org.slf4j.c */
public final class LoggerFactory {
    static int f5587a;
    static SubstituteLoggerFactory f5588b;
    static NOPLoggerFactory f5589c;
    static Class f5590d;
    private static final String[] f5591e;
    private static String f5592f;

    static {
        f5587a = 0;
        f5588b = new SubstituteLoggerFactory();
        f5589c = new NOPLoggerFactory();
        f5591e = new String[]{"1.6"};
        f5592f = "org/slf4j/impl/StaticLoggerBinder.class";
    }

    private LoggerFactory() {
    }

    private static final void m7501b() {
        LoggerFactory.m7506f();
        LoggerFactory.m7503c();
        if (f5587a == 3) {
            LoggerFactory.m7505e();
        }
    }

    private static boolean m7502b(String str) {
        if (str == null) {
            return false;
        }
        if (str.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1) {
            return true;
        }
        if (str.indexOf("org.slf4j.impl.StaticLoggerBinder") != -1) {
            return true;
        }
        return false;
    }

    private static final void m7503c() {
        try {
            StaticLoggerBinder.getSingleton();
            f5587a = 3;
            LoggerFactory.m7504d();
        } catch (Throwable e) {
            if (LoggerFactory.m7502b(e.getMessage())) {
                f5587a = 4;
                Util.m7510a("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.m7510a("Defaulting to no-operation (NOP) logger implementation");
                Util.m7510a("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            LoggerFactory.m7500a(e);
            throw e;
        } catch (NoSuchMethodError e2) {
            String message = e2.getMessage();
            if (!(message == null || message.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") == -1)) {
                f5587a = 2;
                Util.m7510a("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                Util.m7510a("Your binding is version 1.5.5 or earlier.");
                Util.m7510a("Upgrade your binding to version 1.6.x. or 2.0.x");
            }
            throw e2;
        } catch (Throwable e3) {
            LoggerFactory.m7500a(e3);
            throw new IllegalStateException("Unexpected initialization failure", e3);
        }
    }

    static void m7500a(Throwable th) {
        f5587a = 2;
        Util.m7511a("Failed to instantiate SLF4J LoggerFactory", th);
    }

    private static final void m7504d() {
        List a = f5588b.m7508a();
        if (a.size() != 0) {
            Util.m7510a("The following loggers will not work becasue they were created");
            Util.m7510a("during the default configuration phase of the underlying logging system.");
            Util.m7510a("See also http://www.slf4j.org/codes.html#substituteLogger");
            for (int i = 0; i < a.size(); i++) {
                Util.m7510a((String) a.get(i));
            }
        }
    }

    private static final void m7505e() {
        Object obj = null;
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            for (String startsWith : f5591e) {
                if (str.startsWith(startsWith)) {
                    obj = 1;
                }
            }
            if (obj == null) {
                Util.m7510a(new StringBuffer().append("The requested version ").append(str).append(" by your slf4j binding is not compatible with ").append(Arrays.asList(f5591e).toString()).toString());
                Util.m7510a("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError e) {
        } catch (Throwable th) {
            Util.m7511a("Unexpected problem occured during version sanity check", th);
        }
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static void m7506f() {
        try {
            Class class$;
            Enumeration systemResources;
            if (f5590d == null) {
                class$ = LoggerFactory.class$("org.slf4j.c");
                f5590d = class$;
            } else {
                class$ = f5590d;
            }
            ClassLoader classLoader = class$.getClassLoader();
            if (classLoader == null) {
                systemResources = ClassLoader.getSystemResources(f5592f);
            } else {
                systemResources = classLoader.getResources(f5592f);
            }
            Set<URL> linkedHashSet = new LinkedHashSet();
            while (systemResources.hasMoreElements()) {
                linkedHashSet.add((URL) systemResources.nextElement());
            }
            if (linkedHashSet.size() > 1) {
                Util.m7510a("Class path contains multiple SLF4J bindings.");
                for (URL append : linkedHashSet) {
                    Util.m7510a(new StringBuffer().append("Found binding in [").append(append).append("]").toString());
                }
                Util.m7510a("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
            }
        } catch (Throwable e) {
            Util.m7511a("Error getting resources from path", e);
        }
    }

    public static Logger m7499a(String str) {
        return LoggerFactory.m7497a().m7496a(str);
    }

    public static Logger m7498a(Class cls) {
        return LoggerFactory.m7499a(cls.getName());
    }

    public static ILoggerFactory m7497a() {
        if (f5587a == 0) {
            f5587a = 1;
            LoggerFactory.m7501b();
        }
        switch (f5587a) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return f5588b;
            case Task.NETWORK_STATE_ANY /*2*/:
                throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return StaticLoggerBinder.getSingleton().getLoggerFactory();
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return f5589c;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
