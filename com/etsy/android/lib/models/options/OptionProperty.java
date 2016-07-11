package com.etsy.android.lib.models.options;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class OptionProperty extends BaseModel implements Parcelable {
    public static final Creator<OptionProperty> CREATOR;
    private static final String TAG;
    private String mDisplayValue;
    private EtsyId mListingOptionPropertyId;
    private EtsyId mPropertyTypeId;

    /* renamed from: com.etsy.android.lib.models.options.OptionProperty.1 */
    final class C04871 implements Creator<OptionProperty> {
        C04871() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2802a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2803a(i);
        }

        public OptionProperty m2802a(Parcel parcel) {
            return new OptionProperty(parcel);
        }

        public OptionProperty[] m2803a(int i) {
            return new OptionProperty[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(OptionProperty.class);
        CREATOR = new C04871();
    }

    public OptionProperty() {
        this.mListingOptionPropertyId = new EtsyId();
        this.mPropertyTypeId = new EtsyId();
    }

    public OptionProperty(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public EtsyId getPropertyTypeId() {
        return this.mPropertyTypeId;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public EtsyId getListingOptionPropertyId() {
        return this.mListingOptionPropertyId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("listing_option_property_id".equals(currentName)) {
                    this.mListingOptionPropertyId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.DISPLAY_VALUE.equals(currentName)) {
                    this.mDisplayValue = BaseModel.parseString(jsonParser);
                } else if ("property_type_id".equals(currentName)) {
                    this.mPropertyTypeId.setId(BaseModel.parseString(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int describeContents() {
        return hashCode();
    }

    private void readFromParcel(Parcel parcel) {
        this.mListingOptionPropertyId = (EtsyId) parcel.readSerializable();
        this.mDisplayValue = parcel.readString();
        this.mPropertyTypeId = (EtsyId) parcel.readSerializable();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mListingOptionPropertyId);
        parcel.writeString(this.mDisplayValue);
        parcel.writeSerializable(this.mPropertyTypeId);
    }
}
