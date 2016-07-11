package com.etsy.android.lib.models.apiv3;

import android.util.Pair;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class UserAvatar extends BaseModelImage {
    protected static final Pair<Integer, String>[] IMG_SIZES_ARR;
    public static final Pair<Integer, String> IMG_SIZE_400;
    public static final Pair<Integer, String> IMG_SIZE_75;
    private static final long serialVersionUID = -3839220856434950431L;
    protected String mUrl400x400;

    static {
        IMG_SIZE_75 = new Pair(Integer.valueOf(75), "75x75");
        IMG_SIZE_400 = new Pair(Integer.valueOf(400), "400x400");
        IMG_SIZES_ARR = new Pair[]{IMG_SIZE_75, IMG_SIZE_400};
    }

    public UserAvatar() {
        this.mUrl400x400 = StringUtils.EMPTY;
    }

    public UserAvatar(String str) {
        this.mUrl400x400 = StringUtils.EMPTY;
        this.mUrl75x75 = str;
        if (str != null) {
            this.mUrl400x400 = this.mUrl75x75.replace((CharSequence) IMG_SIZE_75.second, (CharSequence) IMG_SIZE_400.second);
        }
    }

    public void parseData(JsonParser jsonParser) {
    }

    public String getImageUrl() {
        return getImageUrlForPixelWidth(((Integer) IMG_SIZE_75.first).intValue());
    }

    public String getImageUrlForPixelWidth(int i) {
        if (ab.m3172a(i) <= ((Integer) IMG_SIZE_75.first).intValue()) {
            return this.mUrl75x75;
        }
        return this.mUrl400x400;
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARR;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_400.second;
    }

    protected String getReplaceDimensionForWidth(int i) {
        return super.getReplaceDimensionForWidth(i);
    }
}
