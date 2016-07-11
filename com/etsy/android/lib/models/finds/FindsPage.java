package com.etsy.android.lib.models.finds;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BannerImage;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.FindsHeroBannerImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.cart.SavedCartItemsFragment;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class FindsPage extends FindsCard {
    @Nullable
    protected BannerImage mBannerImage;
    @NonNull
    protected EtsyId mFindsPagePublishedId;
    @NonNull
    protected List<ListingCard> mHeroListings;
    @NonNull
    protected List<FindsModule> mModules;
    @NonNull
    protected String mSubtitle;

    public FindsPage() {
        this.mFindsPagePublishedId = new EtsyId();
        this.mSubtitle = StringUtils.EMPTY;
        this.mModules = new ArrayList();
        this.mHeroListings = new ArrayList();
    }

    public void parseData(JsonParser jsonParser) {
        super.parseData(jsonParser);
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.SUBTITLE.equals(str)) {
            this.mSubtitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.MODULES.equals(str)) {
            List<FindsModule> parseArray = BaseModel.parseArray(jsonParser, FindsModule.class);
            this.mModules.clear();
            if (parseArray != null) {
                for (FindsModule findsModule : parseArray) {
                    if (!(findsModule.getType() == null || findsModule.getType().isEmpty())) {
                        this.mModules.add(findsModule.getTyped());
                    }
                }
            }
        } else if (SavedCartItemsFragment.PAGE.equals(str)) {
            super.parseData(jsonParser);
        } else if (ResponseConstants.HERO_LISTINGS.equals(str)) {
            this.mHeroListings = BaseModel.parseArray(jsonParser, ListingCard.class);
        } else if (ResponseConstants.HEADER_IMAGES.equals(str)) {
            this.mBannerImage = (BannerImage) BaseModel.parseObject(jsonParser, FindsHeroBannerImage.class);
        } else if (!ResponseConstants.FINDS_PAGE_PUBLISHED_ID.equals(str)) {
            return super.parseField(jsonParser, str);
        } else {
            this.mFindsPagePublishedId.setId(BaseModel.parseString(jsonParser));
        }
        return true;
    }

    public List<FindsModule> getModules() {
        return this.mModules;
    }

    public String getAnalyticsName() {
        return "finds_page";
    }

    public HashMap<String, Object> getAnalyticsAttributes() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(ResponseConstants.SLUG, this.mSlug);
        hashMap.put(ResponseConstants.FINDS_PAGE_ID, this.mFindsPageId);
        return hashMap;
    }

    @NonNull
    public List<ListingCard> getHeroListings() {
        return this.mHeroListings;
    }

    @Nullable
    public BannerImage getBannerImage() {
        return this.mBannerImage;
    }

    @NonNull
    public EtsyId getFindsPagePublishedId() {
        return this.mFindsPagePublishedId;
    }

    @NonNull
    public String getSubtitle() {
        return this.mSubtitle;
    }
}
