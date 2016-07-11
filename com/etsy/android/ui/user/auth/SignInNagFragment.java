package com.etsy.android.ui.user.auth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyNetworkJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.external.ExternalAccountDelegate.NotEnabledException;
import com.etsy.android.lib.core.external.ExternalAccountProfile;
import com.etsy.android.lib.core.external.ExternalAccountProfile.Gender;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ExternalAccountResult;
import com.etsy.android.lib.requests.ExternalAccountRequest;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ae;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.UserInputDialogHelper;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class SignInNagFragment extends SignInBaseFragment implements UserInputDialogHelper {
    private static final int AVATAR_SIZE = 400;
    private SignInActivity mActivity;
    private OnClickListener mClickListener;
    private Button mFacebookSignInButton;
    private Button mGoogleSignInButton;
    private View mLoginFormView;
    private EtsyDialogFragment mParentDialog;
    private View mProgressView;
    private boolean mShowFacebookSignIn;
    private boolean mShowGoogleSignIn;
    private TextView mTxtDesc;

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.1 */
    class C08791 extends TrackingOnClickListener {
        final /* synthetic */ SignInNagFragment f3536a;

        C08791(SignInNagFragment signInNagFragment) {
            this.f3536a = signInNagFragment;
        }

        public void onViewClick(View view) {
            EtsyEventTracker.m4574e();
            ExternalAccountUtil.m3105d("login_nag_google_button_tapped");
            this.f3536a.hideError();
            try {
                this.f3536a.signIn(AccountType.GOOGLE);
            } catch (NotEnabledException e) {
                this.f3536a.showError((int) R.external_error_connecting, AccountType.GOOGLE);
                this.f3536a.mGoogleSignInButton.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.2 */
    class C08802 extends TrackingOnClickListener {
        final /* synthetic */ SignInNagFragment f3537a;

        C08802(SignInNagFragment signInNagFragment) {
            this.f3537a = signInNagFragment;
        }

        public void onViewClick(View view) {
            EtsyEventTracker.m4579f();
            ExternalAccountUtil.m3105d("login_nag_facebook_button_tapped");
            this.f3537a.hideError();
            try {
                this.f3537a.signIn(AccountType.FACEBOOK);
            } catch (NotEnabledException e) {
                this.f3537a.showError((int) R.external_error_connecting, AccountType.FACEBOOK);
                this.f3537a.mFacebookSignInButton.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.3 */
    class C08813 extends TrackingOnClickListener {
        final /* synthetic */ SignInNagFragment f3538a;

        C08813(SignInNagFragment signInNagFragment) {
            this.f3538a = signInNagFragment;
        }

        public void onViewClick(View view) {
            this.f3538a.hideError();
            if (view.getId() == 2131755809) {
                EtsyEventTracker.m4558b();
                this.f3538a.mActivity.showSignIn();
            } else if (view.getId() == 2131755808) {
                EtsyEventTracker.m4570d();
                this.f3538a.mActivity.showRegister();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.4 */
    class C08834 implements Listener<EtsyResult<ExternalAccountResult>> {
        final /* synthetic */ AccountType f3541a;
        final /* synthetic */ String f3542b;
        final /* synthetic */ String f3543c;
        final /* synthetic */ String f3544d;
        final /* synthetic */ SignInNagFragment f3545e;

        /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.4.1 */
        class C08821 implements ae {
            final /* synthetic */ ExternalAccountResult f3539a;
            final /* synthetic */ C08834 f3540b;

            C08821(C08834 c08834, ExternalAccountResult externalAccountResult) {
                this.f3540b = c08834;
                this.f3539a = externalAccountResult;
            }

            public void m5025a(String str) {
                Activity activity = this.f3540b.f3545e.getActivity();
                if (activity != null && !activity.isFinishing() && this.f3540b.f3545e.isAdded() && !this.f3540b.f3545e.isRemoving()) {
                    if (str == null) {
                        this.f3540b.f3545e.showProgress(false);
                        this.f3540b.f3545e.showError((int) R.external_error_connecting, this.f3540b.f3541a);
                    } else if (this.f3539a.isLoginPossible()) {
                        ExternalAccountProfile currentProfile = this.f3540b.f3545e.getCurrentProfile();
                        this.f3540b.f3545e.startSignInTask(new SignInBaseFragment(this.f3540b.f3545e, currentProfile.m1208e(), this.f3540b.f3542b, currentProfile.m1205b(), str), false);
                    } else if (this.f3539a.isSignInPossible()) {
                        this.f3540b.f3545e.mActivity.showLinkAccount(this.f3539a.getEtsyEmail(), this.f3540b.f3542b, this.f3540b.f3543c, str);
                        this.f3540b.f3545e.showProgress(false);
                    } else {
                        this.f3540b.f3545e.startAuthRegistration(this.f3540b.f3542b, this.f3540b.f3544d, str);
                        this.f3540b.f3545e.showProgress(false);
                    }
                }
            }
        }

        C08834(SignInNagFragment signInNagFragment, AccountType accountType, String str, String str2, String str3) {
            this.f3545e = signInNagFragment;
            this.f3541a = accountType;
            this.f3542b = str;
            this.f3543c = str2;
            this.f3544d = str3;
        }

        public /* synthetic */ void onResponse(Object obj) {
            m5026a((EtsyResult) obj);
        }

        public void m5026a(EtsyResult<ExternalAccountResult> etsyResult) {
            this.f3545e.mActivity.getExternalAccountDelegate().m1191a(this.f3541a, new C08821(this, (ExternalAccountResult) etsyResult.m1056g().get(0)), false);
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.5 */
    class C08845 implements ErrorListener {
        final /* synthetic */ SignInNagFragment f3546a;

        C08845(SignInNagFragment signInNagFragment) {
            this.f3546a = signInNagFragment;
        }

        public void onErrorResponse(VolleyError volleyError) {
            EtsyEventTracker.m4575e(this.f3546a.getAnalyticsContext());
            this.f3546a.showError((int) R.error_external_account_api);
            this.f3546a.mActivity.cancelCurrentSignInAttempt();
            this.f3546a.showProgress(false);
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.6 */
    class C08856 extends AnimatorListenerAdapter {
        final /* synthetic */ boolean f3547a;
        final /* synthetic */ SignInNagFragment f3548b;

        C08856(SignInNagFragment signInNagFragment, boolean z) {
            this.f3548b = signInNagFragment;
            this.f3547a = z;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3548b.mLoginFormView != null) {
                this.f3548b.mLoginFormView.setVisibility(this.f3547a ? 4 : 0);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.7 */
    class C08867 extends AnimatorListenerAdapter {
        final /* synthetic */ boolean f3549a;
        final /* synthetic */ SignInNagFragment f3550b;

        C08867(SignInNagFragment signInNagFragment, boolean z) {
            this.f3550b = signInNagFragment;
            this.f3549a = z;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3550b.mProgressView != null) {
                this.f3550b.mProgressView.setVisibility(this.f3549a ? 0 : 8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInNagFragment.8 */
    /* synthetic */ class C08878 {
        static final /* synthetic */ int[] f3551a;
        static final /* synthetic */ int[] f3552b;

        static {
            f3552b = new int[Gender.values().length];
            try {
                f3552b[Gender.FEMALE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3552b[Gender.MALE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f3551a = new int[EtsyAction.values().length];
            try {
                f3551a[EtsyAction.FOLLOW.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3551a[EtsyAction.PURCHASE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3551a[EtsyAction.FAVORITE.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3551a[EtsyAction.CONTACT_USER.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3551a[EtsyAction.MANAGE_ITEM_COLLECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3551a[EtsyAction.VIEW_ORDER.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3551a[EtsyAction.VIEW_PURCHASES.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f3551a[EtsyAction.VIEW_CONVO.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f3551a[EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mClickListener = createClickListener();
        boolean z = supportsGooglePlayServices() && ExternalAccountUtil.m3103c(AccountType.GOOGLE);
        this.mShowGoogleSignIn = z;
        this.mShowFacebookSignIn = ExternalAccountUtil.m3103c(AccountType.FACEBOOK);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903233, viewGroup, false);
        setErrorView((TextView) inflate.findViewById(2131755775));
        this.mGoogleSignInButton = (Button) inflate.findViewById(2131755806);
        if (this.mShowGoogleSignIn) {
            this.mGoogleSignInButton.setOnClickListener(new C08791(this));
        } else {
            this.mGoogleSignInButton.setVisibility(8);
        }
        this.mFacebookSignInButton = (Button) inflate.findViewById(2131755807);
        if (this.mShowFacebookSignIn) {
            this.mFacebookSignInButton.setOnClickListener(new C08802(this));
        } else {
            this.mFacebookSignInButton.setVisibility(8);
        }
        this.mTxtDesc = (TextView) inflate.findViewById(2131755804);
        inflate.findViewById(2131755808).setOnClickListener(this.mClickListener);
        inflate.findViewById(2131755809).setOnClickListener(this.mClickListener);
        this.mLoginFormView = inflate.findViewById(2131755805);
        this.mProgressView = inflate.findViewById(2131755810);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mParentDialog.setWindowAnimation(R.dialog_enter_default);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (SignInActivity) getActivity();
        this.mDialogUtil = this.mActivity.getDialogHelper();
        this.mDialogUtil.m5633a((UserInputDialogHelper) this);
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.setWindowAnimation(R.DialogAnimBottom);
        this.mParentDialog.enableTouchInterceptPadding(getResources().getDimensionPixelSize(2131361936));
        this.mParentDialog.hideHeader();
        String stringExtra = this.mActivity.getIntent().getStringExtra(EtsyAction.ACTION_TYPE_NAME);
        switch (C08878.f3551a[EtsyAction.fromName(stringExtra).ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_follow_desc_text));
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_purchase_desc_text));
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_favorite_desc_text));
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_contact_desc_text));
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_manage_collections_desc_text));
                break;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_view_order_desc_text));
                break;
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_view_purchases_desc_text));
                break;
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_view_convos_desc_text));
                break;
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_subscribe_vacation_notification_text));
                break;
            default:
                this.mTxtDesc.setText(getString(R.sign_in_dialog_default_text));
                break;
        }
        ExternalAccountUtil.m3105d("login_nag_displayed");
        ExternalAccountUtil.m3105d("login_nag_displayed_action." + stringExtra);
    }

    public void onDetach() {
        super.onDetach();
        if (this.mDialogUtil != null) {
            this.mDialogUtil.m5634b(this);
        }
    }

    private OnClickListener createClickListener() {
        return new C08813(this);
    }

    public void onSignIn(AccountType accountType) {
        super.onSignIn(accountType);
        showProgress(true);
        ExternalAccountProfile currentProfile = getCurrentProfile();
        if (currentProfile == null) {
            showError((int) R.external_error_connecting, accountType);
            showProgress(false);
            return;
        }
        String a = currentProfile.m1203a();
        checkForExternalAccount(currentProfile.m1208e(), currentProfile.m1205b(), currentProfile.m1207d(), a);
    }

    public void checkForExternalAccount(String str, String str2, String str3, String str4) {
        AccountType a = ExternalAccountUtil.m3093a(str4);
        EtsyNetworkJob a2 = EtsyJobBuilder.m1307a(ExternalAccountRequest.hasExternalAccount(str2, str3, str, str4)).m1324a();
        a2.m699a(new C08834(this, a, str4, str2, str));
        a2.m698a(new C08845(this));
        getRequestQueue().m1697a((Object) this, a2);
    }

    private void startAuthRegistration(String str, String str2, String str3) {
        String a;
        String a2;
        String b;
        String g;
        String a3;
        String str4;
        String str5 = null;
        ExternalAccountProfile currentProfile = getCurrentProfile();
        if (currentProfile != null) {
            String b2;
            a = currentProfile.m1203a();
            ExternalAccountProfile c = currentProfile.m1206c();
            if (c != null) {
                a2 = c.m1302a();
                b2 = c.m1303b();
            } else {
                b2 = null;
                a2 = null;
            }
            switch (C08878.f3552b[currentProfile.m1209f().ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    str5 = RegisterFragment.GENDER_NAME_FEMALE;
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    str5 = RegisterFragment.GENDER_NAME_MALE;
                    break;
            }
            b = currentProfile.m1205b();
            g = currentProfile.m1210g();
            a3 = currentProfile.m1204a(AVATAR_SIZE);
            str4 = str5;
            str5 = a2;
            a2 = b2;
        } else {
            a3 = null;
            g = null;
            b = null;
            str4 = null;
            a2 = null;
            a = str;
        }
        this.mActivity.showRegisterWithInfo(str5, a2, str2, str4, g, a3, a, b, str3);
    }

    public void onBlockingUI(AccountType accountType, boolean z) {
        super.onBlockingUI(accountType, z);
        showProgress(z);
    }

    public void showProgress(boolean z) {
        float f;
        int i = 0;
        float f2 = FullImageView.ASPECT_RATIO_SQUARE;
        int integer = getResources().getInteger(17694720);
        this.mLoginFormView.setVisibility(z ? 4 : 0);
        ViewPropertyAnimator duration = this.mLoginFormView.animate().setDuration((long) integer);
        if (z) {
            f = 0.0f;
        } else {
            f = FullImageView.ASPECT_RATIO_SQUARE;
        }
        duration.alpha(f).setListener(new C08856(this, z));
        View view = this.mProgressView;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        ViewPropertyAnimator duration2 = this.mProgressView.animate().setDuration((long) integer);
        if (!z) {
            f2 = 0.0f;
        }
        duration2.alpha(f2).setListener(new C08867(this, z));
    }

    public boolean handleBackPressed() {
        EtsyEventTracker.m4565c();
        return super.handleBackPressed();
    }

    public void onError(AccountType accountType, Exception exception) {
        super.onError(accountType, exception);
        showError((int) R.error_external_account_api);
    }

    @NonNull
    public String getTrackingName() {
        return "login_nag";
    }
}
