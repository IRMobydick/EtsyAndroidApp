package com.etsy.android.ui.cardview.p014a;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.uikit.viewholder.a.b;

/* renamed from: com.etsy.android.ui.cardview.a.c */
public class DeepLinkUrlClickHandler extends b<String> {
    public DeepLinkUrlClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3574a(String str) {
        Intent intent = new Intent(this.c, NotificationActivity.class);
        intent.setData(Uri.parse(str));
        this.c.startActivity(intent);
    }
}
