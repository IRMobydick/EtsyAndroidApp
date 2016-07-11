package com.etsy.android.ui.finds.cardview.listener;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.finds.FindsUrl;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.uikit.viewholder.a.b;

/* renamed from: com.etsy.android.ui.finds.cardview.listener.a */
public class FindsUrlClickHandler extends b<FindsUrl> {
    public FindsUrlClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m4253a(FindsUrl findsUrl) {
        if (findsUrl != null) {
            TaxonomyNode taxonomyNode = findsUrl.getTaxonomyNode();
            if (!findsUrl.hasCategoryLandingPage() || taxonomyNode == null) {
                Nav.m4681a(this.c).m4477a(findsUrl);
            } else {
                Nav.m4681a(this.c).m4462a(taxonomyNode, findsUrl.getAnchorListingId() != null ? findsUrl.getAnchorListingId().getId() : null);
            }
        }
    }

    public void m4255a(String str) {
        if (!bh.m3343b(str)) {
            Intent intent = new Intent(this.c, NotificationActivity.class);
            intent.setData(Uri.parse(str));
            this.c.startActivity(intent);
        }
    }
}
