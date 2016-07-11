package com.etsy.android.lib.models.updates;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ListingUpdateImage extends BaseModel implements Parcelable {
    public static final Creator<ListingUpdateImage> CREATOR;
    private static final String TAG;
    private String mUrl340x270;
    private String mUrl570xN;
    private String mUrlFullxFull;

    /* renamed from: com.etsy.android.lib.models.updates.ListingUpdateImage.1 */
    final class C04911 implements Creator<ListingUpdateImage> {
        C04911() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2966a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2967a(i);
        }

        public ListingUpdateImage m2966a(Parcel parcel) {
            return new ListingUpdateImage(parcel);
        }

        public ListingUpdateImage[] m2967a(int i) {
            return new ListingUpdateImage[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ListingUpdateImage.class);
        CREATOR = new C04911();
    }

    public ListingUpdateImage(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public String getUrl570xN() {
        return this.mUrl570xN;
    }

    public String getUrl340x270() {
        return this.mUrl340x270;
    }

    public String getUrlFullxFull() {
        return this.mUrlFullxFull;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.URL_FULLxFULL.equals(currentName)) {
                    this.mUrlFullxFull = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.URL_570xN.equals(currentName)) {
                    this.mUrl570xN = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.URL_340x270.equals(currentName)) {
                    this.mUrl340x270 = BaseModel.parseString(jsonParser);
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
        this.mUrl570xN = parcel.readString();
        this.mUrl340x270 = parcel.readString();
        this.mUrlFullxFull = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl570xN);
        parcel.writeString(this.mUrl340x270);
        parcel.writeString(this.mUrlFullxFull);
    }
}
