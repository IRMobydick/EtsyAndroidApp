package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.adjust.sdk.Constants;
import com.appboy.ui.AppboyWebViewActivity;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class WebAction implements IAction {
    private static final List<String> sSupportedSchemes;
    private final Bundle mExtras;
    private final String mTargetUrl;

    static {
        sSupportedSchemes = Collections.unmodifiableList(Arrays.asList(new String[]{"http", Constants.SCHEME, "ftp", "ftps", ResponseConstants.ABOUT, "javascript"}));
    }

    public WebAction(String str) {
        this(str, null);
    }

    public WebAction(String str, Bundle bundle) {
        this.mTargetUrl = str;
        this.mExtras = bundle;
    }

    public void execute(Context context) {
        Intent intent = new Intent(context, AppboyWebViewActivity.class);
        if (this.mExtras != null) {
            intent.putExtras(this.mExtras);
        }
        intent.putExtra(ResponseConstants.URL, this.mTargetUrl);
        context.startActivity(intent);
    }

    public static List<String> getSupportedSchemes() {
        return sSupportedSchemes;
    }
}
