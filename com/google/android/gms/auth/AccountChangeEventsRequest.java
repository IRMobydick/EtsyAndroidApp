package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Creator<AccountChangeEventsRequest> CREATOR;
    final int mVersion;
    Account zzZB;
    @Deprecated
    String zzaaR;
    int zzaaT;

    static {
        CREATOR = new zzb();
    }

    public AccountChangeEventsRequest() {
        this.mVersion = 1;
    }

    AccountChangeEventsRequest(int i, int i2, String str, Account account) {
        this.mVersion = i;
        this.zzaaT = i2;
        this.zzaaR = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzZB = account;
        } else {
            this.zzZB = new Account(str, "com.google");
        }
    }

    public Account getAccount() {
        return this.zzZB;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzaaR;
    }

    public int getEventIndex() {
        return this.zzaaT;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzZB = account;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzaaR = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i) {
        this.zzaaT = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
