package com.etsy.android.lib.convos;

import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.bl;

/* renamed from: com.etsy.android.lib.convos.c */
class ConvoHelper implements AsyncMultipartRequestCallback<EmptyResult, ConversationRequest<EmptyResult>> {
    Draft f1360a;
    final /* synthetic */ ConvoHelper f1361b;

    public /* synthetic */ void onRequestCreated(EtsyRequest etsyRequest) {
        m959a((ConversationRequest) etsyRequest);
    }

    public ConvoHelper(ConvoHelper convoHelper, Draft draft) {
        this.f1361b = convoHelper;
        this.f1360a = draft;
    }

    public void m959a(ConversationRequest<EmptyResult> conversationRequest) {
        ConvoDatabaseUtil.m972a(this.f1361b.f1359a.getBaseContext(), this.f1360a);
        aj.m1101a().m1124j().m1663a(new ConvoPost(conversationRequest, this.f1360a));
    }

    public void onRequestCreationFailed() {
        bl.m3365b(this.f1361b.f1359a.getBaseContext(), R.convo_message_error_create_request);
    }
}
