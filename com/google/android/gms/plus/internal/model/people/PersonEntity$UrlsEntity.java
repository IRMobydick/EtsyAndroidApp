package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.a.a.j;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class PersonEntity$UrlsEntity extends FastSafeParcelableJsonResponse implements j {
    public static final zzj CREATOR;
    private static final HashMap<String, Field<?, ?>> zzblR;
    String mValue;
    final int mVersionCode;
    String zzVu;
    int zzagd;
    final Set<Integer> zzblS;
    private final int zzbmC;

    static {
        CREATOR = new zzj();
        zzblR = new HashMap();
        zzblR.put(ResponseConstants.LABEL, Field.zzk(ResponseConstants.LABEL, 5));
        zzblR.put(FindsModule.FIELD_TYPE, Field.zza(FindsModule.FIELD_TYPE, 6, new StringToIntConverter().zzh("home", 0).zzh("work", 1).zzh("blog", 2).zzh("profile", 3).zzh(ResponseConstants.OTHER, 4).zzh("otherProfile", 5).zzh("contributor", 6).zzh("website", 7), false));
        zzblR.put(ResponseConstants.VALUE, Field.zzk(ResponseConstants.VALUE, 4));
    }

    public PersonEntity$UrlsEntity() {
        this.zzbmC = 4;
        this.mVersionCode = 1;
        this.zzblS = new HashSet();
    }

    PersonEntity$UrlsEntity(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
        this.zzbmC = 4;
        this.zzblS = set;
        this.mVersionCode = i;
        this.zzVu = str;
        this.zzagd = i2;
        this.mValue = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PersonEntity$UrlsEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PersonEntity$UrlsEntity personEntity$UrlsEntity = (PersonEntity$UrlsEntity) obj;
        for (Field field : zzblR.values()) {
            if (zza(field)) {
                if (!personEntity$UrlsEntity.zza(field)) {
                    return false;
                }
                if (!zzb(field).equals(personEntity$UrlsEntity.zzb(field))) {
                    return false;
                }
            } else if (personEntity$UrlsEntity.zza(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return zzII();
    }

    public String getLabel() {
        return this.zzVu;
    }

    public int getType() {
        return this.zzagd;
    }

    public String getValue() {
        return this.mValue;
    }

    public boolean hasLabel() {
        return this.zzblS.contains(Integer.valueOf(5));
    }

    public boolean hasType() {
        return this.zzblS.contains(Integer.valueOf(6));
    }

    public boolean hasValue() {
        return this.zzblS.contains(Integer.valueOf(4));
    }

    public int hashCode() {
        int i = 0;
        for (Field field : zzblR.values()) {
            int hashCode;
            if (zza(field)) {
                hashCode = zzb(field).hashCode() + (i + field.zzul());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    public boolean isDataValid() {
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj com_google_android_gms_plus_internal_model_people_zzj = CREATOR;
        zzj.zza(this, parcel, i);
    }

    @Deprecated
    public int zzIH() {
        return 4;
    }

    public PersonEntity$UrlsEntity zzII() {
        return this;
    }

    public HashMap<String, Field<?, ?>> zzIx() {
        return zzblR;
    }

    protected boolean zza(Field field) {
        return this.zzblS.contains(Integer.valueOf(field.zzul()));
    }

    protected Object zzb(Field field) {
        switch (field.zzul()) {
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return this.mValue;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return this.zzVu;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return Integer.valueOf(this.zzagd);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.zzul());
        }
    }

    public /* synthetic */ Map zzuf() {
        return zzIx();
    }
}
