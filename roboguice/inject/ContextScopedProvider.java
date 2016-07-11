package roboguice.inject;

import android.content.Context;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ContextScopedProvider<T> {
    @Inject
    protected Provider<T> provider;
    @Inject
    protected ContextScope scope;

    public T get(Context context) {
        T t;
        synchronized (ContextScope.class) {
            this.scope.enter(context);
            try {
                t = this.provider.get();
                this.scope.exit(context);
            } catch (Throwable th) {
                this.scope.exit(context);
            }
        }
        return t;
    }
}
