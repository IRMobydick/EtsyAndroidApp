package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class MarketingBannerImages extends BaseFieldModel {
    static final int OVERSIZE_THRESHOLD_PX = 250;
    static final int UNDERSIZE_THRESHOLD_PX = 50;
    List<MarketingBannerImage> mLandscapeImages;
    List<MarketingBannerImage> mPortraitImages;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.XS.equals(str)) {
            this.mPortraitImages = BaseModel.parseArray(jsonParser, MarketingBannerImage.class);
        } else if (ResponseConstants.SM.equals(str)) {
            this.mLandscapeImages = BaseModel.parseArray(jsonParser, MarketingBannerImage.class);
        }
        return false;
    }

    public MarketingBannerImage getBannerImageForWidth(int i, boolean z) {
        int a = ab.m3172a(i);
        List<MarketingBannerImage> list = z ? this.mLandscapeImages : this.mPortraitImages;
        if (list == null || list.size() <= 0) {
            return null;
        }
        MarketingBannerImage marketingBannerImage = (MarketingBannerImage) list.get(0);
        int i2 = Integer.MAX_VALUE;
        MarketingBannerImage marketingBannerImage2 = marketingBannerImage;
        for (MarketingBannerImage marketingBannerImage3 : list) {
            int i3;
            int fullWidth = marketingBannerImage3.getFullWidth();
            int abs = Math.abs(fullWidth - a);
            if (a > fullWidth) {
                if (abs < UNDERSIZE_THRESHOLD_PX) {
                    return marketingBannerImage3;
                }
                i3 = abs;
            } else if (a == fullWidth) {
                return marketingBannerImage3;
            } else {
                if (a >= fullWidth || abs >= OVERSIZE_THRESHOLD_PX) {
                    marketingBannerImage3 = marketingBannerImage2;
                    i3 = i2;
                } else if (i2 <= abs) {
                    return marketingBannerImage2;
                } else {
                    i3 = abs;
                }
            }
            i2 = i3;
            marketingBannerImage2 = marketingBannerImage3;
        }
        return marketingBannerImage2;
    }
}
