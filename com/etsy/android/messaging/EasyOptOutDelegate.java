package com.etsy.android.messaging;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.NotificationOptOutBannerSetting;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.uikit.messaging.EasyOptOutPopup;
import com.etsy.android.uikit.ui.toast.PersistentToastPopup;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.etsy.android.messaging.b */
public class EasyOptOutDelegate {
    private static final String f2080a;
    private Reference<BOENavDrawerActivity> f2081b;
    private String f2082c;

    /* renamed from: com.etsy.android.messaging.b.1 */
    class EasyOptOutDelegate extends EtsyApiV3RequestJob<NotificationOptOutBannerSetting> {
        final /* synthetic */ String f2071a;
        final /* synthetic */ EasyOptOutDelegate f2072b;

        EasyOptOutDelegate(EasyOptOutDelegate easyOptOutDelegate, String str) {
            this.f2072b = easyOptOutDelegate;
            this.f2071a = str;
        }

        public void m3437a(@NonNull List<NotificationOptOutBannerSetting> list, int i, @NonNull EtsyV3Result<NotificationOptOutBannerSetting> etsyV3Result) {
            if (!list.isEmpty()) {
                NotificationOptOutBannerSetting notificationOptOutBannerSetting = (NotificationOptOutBannerSetting) list.get(0);
                EtsyDebug.m1912c(EasyOptOutDelegate.f2080a, "Fetched opt out banner setting for notification type " + this.f2071a);
                this.f2072b.m3451a(notificationOptOutBannerSetting);
            }
        }

        public void m3435a(int i, @Nullable String str, @NonNull EtsyV3Result<NotificationOptOutBannerSetting> etsyV3Result) {
            EtsyDebug.m1919e(EasyOptOutDelegate.f2080a, "Error fetching notification easy opt out banner setting: " + str);
        }
    }

    /* renamed from: com.etsy.android.messaging.b.2 */
    class EasyOptOutDelegate extends EtsyApiV3RequestJob<EmptyResult> {
        final /* synthetic */ String f2073a;
        final /* synthetic */ EasyOptOutDelegate f2074b;

        EasyOptOutDelegate(EasyOptOutDelegate easyOptOutDelegate, String str) {
            this.f2074b = easyOptOutDelegate;
            this.f2073a = str;
        }

        public void m3441a(@NonNull List<EmptyResult> list, int i, @NonNull EtsyV3Result<EmptyResult> etsyV3Result) {
            EtsyDebug.m1912c(EasyOptOutDelegate.f2080a, "Updated opt out setting for notification type " + this.f2073a);
        }

        public void m3439a(int i, @Nullable String str, @NonNull EtsyV3Result<EmptyResult> etsyV3Result) {
            EtsyDebug.m1919e(EasyOptOutDelegate.f2080a, "Error updating notification easy opt out banner setting for notification type " + this.f2073a + ": " + str);
        }
    }

    /* renamed from: com.etsy.android.messaging.b.3 */
    class EasyOptOutDelegate implements EtsyJobResponse<BaseModel> {
        final /* synthetic */ String f2075a;
        final /* synthetic */ EasyOptOutDelegate f2076b;

        EasyOptOutDelegate(EasyOptOutDelegate easyOptOutDelegate, String str) {
            this.f2076b = easyOptOutDelegate;
            this.f2075a = str;
        }

        public void m3443a(List<BaseModel> list, int i, EtsyResult<BaseModel> etsyResult) {
            EtsyDebug.m1912c(EasyOptOutDelegate.f2080a, "Successfully updated notification setting for " + this.f2075a);
        }
    }

    /* renamed from: com.etsy.android.messaging.b.4 */
    class EasyOptOutDelegate implements EtsyJobResponse {
        final /* synthetic */ String f2077a;
        final /* synthetic */ EasyOptOutDelegate f2078b;

        EasyOptOutDelegate(EasyOptOutDelegate easyOptOutDelegate, String str) {
            this.f2078b = easyOptOutDelegate;
            this.f2077a = str;
        }

