package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.ui.support.UriUtils;
import java.util.Map.Entry;

public final class ActivityAction implements IAction {
    private final Intent mIntent;

    public ActivityAction(String str, Uri uri) {
        this(str, uri, null);
    }

    public ActivityAction(String str, Uri uri, Bundle bundle) {
        this(new Intent());
        this.mIntent.setClassName(str, uri.getHost());
        if (bundle != null) {
            this.mIntent.putExtras(bundle);
        }
        for (Entry entry : UriUtils.getQueryParameters(uri).entrySet()) {
            this.mIntent.putExtra((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public ActivityAction(Intent intent) {
        this.mIntent = intent;
    }

    public void execute(Context context) {
        if (this.mIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(this.mIntent);
        }
    }
}
