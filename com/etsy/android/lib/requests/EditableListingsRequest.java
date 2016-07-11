package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class EditableListingsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String EDITABLE_LISTING_FIELDS = "listing_id,state,category_id,category_path,title,description,price,currency_code,quantity,ending_tsz,last_modified_tsz,tags,materials,shop_section_id,url,shipping_template_id,shipping_profile_id,processing_min,processing_max,who_made,is_supply,when_made,featured_rank,is_digital,non_taxable,in_person_eligible,has_variations,taxonomy_id,should_auto_renew";
    public static final String EDITABLE_LISTING_INCLUDES = "Images(listing_image_id,red,green,blue,url_570xN,rank)";
    private static final long serialVersionUID = 4461679651861714014L;

    public EditableListingsRequest(String str, RequestMethod requestMethod, Class<Result> cls, String str2) {
        super(str, requestMethod, cls, EndpointType.API, str2);
    }

    public static EditableListingsRequest<EditableListing> getAllMyShopListings(String str) {
        return new EditableListingsRequest("/shops/__SELF__/listings", RequestMethod.GET, EditableListing.class, str);
    }

    public static EditableListingsRequest<EditableListing> getMyShopListingsChangelog(String str) {
        return new EditableListingsRequest("/shops/__SELF__/listings/changelog", RequestMethod.GET, EditableListing.class, str);
    }

    public static EditableListingsRequest<EditableListing> getListing(EtsyId etsyId, String str) {
        return new EditableListingsRequest("/listings/" + etsyId.getId(), RequestMethod.GET, EditableListing.class, str);
    }

    public static EditableListingsRequest<EditableListing> updateListing(EtsyId etsyId, String str) {
        return new EditableListingsRequest("/listings/" + etsyId.getId(), RequestMethod.PUT, EditableListing.class, str);
    }

    public static EditableListingsRequest<EditableListing> deleteListing(EtsyId etsyId, String str) {
        return new EditableListingsRequest("/listings/" + etsyId.getId(), RequestMethod.DELETE, EditableListing.class, str);
    }

    public static EditableListingsRequest<EditableListing> createListing(String str) {
        return new EditableListingsRequest("/listings", RequestMethod.POST, EditableListing.class, str);
    }
}
