package com.etsy.android.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.external.ExternalAccountDelegate;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.messaging.EtsyGooglePlayServicesUtil;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.util.CheckAlphaUpdateUtil;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ak;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.EtsyPromoUtil;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.EtsyBuildHelper;

public class UserSettingsActivity extends BOENavDrawerActivity implements ExternalAccountDelegate {
    private static final int REQUEST_CODE_CURRENCY = 100;
    private static final String TAG;
    private CheckAlphaUpdateUtil mAlphaUpdateCheck;
    private OnClickListener mClickListener;
    ExternalAccountDelegate mExternalAccountDelegate;
    private View mUpdateRow;

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.1 */
    class C08481 implements CheckAlphaUpdateUtil {
        final /* synthetic */ UserSettingsActivity f3493a;

        C08481(UserSettingsActivity userSettingsActivity) {
            this.f3493a = userSettingsActivity;
        }

        public void m5015a(AppBuild appBuild) {
            this.f3493a.mUpdateRow.setVisibility(0);
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.2 */
    class C08492 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsActivity f3494a;

        C08492(UserSettingsActivity userSettingsActivity) {
            this.f3494a = userSettingsActivity;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3494a).m4683a().m4549w();
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.3 */
    class C08503 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsActivity f3495a;

        C08503(UserSettingsActivity userSettingsActivity) {
            this.f3495a = userSettingsActivity;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3495a).m4683a().m4537k();
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.4 */
    class C08514 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsActivity f3496a;

        C08514(UserSettingsActivity userSettingsActivity) {
            this.f3496a = userSettingsActivity;
        }

        public void onViewClick(View view) {
            EtsyGooglePlayServicesUtil.m2260b(this.f3496a);
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.5 */
    class C08525 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsActivity f3497a;

        C08525(UserSettingsActivity userSettingsActivity) {
            this.f3497a = userSettingsActivity;
        }

        public void onViewClick(View view) {
            this.f3497a.signOut();
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.6 */
    class C08536 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsActivity f3498a;

        C08536(UserSettingsActivity userSettingsActivity) {
            this.f3498a = userSettingsActivity;
        }

        public void onViewClick(View view) {
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.7 */
    class C08547 extends TrackingOnCheckedChangeListener {
        final /* synthetic */ UserSettingsActivity f3499a;

        C08547(UserSettingsActivity userSettingsActivity) {
            this.f3499a = userSettingsActivity;
        }

        public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
            SharedPreferencesUtility.m3124a(this.f3499a, z);
            EtsyLogger.m1966a().m1987a("history_enabled", "your_account_settings", String.valueOf(z));
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.8 */
    class C08558 extends TrackingOnClickListener {
        final /* synthetic */ TextView f3500a;
        final /* synthetic */ UserSettingsActivity f3501b;

        C08558(UserSettingsActivity userSettingsActivity, TextView textView) {
            this.f3501b = userSettingsActivity;
            this.f3500a = textView;
        }

        public void onViewClick(View view) {
            EtsyDatabaseUtil.m744b(this.f3501b);
            this.f3501b.disableClearViewHistory(this.f3500a);
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.9 */
    class C08569 extends TrackingOnClickListener {
        final /* synthetic */ TextView f3502a;
        final /* synthetic */ UserSettingsActivity f3503b;

        C08569(UserSettingsActivity userSettingsActivity, TextView textView) {
            this.f3503b = userSettingsActivity;
            this.f3502a = textView;
        }

        public void onViewClick(View view) {
            EtsyDatabaseUtil.m751d(this.f3503b);
            this.f3503b.disableClearSearchHistory(this.f3502a);
        }
    }

    public UserSettingsActivity() {
        this.mClickListener = new C08536(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mExternalAccountDelegate = new ExternalAccountDelegate(this);
        setTitle((int) R.settings);
        setContentView(2130903252);
        setUpCommonButtons();
        if (aj.m1101a().m1118d()) {
            setUpSignedInButtons();
        }
        if (EtsyPromoUtil.m5147b()) {
            this.mAlphaUpdateCheck = new CheckAlphaUpdateUtil(this);
            this.mAlphaUpdateCheck.m3414a(new C08481(this));
            return;
        }
        this.mUpdateRow.setVisibility(EtsyPromoUtil.m5148b(this) ? 0 : 8);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == REQUEST_CODE_CURRENCY) {
            Object stringExtra = intent.getStringExtra(CurrencySelectionActivity.EXTRA_CURRENCY_SYMBOL);
            String stringExtra2 = intent.getStringExtra(CurrencySelectionActivity.EXTRA_CURRENCY_CODE);
            ((TextView) findViewById(2131755854)).setText(stringExtra);
            ((TextView) findViewById(2131755855)).setText(CurrencyUtil.m3081d(stringExtra2, stringExtra));
            return;
        }
        super.onActivityResult(i, i2, intent);
        this.mExternalAccountDelegate.m1188a(i, i2, intent);
    }

    static {
        TAG = EtsyDebug.m1891a(UserSettingsFragment.class);
    }

    private void setUpCommonButtons() {
        CheckBox checkBox = (CheckBox) findViewById(2131755850);
        checkBox.setChecked(SharedPreferencesUtility.m3154l(this));
        checkBox.setVisibility(0);
        checkBox.setOnCheckedChangeListener(new C08547(this));
        TextView textView = (TextView) findViewById(2131755851);
        if (EtsyDatabaseUtil.m733a((Context) this) < 1) {
            disableClearViewHistory(textView);
        } else {
            textView.setOnClickListener(new C08558(this, textView));
        }
        textView = (TextView) findViewById(2131755852);
        if (EtsyDatabaseUtil.m749c(this) < 1) {
            disableClearSearchHistory(textView);
        } else {
            textView.setOnClickListener(new C08569(this, textView));
        }
        findViewById(2131755858).setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ UserSettingsActivity f3487a;

            {
                this.f3487a = r1;
            }

            public void onViewClick(View view) {
                Nav.m4682a(this.f3487a).m4683a().m4496b(0);
            }
        });
        this.mUpdateRow = findViewById(2131755862);
        this.mUpdateRow.setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ UserSettingsActivity f3489a;

            /* renamed from: com.etsy.android.ui.user.UserSettingsActivity.11.1 */
            class C08471 implements CheckAlphaUpdateUtil {
                final /* synthetic */ AnonymousClass11 f3488a;

                C08471(AnonymousClass11 anonymousClass11) {
                    this.f3488a = anonymousClass11;
                }

                public void m5014a(boolean z) {
                    this.f3488a.f3489a.showUpdateRow(false);
                }
            }

            {
                this.f3489a = r1;
            }

            public void onViewClick(View view) {
                if (!EtsyPromoUtil.m5147b()) {
                    this.f3489a.startActivity(EtsyPromoUtil.m5141a(this.f3489a.getAnalyticsContext()));
                } else if (this.f3489a.mAlphaUpdateCheck.m3416a()) {
                    this.f3489a.showUpdateRow(true);
                    this.f3489a.mAlphaUpdateCheck.m3415a(new C08471(this));
                } else if (this.f3489a.getConfigMap().m884b()) {
                    Toast.makeText(this.f3489a, "Uh oh. Couldn't fetch a build.", 1).show();
                } else {
                    Toast.makeText(this.f3489a, "You need to be logged in as an admin to update from an internal build", 1).show();
                }
            }
        });
        if (getConfigMap().m884b() || getConfigMap().m885c(EtsyConfigKeys.f1216i)) {
            findViewById(2131755853).setOnClickListener(new TrackingOnClickListener() {
                final /* synthetic */ UserSettingsActivity f3490a;

                {
                    this.f3490a = r1;
                }

                public void onViewClick(View view) {
                    this.f3490a.startActivityForResult(new Intent(this.f3490a, CurrencySelectionActivity.class), UserSettingsActivity.REQUEST_CODE_CURRENCY);
                }
            });
            Object j = CurrencyUtil.m3092j();
            ((TextView) findViewById(2131755854)).setText(j);
            ((TextView) findViewById(2131755855)).setText(CurrencyUtil.m3081d(CurrencyUtil.m3091i(), j));
        } else {
            findViewById(2131755853).setVisibility(8);
            findViewById(2131755856).setVisibility(8);
        }
        View findViewById = findViewById(2131755859);
        if (getConfigMap().m884b()) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                final /* synthetic */ UserSettingsActivity f3491a;

                {
                    this.f3491a = r1;
                }

                public void onViewClick(View view) {
                    Nav.m4682a(this.f3491a).m4683a().m4538l();
                }
            });
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        if (EtsyBuildHelper.m5709d()) {
            findViewById(2131755860).setVisibility(0);
            checkBox = (CheckBox) findViewById(2131755861);
            checkBox.setChecked(SharedPreferencesUtility.m3155m(this));
            checkBox.setOnCheckedChangeListener(new TrackingOnCheckedChangeListener() {
                final /* synthetic */ UserSettingsActivity f3492a;

                {
                    this.f3492a = r1;
                }

                public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                    SharedPreferencesUtility.m3131b(this.f3492a, z);
                }
            });
        }
        findViewById(2131755857).setOnClickListener(new C08492(this));
    }

