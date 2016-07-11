package com.etsy.android.lib.models.apiv3.ipp;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class TaxProfile extends BaseModel implements Parcelable {
    public static final Creator<TaxProfile> CREATOR;
    private EtsyId mId;
    private boolean mIsIPPDefault;
    private String mName;
    private String mPercent;
    private String mRate;

    /* renamed from: com.etsy.android.lib.models.apiv3.ipp.TaxProfile.1 */
    final class C04831 implements Creator<TaxProfile> {
        C04831() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2533a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2534a(i);
        }

        public TaxProfile m2533a(Parcel parcel) {
            return new TaxProfile(parcel);
        }

        public TaxProfile[] m2534a(int i) {
            return new TaxProfile[i];
        }
    }

    public TaxProfile() {
        this.mName = StringUtils.EMPTY;
        this.mPercent = StringUtils.EMPTY;
        this.mRate = StringUtils.EMPTY;
        this.mId = new EtsyId();
    }

    public TaxProfile(Parcel parcel) {
        this();
        this.mId = (EtsyId) parcel.readSerializable();
        this.mName = parcel.readString();
        this.mPercent = parcel.readString();
        this.mRate = parcel.readString();
        this.mIsIPPDefault = parcel.readInt() == 1;
    }

    public EtsyId getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPercent() {
        return this.mPercent;
    }

    public boolean isIPPDefault() {
        return this.mIsIPPDefault;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ID.equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.PERCENT.equals(currentName)) {
                    this.mPercent = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.RATE.equals(currentName)) {
                    this.mRate = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.IN_PERSON_DEFAULT.equals(currentName)) {
                    this.mIsIPPDefault = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPercent);
        parcel.writeString(this.mRate);
        parcel.writeInt(this.mIsIPPDefault ? 1 : 0);
    }

    static {
        CREATOR = new C04831();
    }

    public int hashCode() {
        return this.mId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof TaxProfile) {
            return ((TaxProfile) obj).getId().equals(getId());
        }
        return super.equals(obj);
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TAX_PROFILE_ID, this.mId.getId());
        return hashMap;
    }
}
