package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.Manufacturer;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.bf;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShopHomePage extends BaseModel {
    private static final long serialVersionUID = 1739371541692528723L;
    protected FAQs mFaqs;
    protected List<ListingCard> mFeaturedListings;
    protected List<LocalMarket> mLocalMarkets;
    protected List<Manufacturer> mManufacturers;
    @Nullable
    protected SellerDetails mSellerDetails;
    protected ShopV3 mShop;
    @Nullable
    protected ShopAbout mShopAbout;
    protected List<ListingCard> mShopListings;
    @Nullable
    protected ShopPolicy mShopPolicy;
    @Nullable
    protected ShopReviewsResult mShopReviews;
    protected List<ShopSection> mShopSections;
    @Nullable
    protected StructuredShopPolicies mStructuredShopPolicies;

    public ShopHomePage() {
        this.mFeaturedListings = new ArrayList(0);
        this.mShopSections = new ArrayList(0);
        this.mShopListings = new ArrayList(0);
        this.mManufacturers = new ArrayList(0);
        this.mLocalMarkets = new ArrayList(0);
        this.mFaqs = new FAQs();
    }

    public ShopV3 getShop() {
        return this.mShop;
    }

    public List<ShopSection> getShopSections() {
        return this.mShopSections;
    }

    public List<ListingCard> getFeaturedListings() {
        return this.mFeaturedListings;
    }

    public List<ListingCard> getShopListings() {
        return this.mShopListings;
    }

    @Nullable
    public ShopReviewsResult getShopReviews() {
        return this.mShopReviews;
    }

    @Nullable
    public ShopAbout getShopAbout() {
        return this.mShopAbout;
    }

    public List<Manufacturer> getManufacturers() {
        return this.mManufacturers;
    }

    public List<LocalMarket> getLocalMarkets() {
        return this.mLocalMarkets;
    }

    @Nullable
    public ShopPolicy getShopPolicy() {
        return this.mShopPolicy;
    }

    @Nullable
    public StructuredShopPolicies getStructuredShopPolicies() {
        return this.mStructuredShopPolicies;
    }

    @NonNull
    public FAQs getFAQs() {
        return this.mFaqs;
    }

    @Nullable
    public SellerDetails getSellerDetails() {
        return this.mSellerDetails;
    }

    public void setShopListings(List<ListingCard> list) {
        this.mShopListings = list;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1101458968:
                        if (currentName.equals(ResponseConstants.LISTING_CARDS)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -920234302:
                        if (currentName.equals(ResponseConstants.MANUFACTURERS)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -808922876:
                        if (currentName.equals(ResponseConstants.SHOP_ABOUT)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -258213502:
                        if (currentName.equals(ResponseConstants.SELLER_DETAILS)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case 101142:
                        if (currentName.equals(ResponseConstants.FAQ)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case 3529462:
                        if (currentName.equals(ActivityFeedEntity.SHOP)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 546894160:
                        if (currentName.equals(ResponseConstants.POLICIES)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 947936814:
                        if (currentName.equals(FindsModule.FIELD_SECTIONS)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 1099953179:
                        if (currentName.equals(ResponseConstants.REVIEWS)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 1362332798:
                        if (currentName.equals(ResponseConstants.STRUCTURED_POLICIES)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case 1694949091:
                        if (currentName.equals(ResponseConstants.LOCAL_MARKETS)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 1986639712:
                        if (currentName.equals(ResponseConstants.FEATURED_LISTINGS)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mShop = (ShopV3) BaseModel.parseObject(jsonParser, ShopV3.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mFeaturedListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mShopSections = BaseModel.parseArray(jsonParser, ShopSection.class);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mShopListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mShopReviews = (ShopReviewsResult) BaseModel.parseObject(jsonParser, ShopReviewsResult.class);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mShopAbout = (ShopAbout) BaseModel.parseObject(jsonParser, ShopAbout.class);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mManufacturers = BaseModel.parseArray(jsonParser, Manufacturer.class);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mLocalMarkets = BaseModel.parseArray(jsonParser, LocalMarket.class);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mShopPolicy = (ShopPolicy) BaseModel.parseObject(jsonParser, ShopPolicy.class);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mStructuredShopPolicies = (StructuredShopPolicies) BaseModel.parseObject(jsonParser, StructuredShopPolicies.class);
                        break;
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mFaqs.clear();
                        for (FAQ faq : BaseModel.parseArray(jsonParser, FAQ.class)) {
                            if (bh.m3343b(faq.getQuestion()) && bh.m3343b(faq.getAnswer())) {
                                this.mFaqs.add(faq);
                            }
                        }
                        break;
                    case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                        this.mSellerDetails = (SellerDetails) BaseModel.parseObject(jsonParser, SellerDetails.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        return bf.m3322a(this);
    }
}
