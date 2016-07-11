package com.google.android.gms.ads.internal.reward.mediation.client;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.a.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.internal.jw;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public final class RewardItemParcel extends AbstractSafeParcelable {
    public static final zzc CREATOR;
    public final String type;
    public final int versionCode;
    public final int zzOV;

    static {
        CREATOR = new zzc();
    }

    public RewardItemParcel(int i, String str, int i2) {
        this.versionCode = i;
        this.type = str;
        this.zzOV = i2;
    }

    public RewardItemParcel(a aVar) {
        this(1, aVar.a(), aVar.b());
    }

    public RewardItemParcel(String str, int i) {
        this(1, str, i);
    }

    @Nullable
    public static RewardItemParcel zza(JSONArray jSONArray) {
        return (jSONArray == null || jSONArray.length() == 0) ? null : new RewardItemParcel(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    @Nullable
    public static RewardItemParcel zzaG(@Nullable String str) {
        RewardItemParcel rewardItemParcel = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                rewardItemParcel = zza(new JSONArray(str));
            } catch (JSONException e) {
            }
        }
        return rewardItemParcel;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RewardItemParcel)) {
            return false;
        }
        RewardItemParcel rewardItemParcel = (RewardItemParcel) obj;
        return zzz.equal(this.type, rewardItemParcel.type) && zzz.equal(Integer.valueOf(this.zzOV), Integer.valueOf(rewardItemParcel.zzOV));
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{this.type, Integer.valueOf(this.zzOV)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public JSONArray zzir() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rb_type", this.type);
        jSONObject.put("rb_amount", this.zzOV);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }
}
