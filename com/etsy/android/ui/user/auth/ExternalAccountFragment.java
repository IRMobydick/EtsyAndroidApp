package com.etsy.android.ui.user.auth;

import android.os.Bundle;
import com.etsy.android.lib.core.external.ExternalAccountDelegate;
import com.etsy.android.lib.core.external.ExternalAccountListener;
import com.etsy.android.lib.core.external.ExternalAccountProfile;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.ui.EtsyFragment;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class ExternalAccountFragment extends EtsyFragment implements ExternalAccountListener {
    protected static final String TAG;
    private ExternalAccountDelegate mExternalAccountDelegate;

    static {
        TAG = EtsyDebug.m1891a(ExternalAccountFragment.class);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() instanceof ExternalAccountDelegate) {
            this.mExternalAccountDelegate = ((ExternalAccountDelegate) getActivity()).getExternalAccountDelegate();
            this.mExternalAccountDelegate.m1189a((ExternalAccountListener) this);
            return;
        }
        throw new RuntimeException("Activities which use this fragment must implement ExternalAccountDelegate.Provider");
    }

    public void onDetach() {
        super.onDetach();
        if (this.mExternalAccountDelegate != null) {
            this.mExternalAccountDelegate.m1194b((ExternalAccountListener) this);
            this.mExternalAccountDelegate.m1196c();
        }
    }

    protected boolean supportsGooglePlayServices() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == 0;
    }

    public ExternalAccountProfile getCurrentProfile() {
        return this.mExternalAccountDelegate.m1201f();
    }

    public void signIn(AccountType accountType) {
        this.mExternalAccountDelegate.m1190a(accountType);
    }

    public void signOut() {
        signOut(null);
    }

    public void signOut(AccountType accountType) {
        this.mExternalAccountDelegate.m1195b(accountType);
    }

    public void revokeAccess() {
        revokeAccess(null);
    }

    public void revokeAccess(AccountType accountType) {
        this.mExternalAccountDelegate.m1197c(accountType);
    }

    public void onRevokeAccess(AccountType accountType) {
    }

    public void onSignIn(AccountType accountType) {
    }

    public void onSignOut(AccountType accountType) {
    }

    public void onBlockingUI(AccountType accountType, boolean z) {
    }

    public void onError(AccountType accountType, Exception exception) {
    }

    public void updateState(AccountType accountType) {
    }
}
