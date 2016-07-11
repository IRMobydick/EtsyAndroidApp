package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import java.util.SortedMap;

public class GiftCardBannerImage extends BannerImage {
    private static final int W1166 = 1166;
    private static final int W478 = 478;
    private static final int W574 = 574;
    private static final int W956 = 956;
    private static final int W996 = 996;

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.BANNER_GIFT_CARD_URL_478x.equals(str)) {
            this.mImageUrls.put(Integer.valueOf(W478), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (ResponseConstants.BANNER_GIFT_CARD_URL_574x.equals(str)) {
            this.mImageUrls.put(Integer.valueOf(W574), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (ResponseConstants.BANNER_GIFT_CARD_URL_956x.equals(str)) {
            this.mImageUrls.put(Integer.valueOf(W956), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (ResponseConstants.BANNER_GIFT_CARD_URL_996x.equals(str)) {
            this.mImageUrls.put(Integer.valueOf(W996), BaseModel.parseStringURL(jsonParser));
            return true;
        } else if (!ResponseConstants.BANNER_GIFT_CARD_URL_1166x.equals(str)) {
            return false;
        } else {
            this.mImageUrls.put(Integer.valueOf(W1166), BaseModel.parseStringURL(jsonParser));
            return true;
        }
    }

    protected SortedMap<Integer, String> getImageUrls(boolean z) {
        return this.mImageUrls;
    }
}
