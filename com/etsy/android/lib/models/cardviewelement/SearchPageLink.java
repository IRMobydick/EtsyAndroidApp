package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class SearchPageLink extends PageLink {
    protected boolean mIsCategoryPage;
    protected EtsyId mTaxonomyId;

    public SearchPageLink() {
        this.mTaxonomyId = new EtsyId();
        this.mIsCategoryPage = false;
    }

    public EtsyId getTaxonomyId() {
        return this.mTaxonomyId;
    }

    public boolean isCategoryPage() {
        return this.mIsCategoryPage;
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.TAXONOMY_ID.equals(str)) {
            this.mTaxonomyId.setId(String.valueOf(jsonParser.getValueAsLong()));
        } else if (ResponseConstants.PAGE_TYPE.equals(str)) {
            this.mIsCategoryPage = TaxonomyCategory.LINK_TYPE_CATEGORY.equals(BaseModel.parseString(jsonParser));
        }
        return super.parseField(jsonParser, str);
    }
}
