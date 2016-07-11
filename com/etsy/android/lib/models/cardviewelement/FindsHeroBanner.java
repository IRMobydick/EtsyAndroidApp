package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.lib.models.BannerImage;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.FindsHeroBannerImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class FindsHeroBanner extends FindsCard {
    protected BannerImage mBannerImage;
    protected String mButtonText;
    protected String mSubtitle;

    public FindsHeroBanner() {
        this.mViewType = 29;
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (FindsModule.FIELD_IMAGES.equals(str)) {
            this.mBannerImage = (BannerImage) BaseModel.parseObject(jsonParser, FindsHeroBannerImage.class);
        } else if (ResponseConstants.SUB_TITLE.equals(str)) {
            this.mSubtitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.BUTTON_TEXT.equals(str)) {
            this.mButtonText = BaseModel.parseString(jsonParser);
        }
        return super.parseField(jsonParser, str);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getImageUrl() {
        if (this.mBannerImage != null) {
            return this.mBannerImage.getImageUrl();
        }
        return null;
    }

    public void setBannerImage(BannerImage bannerImage) {
        this.mBannerImage = bannerImage;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public BannerImage getBannerImage(int i, boolean z) {
        return this.mBannerImage.withDisplayWidth(i, z);
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }
}
