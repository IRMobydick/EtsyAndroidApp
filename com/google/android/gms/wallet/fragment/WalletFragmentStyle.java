package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.g;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.h;

public final class WalletFragmentStyle extends AbstractSafeParcelable {
    public static final Creator<WalletFragmentStyle> CREATOR;
    final int mVersionCode;
    Bundle zzbAZ;
    int zzbBa;

    static {
        CREATOR = new zzc();
    }

    public WalletFragmentStyle() {
        this.mVersionCode = 1;
        this.zzbAZ = new Bundle();
        this.zzbAZ.putInt("buyButtonAppearanceDefault", 4);
        this.zzbAZ.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
    }

    WalletFragmentStyle(int i, Bundle bundle, int i2) {
        this.mVersionCode = i;
        this.zzbAZ = bundle;
        this.zzbBa = i2;
    }

    private static int zza(long j, DisplayMetrics displayMetrics) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                i = 0;
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                i = 1;
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                i = 2;
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                i = 3;
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                i = 4;
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                i = 5;
                break;
            case AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS /*128*/:
                return TypedValue.complexToDimensionPixelSize(i2, displayMetrics);
            case 129:
                return i2;
            default:
                throw new IllegalStateException("Unexpected unit or type: " + i);
        }
        return Math.round(TypedValue.applyDimension(i, Float.intBitsToFloat(i2), displayMetrics));
    }

    private static long zza(int i, float f) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return zzw(i, Float.floatToIntBits(f));
            default:
                throw new IllegalArgumentException("Unrecognized unit: " + i);
        }
    }

    private static long zza(TypedValue typedValue) {
        switch (typedValue.type) {
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return zzw(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, typedValue.data);
            case CommonStatusCodes.CANCELED /*16*/:
                return zzmg(typedValue.data);
            default:
                throw new IllegalArgumentException("Unexpected dimension type: " + typedValue.type);
        }
    }

    private void zza(TypedArray typedArray, int i, String str) {
        if (!this.zzbAZ.containsKey(str)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue != null) {
                this.zzbAZ.putLong(str, zza(peekValue));
            }
        }
    }

    private void zza(TypedArray typedArray, int i, String str, String str2) {
        if (!this.zzbAZ.containsKey(str) && !this.zzbAZ.containsKey(str2)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue == null) {
                return;
            }
            if (peekValue.type < 28 || peekValue.type > 31) {
                this.zzbAZ.putInt(str2, peekValue.resourceId);
            } else {
                this.zzbAZ.putInt(str, peekValue.data);
            }
        }
    }

    private void zzb(TypedArray typedArray, int i, String str) {
        if (!this.zzbAZ.containsKey(str)) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue != null) {
                this.zzbAZ.putInt(str, peekValue.data);
            }
        }
    }

    private static long zzmg(int i) {
        if (i >= 0) {
            return zza(0, (float) i);
        }
        if (i == -1 || i == -2) {
            return zzw(129, i);
        }
        throw new IllegalArgumentException("Unexpected dimension value: " + i);
    }

    private static long zzw(int i, int i2) {
        return (((long) i) << 32) | (((long) i2) & 4294967295L);
    }

    public WalletFragmentStyle setBuyButtonAppearance(int i) {
        this.zzbAZ.putInt("buyButtonAppearance", i);
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int i) {
        this.zzbAZ.putLong("buyButtonHeight", zzmg(i));
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int i, float f) {
        this.zzbAZ.putLong("buyButtonHeight", zza(i, f));
        return this;
    }

    public WalletFragmentStyle setBuyButtonText(int i) {
        this.zzbAZ.putInt("buyButtonText", i);
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int i) {
        this.zzbAZ.putLong("buyButtonWidth", zzmg(i));
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int i, float f) {
        this.zzbAZ.putLong("buyButtonWidth", zza(i, f));
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int i) {
        this.zzbAZ.remove("maskedWalletDetailsBackgroundResource");
        this.zzbAZ.putInt("maskedWalletDetailsBackgroundColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int i) {
        this.zzbAZ.remove("maskedWalletDetailsBackgroundColor");
        this.zzbAZ.putInt("maskedWalletDetailsBackgroundResource", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int i) {
        this.zzbAZ.remove("maskedWalletDetailsButtonBackgroundResource");
        this.zzbAZ.putInt("maskedWalletDetailsButtonBackgroundColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int i) {
        this.zzbAZ.remove("maskedWalletDetailsButtonBackgroundColor");
        this.zzbAZ.putInt("maskedWalletDetailsButtonBackgroundResource", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int i) {
        this.zzbAZ.putInt("maskedWalletDetailsButtonTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int i) {
        this.zzbAZ.putInt("maskedWalletDetailsHeaderTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int i) {
        this.zzbAZ.putInt("maskedWalletDetailsLogoImageType", i);
        return this;
    }

    @Deprecated
    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int i) {
        this.zzbAZ.putInt("maskedWalletDetailsLogoTextColor", i);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int i) {
        this.zzbAZ.putInt("maskedWalletDetailsTextAppearance", i);
        return this;
    }

    public WalletFragmentStyle setStyleResourceId(int i) {
        this.zzbBa = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public int zza(String str, DisplayMetrics displayMetrics, int i) {
        return this.zzbAZ.containsKey(str) ? zza(this.zzbAZ.getLong(str), displayMetrics) : i;
    }

    public void zzbt(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.zzbBa <= 0 ? g.WalletFragmentDefaultStyle : this.zzbBa, h.WalletFragmentStyle);
        zza(obtainStyledAttributes, h.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        zza(obtainStyledAttributes, h.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_buyButtonText, "buyButtonText");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        zza(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        zza(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        zzb(obtainStyledAttributes, h.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
}
