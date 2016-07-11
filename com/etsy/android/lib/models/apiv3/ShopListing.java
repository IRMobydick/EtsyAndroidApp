package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.editable.EditableListing.TaxonomyParseState;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.uikit.ui.shop.BaseShopHomeFragment;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ShopListing extends EditableListing {
    private static final long serialVersionUID = 5348433468850149081L;
    private boolean mInPersonEligible;

    public boolean isInPersonEligible() {
        return this.mInPersonEligible;
    }

    public void parseData(JsonParser jsonParser) {
        TaxonomyParseState taxonomyParseState = new TaxonomyParseState();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreationDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.UPDATE_DATE.equals(currentName)) {
                    this.mModifiedTsz = jsonParser.getValueAsLong();
                } else if ("category_tags".equals(currentName)) {
                    taxonomyParseState.setCategoryPathList(BaseModel.parseRawStringArray(jsonParser));
                } else if (BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID.equals(currentName)) {
                    this.mShopSectionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (EtsyRequest.PARAM_CURRENCY.equals(currentName)) {
                    this.mPrice.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.IN_PERSON_ELIGIBLE.equals(currentName)) {
                    this.mInPersonEligible = jsonParser.getValueAsBoolean();
                } else if (!ResponseConstants.STATE.equals(currentName)) {
                    if (ResponseConstants.LEGACY_STATE.equals(currentName)) {
                        this.mState = BaseModel.parseStringURL(jsonParser);
                    } else {
                        parseEditableListingField(jsonParser, taxonomyParseState, currentName);
                    }
                }
            }
        }
        taxonomyParseState.updateListing(this);
    }
}
