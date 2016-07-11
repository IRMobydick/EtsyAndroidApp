package com.google.android.gms.wallet;

import com.google.android.gms.identity.intents.model.UserAddress;

/* renamed from: com.google.android.gms.wallet.e */
public final class C1165e {
    final /* synthetic */ MaskedWallet f5561a;

    private C1165e(MaskedWallet maskedWallet) {
        this.f5561a = maskedWallet;
    }

    public C1165e m7459a(UserAddress userAddress) {
        this.f5561a.zzbza = userAddress;
        return this;
    }

    public C1165e m7460a(String str) {
        this.f5561a.zzbyT = str;
        return this;
    }

    public C1165e m7461a(InstrumentInfo[] instrumentInfoArr) {
        this.f5561a.zzbzc = instrumentInfoArr;
        return this;
    }

    @Deprecated
    public C1165e m7462a(LoyaltyWalletObject[] loyaltyWalletObjectArr) {
        this.f5561a.zzbzN = loyaltyWalletObjectArr;
        return this;
    }

    @Deprecated
    public C1165e m7463a(OfferWalletObject[] offerWalletObjectArr) {
        this.f5561a.zzbzO = offerWalletObjectArr;
        return this;
    }

    public C1165e m7464a(String[] strArr) {
        this.f5561a.zzbyZ = strArr;
        return this;
    }

    public C1165e m7465b(UserAddress userAddress) {
        this.f5561a.zzbzb = userAddress;
        return this;
    }

    public C1165e m7466b(String str) {
        this.f5561a.zzbyU = str;
        return this;
    }

    public C1165e m7467c(String str) {
        this.f5561a.zzbyW = str;
        return this;
    }
}
