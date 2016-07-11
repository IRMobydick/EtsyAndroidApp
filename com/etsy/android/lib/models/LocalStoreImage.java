package com.etsy.android.lib.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Pair;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalStoreImage extends BaseModelImage implements Parcelable {
    public static final Creator<LocalStoreImage> CREATOR;
    protected static final Pair<Integer, String>[] IMG_SIZES_ARRAY;
    public static final Pair<Integer, String> IMG_SIZE_203;
    public static final Pair<Integer, String> IMG_SIZE_812;
    public static final Pair<Integer, String> IMG_SIZE_FULL;
    private static final long serialVersionUID = 3334099742284715324L;
    protected String mImageUrlFullxFull;

    /* renamed from: com.etsy.android.lib.models.LocalStoreImage.1 */
    final class C04771 implements Creator<LocalStoreImage> {
        C04771() {
        }

        public /* synthetic */ Object createFromParcel(android.os.Parcel parcel) {
            return m2325a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2326a(i);
        }

        public LocalStoreImage m2325a(android.os.Parcel parcel) {
            return new LocalStoreImage(parcel);
        }

        public LocalStoreImage[] m2326a(int i) {
            return new LocalStoreImage[i];
        }
    }

    static {
        IMG_SIZE_203 = new Pair(Integer.valueOf(203), "203xN");
        IMG_SIZE_812 = new Pair(Integer.valueOf(812), "812xN");
        IMG_SIZE_FULL = new Pair(Integer.valueOf(0), "fullxfull");
        IMG_SIZES_ARRAY = new Pair[]{IMG_SIZE_203, IMG_SIZE_812};
        CREATOR = new C04771();
    }

    public LocalStoreImage() {
        this.mImageUrlFullxFull = StringUtils.EMPTY;
    }

    public LocalStoreImage(android.os.Parcel parcel) {
        this.mImageUrlFullxFull = StringUtils.EMPTY;
        this.mImageUrlFullxFull = parcel.readString();
    }

    public String getImageUrl() {
        return this.mImageUrlFullxFull;
    }

    public String getImageUrlForPixelWidth(int i) {
        CharSequence replaceDimensionForWidth = getReplaceDimensionForWidth(ab.m3172a(i));
        if (bh.m3340a(this.mImageUrlFullxFull)) {
            return this.mImageUrlFullxFull.replace((CharSequence) IMG_SIZE_FULL.second, replaceDimensionForWidth);
        }
        return StringUtils.EMPTY;
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        String str = (String) IMG_SIZE_812.second;
        if (ab.m3172a(i) <= ((Integer) IMG_SIZE_203.first).intValue()) {
            str = (String) IMG_SIZE_203.second;
        }
        return replaceImageUrlWithSize(str);
    }

    protected String replaceImageUrlWithSize(String str) {
        if (bh.m3340a(this.mImageUrlFullxFull)) {
            return this.mImageUrlFullxFull.replace((CharSequence) IMG_SIZE_FULL.second, str);
        }
        return null;
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARRAY;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_812.second;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.URL_FULLxFULL.equals(currentName)) {
                    this.mImageUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(this.mImageUrlFullxFull);
    }
}
