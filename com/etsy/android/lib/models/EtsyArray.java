package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class EtsyArray extends BaseModel implements Parcelable {
    public static final Creator<EtsyArray> CREATOR;
    private static final String TAG;
    private static final long serialVersionUID = 8806610668463280833L;
    protected JSONObject mData;

    /* renamed from: com.etsy.android.lib.models.EtsyArray.1 */
    final class C04731 implements Creator<EtsyArray> {
        C04731() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2317a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2318a(i);
        }

        public EtsyArray m2317a(Parcel parcel) {
            return new EtsyArray(parcel);
        }

        public EtsyArray[] m2318a(int i) {
            return new EtsyArray[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(EtsyArray.class);
        CREATOR = new C04731();
    }

    protected EtsyArray(Parcel parcel) {
        try {
            this.mData = new JSONObject(parcel.readString());
        } catch (JSONException e) {
            this.mData = null;
        }
    }

    public JSONObject getData() {
        return this.mData;
    }

    public void parseData(JsonParser jsonParser) {
        try {
            this.mData = new JSONObject(jsonParser.readValueAsTree().toString());
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "EtsyArray parseData error", e);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mData != null ? this.mData.toString() : StringUtils.EMPTY);
    }
}
