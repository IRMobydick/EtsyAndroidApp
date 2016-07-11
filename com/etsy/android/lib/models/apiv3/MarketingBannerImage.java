package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class MarketingBannerImage extends BaseFieldModel implements IFullImage {
    protected int mHeight;
    protected int mImageColor;
    protected String mUrl;
    protected int mWidth;

    public int getImageColor() {
        return 0;
    }

    public int getFullHeight() {
        return this.mHeight;
    }

    public int getFullWidth() {
        return this.mWidth;
    }

    public String get4to3ImageUrlForPixelWidth(int i) {
        return this.mUrl;
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        return this.mUrl;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.WIDTH.equals(str)) {
            this.mWidth = jsonParser.getIntValue();
        } else if (ResponseConstants.HEIGHT.equals(str)) {
            this.mHeight = jsonParser.getIntValue();
        } else if (!ResponseConstants.URL.equals(str)) {
            return false;
        } else {
            this.mUrl = BaseModel.parseStringURL(jsonParser);
        }
        return true;
    }
}
