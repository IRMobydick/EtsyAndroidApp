package com.etsy.android.uikit.util;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.SupportFeedbackRequest;
import com.etsy.android.lib.util.bl;
import java.io.File;
import java.util.List;

/* renamed from: com.etsy.android.uikit.util.p */
public class SupportFeedbackUtil {
    private static final String f4167a;
    private final FragmentActivity f4168b;
    private final SupportFeedbackUtil f4169c;
    private AsyncMultipartRequestCallback f4170d;

    /* renamed from: com.etsy.android.uikit.util.p.1 */
    class SupportFeedbackUtil implements AsyncMultipartRequestCallback<EmptyResult, SupportFeedbackRequest<EmptyResult>> {
        final /* synthetic */ SupportFeedbackUtil f4166a;

        SupportFeedbackUtil(SupportFeedbackUtil supportFeedbackUtil) {
            this.f4166a = supportFeedbackUtil;
        }

        public /* synthetic */ void onRequestCreated(EtsyRequest etsyRequest) {
            m5561a((SupportFeedbackRequest) etsyRequest);
        }

        public void m5561a(SupportFeedbackRequest<EmptyResult> supportFeedbackRequest) {
            this.f4166a.f4169c.onMessageQueued();
            this.f4166a.m5566a(this.f4166a.f4168b, (SupportFeedbackRequest) supportFeedbackRequest);
        }

        public void onRequestCreationFailed() {
            this.f4166a.m5563a(R.convo_message_error_create_request);
        }
    }

    static {
        f4167a = EtsyDebug.m1891a(SupportFeedbackUtil.class);
    }

    public SupportFeedbackUtil(FragmentActivity fragmentActivity, SupportFeedbackUtil supportFeedbackUtil) {
        this.f4170d = new SupportFeedbackUtil(this);
        this.f4168b = fragmentActivity;
        this.f4169c = supportFeedbackUtil;
    }

    public void m5567a(String str, String str2, String str3, List<File> list) {
        if (m5569a(str, str3)) {
            SupportFeedbackRequest.createNewSupportFeedback(str, str2, str3, list, this.f4170d);
        }
    }

    public void m5566a(FragmentActivity fragmentActivity, SupportFeedbackRequest<EmptyResult> supportFeedbackRequest) {
        aj.m1101a().m1124j().m1663a(new SupportFeedbackPost(supportFeedbackRequest));
        new AdHocEventCompatBuilder("supportfeedback_sent").m5515a("supportfeedback_compose").m5517a();
    }

    private void m5563a(int i) {
        this.f4169c.onMessageError();
        bl.m3365b(this.f4168b, i);
    }

    public boolean m5569a(String str, String str2) {
        return m5568a(str2) && m5570b(str);
    }

    public boolean m5568a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        m5563a(R.convo_message_username_validation);
        return false;
    }

    public boolean m5570b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        m5563a(R.convo_message_validation);
        return false;
    }
}
