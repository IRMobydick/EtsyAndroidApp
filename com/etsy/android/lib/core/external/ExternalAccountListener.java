package com.etsy.android.lib.core.external;

import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;

/* renamed from: com.etsy.android.lib.core.external.c */
public interface ExternalAccountListener {
    void onBlockingUI(AccountType accountType, boolean z);

    void onError(AccountType accountType, Exception exception);

    void onRevokeAccess(AccountType accountType);

    void onSignIn(AccountType accountType);

    void onSignOut(AccountType accountType);

    void updateState(AccountType accountType);
}
