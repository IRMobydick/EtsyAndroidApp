package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class MarketingBanner extends BaseFieldModel implements ICardViewElement {
    protected String mButtonText;
    protected MarketingBannerImages mMarketingBannerImages;
    protected int mPlaceholderColor;
    protected String mSubtitle;
    protected String mTitle;
    protected String mUrl;

    public MarketingBanner() {
        this.mSubtitle = StringUtils.EMPTY;
        this.mButtonText = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mPlaceholderColor = -1;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.SUBTITLE.equals(str)) {
            this.mSubtitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.BUTTON_TEXT.equals(str)) {
            this.mButtonText = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.URL.equals(str)) {
            this.mUrl = BaseModel.parseString(jsonParser);
        } else if (FindsModule.FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.IMAGES_BY_SIZE.equals(str)) {
            this.mMarketingBannerImages = (MarketingBannerImages) BaseModel.parseObject(jsonParser, MarketingBannerImages.class);
        } else if (!ResponseConstants.PLACEHOLDER_COLOR.equals(str)) {
            return false;
        } else {
            this.mPlaceholderColor = ab.m3174a(BaseModel.parseString(jsonParser));
        }
        return true;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public MarketingBannerImage getMarketingBannerImage(int i, boolean z) {
        if (this.mMarketingBannerImages != null) {
            return this.mMarketingBannerImages.getBannerImageForWidth(i, z);
        }
        return null;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getViewType() {
        return 35;
    }

    public int getPlaceholderColor() {
        return this.mPlaceholderColor;
    }

    MarketingBannerImages getMarketingBannerImages() {
        return this.mMarketingBannerImages;
    }
}
