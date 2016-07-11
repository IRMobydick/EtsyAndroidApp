package com.etsy.android.lib.convos;

import com.etsy.android.lib.convos.adapters.ConvoMessageHelper;
import com.etsy.android.lib.models.ConversationImage;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.List;

/* renamed from: com.etsy.android.lib.convos.h */
public interface ConvoMessageCallbacks {
    void m996a(int i, List<ConversationImage> list);

    void m997a(ConversationMessage conversationMessage, ConvoMessageHelper convoMessageHelper);

    void m998a(EtsyId etsyId);

    boolean m999a();
}
