package roboguice.inject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import p000a.p001a.Singleton;

@Singleton
public class ViewListener implements TypeListener {
    protected static Class fragmentClass;
    protected static Method fragmentFindFragmentByIdMethod;
    protected static Method fragmentFindFragmentByTagMethod;
    protected static Method fragmentGetViewMethod;
    protected static Class fragmentManagerClass;

    static {
        fragmentClass = null;
        fragmentManagerClass = null;
        fragmentGetViewMethod = null;
        fragmentFindFragmentByIdMethod = null;
        fragmentFindFragmentByTagMethod = null;
        try {
            fragmentClass = Class.forName("android.support.v4.app.Fragment");
            fragmentManagerClass = Class.forName("android.support.v4.app.FragmentManager");
            fragmentGetViewMethod = fragmentClass.getDeclaredMethod("getView", new Class[0]);
            fragmentFindFragmentByIdMethod = fragmentManagerClass.getMethod("findFragmentById", new Class[]{Integer.TYPE});
            fragmentFindFragmentByTagMethod = fragmentManagerClass.getMethod("findFragmentByTag", new Class[]{Object.class});
        } catch (Throwable th) {
        }
    }

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        for (Class rawType = typeLiteral.getRawType(); rawType != Object.class; rawType = rawType.getSuperclass()) {
            for (Field field : rawType.getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectView.class)) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        throw new UnsupportedOperationException("Views may not be statically injected");
                    } else if (!View.class.isAssignableFrom(field.getType())) {
                        throw new UnsupportedOperationException("You may only use @InjectView on fields descended from type View");
                    } else if (!Context.class.isAssignableFrom(field.getDeclaringClass()) || Activity.class.isAssignableFrom(field.getDeclaringClass())) {
                        typeEncounter.register(new ViewMembersInjector(field, field.getAnnotation(InjectView.class), typeEncounter));
                    } else {
                        throw new UnsupportedOperationException("You may only use @InjectView in Activity contexts");
                    }
                } else if (!field.isAnnotationPresent(InjectFragment.class)) {
                    continue;
                } else if (Modifier.isStatic(field.getModifiers())) {
                    throw new UnsupportedOperationException("Fragments may not be statically injected");
                } else if (fragmentClass != null && !fragmentClass.isAssignableFrom(field.getType())) {
                    throw new UnsupportedOperationException("You may only use @InjectFragment on fields descended from type Fragment");
                } else if (!Context.class.isAssignableFrom(field.getDeclaringClass()) || Activity.class.isAssignableFrom(field.getDeclaringClass())) {
                    typeEncounter.register(new ViewMembersInjector(field, field.getAnnotation(InjectFragment.class), typeEncounter));
                } else {
                    throw new UnsupportedOperationException("You may only use @InjectFragment in Activity contexts");
                }
            }
        }
    }
}
