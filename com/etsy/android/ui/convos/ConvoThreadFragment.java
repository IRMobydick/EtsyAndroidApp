package com.etsy.android.ui.convos;

import android.os.AsyncTask;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.ConvoStateManager;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.ui.user.UserBadgeCountManager;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.convos.c */
class ConvoThreadFragment extends EtsyRequestJob<EmptyResult> {
    boolean f2675a;
    boolean f2676c;
    final /* synthetic */ ConvoThreadFragment f2677d;

    /* renamed from: com.etsy.android.ui.convos.c.1 */
    class ConvoThreadFragment extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ long f2672a;
        final /* synthetic */ boolean f2673b;
        final /* synthetic */ ConvoThreadFragment f2674c;

        ConvoThreadFragment(ConvoThreadFragment convoThreadFragment, long j, boolean z) {
            this.f2674c = convoThreadFragment;
            this.f2672a = j;
            this.f2673b = z;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m3938a((Void[]) objArr);
        }

        protected Void m3938a(Void... voidArr) {
            ConvoDatabaseUtil.m971a(this.f2674c.f2677d.mActivity, this.f2672a, this.f2673b);
            return null;
        }
    }

    public ConvoThreadFragment(ConvoThreadFragment convoThreadFragment, boolean z) {
        this.f2677d = convoThreadFragment;
        this.f2675a = !convoThreadFragment.mConversation.isRead();
        this.f2676c = z;
    }

    protected void b_() {
        m3940a(this.f2675a);
        if (this.f2676c && (this.f2677d.getActivity() instanceof ConvoViewActivity)) {
            ConvoStateManager.m1005b(this.f2677d.getActivity());
            ((ConvoViewActivity) this.f2677d.getActivity()).popOrGoBack();
        }
    }

    protected EtsyRequest<EmptyResult> m3941a() {
        return ConversationRequest.markConvoRead(this.f2677d.mConversation.getConversationId(), Boolean.valueOf(this.f2675a));
    }

    protected void m3942a(EtsyResult etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a()) {
            boolean z;
            if (this.f2677d.getActivity() != null) {
                Toast.makeText(this.f2677d.getActivity(), this.f2677d.getActivity().getString(R.convo_error_read_state), 0).show();
                String access$1600 = ConvoThreadFragment.TAG;
                String str = "Error updating read state %s";
                Object[] objArr = new Object[1];
                objArr[0] = etsyResult != null ? etsyResult.m1052c() : StringUtils.EMPTY;
                EtsyDebug.m1920e(access$1600, str, objArr);
            }
            if (this.f2675a) {
                z = false;
            } else {
                z = true;
            }
            m3940a(z);
        }
    }

    private void m3940a(boolean z) {
        this.f2677d.mConversation.setIsRead(Boolean.valueOf(z));
        if (z) {
            UserBadgeCountManager.m5068b(this.f2677d.getActivity());
        }
        m3939a(this.f2677d.mConversation.getConversationId(), z);
    }

    private void m3939a(long j, boolean z) {
        ap.m1142a(new ConvoThreadFragment(this, j, z), new Void[0]);
    }
}
