package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11 {
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static TwilightManager sTwilightManager;
    private boolean mApplyDayNightCalled;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;

    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase {
        AppCompatWindowCallbackV14(Callback callback) {
            super(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
                return startAsSupportActionMode(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final ActionMode startAsSupportActionMode(ActionMode.Callback callback) {
            Object callbackWrapper = new CallbackWrapper(AppCompatDelegateImplV14.this.mContext, callback);
            android.support.v7.view.ActionMode startSupportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }
    }

    AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mLocalNightMode = -100;
        this.mHandleNativeActionModes = true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt(KEY_LOCAL_NIGHT_MODE, -100);
        }
    }

    Callback wrapWindowCallback(Callback callback) {
        return new AppCompatWindowCallbackV14(callback);
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.mHandleNativeActionModes = z;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    public boolean applyDayNight() {
        this.mApplyDayNightCalled = true;
        int mapNightMode = mapNightMode(this.mLocalNightMode == -100 ? AppCompatDelegate.getDefaultNightMode() : this.mLocalNightMode);
        if (mapNightMode != -1) {
            return updateConfigurationForNightMode(mapNightMode);
        }
        return false;
    }

    public void setLocalNightMode(int i) {
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
            case Task.NETWORK_STATE_CONNECTED /*0*/:
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                if (this.mLocalNightMode != i) {
                    this.mLocalNightMode = i;
                    if (this.mApplyDayNightCalled) {
                        applyDayNight();
                    }
                }
            default:
                Log.d("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
        }
    }

    int mapNightMode(int i) {
        switch (i) {
            case -100:
                return -1;
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return getTwilightManager().isNight() ? 2 : 1;
            default:
                return i;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mLocalNightMode != -100) {
            bundle.putInt(KEY_LOCAL_NIGHT_MODE, this.mLocalNightMode);
        }
    }

    private boolean updateConfigurationForNightMode(int i) {
        Resources resources = this.mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        int i3 = i == 2 ? 32 : 16;
        if (i2 == i3) {
            return false;
        }
        configuration.uiMode = i3 | (configuration.uiMode & -49);
        resources.updateConfiguration(configuration, null);
        return true;
    }

    private TwilightManager getTwilightManager() {
        if (sTwilightManager == null) {
            sTwilightManager = new TwilightManager(this.mContext.getApplicationContext());
        }
        return sTwilightManager;
    }
}
