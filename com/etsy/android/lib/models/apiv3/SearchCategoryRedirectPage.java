package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class SearchCategoryRedirectPage extends BaseFieldModel {
    public static final String PARAM_QUERY = "q";
    private final String KEY_FILTER_PARAMS;
    private final String KEY_LANDING_PAGE;
    private final String KEY_NODE;
    private final String KEY_SEARCH_RESULTS;
    protected Page mCategoryLandingPage;
    protected EtsyArray mFilterParams;
    protected SearchWithAds mSearchWithAds;
    protected TaxonomyNode mTaxonomyNode;

    public SearchCategoryRedirectPage() {
        this.KEY_LANDING_PAGE = ResponseConstants.LANDING_PAGE;
        this.KEY_SEARCH_RESULTS = "search_results";
        this.KEY_NODE = "node";
        this.KEY_FILTER_PARAMS = "filter_parameters";
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.LANDING_PAGE.equals(str)) {
            this.mCategoryLandingPage = (Page) BaseModel.parseObject(jsonParser, Page.class);
            return true;
        } else if ("search_results".equals(str)) {
            this.mSearchWithAds = (SearchWithAds) BaseModel.parseObject(jsonParser, SearchWithAds.class);
            return true;
        } else if ("node".equals(str)) {
            this.mTaxonomyNode = (TaxonomyNode) BaseModel.parseObject(jsonParser, TaxonomyNode.class);
            return true;
        } else if (!"filter_parameters".equals(str)) {
            return false;
        } else {
            this.mFilterParams = (EtsyArray) BaseModel.parseObject(jsonParser, EtsyArray.class);
            return true;
        }
    }

    public Page getCategoryLandingPage() {
        return this.mCategoryLandingPage;
    }

    public SearchWithAds getSearchResults() {
        return this.mSearchWithAds;
    }

    public boolean isCategoryLandingPage() {
        return this.mCategoryLandingPage != null;
    }

    public TaxonomyNode getTaxonomyNode() {
        return this.mTaxonomyNode;
    }

    public String getFilterParam(String str) {
        if (this.mFilterParams == null) {
            return StringUtils.EMPTY;
        }
        return this.mFilterParams.getData().optString(str, StringUtils.EMPTY);
    }
}
