package com.etsy.android.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;

public class CurrencySelectionActivity extends BOENavDrawerActivity {
    public static final String EXTRA_CURRENCY_CODE = "curr_code";
    public static final String EXTRA_CURRENCY_SYMBOL = "curr_symbol";

    /* renamed from: com.etsy.android.ui.user.CurrencySelectionActivity.1 */
    class C08201 implements CurrencySelectFragment {
        final /* synthetic */ CurrencySelectionActivity f3444a;

        C08201(CurrencySelectionActivity currencySelectionActivity) {
            this.f3444a = currencySelectionActivity;
        }

        public void m5003a(EtsyCurrency etsyCurrency) {
            EventTracker.m2011a(this.f3444a.getAnalyticsContext(), "view_locale_preferences");
            Intent intent = this.f3444a.getIntent();
            intent.putExtra(CurrencySelectionActivity.EXTRA_CURRENCY_CODE, etsyCurrency.getUnit().getCurrencyCode());
            intent.putExtra(CurrencySelectionActivity.EXTRA_CURRENCY_SYMBOL, etsyCurrency.getUnit().getCurrencySymbol());
            this.f3444a.setResult(-1, intent);
            this.f3444a.finish();
        }

        public void m5002a() {
            this.f3444a.setResult(0);
            this.f3444a.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.prefs_set_currency_title);
        Nav.m4682a((FragmentActivity) this).m4684b().m4625a(AnimationMode.FADE).m4628a(new C08201(this));
    }
}
