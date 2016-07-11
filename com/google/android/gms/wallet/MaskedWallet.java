package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet extends AbstractSafeParcelable {
    public static final Creator<MaskedWallet> CREATOR;
    private final int mVersionCode;
    String zzbyT;
    String zzbyU;
    String zzbyW;
    Address zzbyX;
    Address zzbyY;
    String[] zzbyZ;
    LoyaltyWalletObject[] zzbzN;
    OfferWalletObject[] zzbzO;
    UserAddress zzbza;
    UserAddress zzbzb;
    InstrumentInfo[] zzbzc;

    static {
        CREATOR = new zzl();
    }

    private MaskedWallet() {
        this.mVersionCode = 2;
    }

    MaskedWallet(int i, String str, String str2, String[] strArr, String str3, Address address, Address address2, LoyaltyWalletObject[] loyaltyWalletObjectArr, OfferWalletObject[] offerWalletObjectArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr) {
        this.mVersionCode = i;
        this.zzbyT = str;
        this.zzbyU = str2;
        this.zzbyZ = strArr;
        this.zzbyW = str3;
        this.zzbyX = address;
        this.zzbyY = address2;
        this.zzbzN = loyaltyWalletObjectArr;
        this.zzbzO = offerWalletObjectArr;
        this.zzbza = userAddress;
        this.zzbzb = userAddress2;
        this.zzbzc = instrumentInfoArr;
    }

    public static C1165e newBuilderFrom(MaskedWallet maskedWallet) {
        zzaa.zzz(maskedWallet);
        return zzNg().m7460a(maskedWallet.getGoogleTransactionId()).m7466b(maskedWallet.getMerchantTransactionId()).m7464a(maskedWallet.getPaymentDescriptions()).m7461a(maskedWallet.getInstrumentInfos()).m7467c(maskedWallet.getEmail()).m7462a(maskedWallet.zzNh()).m7463a(maskedWallet.zzNi()).m7459a(maskedWallet.getBuyerBillingAddress()).m7465b(maskedWallet.getBuyerShippingAddress());
    }

    public static C1165e zzNg() {
        MaskedWallet maskedWallet = new MaskedWallet();
        maskedWallet.getClass();
        return new C1165e(null);
    }

    @Deprecated
    public Address getBillingAddress() {
        return this.zzbyX;
    }

    public UserAddress getBuyerBillingAddress() {
        return this.zzbza;
    }

    public UserAddress getBuyerShippingAddress() {
        return this.zzbzb;
    }

    public String getEmail() {
        return this.zzbyW;
    }

    public String getGoogleTransactionId() {
        return this.zzbyT;
    }

    public InstrumentInfo[] getInstrumentInfos() {
        return this.zzbzc;
    }

    public String getMerchantTransactionId() {
        return this.zzbyU;
    }

    public String[] getPaymentDescriptions() {
        return this.zzbyZ;
    }

    @Deprecated
    public Address getShippingAddress() {
        return this.zzbyY;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.zza(this, parcel, i);
    }

    @Deprecated
    public LoyaltyWalletObject[] zzNh() {
        return this.zzbzN;
    }

    @Deprecated
    public OfferWalletObject[] zzNi() {
        return this.zzbzO;
    }
}
