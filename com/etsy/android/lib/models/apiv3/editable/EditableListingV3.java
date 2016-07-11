package com.etsy.android.lib.models.apiv3.editable;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.editable.EditableListing.TaxonomyParseState;
import com.fasterxml.jackson.core.JsonParser;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EditableListingV3 extends EditableListing {
    public static final String CATEGORY_ID_FOR_EVERYTHING_ELSE = "68887416";
    public static final int LISTING_OPERATION_ACTIVATE = 6;
    public static final int LISTING_OPERATION_ACTIVATE_SAVE = 7;
    public static final int LISTING_OPERATION_COPY = 11;
    public static final int LISTING_OPERATION_CREATE = 3;
    public static final int LISTING_OPERATION_CREATE_DRAFT = 4;
    public static final int LISTING_OPERATION_DEACTIVATE = 8;
    public static final int LISTING_OPERATION_DELETE = 5;
    public static final int LISTING_OPERATION_RENEW = 9;
    public static final int LISTING_OPERATION_RENEW_SAVE = 10;
    public static final int LISTING_OPERATION_SIMPLE_FETCH = 1;
    public static final int LISTING_OPERATION_UPDATE = 2;
    public static final String LISTING_STATE_ACTIVE = "active";
    public static final String LISTING_STATE_INACTIVE = "inactive";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ListingOperation {
    }

    protected void parseEditableListingField(JsonParser jsonParser, TaxonomyParseState taxonomyParseState, String str) {
        if (ResponseConstants.UPDATE_DATE.equals(str)) {
            this.mModifiedTsz = jsonParser.getValueAsLong();
        } else if (ResponseConstants.ENDING_DATE.equals(str)) {
            setExpirationDate(jsonParser.getValueAsLong() * 1000);
        } else if (!ResponseConstants.STATE.equals(str)) {
            if (ResponseConstants.LEGACY_STATE.equals(str)) {
                this.mState = BaseModel.parseString(jsonParser);
            } else if (EditableListing.FIELD_FEATURED_RANK.equals(str)) {
                setFeaturedRank(jsonParser.getValueAsInt() + LISTING_OPERATION_SIMPLE_FETCH);
            } else if (EditableListing.FIELD_SHIPPING_PROFILE_ID.equals(str)) {
                setShippingTemplateId(BaseModel.parseStringIdOrNumericValue(jsonParser));
            } else {
                super.parseEditableListingField(jsonParser, taxonomyParseState, str);
            }
        }
    }
}
