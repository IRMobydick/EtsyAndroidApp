package com.facebook.internal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

public class FacebookDialogFragment extends DialogFragment {
    public static final String TAG = "FacebookDialogFragment";
    private Dialog dialog;

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.dialog == null) {
            Dialog facebookWebFallbackDialog;
            Context activity = getActivity();
            Bundle d = ah.m5763d(activity.getIntent());
            String string;
            if (d.getBoolean("is_fallback", false)) {
                string = d.getString(ResponseConstants.URL);
                if (aq.a(string)) {
                    aq.b(TAG, "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                facebookWebFallbackDialog = new FacebookWebFallbackDialog(activity, string, String.format("fb%s://bridge/", new Object[]{FacebookSdk.m5786h()}));
                facebookWebFallbackDialog.setOnCompleteListener(new 2(this));
            } else {
                string = d.getString("action");
                d = d.getBundle(ResponseConstants.PARAMS);
                if (aq.a(string)) {
                    aq.b(TAG, "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                facebookWebFallbackDialog = new aw(activity, string, d).a(new 1(this)).a();
            }
            this.dialog = facebookWebFallbackDialog;
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.dialog == null) {
            onCompleteWebDialog(null, null);
            setShowsDialog(false);
        }
        return this.dialog;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.dialog instanceof WebDialog) {
            ((WebDialog) this.dialog).resize();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void onCompleteWebDialog(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        activity.setResult(facebookException == null ? -1 : 0, ah.m5749a(activity.getIntent(), bundle, facebookException));
        activity.finish();
    }

    private void onCompleteWebFallbackDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
