package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;

/* renamed from: com.etsy.android.ui.cardview.a.d */
public class FindsClickHandler extends b<FindsCard> {
    public FindsClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3575a(FindsCard findsCard) {
        if (findsCard != null && !TextUtils.isEmpty(findsCard.getSlug())) {
            Nav.m4681a(this.c).m4491a(findsCard.getSlug(), null, !findsCard.isPublic());
        }
    }
}
