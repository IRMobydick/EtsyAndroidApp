package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;

public final class MaskedWalletRequest extends AbstractSafeParcelable {
    public static final Creator<MaskedWalletRequest> CREATOR;
    private final int mVersionCode;
    ArrayList<CountrySpecification> zzbAa;
    PaymentMethodTokenizationParameters zzbAb;
    ArrayList<Integer> zzbAc;
    String zzbyN;
    String zzbyU;
    boolean zzbzQ;
    boolean zzbzR;
    boolean zzbzS;
    String zzbzT;
    String zzbzU;
    boolean zzbzV;
    boolean zzbzW;
    CountrySpecification[] zzbzX;
    boolean zzbzY;
    boolean zzbzZ;
    Cart zzbze;

    static {
        CREATOR = new zzm();
    }

    MaskedWalletRequest() {
        this.mVersionCode = 3;
        this.zzbzY = true;
        this.zzbzZ = true;
    }

    MaskedWalletRequest(int i, String str, boolean z, boolean z2, boolean z3, String str2, String str3, String str4, Cart cart, boolean z4, boolean z5, CountrySpecification[] countrySpecificationArr, boolean z6, boolean z7, ArrayList<CountrySpecification> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, ArrayList<Integer> arrayList2) {
        this.mVersionCode = i;
        this.zzbyU = str;
        this.zzbzQ = z;
        this.zzbzR = z2;
        this.zzbzS = z3;
        this.zzbzT = str2;
        this.zzbyN = str3;
        this.zzbzU = str4;
        this.zzbze = cart;
        this.zzbzV = z4;
        this.zzbzW = z5;
        this.zzbzX = countrySpecificationArr;
        this.zzbzY = z6;
        this.zzbzZ = z7;
        this.zzbAa = arrayList;
        this.zzbAb = paymentMethodTokenizationParameters;
        this.zzbAc = arrayList2;
    }

    public static f newBuilder() {
        MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new f(maskedWalletRequest, null);
    }

    public boolean allowDebitCard() {
        return this.zzbzZ;
    }

    public boolean allowPrepaidCard() {
        return this.zzbzY;
    }

    public ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzbAc;
    }

    public ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.zzbAa;
    }

    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzbzX;
    }

    public Cart getCart() {
        return this.zzbze;
    }

    public String getCurrencyCode() {
        return this.zzbyN;
    }

    public String getEstimatedTotalPrice() {
        return this.zzbzT;
    }

    public String getMerchantName() {
        return this.zzbzU;
    }

    public String getMerchantTransactionId() {
        return this.zzbyU;
    }

    public PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzbAb;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    @Deprecated
    public boolean isBillingAgreement() {
        return this.zzbzW;
    }

    public boolean isPhoneNumberRequired() {
        return this.zzbzQ;
    }

    public boolean isShippingAddressRequired() {
        return this.zzbzR;
    }

    @Deprecated
    public boolean useMinimalBillingAddress() {
        return this.zzbzS;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
