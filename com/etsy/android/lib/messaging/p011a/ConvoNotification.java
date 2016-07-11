package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.e */
public class ConvoNotification extends InboxStyleNotification {
    private static ConvoNotification f1872f;

    static {
        f1872f = null;
    }

    private ConvoNotification() {
        super(NotificationType.CONVO);
    }

    public static synchronized ConvoNotification m2170n() {
        ConvoNotification convoNotification;
        synchronized (ConvoNotification.class) {
            if (f1872f == null) {
                f1872f = new ConvoNotification();
                f1872f.m2125g();
            }
            convoNotification = f1872f;
        }
        return convoNotification;
    }

    protected String m2173i() {
        return "username";
    }

    protected String m2174j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2175k() {
        return "o";
    }

    protected CharSequence m2171b(Context context, Bundle bundle) {
        return context.getResources().getQuantityString(R.convo_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2172h() {
        return EtsyEntity.ALL_CONVOS;
    }
}
