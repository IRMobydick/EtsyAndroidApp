package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.facebook.FacebookException;
import com.facebook.j;
import com.facebook.share.b;

/* renamed from: com.etsy.android.uikit.share.c */
public class ShareBrokerFragment implements j<b> {
    final /* synthetic */ ShareBrokerFragment f4027a;
    private ResolveInfo f4028b;

    public ShareBrokerFragment(ShareBrokerFragment shareBrokerFragment, ResolveInfo resolveInfo) {
        this.f4027a = shareBrokerFragment;
        this.f4028b = resolveInfo;
    }

    public void m5390a(b bVar) {
        EtsyDebug.m1914c(ShareBrokerFragment.TAG, "SUCCESS share to facebook {post_id:%s}", bVar.a());
    }

    public void m5388a() {
        EtsyDebug.m1912c(ShareBrokerFragment.TAG, "CANCEL share to facebook {post_id:%s}");
    }

    public void m5389a(FacebookException facebookException) {
        this.f4027a.onShareError(this.f4028b);
        EtsyDebug.m1914c(ShareBrokerFragment.TAG, "ERROR share to facebook - %s", facebookException.getMessage());
    }
}
