package com.appboy.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.Constants;
import com.appboy.IAppboyNavigator;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.actions.ActivityAction;
import com.appboy.ui.actions.WebAction;
import com.appboy.ui.activities.AppboyFeedActivity;

public class AppboyNavigator implements IAppboyNavigator {
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNavigator.class.getName()});
    }

    public void gotoNewsFeed(Context context, Bundle bundle) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context, AppboyFeedActivity.class), 1);
            new ActivityAction(new Intent(context, AppboyFeedActivity.class)).execute(context);
        } catch (NameNotFoundException e) {
            AppboyLogger.m662d(TAG, "The AppboyFeedActivity is not registered in the manifest. Ignoring request to display the news feed.");
        }
    }

    public void gotoURI(Context context, Uri uri, Bundle bundle) {
        if (uri == null) {
            AppboyLogger.m664e(TAG, "IAppboyNavigator cannot open URI because the URI is null.");
        } else {
            new WebAction(uri.toString()).execute(context);
        }
    }
}
