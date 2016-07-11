package com.etsy.android.ui.user.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import org.apache.commons.lang3.StringUtils;

public class SignInFragment extends SignInBaseFragment implements OnTouchListener {
    private EtsyDialogFragment mParentDialog;
    private Button mSignInButton;
    private TextView mSwitchToRegister;
    private TextView mTxtDialogSubTitle;
    private TextView mTxtDialogTitle;
    private EditText mTxtPassword;
    private EditText mTxtUsername;

    /* renamed from: com.etsy.android.ui.user.auth.SignInFragment.1 */
    class C08741 extends TrackingOnClickListener {
        final /* synthetic */ SignInFragment f3531a;

        C08741(SignInFragment signInFragment) {
            this.f3531a = signInFragment;
        }

        public void onViewClick(View view) {
            this.f3531a.signIn();
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInFragment.2 */
    class C08752 implements OnEditorActionListener {
        final /* synthetic */ SignInFragment f3532a;

        C08752(SignInFragment signInFragment) {
            this.f3532a = signInFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            this.f3532a.mSignInButton.performClick();
            return true;
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInFragment.3 */
    class C08763 implements Runnable {
        final /* synthetic */ SignInFragment f3533a;

        C08763(SignInFragment signInFragment) {
            this.f3533a = signInFragment;
        }

        public void run() {
            if (TextUtils.isEmpty(this.f3533a.mTxtUsername.getText())) {
                this.f3533a.mTxtUsername.requestFocusFromTouch();
            } else {
                this.f3533a.mTxtPassword.requestFocusFromTouch();
            }
            if (this.f3533a.mActivity != null && !new TabletSupportUtil(this.f3533a.mActivity).m5627g()) {
                ai.m3226b(this.f3533a.mActivity).toggleSoftInput(0, 0);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInFragment.4 */
    class C08774 extends TrackingOnClickListener {
        final /* synthetic */ SignInFragment f3534a;

        C08774(SignInFragment signInFragment) {
            this.f3534a = signInFragment;
        }

        public void onViewClick(View view) {
            this.f3534a.forgotPassword();
        }
    }

    /* renamed from: com.etsy.android.ui.user.auth.SignInFragment.5 */
    class C08785 extends TrackingOnClickListener {
        final /* synthetic */ SignInFragment f3535a;

        C08785(SignInFragment signInFragment) {
            this.f3535a = signInFragment;
        }

        public void onViewClick(View view) {
            this.f3535a.mParentDialog.dismissAllowingStateLoss();
            this.f3535a.mActivity.showRegister();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903232, viewGroup, false);
        Bundle arguments = getArguments();
        setErrorView((TextView) inflate.findViewById(2131755775));
        this.mTxtUsername = (EditText) inflate.findViewById(2131755629);
        this.mTxtPassword = (EditText) inflate.findViewById(2131755800);
        this.mTxtDialogTitle = (TextView) inflate.findViewById(R.txt_title);
        this.mTxtDialogSubTitle = (TextView) inflate.findViewById(R.txt_subtitle);
        if (arguments.getBoolean("link_external_account")) {
            this.mTxtUsername.setText(arguments.getString(ResponseConstants.EMAIL));
            this.mTxtDialogTitle.setText(R.external_signin_header);
            this.mTxtDialogSubTitle.setText(getString(R.external_signin_subheader, getString(ExternalAccountUtil.m3099b(arguments.getString("account_type_name")))));
            this.mTxtDialogSubTitle.setVisibility(0);
        }
        this.mTxtPassword.setOnTouchListener(this);
        this.mTxtUsername.setOnTouchListener(this);
        this.mSignInButton = (Button) inflate.findViewById(2131755802);
        this.mSignInButton.setOnClickListener(new C08741(this));
        if (!GraphikUtil.m5547a(this.mTxtPassword, R.typeface_normal)) {
            this.mTxtPassword.setTypeface(Typeface.DEFAULT);
        }
        this.mTxtPassword.setOnEditorActionListener(new C08752(this));
        this.mTxtUsername.postDelayed(new C08763(this), 400);
        inflate.findViewById(2131755801).setOnClickListener(new C08774(this));
        this.mSwitchToRegister = (TextView) inflate.findViewById(2131755803);
        bl.m3361a(this.mSwitchToRegister, (int) R.sign_in_switch_to_register, (int) R.sign_in_switch_to_register_highlight, (int) R.blue);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.hideHeader();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mSwitchToRegister.setOnClickListener(new C08785(this));
        ExternalAccountUtil.m3097a("login_view", getArguments().getString("account_type_name", null));
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mTxtUsername != null) {
            this.mTxtUsername.requestFocusFromTouch();
        }
        if (this.mActivity != null && !z) {
            ai.m3225a(this.mActivity, this.mTxtUsername);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.requestFocusFromTouch();
        return false;
    }

    public void onPause() {
        this.mTxtPassword.setText(StringUtils.EMPTY);
        super.onPause();
    }

    private void signIn() {
        boolean z;
        boolean z2 = false;
        String trim = this.mTxtUsername.getText().toString().trim();
        String obj = this.mTxtPassword.getText().toString();
        if (TextUtils.isEmpty(trim)) {
            this.mTxtUsername.setError(getString(R.error_username_empty));
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(obj)) {
            this.mTxtPassword.setError(getString(R.error_password_empty));
            z = false;
        }
        if (NetworkUtils.m3107a().m3114b()) {
            z2 = z;
        } else {
            showError((int) R.network_unavailable);
        }
        if (z2) {
            SignInBaseFragment signInBaseFragment;
            Bundle arguments = getArguments();
            if (arguments.getBoolean("link_external_account")) {
                signInBaseFragment = new SignInBaseFragment(this, trim, obj, arguments.getString("account_type_name"), arguments.getString("account_id"), arguments.getString("auth_token"));
                EtsyEventTracker.m4586g(getAnalyticsContext(), SignInFlow.LINK);
            } else {
                signInBaseFragment = new SignInBaseFragment(trim, obj);
                EtsyEventTracker.m4586g(getAnalyticsContext(), SignInFlow.REGULAR);
            }
            startSignInTask(signInBaseFragment, true);
        }
    }

    private void forgotPassword() {
        SignInFlow signInFlow;
        String trim = this.mTxtUsername != null ? this.mTxtUsername.getText().toString().trim() : StringUtils.EMPTY;
        if (getArguments().getBoolean("link_external_account")) {
            signInFlow = SignInFlow.LINK;
        } else {
            signInFlow = SignInFlow.REGULAR;
        }
        Nav.m4682a(getActivity()).m4683a().m4487a(trim, signInFlow);
    }

    public boolean handleBackPressed() {
        if (getArguments().getBoolean("link_external_account")) {
            EtsyEventTracker.m4590h(getAnalyticsContext(), SignInFlow.LINK);
        } else {
            EtsyEventTracker.m4590h(getAnalyticsContext(), SignInFlow.REGULAR);
        }
        return super.handleBackPressed();
    }

    @NonNull
    public String getTrackingName() {
        return "login_view";
    }
}
