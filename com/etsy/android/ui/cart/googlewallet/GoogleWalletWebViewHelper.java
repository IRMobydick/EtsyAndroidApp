package com.etsy.android.ui.cart.googlewallet;

import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.icht.IchtToken;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.IchtTokenRequest;
import com.etsy.android.lib.util.bl;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.ProxyCard;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.cart.googlewallet.e */
class GoogleWalletWebViewHelper extends EtsyRequestJob<IchtToken> {
    FullWallet f2482a;
    String f2483c;
    final /* synthetic */ GoogleWalletWebViewHelper f2484d;

    public GoogleWalletWebViewHelper(GoogleWalletWebViewHelper googleWalletWebViewHelper, FullWallet fullWallet, String str) {
        this.f2484d = googleWalletWebViewHelper;
        this.f2482a = fullWallet;
        this.f2483c = str;
    }

    protected EtsyRequest<IchtToken> m3837a() {
        ProxyCard proxyCard = this.f2482a.getProxyCard();
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.CC_NUMBER, proxyCard.getPan());
        hashMap.put(ResponseConstants.EXPIRATION_MONTH, Integer.toString(proxyCard.getExpirationMonth()));
        hashMap.put(ResponseConstants.EXPIRATION_YEAR, Integer.toString(proxyCard.getExpirationYear()));
        hashMap.put(ResponseConstants.CSC_NUMBER, proxyCard.getCvn());
        hashMap.put(ResponseConstants.NAME, this.f2482a.getBillingAddress().getName());
        hashMap.put(ResponseConstants.NONCE, this.f2483c);
        return IchtTokenRequest.getTokenizeRequest(hashMap);
    }

    protected void m3838a(EtsyResult<IchtToken> etsyResult) {
        if (etsyResult.m1049a() && etsyResult.m1058i()) {
            this.f2484d.f2477e.purchase(this.f2484d.m3826a(this.f2482a, (IchtToken) etsyResult.m1057h()));
            return;
        }
        EtsyLogger.m1966a().m1996b(GoogleWalletHelperBase.f2472a, "Failed to get ICHT token");
        this.f2484d.f2477e.hideCover();
        bl.m3365b(this.f2484d.f2479g, (int) R.whoops_somethings_wrong);
    }
}
