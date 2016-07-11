package com.etsy.android;

import android.content.Context;
import com.adjust.sdk.AdjustConfig;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.al;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.requests.LocaleRequest;
import com.etsy.android.ui.BOENavigationMenuManager;
import com.etsy.android.ui.util.EtsyPromoUtil;
import com.etsy.android.ui.util.GiftCardUtil;
import com.etsy.android.uikit.EtsyAppBuilder;
import com.etsy.android.util.AppboyUtil;
import com.etsy.android.util.EtsyBuildHelper;
import com.etsy.android.util.SessionManager;
import com.etsy.android.util.UpgradeUtil;
import java.util.Timer;
import java.util.TimerTask;

public class BOEApplication extends EtsyApplication implements EtsyAppBuilder {
    private static final String ADJUST_APP_TOKEN = "q4argp6xa97r";
    private static final String API_KEY = "gf665hj0kpsdkpllgek11zrz";
    private static final String API_SECRET = "wwzr1l5lkn";
    private static final String BOE_LOG_PREFIX = "ETSY:";
    private static final String CRITTERCISM_API_KEY = "53d955c607229a646b000001";
    private static final String CRITTERCISM_API_KEY_ALPHA = "50cb6e5d866b841def000006";
    private static final String TAG;
    private al mGiftCardUnregisterListener;

    /* renamed from: com.etsy.android.BOEApplication.1 */
    class C04361 extends TimerTask {
        final /* synthetic */ EtsyGraphite f1070a;
        final /* synthetic */ BOEApplication f1071b;

        C04361(BOEApplication bOEApplication, EtsyGraphite etsyGraphite) {
            this.f1071b = bOEApplication;
            this.f1070a = etsyGraphite;
        }

        public void run() {
            this.f1070a.m1816a();
        }
    }

    /* renamed from: com.etsy.android.BOEApplication.2 */
    class C04372 implements al {
        final /* synthetic */ BOEApplication f1072a;

        C04372(BOEApplication bOEApplication) {
            this.f1072a = bOEApplication;
        }

        public void onSignedInChanged(Context context, boolean z) {
            GiftCardUtil.m5201d();
        }
    }

    public BOEApplication() {
        this.mGiftCardUnregisterListener = new C04372(this);
    }

    static {
        TAG = EtsyDebug.m1891a(BOEApplication.class);
    }

    public void onCreate() {
        super.onCreate();
        boolean d = EtsyBuildHelper.m5709d();
        EtsyAppBuilder.m5329a(getApplicationContext()).m5336a(EtsyApplication.BOE_NAME).m5330a(2130837953).m5341b(BOE_LOG_PREFIX).m5338a(d).m5343b(d).m5346c(d).m5331a(EtsyBuildHolder.f1073a).m5340b(2131034112).m5344c(2131034114).m5334a(new SessionManager()).m5333a(this.mGiftCardUnregisterListener).m5335a((EtsyAppBuilder) this).m5337a(API_KEY, API_SECRET).m5345c("1431618").m5332a(EtsyConfigKeys.bp, d ? CRITTERCISM_API_KEY_ALPHA : CRITTERCISM_API_KEY).m5342b(ADJUST_APP_TOKEN, d ? AdjustConfig.ENVIRONMENT_SANDBOX : AdjustConfig.ENVIRONMENT_PRODUCTION).m5339a();
        BOENavigationMenuManager.m3487a(getApplicationContext());
        if (d && EtsyConfig.m853e(this)) {
            EtsyConfig a = EtsyConfig.m837a();
            a.m861a((Context) this);
            aj.m1101a().m1116c();
            a.m870d((Context) this);
        }
        new Timer().schedule(new C04361(this, EtsyGraphite.m1814b("nulltest.timer", 0.009999999776482582d)), 100);
        AppboyUtil.m5683a();
        EtsyDebug.m1906b(TAG, "onCreate");
        aj.m1101a().m1107a(new GetUserInfoAndCurrencyBatchJob(this));
        if (aj.m1101a().m1118d()) {
            LocaleRequest.setUserLocale();
        }
    }

    public String getConvoAuthority() {
        return "com.etsy.android.contentproviders.EtsyConvoProvider";
    }

    public boolean onAppUpgrade(int i) {
        boolean z = true;
        if (i <= 83) {
            handleTwoDotOhUpgrade();
            handleTwoDotThreeUpgrade();
            z = false;
        } else if (i <= 2030006) {
            handleTwoDotThreeUpgrade();
        }
        if (i > 0) {
            EtsyPromoUtil.m5143a(this, i);
        }
        return z;
    }

    private void handleTwoDotOhUpgrade() {
        aj.m1101a().m1119e();
    }

    private void handleTwoDotThreeUpgrade() {
        UpgradeUtil.m5726b(this);
        UpgradeUtil.m5725a(this);
    }
}