        public void m3444a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1912c(EasyOptOutDelegate.f2080a, "Error updating notification setting for " + this.f2077a);
        }
    }

    /* renamed from: com.etsy.android.messaging.b.5 */
    class EasyOptOutDelegate implements PersistentToastPopup {
        final /* synthetic */ EasyOptOutDelegate f2079a;

        EasyOptOutDelegate(EasyOptOutDelegate easyOptOutDelegate) {
            this.f2079a = easyOptOutDelegate;
        }

        public void m3447a() {
            this.f2079a.m3459b(this.f2079a.f2082c, false);
            this.f2079a.m3455a(this.f2079a.f2082c, false);
            EventTracker.m2021a(this.f2079a.f2082c, ((BOENavDrawerActivity) this.f2079a.f2081b.get()).getAnalyticsContext());
        }

        public void m3448b() {
            this.f2079a.m3455a(this.f2079a.f2082c, false);
            EventTracker.m2033b(this.f2079a.f2082c, ((BOENavDrawerActivity) this.f2079a.f2081b.get()).getAnalyticsContext());
        }
    }

    static {
        f2080a = EtsyDebug.m1891a(EasyOptOutDelegate.class);
    }

    public EasyOptOutDelegate(BOENavDrawerActivity bOENavDrawerActivity) {
        this.f2081b = new WeakReference(bOENavDrawerActivity);
        if (bOENavDrawerActivity.getConfigMap().m885c(EtsyConfigKeys.bX)) {
            this.f2082c = m3456b();
            if (!TextUtils.isEmpty(this.f2082c)) {
                m3454a(this.f2082c);
            }
        }
    }

    @Nullable
    private String m3456b() {
        if (this.f2081b.get() == null) {
            return null;
        }
        Intent intent = ((BOENavDrawerActivity) this.f2081b.get()).getIntent();
        return intent == null ? null : intent.getStringExtra("opened_notification_type");
    }

    private void m3454a(String str) {
        if (this.f2081b.get() != null) {
            aj.m1101a().m1123i().m1696a(this.f2081b.get(), ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) NotificationOptOutBannerSetting.createRequestBuilder(InstallInfo.m919a().m925b(), str).m1393d()).m1422a(new EasyOptOutDelegate(this, str), (Activity) this.f2081b.get())).m1426c());
        }
    }

    private void m3455a(String str, boolean z) {
        EtsyApiV3Request etsyApiV3Request = new EtsyApiV3Request(EmptyResult.class, EtsyV3Urls.m1505b(InstallInfo.m919a().m925b(), str));
        etsyApiV3Request.m1382a(2);
        etsyApiV3Request.m1385a(ResponseConstants.BANNER_ENABLED, String.valueOf(z));
        aj.m1101a().m1123i().m1696a(null, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) etsyApiV3Request.m1393d()).m1421a(new EasyOptOutDelegate(this, str))).m1426c());
    }

    private void m3459b(String str, boolean z) {
        EtsyJobBuilder a = EtsyJobBuilder.m1307a(DeviceRequest.updateNotificationSetting(InstallInfo.m919a().m925b(), str, z));
        a.m1321a(new EasyOptOutDelegate(this, str));
        a.m1320a(new EasyOptOutDelegate(this, str));
        aj.m1101a().m1123i().m1697a(null, a.m1324a());
    }

    private void m3451a(@NonNull NotificationOptOutBannerSetting notificationOptOutBannerSetting) {
        if (notificationOptOutBannerSetting.getBannerShouldShow() && this.f2081b.get() != null) {
            EasyOptOutPopup easyOptOutPopup = new EasyOptOutPopup((Activity) this.f2081b.get());
            easyOptOutPopup.m5366a(notificationOptOutBannerSetting.getBannerText());
            easyOptOutPopup.m5368b(notificationOptOutBannerSetting.getBannerDisableLink());
            easyOptOutPopup.m5355a(new EasyOptOutDelegate(this));
            easyOptOutPopup.m5357b();
        }
    }
}
