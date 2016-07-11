package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet extends AbstractSafeParcelable {
    public static final Creator<FullWallet> CREATOR;
    private final int mVersionCode;
    String zzbyT;
    String zzbyU;
    ProxyCard zzbyV;
    String zzbyW;
    Address zzbyX;
    Address zzbyY;
    String[] zzbyZ;
    UserAddress zzbza;
    UserAddress zzbzb;
    InstrumentInfo[] zzbzc;
    PaymentMethodToken zzbzd;

    static {
        CREATOR = new zze();
    }

    private FullWallet() {
        this.mVersionCode = 1;
    }

    FullWallet(int i, String str, String str2, ProxyCard proxyCard, String str3, Address address, Address address2, String[] strArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr, PaymentMethodToken paymentMethodToken) {
        this.mVersionCode = i;
        this.zzbyT = str;
        this.zzbyU = str2;
        this.zzbyV = proxyCard;
        this.zzbyW = str3;
        this.zzbyX = address;
        this.zzbyY = address2;
        this.zzbyZ = strArr;
        this.zzbza = userAddress;
        this.zzbzb = userAddress2;
        this.zzbzc = instrumentInfoArr;
        this.zzbzd = paymentMethodToken;
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

    public PaymentMethodToken getPaymentMethodToken() {
        return this.zzbzd;
    }

    public ProxyCard getProxyCard() {
        return this.zzbyV;
    }

    @Deprecated
    public Address getShippingAddress() {
        return this.zzbyY;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
