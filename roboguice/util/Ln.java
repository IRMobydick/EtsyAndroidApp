package roboguice.util;

import android.app.Application;
import android.util.Log;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class Ln {
    @Inject
    protected static BaseConfig config;
    @Inject
    protected static Print print;

    public class BaseConfig implements Config {
        protected int minimumLogLevel;
        protected String packageName;
        protected String scope;

        protected BaseConfig() {
            this.minimumLogLevel = 2;
            this.packageName = StringUtils.EMPTY;
            this.scope = StringUtils.EMPTY;
        }

        @Inject
        public BaseConfig(Application application) {
            int i = 2;
            this.minimumLogLevel = 2;
            this.packageName = StringUtils.EMPTY;
            this.scope = StringUtils.EMPTY;
            try {
                this.packageName = application.getPackageName();
                if ((application.getPackageManager().getApplicationInfo(this.packageName, 0).flags & 2) == 0) {
                    i = 4;
                }
                this.minimumLogLevel = i;
                this.scope = this.packageName.toUpperCase();
                Ln.m7512d("Configuring Logging, minimum log level is %s", Ln.logLevelToString(this.minimumLogLevel));
            } catch (Throwable e) {
                Log.e(this.packageName, "Error configuring logger", e);
            }
        }

        public int getLoggingLevel() {
            return this.minimumLogLevel;
        }

        public void setLoggingLevel(int i) {
            this.minimumLogLevel = i;
        }
    }

    static {
        config = new BaseConfig();
        print = new Print();
    }

    private Ln() {
    }

    public static int m7522v(Throwable th) {
        return config.minimumLogLevel <= 2 ? print.println(2, Log.getStackTraceString(th)) : 0;
    }

    public static int m7521v(Object obj, Object... objArr) {
        if (config.minimumLogLevel > 2) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(2, strings);
    }

    public static int m7523v(Throwable th, Object obj, Object... objArr) {
        if (config.minimumLogLevel > 2) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(2, stringBuilder.append(strings).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public static int m7513d(Throwable th) {
        return config.minimumLogLevel <= 3 ? print.println(3, Log.getStackTraceString(th)) : 0;
    }

    public static int m7512d(Object obj, Object... objArr) {
        if (config.minimumLogLevel > 3) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(3, strings);
    }

    public static int m7514d(Throwable th, Object obj, Object... objArr) {
        if (config.minimumLogLevel > 3) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(3, stringBuilder.append(strings).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public static int m7519i(Throwable th) {
        return config.minimumLogLevel <= 4 ? print.println(4, Log.getStackTraceString(th)) : 0;
    }

    public static int m7518i(Object obj, Object... objArr) {
        if (config.minimumLogLevel > 4) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(4, strings);
    }

    public static int m7520i(Throwable th, Object obj, Object... objArr) {
        if (config.minimumLogLevel > 4) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(4, stringBuilder.append(strings).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public static int m7525w(Throwable th) {
        return config.minimumLogLevel <= 5 ? print.println(5, Log.getStackTraceString(th)) : 0;
    }

    public static int m7524w(Object obj, Object... objArr) {
        if (config.minimumLogLevel > 5) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(5, strings);
    }

    public static int m7526w(Throwable th, Object obj, Object... objArr) {
        if (config.minimumLogLevel > 5) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(5, stringBuilder.append(strings).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public static int m7516e(Throwable th) {
        return config.minimumLogLevel <= 6 ? print.println(6, Log.getStackTraceString(th)) : 0;
    }

    public static int m7515e(Object obj, Object... objArr) {
        if (config.minimumLogLevel > 6) {
            return 0;
        }
        String strings = Strings.toString(obj);
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(6, strings);
    }

    public static int m7517e(Throwable th, Object obj, Object... objArr) {
        if (config.minimumLogLevel > 6) {
            return 0;
        }
        String strings = Strings.toString(obj);
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            strings = String.format(strings, objArr);
        }
        return print.println(6, stringBuilder.append(strings).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public static boolean isDebugEnabled() {
        return config.minimumLogLevel <= 3;
    }

    public static boolean isVerboseEnabled() {
        return config.minimumLogLevel <= 2;
    }

    public static Config getConfig() {
        return config;
    }

    public static String logLevelToString(int i) {
        switch (i) {
            case Task.NETWORK_STATE_ANY /*2*/:
                return "VERBOSE";
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "DEBUG";
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return "INFO";
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return "WARN";
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return "ERROR";
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return "ASSERT";
            default:
                return "UNKNOWN";
        }
    }
}
