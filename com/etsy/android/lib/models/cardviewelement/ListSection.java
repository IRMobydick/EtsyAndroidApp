package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.MarketingBanner;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.apiv3.UserCard;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.finds.GiftCardBanner;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageInfoHolder;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.homescreen.SeasonalSegmentCard;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ListSection extends BaseFieldModel {
    private static final String TAG;
    public static final String TYPE_ANCHOR_LISTING = "anchorListing";
    public static final String TYPE_CART_GROUP = "cartGroup";
    public static final String TYPE_CART_ITEM = "cartItem";
    public static final String TYPE_CATEGORY_REC_CARD = "categoryRecCard";
    public static final String TYPE_FINDS_BANNER = "findsBanner";
    public static final String TYPE_GIFT_CARD_BANNER = "giftCardBanner";
    public static final String TYPE_HERO_BANNER = "holidayBanner";
    public static final String TYPE_LISTING_CARD = "listingCard";
    public static final String TYPE_LOCAL_DELIVERY_HOLIDAY_BANNER = "localDeliveryHolidayBanner";
    public static final String TYPE_MARKETING_BANNER = "marketingBanner";
    public static final String TYPE_MESSAGE_CARD = "messageCard";
    public static final String TYPE_SAVE_FOR_LATER_CART = "cartListing";
    public static final String TYPE_SEASONAL_SEGMENT_CARD = "seasonalSegmentCard";
    public static final String TYPE_SEGMENT_CARD = "segmentCard";
    public static final String TYPE_SHOP_CARD = "shopCard";
    public static final String TYPE_SHOP_SHARE_CARD = "shopShareCard";
    public static final String TYPE_SMALL_FINDS_BANNER = "smallFindsBanner";
    public static final String TYPE_TAXONOMY_CATEGORY = "taxonomyCategory";
    public static final String TYPE_TAXONOMY_NODE = "taxonomyNode";
    public static final String TYPE_USER_CARD = "userCard";
    private static final ArrayMap<String, Class<? extends BaseModel>> mTypeToClassMap;
    private static final long serialVersionUID = -4492212913528156734L;
    BasicSectionHeader mHeader;
    boolean mIsHorizontal;
    List<IBaseRecyclerViewElement> mItems;
    @Nullable
    Parcelable mLayoutState;
    PageLink mPageLink;

    public ListSection() {
        this.mHeader = null;
        this.mPageLink = null;
        this.mIsHorizontal = false;
        this.mItems = new ArrayList();
    }

    static {
        TAG = EtsyDebug.m1891a(ListSection.class);
        mTypeToClassMap = new ArrayMap();
        mTypeToClassMap.put(TYPE_LISTING_CARD, ListingCard.class);
        mTypeToClassMap.put(TYPE_SHOP_CARD, ShopCard.class);
        mTypeToClassMap.put(TYPE_USER_CARD, UserCard.class);
        mTypeToClassMap.put(TYPE_SEGMENT_CARD, Segment.class);
        mTypeToClassMap.put(TYPE_MESSAGE_CARD, MessageCard.class);
        mTypeToClassMap.put(TYPE_SEASONAL_SEGMENT_CARD, SeasonalSegmentCard.class);
        mTypeToClassMap.put(TYPE_CATEGORY_REC_CARD, CategoryRecommendationCard.class);
        mTypeToClassMap.put(TYPE_SHOP_SHARE_CARD, ShopShareCard.class);
        mTypeToClassMap.put(TYPE_TAXONOMY_CATEGORY, TaxonomyCategory.class);
        mTypeToClassMap.put(TYPE_FINDS_BANNER, FindsCard.class);
        mTypeToClassMap.put(TYPE_SMALL_FINDS_BANNER, FindsCard.class);
        mTypeToClassMap.put(TYPE_TAXONOMY_NODE, TaxonomyCategory.class);
        mTypeToClassMap.put(TYPE_MARKETING_BANNER, MarketingBanner.class);
        mTypeToClassMap.put(TYPE_ANCHOR_LISTING, ListingCard.class);
        mTypeToClassMap.put(TYPE_SAVE_FOR_LATER_CART, SavedCart.class);
        mTypeToClassMap.put(TYPE_CART_GROUP, CartGroup.class);
        mTypeToClassMap.put(TYPE_GIFT_CARD_BANNER, GiftCardBanner.class);
        mTypeToClassMap.put(TYPE_CART_ITEM, CartGroupItem.class);
    }

    public List<IBaseRecyclerViewElement> getItems() {
        return this.mItems;
    }

    public boolean isHorizontal() {
        return this.mIsHorizontal;
    }

    public BasicSectionHeader getHeader() {
        return this.mHeader;
    }

    public PageLink getPageLink() {
        return this.mPageLink;
    }

    public void setPageLink(PageLink pageLink) {
        this.mPageLink = pageLink;
    }

    public void setItems(List<? extends IBaseRecyclerViewElement> list) {
        this.mItems.clear();
        this.mItems.addAll(list);
    }

    public void setHeader(BasicSectionHeader basicSectionHeader) {
        this.mHeader = basicSectionHeader;
    }

    @Nullable
    public Parcelable getLayoutState() {
        return this.mLayoutState;
    }

    public void setLayoutState(@NonNull Parcelable parcelable) {
        this.mLayoutState = parcelable;
    }

    public void parseData(JsonParser jsonParser) {
        super.parseData(jsonParser);
        foldLinkIntoCards();
    }

    protected void foldLinkIntoCards() {
        if (this.mItems != null) {
            for (IBaseRecyclerViewElement iBaseRecyclerViewElement : this.mItems) {
                if ((iBaseRecyclerViewElement instanceof LandingPageInfoHolder) && (this.mPageLink instanceof LandingPageInfo)) {
                    ((LandingPageInfoHolder) iBaseRecyclerViewElement).setLandingPage((LandingPageInfo) this.mPageLink);
                }
            }
        }
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (FindsModule.FIELD_TITLE.equals(str)) {
            if (this.mHeader == null) {
                this.mHeader = new BasicSectionHeader();
            }
            this.mHeader.setTitle(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.SUB_TITLE.equals(str)) {
            if (this.mHeader == null) {
                this.mHeader = new BasicSectionHeader();
            }
            this.mHeader.setSubtitle(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.SECTION_HEADER.equals(str)) {
            this.mHeader = (BasicSectionHeader) BaseModel.parseObject(jsonParser, BasicSectionHeader.class);
        } else if (ResponseConstants.LANDING_PAGE.equals(str)) {
            if (this.mPageLink == null) {
                this.mPageLink = new LandingPageLink(this.mHeader != null ? this.mHeader.getTitle() : StringUtils.EMPTY);
            }
            this.mPageLink.parseData(jsonParser);
        } else if (ResponseConstants.SEARCH_PAGE.equals(str)) {
            if (this.mPageLink == null) {
                this.mPageLink = (PageLink) BaseModel.parseObject(jsonParser, SearchPageLink.class);
            }
        } else if (ResponseConstants.HORIZONTAL.equals(str)) {
            this.mIsHorizontal = jsonParser.getValueAsBoolean();
        } else if (TYPE_LISTING_CARD.equals(str)) {
            Collection parseArray = BaseModel.parseArray(jsonParser, ListingCard.class);
            if (this.mItems == null) {
                this.mItems = new ArrayList();
            }
            this.mItems.addAll(parseArray);
        } else if (TYPE_ANCHOR_LISTING.equals(str)) {
            ListingCard listingCard = (ListingCard) BaseModel.parseObject(jsonParser, ListingCard.class);
            if (!listingCard.hasError()) {
                listingCard.setViewType(34);
                if (this.mItems == null) {
                    this.mItems = new ArrayList();
                }
                this.mItems.add(listingCard);
            }
        } else if (TYPE_SHOP_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, ShopCard.class));
        } else if (TYPE_USER_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, UserCard.class));
        } else if (TYPE_SEGMENT_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, Segment.class));
        } else if (TYPE_MESSAGE_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, MessageCard.class));
        } else if (TYPE_SEASONAL_SEGMENT_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, SeasonalSegmentCard.class));
        } else if (TYPE_CATEGORY_REC_CARD.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, CategoryRecommendationCard.class));
        } else if (TYPE_TAXONOMY_CATEGORY.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, TaxonomyCategory.class));
        } else if (TYPE_TAXONOMY_NODE.equals(str)) {
            Collection<TaxonomyCategory> parseArray2 = BaseModel.parseArray(jsonParser, TaxonomyCategory.class);
            for (TaxonomyCategory viewType : parseArray2) {
                viewType.setViewType(7);
            }
            this.mItems.addAll(parseArray2);
        } else if (TYPE_SHOP_SHARE_CARD.equals(str)) {
            Collection<ShopShareCard> parseArray3 = BaseModel.parseArray(jsonParser, ShopShareCard.class);
            for (ShopShareCard viewType2 : parseArray3) {
                viewType2.setViewType(18);
            }
            this.mItems.addAll(parseArray3);
        } else if (TYPE_FINDS_BANNER.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, FindsCard.class));
        } else if (TYPE_SMALL_FINDS_BANNER.equals(str)) {
            Collection<FindsCard> parseArray4 = BaseModel.parseArray(jsonParser, FindsCard.class);
            for (FindsCard viewType3 : parseArray4) {
                viewType3.setViewType(30);
            }
            this.mItems.addAll(parseArray4);
        } else if (TYPE_MARKETING_BANNER.equals(str)) {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, MarketingBanner.class));
        } else if (!mTypeToClassMap.containsKey(str)) {
            return false;
        } else {
            this.mItems.addAll(BaseModel.parseArray(jsonParser, (Class) mTypeToClassMap.get(str)));
        }
        return true;
    }

    public void setHorizontal(boolean z) {
        this.mIsHorizontal = z;
    }
}
