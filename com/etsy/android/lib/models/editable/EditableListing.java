package com.etsy.android.lib.models.editable;

import android.text.TextUtils;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Category;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.editable.EditableListingV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.enums.WhoMade;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class EditableListing extends Listing {
    public static final String CATEGORY_PATH_JOIN_STRING = " > ";
    public static final String FIELD_CATEGORY_PATH = "category_path";
    public static final String FIELD_ENDING_TSZ = "ending_tsz";
    public static final String FIELD_FEATURED_RANK = "featured_rank";
    public static final String FIELD_IMAGE_IDS = "image_ids";
    public static final String FIELD_IS_SUPPLY = "is_supply";
    public static final String FIELD_IS_WHOLESALE = "is_wholesale";
    public static final String FIELD_LAST_MODIFIED_TSZ = "last_modified_tsz";
    public static final String FIELD_MATERIALS = "materials";
    public static final String FIELD_RENEW = "renew";
    public static final String FIELD_SHIPPING_PROFILE_ID = "shipping_profile_id";
    public static final String FIELD_SHIPPING_TEMPLATE_ID = "shipping_template_id";
    public static final String FIELD_SHOP_SECTION_ID = "shop_section_id";
    public static final String FIELD_SUGGESTED_TAXONOMY_ID = "suggested_taxonomy_id";
    public static final String FIELD_TAGS = "tags";
    public static final String FIELD_TAXONOMY_PATH = "taxonomy_path";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_WHEN_MADE = "when_made";
    public static final String FIELD_WHO_MADE = "who_made";
    public static final String LISTING_ID_DEVICE_DRAFT = "-1";
    public static final String LISTING_TYPE_BOTH = "both";
    public static final String LISTING_TYPE_DOWNLOAD = "download";
    public static final String LISTING_TYPE_PHYSICAL = "physical";
    public static final String REQUEST_PARAM_PUBLISH = "publish";
    private static final long serialVersionUID = 5348433468850149081L;
    private Category mCategory;
    private Date mExpirationDate;
    private int mFeaturedRank;
    private boolean mHasImageEdits;
    private boolean mHasVariations;
    private boolean mInPersonEligible;
    private boolean mIsSupply;
    private boolean mIsWholesale;
    private WhoMade mMaker;
    private final List<String> mMaterials;
    protected long mModifiedTsz;
    private boolean mNonTaxable;
    private final EtsyId mShippingProfileId;
    private final EtsyId mShippingTemplateId;
    protected final EtsyId mShopSectionId;
    private boolean mShouldAutoRenew;
    private TaxonomyNode mSuggestedTaxonomyNode;
    private final List<String> mTags;
    private TaxonomyNode mTaxonomyNode;
    protected String mType;
    private String mWhenMade;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ListingType {
    }

    public class TaxonomyParseState {
        long mCategoryId;
        List<String> mCategoryPathList;
        long mSuggestedTaxonomyNodeId;
        long mTaxonomyNodeId;
        List<Integer> mTaxonomyPathIdList;
        List<String> mTaxonomyPathList;

        public TaxonomyParseState() {
            this.mTaxonomyNodeId = -1;
            this.mSuggestedTaxonomyNodeId = -1;
            this.mTaxonomyPathList = new ArrayList();
            this.mTaxonomyPathIdList = new ArrayList();
            this.mCategoryId = -1;
            this.mCategoryPathList = new ArrayList();
        }

        public void setCategoryPathList(List<String> list) {
            this.mCategoryPathList = list;
        }

        public void setTaxonomyPathList(List<String> list) {
            this.mTaxonomyPathList = list;
        }

        public void setTaxonomyPathIdList(List<Integer> list) {
            this.mTaxonomyPathIdList = list;
        }

        public void setTaxonomyNodeId(long j) {
            this.mTaxonomyNodeId = j;
        }

        public void setCategoryId(long j) {
            this.mCategoryId = j;
        }

        public void setSuggestedTaxonomyNodeId(long j) {
            this.mSuggestedTaxonomyNodeId = j;
        }

        public void updateListing(EditableListing editableListing) {
            if (this.mTaxonomyNodeId != -1) {
                if (this.mTaxonomyPathIdList.isEmpty()) {
                    editableListing.setTaxonomyNode(new TaxonomyNode(String.valueOf(this.mTaxonomyNodeId), this.mTaxonomyPathList));
                } else {
                    editableListing.setTaxonomyNode(new TaxonomyNode(String.valueOf(this.mTaxonomyNodeId), this.mTaxonomyPathList, this.mTaxonomyPathIdList));
                }
            }
            if (this.mCategoryId != -1) {
                editableListing.setCategory(new Category(String.valueOf(this.mCategoryId), StringUtils.join(this.mCategoryPathList, EditableListing.CATEGORY_PATH_JOIN_STRING)));
            }
            if (this.mSuggestedTaxonomyNodeId != -1) {
                editableListing.mSuggestedTaxonomyNode = new TaxonomyNode(String.valueOf(this.mSuggestedTaxonomyNodeId), this.mTaxonomyPathList);
            }
        }
    }

    public EditableListing() {
        this.mWhenMade = StringUtils.EMPTY;
        this.mInPersonEligible = false;
        this.mShouldAutoRenew = false;
        this.mType = LISTING_TYPE_PHYSICAL;
        this.mShopSectionId = new EtsyId();
        this.mShippingTemplateId = new EtsyId();
        this.mShippingProfileId = new EtsyId();
        this.mTags = new ArrayList();
        this.mMaterials = new ArrayList();
    }

    public EditableListing(EtsyId etsyId, String str, String str2, String str3, String str4, int i) {
        super(etsyId, str, str2, str3, str4, i);
        this.mWhenMade = StringUtils.EMPTY;
        this.mInPersonEligible = false;
        this.mShouldAutoRenew = false;
        this.mType = LISTING_TYPE_PHYSICAL;
        this.mShopSectionId = new EtsyId();
        this.mShippingTemplateId = new EtsyId();
        this.mShippingProfileId = new EtsyId();
        this.mTags = new ArrayList();
        this.mMaterials = new ArrayList();
    }

    public void parseData(JsonParser jsonParser) {
        TaxonomyParseState taxonomyParseState = new TaxonomyParseState();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseEditableListingField(jsonParser, taxonomyParseState, currentName);
            }
        }
        taxonomyParseState.updateListing(this);
    }

    protected void parseEditableListingField(JsonParser jsonParser, TaxonomyParseState taxonomyParseState, String str) {
        if (FIELD_WHO_MADE.equals(str)) {
            this.mMaker = WhoMade.getEnumForJson(BaseModel.parseString(jsonParser));
        } else if (FIELD_IS_SUPPLY.equals(str)) {
            this.mIsSupply = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.NON_TAXABLE.equals(str)) {
            this.mNonTaxable = jsonParser.getValueAsBoolean();
        } else if (FIELD_WHEN_MADE.equals(str)) {
            this.mWhenMade = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.CATEGORY_ID.equals(str)) {
            taxonomyParseState.setCategoryId(jsonParser.getValueAsLong());
        } else if (FIELD_FEATURED_RANK.equals(str)) {
            this.mFeaturedRank = jsonParser.getValueAsInt();
        } else if (FIELD_SHOP_SECTION_ID.equals(str)) {
            this.mShopSectionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.SHOULD_AUTO_RENEW.equals(str)) {
            this.mShouldAutoRenew = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IN_PERSON_ELIGIBLE.equals(str)) {
            this.mInPersonEligible = jsonParser.getValueAsBoolean();
        } else if (FIELD_LAST_MODIFIED_TSZ.equals(str)) {
            this.mModifiedTsz = jsonParser.getValueAsLong();
        } else if (FIELD_SHIPPING_TEMPLATE_ID.equals(str)) {
            this.mShippingTemplateId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (FIELD_SHIPPING_PROFILE_ID.equals(str)) {
            this.mShippingProfileId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (FIELD_ENDING_TSZ.equals(str)) {
            this.mExpirationDate = BaseModel.parseIntoDate(jsonParser);
        } else if (FIELD_TAGS.equals(str)) {
            setTags(BaseModel.parseStringArray(jsonParser));
        } else if (FIELD_MATERIALS.equals(str)) {
            setMaterials(BaseModel.parseStringArray(jsonParser));
        } else if (FIELD_CATEGORY_PATH.equals(str)) {
            taxonomyParseState.setCategoryPathList(BaseModel.parseRawStringArray(jsonParser));
        } else if (ResponseConstants.HAS_VARIATIONS.equals(str)) {
            this.mHasVariations = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.TAXONOMY_ID.equals(str)) {
            taxonomyParseState.setTaxonomyNodeId(jsonParser.getValueAsLong());
        } else if (FIELD_TAXONOMY_PATH.equals(str)) {
            taxonomyParseState.setTaxonomyPathList(BaseModel.parseRawStringArray(jsonParser));
        } else if (FIELD_SUGGESTED_TAXONOMY_ID.equals(str)) {
            taxonomyParseState.setSuggestedTaxonomyNodeId(jsonParser.getValueAsLong());
        } else if (FIELD_IS_WHOLESALE.equals(str)) {
            this.mIsWholesale = jsonParser.getValueAsBoolean();
        } else if (FIELD_TYPE.equals(str)) {
            this.mType = getListingTypeForString(BaseModel.parseString(jsonParser));
        } else {
            parseListingField(jsonParser, str);
        }
    }

    public WhoMade getMaker() {
        return this.mMaker;
    }

    public boolean isSupply() {
        return this.mIsSupply;
    }

    public boolean isWholesale() {
        return this.mIsWholesale;
    }

    public String getWhenMade() {
        return this.mWhenMade;
    }

    public int getFeaturedRank() {
        return this.mFeaturedRank;
    }

    public EtsyId getSectionId() {
        return this.mShopSectionId;
    }

    public Date getExpirationDate() {
        return this.mExpirationDate;
    }

    public List<String> getTags() {
        return this.mTags;
    }

    public List<String> getMaterials() {
        return this.mMaterials;
    }

    public long getLastModifiedTsz() {
        return this.mModifiedTsz;
    }

    public EtsyId getShippingTemplateId() {
        return this.mShippingTemplateId;
    }

    public EtsyId getShippingProfileId() {
        return this.mShippingProfileId;
    }

    public boolean hasImageEdits() {
        return this.mHasImageEdits;
    }

    public boolean isTaxable() {
        return !this.mNonTaxable;
    }

    public boolean isInPersonEligible() {
        return this.mInPersonEligible;
    }

    public boolean getShouldAutoRenew() {
        return this.mShouldAutoRenew;
    }

    public boolean hasVariations() {
        return this.mHasVariations;
    }

    public String getEquivalentState() {
        if (isEditState()) {
            return EditableListingV3.LISTING_STATE_INACTIVE;
        }
        if (isOnVacation()) {
            return EditableListingV3.LISTING_STATE_ACTIVE;
        }
        return this.mState;
    }

    public boolean isDeviceDraft() {
        return LISTING_ID_DEVICE_DRAFT.equalsIgnoreCase(this.mListingId.getId());
    }

    public boolean isActiveOrVacation() {
        return isActive() || isOnVacation();
    }

    public static boolean isActiveOrVacation(String str) {
        return Listing.isActive(str) || Listing.isOnVacation(str);
    }

    public String getCommaSeparatedTags() {
        if (this.mTags.size() > 0) {
            return TextUtils.join(",", this.mTags);
        }
        return StringUtils.EMPTY;
    }

    public String getCommaSeparatedMaterials() {
        if (this.mMaterials.size() > 0) {
            return TextUtils.join(",", this.mMaterials);
        }
        return StringUtils.EMPTY;
    }

    public boolean isInactiveAndExpired() {
        return isInactiveOrEdit() && this.mExpirationDate.getTime() < System.currentTimeMillis();
    }

    public void setMaker(WhoMade whoMade) {
        this.mMaker = whoMade;
    }

    public void setIsSupply(boolean z) {
        this.mIsSupply = z;
    }

    public void setIsWholesale(boolean z) {
        this.mIsWholesale = z;
    }

    public void setWhenMade(String str) {
        this.mWhenMade = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setQuantity(int i) {
        this.mQuantity = i;
    }

    public void setPriceAndCurrency(String str, String str2) {
        this.mPrice = this.mPrice.withAmount(str);
        this.mPrice = this.mPrice.withCurrency(str2);
    }

    public void setState(String str) {
        this.mState = str;
    }

    public void setFeaturedRank(int i) {
        this.mFeaturedRank = i;
    }

    public void setSectionId(String str) {
        this.mShopSectionId.setId(str);
    }

    public void setExpirationDate(long j) {
        this.mExpirationDate = new Date();
        this.mExpirationDate.setTime(j);
    }

    public void setShippingTemplateId(String str) {
        this.mShippingTemplateId.setId(str);
    }

    public void setShippingProfileId(String str) {
        this.mShippingProfileId.setId(str);
    }

    public void setProcessingMin(int i) {
        this.mProcessingMin = i;
    }

    public void setProcessingMax(int i) {
        this.mProcessingMax = i;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setTags(List<String> list) {
        this.mTags.clear();
        this.mTags.addAll(list);
    }

    public void setMaterials(List<String> list) {
        this.mMaterials.clear();
        this.mMaterials.addAll(list);
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setHasImageEdits(boolean z) {
        this.mHasImageEdits = z;
    }

    public void setIsDigitalDownload(boolean z) {
        this.mIsDigitalDownload = z;
    }

    public void setIsTaxable(boolean z) {
        this.mNonTaxable = !z;
    }

    public void setShouldAutoRenew(boolean z) {
        this.mShouldAutoRenew = z;
    }

    public void setIsInPersonEligible(boolean z) {
        this.mInPersonEligible = z;
    }

    public void setHasVariations(boolean z) {
        this.mHasVariations = z;
    }

    public void setCategory(Category category) {
        this.mCategory = category;
    }

    public Category getCategory() {
        return this.mCategory;
    }

    public void setTaxonomyNode(TaxonomyNode taxonomyNode) {
        this.mTaxonomyNode = taxonomyNode;
    }

    public TaxonomyNode getTaxonomyNode() {
        return this.mTaxonomyNode;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public TaxonomyNode getSuggestedTaxonomyNode() {
        return this.mSuggestedTaxonomyNode;
    }

    public void setSuggestedTaxonomyNode(TaxonomyNode taxonomyNode) {
        this.mSuggestedTaxonomyNode = taxonomyNode;
    }

    public String getBasePrice() {
        return this.mPrice.format();
    }

    public static String getListingTypeForString(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 3029889:
                if (str.equals(LISTING_TYPE_BOTH)) {
                    obj = 1;
                    break;
                }
                break;
            case 1427818632:
                if (str.equals(LISTING_TYPE_DOWNLOAD)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return LISTING_TYPE_DOWNLOAD;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return LISTING_TYPE_BOTH;
            default:
                return LISTING_TYPE_PHYSICAL;
        }
    }
}
