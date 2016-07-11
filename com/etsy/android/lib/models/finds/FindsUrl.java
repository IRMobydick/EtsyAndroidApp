package com.etsy.android.lib.models.finds;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class FindsUrl extends BaseFieldModel {
    private static final String TYPE_MARKETPLACE = "marketplace";
    private static final String TYPE_QUERY = "query";
    private static final String TYPE_TAXONOMY = "taxonomy";
    protected EtsyId mAnchorListingId;
    protected boolean mHasCategoryLandingPage;
    protected String mMarketplace;
    protected String mMaxPrice;
    protected String mMinPrice;
    protected String mQuery;
    protected TaxonomyNode mTaxonomyNode;

    public FindsUrl() {
        this.mQuery = StringUtils.EMPTY;
        this.mMarketplace = StringUtils.EMPTY;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (str.equals(TYPE_TAXONOMY)) {
            this.mTaxonomyNode = (TaxonomyNode) BaseModel.parseObject(jsonParser, TaxonomyNode.class);
        } else if (str.equals(TYPE_QUERY)) {
            this.mQuery = BaseModel.parseString(jsonParser);
        } else if (str.equals(TYPE_MARKETPLACE)) {
            this.mMarketplace = BaseModel.parseString(jsonParser);
        } else if (str.equals(ResponseConstants.HAS_CATEGORY_LANDING_PAGE)) {
            this.mHasCategoryLandingPage = jsonParser.getBooleanValue();
        } else if (str.equals(ResponseConstants.ANCHOR_LISTING_ID)) {
            this.mAnchorListingId = new EtsyId(BaseModel.parseString(jsonParser));
        } else if (str.equals(PerfDbContentProvider.COL_MAX)) {
            this.mMaxPrice = BaseModel.parseString(jsonParser);
        } else if (!str.equals(PerfDbContentProvider.COL_MIN)) {
            return false;
        } else {
            this.mMinPrice = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    @Nullable
    public TaxonomyNode getTaxonomyNode() {
        return this.mTaxonomyNode;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public String getMarketplace() {
        return this.mMarketplace;
    }

    public boolean hasCategoryLandingPage() {
        return this.mHasCategoryLandingPage;
    }

    public void setAnchorListingId(@Nullable EtsyId etsyId) {
        this.mAnchorListingId = etsyId;
    }

    @Nullable
    public EtsyId getAnchorListingId() {
        return this.mAnchorListingId;
    }

    public String getMaxPrice() {
        return this.mMaxPrice;
    }

    public String getMinPrice() {
        return this.mMinPrice;
    }
}
