package p000a.p001a;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: a.a.b */
public @interface Named {
    String m0a() default "";
}
