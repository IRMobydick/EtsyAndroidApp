package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ListingsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String LISTING_CARD_FIELDS = "state,listing_id,is_favorite,in_collections,price,currency_code,converted_price,converted_currency_code,title,is_fund_on_etsy_campaign,url";
    public static final String LISTING_CARD_INCLUDES = "Images(url_170x135,full_width,full_height,red,green,blue):1,Shop(shop_name),FundOnEtsyCampaign";
    private static final long serialVersionUID = 4461679651861714014L;

    public ListingsRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static ListingsRequest<Listing> getListing(EtsyId etsyId) {
        return new ListingsRequest("/listings/" + etsyId.getId(), RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> updateListing(EtsyId etsyId) {
        return new ListingsRequest("/listings/" + etsyId.getId(), RequestMethod.PUT, Listing.class);
    }

    public static ListingsRequest<Variation> getVariations(String str) {
        return new ListingsRequest("/listings/" + str + "/variations", RequestMethod.GET, Variation.class);
    }

    public static ListingsRequest<Listing> findAllListingActive() {
        return new ListingsRequest("/listings/active", RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> findTrendingListings() {
        return new ListingsRequest("/listings/trending", RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> findBrowseSegmentListings(String str) {
        return new ListingsRequest(String.format("/segments/%s/listings", new Object[]{str}), RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> findAllCollectionListings(String str) {
        return new ListingsRequest("/collections/" + str + "/full_listings", RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> findAllShopListingsActive(EtsyId etsyId) {
        return new ListingsRequest("/shops/" + etsyId.getId() + "/listings/active", RequestMethod.GET, Listing.class);
    }

    public static ListingsRequest<Listing> findAllShopSectionListingsActive(EtsyId etsyId, EtsyId etsyId2) {
        return new ListingsRequest("/shops/" + etsyId.getId() + "/sections/" + etsyId2.getId() + "/listings/active", RequestMethod.GET, Listing.class);
    }
}
