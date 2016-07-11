package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class FindsHeroBannerImage extends BannerImage {
    private static final int W1024 = 1024;
    private static final int W1242 = 1242;
    private static final int W1536 = 1536;
    private static final int W640 = 640;
    private static final int W750 = 750;
    private static final int W768 = 768;

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(ResponseConstants.BANNER_URL_PREFIX_768x)) {
            this.mImageUrls.put(Integer.valueOf(W768), BaseModel.parseStringURL(jsonParser));
            this.mImageUrlsLandscape.put(Integer.valueOf(W768), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (str.startsWith(ResponseConstants.BANNER_URL_PREFIX_1024x)) {
            this.mImageUrls.put(Integer.valueOf(W1024), BaseModel.parseStringURL(jsonParser));
            this.mImageUrlsLandscape.put(Integer.valueOf(W1024), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (!str.startsWith(ResponseConstants.BANNER_URL_PREFIX_1536x)) {
            return false;
        } else {
            this.mImageUrls.put(Integer.valueOf(W1536), BaseModel.parseStringURL(jsonParser));
            this.mImageUrlsLandscape.put(Integer.valueOf(W1536), BaseModel.parseStringURL(jsonParser));
            return true;
        }
    }
}
