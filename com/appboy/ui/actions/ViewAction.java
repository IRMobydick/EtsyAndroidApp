package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ViewAction implements IAction {
    private final Intent mIntent;

    public ViewAction(Uri uri) {
        this(uri, null);
    }

    public ViewAction(Uri uri, Bundle bundle) {
        this.mIntent = new Intent("android.intent.action.VIEW");
        this.mIntent.setData(uri);
        if (bundle != null) {
            this.mIntent.putExtras(bundle);
        }
    }

    public void execute(Context context) {
        if (this.mIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(this.mIntent);
        }
    }
}
