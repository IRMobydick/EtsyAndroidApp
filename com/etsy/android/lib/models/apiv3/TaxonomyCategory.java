package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.cardviewelement.SearchPageLink;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class TaxonomyCategory extends SearchGroup {
    public static final String LINK_TYPE_CATEGORY = "category_page";
    public static final String LINK_TYPE_ESEARCH = "esearch_page";
    protected SearchPageLink mPageLink;
    protected String mPath;
    protected EtsyId mTaxonomyId;
    protected int mViewType;

    public TaxonomyCategory() {
        this.mPath = StringUtils.EMPTY;
        this.mViewType = 15;
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.PAGE_LINK.equals(str)) {
            this.mPageLink = (SearchPageLink) BaseModel.parseObject(jsonParser, SearchPageLink.class);
        } else if (ResponseConstants.PATH.equals(str)) {
            this.mPath = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.TAXONOMY_ID.equals(str)) {
            this.mTaxonomyId = new EtsyId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        }
        return super.parseField(jsonParser, str);
    }

    public SearchPageLink getPageLink() {
        return this.mPageLink;
    }

    public String getPath() {
        return this.mPath;
    }

    @Nullable
    public TaxonomyNode buildTaxonomyNode() {
        String etsyId = this.mTaxonomyId != null ? this.mTaxonomyId.toString() : (this.mPageLink == null || this.mPageLink.getTaxonomyId() == null) ? null : this.mPageLink.getTaxonomyId().toString();
        if (TextUtils.isEmpty(etsyId)) {
            return null;
        }
        return new TaxonomyNode(etsyId, this.mName, this.mName);
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }
}
