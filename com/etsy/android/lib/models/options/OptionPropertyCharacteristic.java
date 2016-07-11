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

public class OptionPropertyCharacteristic extends BaseModel implements Parcelable {
    public static final Creator<OptionPropertyCharacteristic> CREATOR;
    private static final String TAG;
    private String mDisplayValue;
    private EtsyId mListingOptionCharacteristicId;
    private EtsyId mListingOptionCharacteristicSuggestedValueId;
    private int mPosition;
    private String mValue;

    /* renamed from: com.etsy.android.lib.models.options.OptionPropertyCharacteristic.1 */
    final class C04881 implements Creator<OptionPropertyCharacteristic> {
        C04881() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2804a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2805a(i);
        }

        public OptionPropertyCharacteristic m2804a(Parcel parcel) {
            return new OptionPropertyCharacteristic(parcel);
        }

        public OptionPropertyCharacteristic[] m2805a(int i) {
            return new OptionPropertyCharacteristic[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(OptionPropertyCharacteristic.class);
        CREATOR = new C04881();
    }

    public OptionPropertyCharacteristic() {
        this.mListingOptionCharacteristicId = new EtsyId();
        this.mListingOptionCharacteristicSuggestedValueId = new EtsyId();
    }

    public OptionPropertyCharacteristic(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public EtsyId getListingOptionCharacteristicId() {
        return this.mListingOptionCharacteristicId;
    }

    public EtsyId getListingOptionCharacteristicSuggestedValueId() {
        return this.mListingOptionCharacteristicSuggestedValueId;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public String getValue() {
        return this.mValue;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("listing_option_characteristic_id".equals(currentName)) {
                    this.mListingOptionCharacteristicId.setId(BaseModel.parseString(jsonParser));
                } else if ("listing_option_characteristic_suggested_value_id".equals(currentName)) {
                    this.mListingOptionCharacteristicSuggestedValueId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.DISPLAY_VALUE.equals(currentName)) {
                    this.mDisplayValue = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.VALUE.equals(currentName)) {
                    this.mValue = BaseModel.parseString(jsonParser);
                } else if ("position".equals(currentName)) {
                    this.mPosition = jsonParser.getValueAsInt();
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
        this.mListingOptionCharacteristicId = (EtsyId) parcel.readSerializable();
        this.mListingOptionCharacteristicSuggestedValueId = (EtsyId) parcel.readSerializable();
        this.mDisplayValue = parcel.readString();
        this.mValue = parcel.readString();
        this.mPosition = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mListingOptionCharacteristicId);
        parcel.writeSerializable(this.mListingOptionCharacteristicSuggestedValueId);
        parcel.writeString(this.mDisplayValue);
        parcel.writeString(this.mValue);
        parcel.writeInt(this.mPosition);
    }
}
