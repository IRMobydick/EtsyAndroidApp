package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.bh;

/* renamed from: com.etsy.android.lib.messaging.a.c */
public class BuyerLeftReviewNotification extends InboxStyleNotification {
    private static final String f1869f;
    private static BuyerLeftReviewNotification f1870g;

    static {
        f1869f = BuyerLeftReviewNotification.class.getName();
        f1870g = null;
    }

    private BuyerLeftReviewNotification() {
        super(NotificationType.BUYER_LEFT_REVIEW);
    }

    public static synchronized BuyerLeftReviewNotification m2155n() {
        BuyerLeftReviewNotification buyerLeftReviewNotification;
        synchronized (BuyerLeftReviewNotification.class) {
            if (f1870g == null) {
                f1870g = new BuyerLeftReviewNotification();
                f1870g.m2125g();
            }
            buyerLeftReviewNotification = f1870g;
        }
        return buyerLeftReviewNotification;
    }

    protected String m2161i() {
        return "username";
    }

    protected String m2162j() {
        return null;
    }

    protected String m2163k() {
        return "o";
    }

    protected CharSequence m2159c(Context context, Bundle bundle) {
        int i = 0;
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        String string = bundle.getString(ResponseConstants.RATING);
        if (bh.m3340a(string)) {
            try {
                i = Integer.parseInt(string);
            } catch (NumberFormatException e) {
            }
        }
        string = bundle.getString(ResponseConstants.TRANSLATED_REVIEW);
        spannableStringBuilder.append(m2156a(context, i));
        if (bh.m3340a(string)) {
            spannableStringBuilder.append(" ");
            spannableStringBuilder.append(string);
        }
        return spannableStringBuilder;
    }

    protected CharSequence m2156a(Context context, int i) {
        EtsyDebug.m1912c(f1869f, "Parsed review int: " + i);
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        for (int i2 = 0; i2 < 5; i2++) {
            spannableStringBuilder.append("\u2605");
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(InputDeviceCompat.SOURCE_ANY), 0, i, 33);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(0.7f), 0, 5, 33);
        spannableStringBuilder.setSpan(new SuperscriptSpan(), 0, 5, 33);
        return spannableStringBuilder;
    }

    protected String m2157a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bundle.getString(m2161i()));
        stringBuilder.append("_");
        stringBuilder.append(bundle.getString(m2163k()));
        return stringBuilder.toString();
    }

    protected CharSequence m2158b(Context context, Bundle bundle) {
        return context.getResources().getQuantityString(R.buyer_left_review_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2160h() {
        return EtsyEntity.SHOP_REVIEWS;
    }
}
