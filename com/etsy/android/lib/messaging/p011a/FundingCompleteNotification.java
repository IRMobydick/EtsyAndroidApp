package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.f */
public class FundingCompleteNotification extends InboxStyleNotification {
    private static FundingCompleteNotification f1873f;

    static {
        f1873f = null;
    }

    private FundingCompleteNotification() {
        super(NotificationType.FUNDING_ENDED);
    }

    public static synchronized FundingCompleteNotification m2176n() {
        FundingCompleteNotification fundingCompleteNotification;
        synchronized (FundingCompleteNotification.class) {
            if (f1873f == null) {
                f1873f = new FundingCompleteNotification();
                f1873f.m2125g();
            }
            fundingCompleteNotification = f1873f;
        }
        return fundingCompleteNotification;
    }

    protected String m2179i() {
        return "username";
    }

    protected String m2180j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2181k() {
        return "o";
    }

    protected CharSequence m2177b(Context context, Bundle bundle) {
        return context.getResources().getString(R.funding_successful);
    }

    protected EtsyEntity m2178h() {
        return EtsyEntity.LISTING;
    }
}
