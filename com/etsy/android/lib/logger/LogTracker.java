package com.etsy.android.lib.logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import java.util.HashMap;

/* renamed from: com.etsy.android.lib.logger.v */
public class LogTracker {
    protected final EtsyLogger f1850a;

    public LogTracker() {
        this.f1850a = EtsyLogger.m1966a();
    }

    public void m2080a(String str, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        hashMap.put("flow_type", signInFlow.toString());
        this.f1850a.m1992a(str, (HashMap) hashMap);
    }

    public void m2079a(String str, SignInFlow signInFlow) {
        m2080a(str, signInFlow, new HashMap());
    }

    public void m2078a(SignInFlow signInFlow) {
        m2079a("login_view", signInFlow);
    }

    public void m2082b(SignInFlow signInFlow) {
        m2079a("register_view", signInFlow);
    }

    public void m2075a() {
        this.f1850a.m1985a("conversations");
        new AdHocEventCompatBuilder("convo_main").m5515a("conversations").m5517a();
    }

    public void m2077a(@Nullable AnalyticsTracker analyticsTracker, String str) {
        this.f1850a.m1985a("conversations_thread");
        new AdHocEventCompatBuilder("convo_view").m5515a("conversations_thread").m5516a(new LogTracker$1(this, str)).m5512a(AnalyticsLogAttribute.CONVO_ID, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public void m2081b() {
        new AdHocEventCompatBuilder("conversation").m5515a("internal_url").m5517a();
        this.f1850a.m1985a("conversations_new");
    }

    public void m2083c() {
        new AdHocEventCompatBuilder("conversation").m5515a("internal_url").m5517a();
        this.f1850a.m1985a("conversations_thread_reply");
    }

    public void m2084d() {
        this.f1850a.m1985a("push_notifications");
    }

    public void m2076a(Bundle bundle) {
        this.f1850a.m1985a("supportfeedback_compose");
    }

    public void m2085e() {
        this.f1850a.m1985a("help");
    }

    public void m2086f() {
        this.f1850a.m1985a("popup_help");
    }
}
