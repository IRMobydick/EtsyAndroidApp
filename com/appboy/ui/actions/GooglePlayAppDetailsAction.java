package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.appboy.Constants;
import com.appboy.enums.AppStore;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.AppboyWebViewActivity;
import org.apache.commons.lang3.StringUtils;

public final class GooglePlayAppDetailsAction implements IAction {
    private static final String AMAZON_STORE_APP_BASE = "amzn://apps/android?asin=";
    private static final String AMAZON_STORE_WEB_BASE = "http://www.amazon.com/gp/mas/dl/android?asin=";
    private static final String PLAY_STORE_APP_BASE = "market://details?id=";
    private static final String PLAY_STORE_WEB_BASE = "https://play.google.com/store/apps/details?id=";
    private static final String TAG;
    private final AppStore mAppStore;
    private String mKindleId;
    private final String mPackageName;
    private boolean mUseAppboyWebView;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, GooglePlayAppDetailsAction.class.getName()});
    }

    public GooglePlayAppDetailsAction(String str, boolean z, AppStore appStore) {
        this.mPackageName = str;
        this.mUseAppboyWebView = z;
        this.mAppStore = appStore;
    }

    public GooglePlayAppDetailsAction(String str, boolean z, AppStore appStore, String str2) {
        this.mPackageName = str;
        this.mUseAppboyWebView = z;
        this.mAppStore = appStore;
        this.mKindleId = str2;
    }

    public void execute(Context context) {
        if (this.mAppStore != AppStore.KINDLE_STORE) {
            try {
                context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            } catch (NameNotFoundException e) {
                AppboyLogger.m666i(TAG, "Google Play Store not found, launching Play Store with WebView");
                this.mUseAppboyWebView = true;
            } catch (Exception e2) {
                AppboyLogger.m664e(TAG, String.format("Unexpected exception while checking for %s.", new Object[]{"com.google.android.gsf"}));
                this.mUseAppboyWebView = true;
            }
        }
        String str = StringUtils.EMPTY;
        if (this.mUseAppboyWebView) {
            if (this.mAppStore == AppStore.KINDLE_STORE) {
                str = AMAZON_STORE_WEB_BASE + this.mKindleId;
            } else {
                str = PLAY_STORE_WEB_BASE + this.mPackageName;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str), context, AppboyWebViewActivity.class));
            return;
        }
        if (this.mAppStore == AppStore.KINDLE_STORE) {
            str = AMAZON_STORE_APP_BASE + this.mKindleId;
        } else {
            str = PLAY_STORE_APP_BASE + this.mPackageName;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