    private void showUpdateRow(boolean z) {
        boolean z2 = false;
        ((TextView) this.mUpdateRow.findViewById(2131755864)).setText(z ? R.updating : R.update);
        this.mUpdateRow.findViewById(2131755863).setVisibility(z ? 0 : 8);
        View view = this.mUpdateRow;
        if (!z) {
            z2 = true;
        }
        view.setEnabled(z2);
    }

    private void disableClearViewHistory(TextView textView) {
        textView.setText(R.viewing_history_cleared);
        textView.setEnabled(false);
    }

    private void disableClearSearchHistory(TextView textView) {
        textView.setText(R.search_history_cleared);
        textView.setEnabled(false);
    }

    private void setUpSignedInButtons() {
        findViewById(2131755865).setVisibility(0);
        ((TextView) findViewById(2131755866)).setText(String.format(getString(R.connected_as_user), new Object[]{SharedPreferencesUtility.m3138d(this)}).toUpperCase());
        TextView textView = (TextView) findViewById(2131755867);
        if (ak.m3245a(this)) {
            textView.setOnClickListener(new C08503(this));
        } else if (EtsyGooglePlayServicesUtil.m2258a((Context) this)) {
            textView.setText(R.get_notifications);
            textView.setOnClickListener(new C08514(this));
        } else {
            textView.setVisibility(8);
        }
        findViewById(2131755868).setOnClickListener(new C08525(this));
    }

    public void signOut() {
        this.mExternalAccountDelegate.m1199d();
        signOutEtsyAccount();
    }

    public void revokeAccess() {
        this.mExternalAccountDelegate.m1200e();
        signOutEtsyAccount();
    }

    private void signOutEtsyAccount() {
        aj.m1101a().m1119e();
        EventTracker.m2019a("your_account_settings");
        finish();
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    protected void onStart() {
        super.onStart();
        this.mExternalAccountDelegate.m1187a();
    }

    protected void onStop() {
        super.onStop();
        this.mExternalAccountDelegate.m1193b();
    }

    public ExternalAccountDelegate getExternalAccountDelegate() {
        return this.mExternalAccountDelegate;
    }
}
