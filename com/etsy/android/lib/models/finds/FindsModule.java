package com.etsy.android.lib.models.finds;

import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.GiftCardBannerImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.finds.FindsCrossLinkModule.Page;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Section;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcel;

@Parcel
public class FindsModule extends BaseFieldModel {
    public static final String CATEGORY_CARDS_TYPE = "category_cards";
    public static final String CROSSLINK_TYPE = "cross_link";
    public static final String FIELD_CATEGORIES = "categories";
    public static final String FIELD_CTA = "cta";
    public static final String FIELD_IMAGES = "images";
    public static final String FIELD_LISTINGS = "listings";
    public static final String FIELD_PAGES = "pages";
    public static final String FIELD_SECTIONS = "sections";
    public static final String FIELD_SHOPS = "shops";
    public static final String FIELD_TEXT = "text";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_TYPE = "type";
    public static final String GIFT_CARD_BANNER = "gift_card_banner";
    public static final String HEADING_TYPE = "heading";
    private static final Map<String, Class<? extends FindsModule>> KNOWN_TYPES;
    public static final String LISTINGS_CARD_TYPE = "listings";
    public static final String SHOPS_CARD_TYPE = "shops";
    public static final String SMALL_CROSSLINK_TYPE = "small_cross_link";
    private static final String TAG;
    public static final String TWO_TITLED_LISTINGS_TYPE = "two_title_listings";
    protected List<FindsSearchCategory> mCategories;
    protected String mCta;
    protected GiftCardBannerImage mGiftCardBannerImage;
    protected List<ListingCard> mListings;
    protected List<Page> mPages;
    protected List<Section> mSections;
    protected List<ShopCard> mShops;
    protected String mText;
    protected String mTitle;
    protected String mType;

    static {
        TAG = EtsyDebug.m1891a(FindsModule.class);
        KNOWN_TYPES = new HashMap();
        KNOWN_TYPES.put(HEADING_TYPE, FindsHeadingModule.class);
        KNOWN_TYPES.put(CATEGORY_CARDS_TYPE, FindsCategoryModule.class);
        KNOWN_TYPES.put(SHOPS_CARD_TYPE, FindsShopModule.class);
        KNOWN_TYPES.put(LISTINGS_CARD_TYPE, FindsListingsModules.class);
        KNOWN_TYPES.put(TWO_TITLED_LISTINGS_TYPE, FindsTwoTitledListingsModule.class);
        KNOWN_TYPES.put(CROSSLINK_TYPE, FindsCrossLinkModule.class);
        KNOWN_TYPES.put(SMALL_CROSSLINK_TYPE, FindsCrossLinkModule.class);
        KNOWN_TYPES.put(GIFT_CARD_BANNER, GiftCardBanner.class);
    }

    protected void setFromGeneric(FindsModule findsModule) {
        this.mType = findsModule.mType;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (FIELD_TYPE.equals(str)) {
            String parseString = BaseModel.parseString(jsonParser);
            if (!KNOWN_TYPES.containsKey(parseString)) {
                return false;
            }
            this.mType = parseString;
        } else if (FIELD_TEXT.equals(str)) {
            this.mText = BaseModel.parseString(jsonParser);
        } else if (FIELD_CATEGORIES.equals(str)) {
            this.mCategories = BaseModel.parseArray(jsonParser, FindsSearchCategory.class);
        } else if (SHOPS_CARD_TYPE.equals(str)) {
            this.mShops = BaseModel.parseArray(jsonParser, ShopCard.class);
        } else if (LISTINGS_CARD_TYPE.equals(str)) {
            this.mListings = BaseModel.parseArray(jsonParser, ListingCard.class);
        } else if (FIELD_SECTIONS.equals(str)) {
            this.mSections = BaseModel.parseArray(jsonParser, Section.class);
        } else if (FIELD_PAGES.equals(str)) {
            this.mPages = BaseModel.parseArray(jsonParser, Page.class);
        } else if (FIELD_IMAGES.equals(str)) {
            this.mGiftCardBannerImage = (GiftCardBannerImage) BaseModel.parseObject(jsonParser, GiftCardBannerImage.class);
        } else if (FIELD_CTA.equals(str)) {
            this.mCta = BaseModel.parseString(jsonParser);
        } else if (!FIELD_TITLE.equals(str)) {
            return false;
        } else {
            this.mTitle = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    public String getType() {
        return this.mType;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return new ArrayList(0);
    }

    public List<? extends ICardViewElement> getCardViewElements(boolean z) {
        return getCardViewElements();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.etsy.android.lib.models.finds.FindsModule getTyped() {
        /*
        r5 = this;
        r0 = KNOWN_TYPES;
        r1 = r5.mType;
        r0 = r0.get(r1);
        r0 = (java.lang.Class) r0;
        r1 = r0.newInstance();	 Catch:{ InstantiationException -> 0x0014, IllegalAccessException -> 0x002f }
        r1 = (com.etsy.android.lib.models.finds.FindsModule) r1;	 Catch:{ InstantiationException -> 0x0014, IllegalAccessException -> 0x002f }
        r1.setFromGeneric(r5);	 Catch:{ InstantiationException -> 0x0014, IllegalAccessException -> 0x002f }
    L_0x0013:
        return r1;
    L_0x0014:
        r1 = move-exception;
        r2 = TAG;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "InstantiationException creating class=";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.etsy.android.lib.logger.EtsyDebug.m1917d(r2, r0, r1);
    L_0x002d:
        r1 = 0;
        goto L_0x0013;
    L_0x002f:
        r1 = move-exception;
        r2 = TAG;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "IllegalAccessException creating class=";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.etsy.android.lib.logger.EtsyDebug.m1917d(r2, r0, r1);
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.lib.models.finds.FindsModule.getTyped():com.etsy.android.lib.models.finds.FindsModule");
    }

    public String getText() {
        return this.mText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getCta() {
        return this.mCta;
    }

    public GiftCardBannerImage getGiftCardBannerImages() {
        return this.mGiftCardBannerImage;
    }
}
