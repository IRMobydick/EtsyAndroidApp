package com.fasterxml.jackson.annotation;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {

    /* renamed from: com.fasterxml.jackson.annotation.JsonAutoDetect.1 */
    /* synthetic */ class C10481 {
        static final /* synthetic */ int[] f4333a;

        static {
            f4333a = new int[Visibility.values().length];
            try {
                f4333a[Visibility.ANY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4333a[Visibility.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4333a[Visibility.NON_PRIVATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4333a[Visibility.PROTECTED_AND_PUBLIC.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4333a[Visibility.PUBLIC_ONLY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum Visibility {
        ANY,
        NON_PRIVATE,
        PROTECTED_AND_PUBLIC,
        PUBLIC_ONLY,
        NONE,
        DEFAULT;

        public boolean isVisible(Member member) {
            switch (C10481.f4333a[ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    return true;
                case Task.NETWORK_STATE_ANY /*2*/:
                    return false;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    if (Modifier.isPrivate(member.getModifiers())) {
                        return false;
                    }
                    return true;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    if (Modifier.isProtected(member.getModifiers())) {
                        return true;
                    }
                    break;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    break;
                default:
                    return false;
            }
            return Modifier.isPublic(member.getModifiers());
        }
    }

    Visibility creatorVisibility() default Visibility.DEFAULT;

    Visibility fieldVisibility() default Visibility.DEFAULT;

    Visibility getterVisibility() default Visibility.DEFAULT;

    Visibility isGetterVisibility() default Visibility.DEFAULT;

    Visibility setterVisibility() default Visibility.DEFAULT;
}
