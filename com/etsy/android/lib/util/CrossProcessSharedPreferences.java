package com.etsy.android.lib.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class CrossProcessSharedPreferences {

    public class Receiver extends BroadcastReceiver {
        public static final String ACTION_PREFIX = "com.etsy.android.crossprocesssharedpref.";
        public static final String ACTION_PUT_BOOLEAN = "com.etsy.android.crossprocesssharedpref.PUT_BOOLEAN";
        public static final String ACTION_PUT_FLOAT = "com.etsy.android.crossprocesssharedpref.PUT_FLOAT";
        public static final String ACTION_PUT_INT = "com.etsy.android.crossprocesssharedpref.PUT_INT";
        public static final String ACTION_PUT_LONG = "com.etsy.android.crossprocesssharedpref.PUT_LONG";
        public static final String ACTION_PUT_STRING = "com.etsy.android.crossprocesssharedpref.PUT_STRING";
        public static final String ACTION_REMOVE = "com.etsy.android.crossprocesssharedpref.REMOVE";
        public static final String KEY = "KEY";
        public static final String VALUE = "VALUE";

        public void onReceive(@NonNull Context context, @NonNull Intent intent) {
            Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
            String stringExtra = intent.getStringExtra(KEY);
            String action = intent.getAction();
            boolean z = true;
            switch (action.hashCode()) {
                case -1569667533:
                    if (action.equals(ACTION_PUT_INT)) {
                        z = true;
                        break;
                    }
                    break;
                case -1414963048:
                    if (action.equals(ACTION_PUT_LONG)) {
                        z = true;
                        break;
                    }
                    break;
                case -919811168:
                    if (action.equals(ACTION_PUT_FLOAT)) {
                        z = true;
                        break;
                    }
                    break;
                case 268718672:
                    if (action.equals(ACTION_REMOVE)) {
                        z = true;
                        break;
                    }
                    break;
                case 1655885932:
                    if (action.equals(ACTION_PUT_BOOLEAN)) {
                        z = true;
                        break;
                    }
                    break;
                case 1930288941:
                    if (action.equals(ACTION_PUT_STRING)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    edit.putString(stringExtra, intent.getStringExtra(VALUE));
                    break;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    edit.putInt(stringExtra, intent.getIntExtra(VALUE, 0));
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    edit.putLong(stringExtra, intent.getLongExtra(VALUE, 0));
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    edit.putFloat(stringExtra, intent.getFloatExtra(VALUE, 0.0f));
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    edit.putBoolean(stringExtra, intent.getBooleanExtra(VALUE, false));
                    break;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    edit.remove(stringExtra);
                    break;
            }
            edit.apply();
        }
    }
}
