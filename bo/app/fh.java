package bo.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class fh {
    public static Method m345a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method m346a(String str, String str2, Class<?>... clsArr) {
        try {
            return m345a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object m344a(Object obj, Method method, Object... objArr) {
        Object obj2 = null;
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return obj2;
    }
}
