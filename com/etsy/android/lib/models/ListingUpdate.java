package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.updates.ListingUpdateImage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Date;

public class ListingUpdate extends BaseModel implements Parcelable {
    public static final Creator<ListingUpdate> CREATOR;
    private static final String TAG;
    private Date mCreateDate;
    private String mFormattedCreateDate;
    private EtsyId mListingUdpateId;
    private ListingUpdateImage mListingUpdateImage;
    private String mMediaType;
    private String mUpdateText;

    /* renamed from: com.etsy.android.lib.models.ListingUpdate.1 */
    final class C04761 implements Creator<ListingUpdate> {
        C04761() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2323a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2324a(i);
        }

        public ListingUpdate m2323a(Parcel parcel) {
            return new ListingUpdate(parcel);
        }

        public ListingUpdate[] m2324a(int i) {
            return new ListingUpdate[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ListingUpdate.class);
        CREATOR = new C04761();
    }

    public ListingUpdate() {
        this.mListingUdpateId = new EtsyId();
    }

    public ListingUpdate(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public EtsyId getListingUdpateId() {
        return this.mListingUdpateId;
    }

    public String getUpdateText() {
        return this.mUpdateText;
    }

    public Date getCreateDate() {
        return this.mCreateDate;
    }

    public String getFormattedCreateDate() {
        return this.mFormattedCreateDate;
    }

    public String getMediaType() {
        return this.mMediaType;
    }

    public ListingUpdateImage getListingUpdateImage() {
        return this.mListingUpdateImage;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("listing_update_id".equals(currentName)) {
                    this.mListingUdpateId.setId(BaseModel.parseString(jsonParser));
                } else if ("update_text".equals(currentName)) {
                    this.mUpdateText = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = new Date(jsonParser.getValueAsLong() * 1000);
                } else if (ResponseConstants.MEDIA_TYPE.equals(currentName)) {
                    this.mMediaType = BaseModel.parseString(jsonParser);
                } else if ("formatted_create_date".equals(currentName)) {
                    this.mFormattedCreateDate = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    this.mListingUpdateImage = (ListingUpdateImage) BaseModel.parseObject(jsonParser, ListingUpdateImage.class);
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
        this.mListingUdpateId = (EtsyId) parcel.readSerializable();
        this.mUpdateText = parcel.readString();
        this.mCreateDate = new Date(parcel.readLong());
        this.mFormattedCreateDate = parcel.readString();
        this.mMediaType = parcel.readString();
        this.mListingUpdateImage = (ListingUpdateImage) parcel.readParcelable(ListingUpdateImage.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.mListingUdpateId);
        parcel.writeString(this.mUpdateText);
        parcel.writeLong(this.mCreateDate != null ? this.mCreateDate.getTime() : 0);
        parcel.writeString(this.mFormattedCreateDate);
        parcel.writeString(this.mMediaType);
        parcel.writeParcelable(this.mListingUpdateImage, i);
    }
}
