package com.etsy.android.lib.models.apiv3.editable;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShippingTemplate;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.editable.EditableListing.TaxonomyParseState;
import com.fasterxml.jackson.core.JsonParser;
import java.util.List;

public class ShopListingForm extends EditableListingV3 {
    public static final String FIELD_FULL_PATH_TAXONOMY_IDS = "full_path_taxonomy_ids";
    public static final String FIELD_FULL_PATH_TAXONOMY_NAMES = "full_path_taxonomy_names";
    public static final String FIELD_SOURCE_SHIPPING_PROFILE_ID = "source_shipping_profile_id";
    private ShippingTemplate mShippingTemplate;

    public void parseData(JsonParser jsonParser) {
        super.parseData(jsonParser);
        if (this.mShippingTemplate != null) {
            setShippingProfileId(this.mShippingTemplate.getId().toString());
        }
    }

    protected void parseEditableListingField(JsonParser jsonParser, TaxonomyParseState taxonomyParseState, String str) {
        if (!ActivityFeedEntity.LISTING.equals(str)) {
            if (ResponseConstants.SHIPPING.equals(str)) {
                this.mShippingTemplate = (ShippingTemplate) BaseModel.parseObject(jsonParser, ShippingTemplate.class);
            } else if (FIELD_FULL_PATH_TAXONOMY_NAMES.equals(str)) {
                List parseRawStringArray = BaseModel.parseRawStringArray(jsonParser);
                taxonomyParseState.setTaxonomyPathList(parseRawStringArray);
                taxonomyParseState.setCategoryPathList(parseRawStringArray);
            } else if (FIELD_FULL_PATH_TAXONOMY_IDS.equals(str)) {
                taxonomyParseState.setTaxonomyPathIdList(BaseModel.parseIntArray(jsonParser));
            } else if (FIELD_SOURCE_SHIPPING_PROFILE_ID.equals(str)) {
                setShippingTemplateId(BaseModel.parseStringIdOrNumericValue(jsonParser));
            } else {
                super.parseEditableListingField(jsonParser, taxonomyParseState, str);
            }
        }
    }
}
