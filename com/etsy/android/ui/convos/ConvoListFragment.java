package com.etsy.android.ui.convos;

import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* renamed from: com.etsy.android.ui.convos.a */
class ConvoListFragment extends EtsyRequestJob<Conversation> {
    final /* synthetic */ ConvoListFragment f2668a;
    private boolean f2669c;

    private ConvoListFragment(ConvoListFragment convoListFragment) {
        this.f2668a = convoListFragment;
    }

    protected EtsyRequest<Conversation> m3931a() {
        this.f2669c = this.f2668a.mOffset == 0;
        return ConversationRequest.getConversations(20, this.f2668a.mOffset);
    }

    protected void m3934b(EtsyResult<Conversation> etsyResult) {
        if (!etsyResult.m1049a()) {
            return;
        }
        if (this.f2669c) {
            ConvoDatabaseUtil.m975a(this.f2668a.mActivity, etsyResult.m1056g());
        } else {
            ConvoDatabaseUtil.m978b(this.f2668a.mActivity, etsyResult.m1056g());
        }
    }

    protected void m3932a(EtsyResult<Conversation> etsyResult) {
        if (etsyResult.m1049a()) {
            access$212(this.f2668a, etsyResult.m1054e());
            this.f2668a.mMaxCount = etsyResult.m1055f();
            if (m3930c(etsyResult)) {
                this.f2668a.showEmptyView();
            }
        } else if (!etsyResult.m1049a()) {
            this.f2668a.shouldShowErrorView();
        }
        if (this.f2668a.endReached()) {
            this.f2668a.stopEndless();
        }
        this.f2668a.stopPullToRefresh();
    }

    private boolean m3930c(EtsyResult etsyResult) {
        return !(!this.f2669c || this.f2668a.mShowingData || etsyResult.m1058i()) || (this.f2668a.mMaxCount == 0 && etsyResult.m1049a());
    }
}
