package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.messaging.EtsyGooglePlayServicesUtil;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.models.GiftCard;
import com.etsy.android.lib.util.CheckAlphaUpdateUtil;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ak;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.user.auth.ExternalAccountFragment;
import com.etsy.android.ui.util.EtsyPromoUtil;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.EtsyBuildHelper;

public class UserSettingsFragment extends ExternalAccountFragment {
    private static final String TAG;
    private CheckAlphaUpdateUtil mAlphaUpdateCheck;
    private OnClickListener mClickListener;
    private EtsyDialogFragment mParentDialog;
    private View mUpdateRow;
    private View mView;

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.1 */
    class C08591 implements CheckAlphaUpdateUtil {
        final /* synthetic */ UserSettingsFragment f3511a;

        C08591(UserSettingsFragment userSettingsFragment) {
            this.f3511a = userSettingsFragment;
        }

        public void m5019a(AppBuild appBuild) {
            if (this.f3511a.mUpdateRow != null) {
                this.f3511a.mUpdateRow.setVisibility(0);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.2 */
    class C08602 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsFragment f3512a;

        C08602(UserSettingsFragment userSettingsFragment) {
            this.f3512a = userSettingsFragment;
        }

        public void onViewClick(View view) {
            if (this.f3512a.getConfigMap().m885c(EtsyConfigKeys.aI)) {
                Nav.m4682a(this.f3512a.mActivity).m4683a().m4496b(10);
            } else {
                Nav.m4682a(this.f3512a.mActivity).m4684b().m4624a(this.f3512a.mParentDialog.getChildFragmentManager()).m4625a(AnimationMode.FADE).m4622a((int) R.inner_fragment_container).m4664p();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.3 */
    class C08613 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsFragment f3513a;

        C08613(UserSettingsFragment userSettingsFragment) {
            this.f3513a = userSettingsFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3513a.mActivity).m4683a().m4537k();
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.4 */
    class C08624 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsFragment f3514a;

        C08624(UserSettingsFragment userSettingsFragment) {
            this.f3514a = userSettingsFragment;
        }

        public void onViewClick(View view) {
            EtsyGooglePlayServicesUtil.m2260b(this.f3514a.mActivity);
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.5 */
    class C08635 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsFragment f3515a;

        C08635(UserSettingsFragment userSettingsFragment) {
            this.f3515a = userSettingsFragment;
        }

        public void onViewClick(View view) {
            this.f3515a.signOut();
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.6 */
    class C08646 extends TrackingOnClickListener {
        final /* synthetic */ UserSettingsFragment f3516a;

        C08646(UserSettingsFragment userSettingsFragment) {
            this.f3516a = userSettingsFragment;
        }

        public void onViewClick(View view) {
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.7 */
    class C08657 extends TrackingOnCheckedChangeListener {
        final /* synthetic */ UserSettingsFragment f3517a;

        C08657(UserSettingsFragment userSettingsFragment) {
            this.f3517a = userSettingsFragment;
        }

        public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
            SharedPreferencesUtility.m3124a(this.f3517a.mActivity, z);
            EtsyLogger.m1966a().m1987a("history_enabled", "your_account_settings", String.valueOf(z));
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.8 */
    class C08668 extends TrackingOnClickListener {
        final /* synthetic */ TextView f3518a;
        final /* synthetic */ UserSettingsFragment f3519b;

        C08668(UserSettingsFragment userSettingsFragment, TextView textView) {
            this.f3519b = userSettingsFragment;
            this.f3518a = textView;
        }

        public void onViewClick(View view) {
            EtsyDatabaseUtil.m744b(this.f3519b.mActivity);
            this.f3519b.disableClearViewHistory(this.f3518a);
        }
    }

    /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.9 */
    class C08679 extends TrackingOnClickListener {
        final /* synthetic */ TextView f3520a;
        final /* synthetic */ UserSettingsFragment f3521b;

        C08679(UserSettingsFragment userSettingsFragment, TextView textView) {
            this.f3521b = userSettingsFragment;
            this.f3520a = textView;
        }

        public void onViewClick(View view) {
            EtsyDatabaseUtil.m751d(this.f3521b.mActivity);
            this.f3521b.disableClearSearchHistory(this.f3520a);
        }
    }

    public UserSettingsFragment() {
        this.mClickListener = new C08646(this);
    }

    static {
        TAG = EtsyDebug.m1891a(UserSettingsFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = layoutInflater.inflate(2130903252, viewGroup, false);
        setUpCommonButtons();
        if (aj.m1101a().m1118d()) {
            setUpSignedInButtons();
        }
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.setWindowAnimation(R.DialogAnimBottom);
        this.mParentDialog.enableTouchInterceptPadding(getResources().getDimensionPixelSize(2131361936));
        if (EtsyPromoUtil.m5147b()) {
            this.mAlphaUpdateCheck = new CheckAlphaUpdateUtil(getActivity());
            this.mAlphaUpdateCheck.m3414a(new C08591(this));
            return;
        }
        this.mUpdateRow.setVisibility(EtsyPromoUtil.m5148b(getActivity()) ? 0 : 8);
    }

    private void setUpCommonButtons() {
        CheckBox checkBox = (CheckBox) this.mView.findViewById(2131755850);
        checkBox.setChecked(SharedPreferencesUtility.m3154l(this.mActivity));
        checkBox.setVisibility(0);
        checkBox.setOnCheckedChangeListener(new C08657(this));
        TextView textView = (TextView) this.mView.findViewById(2131755851);
        if (EtsyDatabaseUtil.m733a(this.mActivity) < 1) {
            disableClearViewHistory(textView);
        } else {
            textView.setOnClickListener(new C08668(this, textView));
        }
        textView = (TextView) this.mView.findViewById(2131755852);
        if (EtsyDatabaseUtil.m749c(this.mActivity) < 1) {
            disableClearSearchHistory(textView);
        } else {
            textView.setOnClickListener(new C08679(this, textView));
        }
        this.mView.findViewById(2131755858).setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ UserSettingsFragment f3504a;

            {
                this.f3504a = r1;
            }

            public void onViewClick(View view) {
                Nav.m4682a(this.f3504a.mActivity).m4683a().m4496b(0);
            }
        });
        this.mUpdateRow = this.mView.findViewById(2131755862);
        this.mUpdateRow.setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ UserSettingsFragment f3506a;

            /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.11.1 */
            class C08571 implements CheckAlphaUpdateUtil {
                final /* synthetic */ AnonymousClass11 f3505a;

                C08571(AnonymousClass11 anonymousClass11) {
                    this.f3505a = anonymousClass11;
                }

                public void m5016a(boolean z) {
                    this.f3505a.f3506a.showUpdateRow(false);
                }
            }

            {
                this.f3506a = r1;
            }

            public void onViewClick(View view) {
                if (!EtsyPromoUtil.m5147b()) {
                    this.f3506a.startActivity(EtsyPromoUtil.m5141a(this.f3506a.getAnalyticsContext()));
                } else if (this.f3506a.mAlphaUpdateCheck.m3416a()) {
                    this.f3506a.showUpdateRow(true);
                    this.f3506a.mAlphaUpdateCheck.m3415a(new C08571(this));
                } else if (this.f3506a.getConfigMap().m884b()) {
                    Toast.makeText(this.f3506a.getActivity(), "Uh oh. Couldn't fetch a build.", 1).show();
                } else {
                    Toast.makeText(this.f3506a.getActivity(), "You need to be logged in as an admin to update from an internal build", 1).show();
                }
            }
        });
        if (getConfigMap().m884b() || getConfigMap().m885c(EtsyConfigKeys.f1216i)) {
            this.mView.findViewById(2131755853).setOnClickListener(new TrackingOnClickListener() {
                final /* synthetic */ UserSettingsFragment f3508a;

                /* renamed from: com.etsy.android.ui.user.UserSettingsFragment.12.1 */
                class C08581 implements CurrencySelectFragment {
                    final /* synthetic */ AnonymousClass12 f3507a;

                    C08581(AnonymousClass12 anonymousClass12) {
                        this.f3507a = anonymousClass12;
                    }

                    public void m5018a(EtsyCurrency etsyCurrency) {
                        ((TextView) this.f3507a.f3508a.mView.findViewById(2131755854)).setText(etsyCurrency.getUnit().getCurrencySymbol());
                        ((TextView) this.f3507a.f3508a.mView.findViewById(2131755855)).setText(etsyCurrency.getUnit().getCurrencyCodeIfNotEqualToSupplied(GiftCard.CURRENCY_USD));
                        this.f3507a.f3508a.mParentDialog.setTitle(this.f3507a.f3508a.mActivity.getString(R.settings));
                        EventTracker.m2011a(this.f3507a.f3508a.getAnalyticsContext(), "view_locale_preferences");
                    }

                    public void m5017a() {
                        this.f3507a.f3508a.mParentDialog.setTitle(this.f3507a.f3508a.mActivity.getString(R.settings));
                    }
                }

                {
                    this.f3508a = r1;
                }

                public void onViewClick(View view) {
                    Nav.m4682a(this.f3508a.mActivity).m4684b().m4624a(this.f3508a.mParentDialog.getChildFragmentManager()).m4625a(AnimationMode.FADE).m4622a((int) R.inner_fragment_container).m4628a(new C08581(this));
                }
            });
            Object j = CurrencyUtil.m3092j();
            ((TextView) this.mView.findViewById(2131755854)).setText(j);
            ((TextView) this.mView.findViewById(2131755855)).setText(CurrencyUtil.m3081d(CurrencyUtil.m3091i(), j));
        } else {
            this.mView.findViewById(2131755853).setVisibility(8);
            this.mView.findViewById(2131755856).setVisibility(8);
        }
        View findViewById = this.mView.findViewById(2131755859);
        if (getConfigMap().m884b()) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                final /* synthetic */ UserSettingsFragment f3509a;

                {
                    this.f3509a = r1;
                }

                public void onViewClick(View view) {
                    Nav.m4682a(this.f3509a.mActivity).m4683a().m4538l();
                }
            });
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        if (EtsyBuildHelper.m5709d()) {
            this.mView.findViewById(2131755860).setVisibility(0);
            checkBox = (CheckBox) this.mView.findViewById(2131755861);
            checkBox.setChecked(SharedPreferencesUtility.m3155m(this.mActivity));
            checkBox.setOnCheckedChangeListener(new TrackingOnCheckedChangeListener() {
                final /* synthetic */ UserSettingsFragment f3510a;

                {
                    this.f3510a = r1;
                }

                public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                    SharedPreferencesUtility.m3131b(this.f3510a.mActivity, z);
                }
            });
        }
        this.mView.findViewById(2131755857).setOnClickListener(new C08602(this));
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
        this.mView.findViewById(2131755865).setVisibility(0);
        ((TextView) this.mView.findViewById(2131755866)).setText(String.format(getString(R.connected_as_user), new Object[]{SharedPreferencesUtility.m3138d(this.mActivity)}).toUpperCase());
        TextView textView = (TextView) this.mView.findViewById(2131755867);
        if (ak.m3245a(this.mActivity)) {
            textView.setOnClickListener(new C08613(this));
        } else if (EtsyGooglePlayServicesUtil.m2258a(this.mActivity)) {
            textView.setText(R.get_notifications);
            textView.setOnClickListener(new C08624(this));
        } else {
            textView.setVisibility(8);
        }
        this.mView.findViewById(2131755868).setOnClickListener(new C08635(this));
    }

    public void signOut() {
        super.signOut();
        signOutEtsyAccount();
    }

    public void revokeAccess() {
        super.revokeAccess();
        signOutEtsyAccount();
    }

    private void signOutEtsyAccount() {
        aj.m1101a().m1119e();
        EventTracker.m2019a("your_account_settings");
        this.mParentDialog.dismiss();
        this.mActivity.finish();
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    @NonNull
    public String getTrackingName() {
        return "your_account_settings";
    }
}
