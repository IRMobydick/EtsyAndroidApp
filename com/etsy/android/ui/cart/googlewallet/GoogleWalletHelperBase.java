package com.etsy.android.ui.cart.googlewallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bl;
import com.etsy.android.util.EtsyBuildHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.wallet.Address;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.j;
import com.google.android.gms.wallet.l;
import java.util.HashMap;

public abstract class GoogleWalletHelperBase implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener {
    protected static final String f2472a;
    protected GoogleApiClient f2473b;
    protected ConnectionResult f2474c;

    public enum ReadyState {
        UNKNOWN,
        READY,
        NOT_READY,
        CANCELED,
        INTERRUPTED
    }

    static {
        f2472a = EtsyDebug.m1891a(GoogleWalletHelperBase.class);
    }

    public GoogleWalletHelperBase(Context context) {
        this.f2473b = new Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(j.a, new l().a(m3811d()).b(1).a()).build();
    }

    public static boolean m3806a(@NonNull ad adVar) {
        return adVar.m1864f().m885c(EtsyConfigKeys.f1222o);
    }

    public void m3809b() {
        this.f2473b.connect();
    }

    public void m3810c() {
        this.f2473b.disconnect();
    }

    public void onConnected(Bundle bundle) {
        this.f2474c = null;
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.f2474c = connectionResult;
    }

    protected int m3811d() {
        return EtsyBuildHelper.m5708c() ? 0 : 1;
    }

    protected static HashMap<String, String> m3804a(String str, Address address) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(str + "[" + ResponseConstants.COUNTRY_CODE + "]", address.getCountryCode());
        hashMap.put(str + "[" + ResponseConstants.NAME + "]", address.getName());
        hashMap.put(str + "[" + ResponseConstants.FIRST_LINE + "]", address.getAddress1());
        hashMap.put(str + "[" + ResponseConstants.SECOND_LINE + "]", address.getAddress2());
        hashMap.put(str + "[" + ResponseConstants.CITY + "]", address.getCity());
        hashMap.put(str + "[" + ResponseConstants.STATE + "]", address.getState());
        hashMap.put(str + "[" + ResponseConstants.ZIP + "]", address.getPostalCode());
        return hashMap;
    }

    public static MaskedWallet m3802a(Intent intent) {
        return intent != null ? (MaskedWallet) intent.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET") : null;
    }

    public static HashMap<String, String> m3803a(MaskedWallet maskedWallet) {
        HashMap<String, String> hashMap = new HashMap();
        if (maskedWallet != null) {
            hashMap.putAll(m3804a(ResponseConstants.SHIPPING_ADDRESS, maskedWallet.getShippingAddress()));
            hashMap.put("google_wallet[email]", maskedWallet.getEmail());
            hashMap.put("google_wallet[transaction_id]", maskedWallet.getGoogleTransactionId());
            for (Object put : maskedWallet.getPaymentDescriptions()) {
                hashMap.put("google_wallet[payment][]", put);
            }
        }
        return hashMap;
    }

    public int m3808b(Intent intent) {
        return intent == null ? -1 : intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", -1);
    }

    public void m3807a(int i, Context context) {
        switch (i) {
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                bl.m3356a(context, context.getString(R.network_unavailable));
                EtsyDebug.m1906b(f2472a, "Network error: " + i);
                EtsyLogger.m1966a().m1996b(f2472a, "Network error: " + i);
                break;
            case 406:
                bl.m3356a(context, context.getString(R.google_wallet_error_spending_limit_exceeded));
                break;
            default:
                m3805a(context);
                EtsyDebug.m1906b(f2472a, "Unrecoverable error when checking out with Google Wallet: " + i);
                EtsyLogger.m1966a().m1996b(f2472a, "Unrecoverable error when checking out with Google Wallet: " + i);
                break;
        }
        EtsyGraphite.m1807a("google_wallet.error." + i);
    }

    public static void m3805a(Context context) {
        bl.m3365b(context, (int) R.google_wallet_error_unavailable);
    }
}
