package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.options.OptionProperty;
import com.etsy.android.lib.models.options.OptionPropertyCharacteristic;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListingOption extends BaseModel implements Parcelable, Comparable<ListingOption> {
    public static final Creator<ListingOption> CREATOR;
    private static final String TAG;
    private List<OptionPropertyCharacteristic> mCharacteristics;
    private String mDisplayValue;
    private boolean mIsUnlimitedQuantity;
    private EtsyId mListingOptionId;
    private String mOptionValue;
    private List<ListingOption> mOptions;
    private int mPosition;
    private EtsyMoney mPrice;
    private OptionProperty mProperty;
    private int mQuantity;
    private int mShipDate;
    private String mShipDateDisplay;

    /* renamed from: com.etsy.android.lib.models.ListingOption.1 */
    final class C04751 implements Creator<ListingOption> {
        C04751() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2321a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2322a(i);
        }

        public ListingOption m2321a(Parcel parcel) {
            return new ListingOption(parcel);
        }

        public ListingOption[] m2322a(int i) {
            return new ListingOption[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ListingOption.class);
        CREATOR = new C04751();
    }

    public ListingOption() {
        this.mListingOptionId = new EtsyId();
        this.mCharacteristics = new ArrayList();
        this.mOptions = new ArrayList();
        this.mPrice = new EtsyMoney();
    }

    public ListingOption(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public EtsyId getListingOptionId() {
        return this.mListingOptionId;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public String getOptionValue() {
        return this.mOptionValue;
    }

    public String getShipDateDisplay() {
        return this.mShipDateDisplay;
    }

    public int getShipDate() {
        return this.mShipDate;
    }

    public EtsyMoney getPrice() {
        return this.mPrice;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public Boolean getIsUnlimitedQuantity() {
        return Boolean.valueOf(this.mIsUnlimitedQuantity);
    }

    public int getPosition() {
        return this.mPosition;
    }

    public OptionProperty getProperty() {
        return this.mProperty;
    }

    public List<OptionPropertyCharacteristic> getCharacteristics() {
        return this.mCharacteristics;
    }

    public List<ListingOption> getOptions() {
        return this.mOptions;
    }

    public boolean isSoldOut() {
        return this.mQuantity <= 0;
    }

    public boolean isPurchasable() {
        return this.mOptions == null || this.mOptions.size() == 0;
    }

    public String getFormattedDisplayValue() {
        Iterable arrayList = new ArrayList();
        if (getCharacteristics() != null) {
            for (OptionPropertyCharacteristic displayValue : getCharacteristics()) {
                arrayList.add(displayValue.getDisplayValue());
            }
        }
        return String.format("%s %s", new Object[]{getDisplayValue(), TextUtils.join(", ", arrayList)});
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("listing_option_id".equals(currentName)) {
                    this.mListingOptionId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.DISPLAY_VALUE.equals(currentName)) {
                    this.mDisplayValue = BaseModel.parseString(jsonParser);
                } else if ("option_value".equals(currentName)) {
                    this.mOptionValue = BaseModel.parseString(jsonParser);
                } else if ("ship_date_display".equals(currentName)) {
                    this.mShipDateDisplay = BaseModel.parseString(jsonParser);
                } else if ("ship_date".equals(currentName)) {
                    this.mShipDate = jsonParser.getValueAsInt();
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mPrice = this.mPrice.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.PRICE.equals(currentName)) {
                    this.mPrice = this.mPrice.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.QUANTITY.equals(currentName)) {
                    this.mQuantity = jsonParser.getValueAsInt();
                } else if ("is_unlimited_quantity".equals(currentName)) {
                    this.mIsUnlimitedQuantity = jsonParser.getValueAsBoolean();
                } else if ("position".equals(currentName)) {
                    this.mPosition = jsonParser.getValueAsInt();
                } else if (ResponseConstants.OPTIONS.equals(currentName)) {
                    this.mOptions = BaseModel.parseArray(jsonParser, ListingOption.class);
                } else if ("property".equals(currentName)) {
                    this.mProperty = (OptionProperty) BaseModel.parseObject(jsonParser, OptionProperty.class);
                } else if ("characteristics".equals(currentName)) {
                    this.mCharacteristics = BaseModel.parseArray(jsonParser, OptionPropertyCharacteristic.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int compareTo(ListingOption listingOption) {
        if (getListingOptionId().equals(listingOption.getListingOptionId())) {
            return 0;
        }
        if (getPosition() > listingOption.getPosition()) {
            return -1;
        }
        return 1;
    }

    public int describeContents() {
        return hashCode();
    }

    private void readFromParcel(Parcel parcel) {
        this.mListingOptionId = (EtsyId) parcel.readSerializable();
        this.mDisplayValue = parcel.readString();
        this.mOptionValue = parcel.readString();
        this.mShipDateDisplay = parcel.readString();
        this.mShipDate = parcel.readInt();
        this.mPrice = this.mPrice.withCurrency(parcel.readString());
        this.mPrice = this.mPrice.withAmount(parcel.readString());
        this.mQuantity = parcel.readInt();
        this.mIsUnlimitedQuantity = parcel.readInt() == 1;
        this.mPosition = parcel.readInt();
        this.mProperty = (OptionProperty) parcel.readParcelable(OptionProperty.class.getClassLoader());
        parcel.readTypedList(this.mCharacteristics, OptionPropertyCharacteristic.CREATOR);
        parcel.readTypedList(this.mOptions, CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mListingOptionId);
        parcel.writeString(this.mDisplayValue);
        parcel.writeString(this.mOptionValue);
        parcel.writeString(this.mShipDateDisplay);
        parcel.writeInt(this.mShipDate);
        parcel.writeString(this.mPrice.getCurrency().getCurrencyCode());
        parcel.writeString(this.mPrice.getAmount().toString());
        parcel.writeInt(this.mQuantity);
        parcel.writeInt(this.mIsUnlimitedQuantity ? 1 : 0);
        parcel.writeInt(this.mPosition);
        parcel.writeParcelable(this.mProperty, i);
        parcel.writeTypedList(this.mCharacteristics);
        parcel.writeTypedList(this.mOptions);
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LISTING_OPTION_ID, this.mListingOptionId.getId());
        return hashMap;
    }
}
