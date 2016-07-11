package roboguice.inject;

import android.app.Application;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SystemServiceProvider<T> implements Provider<T> {
    @Inject
    protected Application application;
    protected String serviceName;

    public SystemServiceProvider(String str) {
        this.serviceName = str;
    }

    public T get() {
        return this.application.getSystemService(this.serviceName);
    }
}
