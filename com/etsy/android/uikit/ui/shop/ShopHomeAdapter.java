package com.etsy.android.uikit.ui.shop;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.CircularIntArray;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.Manufacturer;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ListingMemberData;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.ShopHomeMemberData;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopIconV3;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.models.apiv3.ShopPolicy;
import com.etsy.android.lib.models.apiv3.ShopReviewsResult;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.apiv3.StructuredShopPayments;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.apiv3.StructuredShopPrivacy;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.view.shop.FAQTranslationData;
import com.etsy.android.lib.models.view.shop.ShopAboutMemberViewModel;
import com.etsy.android.lib.models.view.shop.ShopHomeLocalMarketViewModel;
import com.etsy.android.lib.models.view.shop.ShopHomeRelatedLinkViewModel;
import com.etsy.android.lib.models.view.shop.ShopHomeReviewResponseViewData;
import com.etsy.android.lib.models.view.shop.ShopHomeReviewViewModel;
import com.etsy.android.lib.models.view.shop.ShopOwnerViewModel;
import com.etsy.android.lib.models.view.shop.ShopVacationBannerViewData;
import com.etsy.android.lib.models.view.shop.section.ShopHomeAboutSectionViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomeLocalMarketsSectionViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomeMoreInfoSectionViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomePoliciesSectionViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomeStructuredPoliciesSectionViewModel;
import com.etsy.android.lib.models.view.shop.section.TermsAndConditionsSectionViewModel;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.text.ClickableSpanTouchListener;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.ShopHomeSpacingUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.util.shop.ShopHomeTabsUtil;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.ButtonAndDescriptionViewHolder;
import com.etsy.android.uikit.viewholder.HorizontalImageAndHeadingViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.uikit.viewholder.SpaceViewHolder;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.TitleAndDescriptionViewHolder;
import com.etsy.android.uikit.viewholder.TranslationToggleViewHolder;
import com.etsy.android.uikit.viewholder.g;
import com.etsy.android.uikit.viewholder.h;
import com.etsy.android.uikit.viewholder.k;
import com.etsy.android.uikit.viewholder.shop.EmptyLayoutViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopCoverPhotoViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeActionButtonsViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeAnnouncementContentViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeAppreciationPhotoViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeInfoViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeListingCardViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeLocalMapViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeOwnerViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeReviewMessageViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeReviewRatingViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopHomeVacationModeBannerViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopIconViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopItemsFilterViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopItemsSearchViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopReviewListingInfoViewHolder;
import com.etsy.android.uikit.viewholder.shop.ShopReviewsDescriptionViewHolder;
import com.etsy.android.uikit.viewholder.shop.a;
import com.etsy.android.uikit.viewholder.shop.b;
import com.etsy.android.uikit.viewholder.shop.c;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopHomeAboutImagesViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopHomeFAQViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopHomeRelatedLinkViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopHomeSectionHeadingViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopHomeSellerDetailsViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopStructuredPoliciesPaymentsViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopStructuredPolicyPrivacyViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopStructuredPolicyRefundsViewHolder;
import com.etsy.android.uikit.viewholder.shop.homesection.ShopStructuredPolicyShippingViewHolder;
import com.google.android.gms.maps.o;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.text.WordUtils;

public class ShopHomeAdapter extends BaseRecyclerViewAdapter<Pair<Integer, ?>> implements ICardViewAdapter, c {
    private static final SimpleDateFormat DATE_FORMAT;
    private static final Object DEFAULT_PAYLOAD;
    private static final String HIDE_SEARCH_DESCRIPTION_PAYLOAD = "clear_search_description";
    public static final int TYPE_ACTION_BUTTONS = 26;
    public static final int TYPE_BUTTON_BLUE_WITH_DESCRIPTION = 12;
    public static final int TYPE_EMPTY_LAYOUT = 35;
    public static final int TYPE_FAQ_CONTENT = 34;
    public static final int TYPE_FEATURED_LISTING = 5;
    public static final int TYPE_FEATURED_LISTINGS_EXTRA_SPACE = 27;
    public static final int TYPE_IMAGE_AND_HEADING = 18;
    public static final int TYPE_LOADING_WITH_DESCRIPTION = 38;
    public static final int TYPE_LOCAL_MAP = 19;
    public static final int TYPE_OWNER_INFO = 4;
    public static final int TYPE_RELATED_LINK = 16;
    public static final int TYPE_REVIEW_APPRECIATION_PHOTO = 23;
    public static final int TYPE_REVIEW_LISTING_INFO = 25;
    public static final int TYPE_REVIEW_MESSAGE = 24;
    public static final int TYPE_REVIEW_RATING = 22;
    public static final int TYPE_SECTION_EXTRA_SPACE = 36;
    public static final int TYPE_SELLER_DETAILS = 33;
    public static final int TYPE_SHOP_ABOUT_IMAGES = 14;
    public static final int TYPE_SHOP_ANNOUNCEMENT_CONTENT = 7;
    public static final int TYPE_SHOP_ANNOUNCEMENT_INFO = 6;
    public static final int TYPE_SHOP_COVER_PHOTO = 1;
    public static final int TYPE_SHOP_FAQ_SUBSECTION_HEADING = 39;
    public static final int TYPE_SHOP_ICON = 2;
    public static final int TYPE_SHOP_INFO = 3;
    public static final int TYPE_SHOP_ITEMS_FILTER = 9;
    public static final int TYPE_SHOP_ITEMS_SEARCH = 8;
    public static final int TYPE_SHOP_LISTING = 10;
    public static final int TYPE_SHOP_REVIEWS_DESCRIPTION = 11;
    public static final int TYPE_SHOP_SECTION_CONTENT = 15;
    public static final int TYPE_SHOP_SECTION_HEADING = 13;
    public static final int TYPE_SHOP_SUBSECTION_HEADING = 17;
    public static final int TYPE_SHOP_TERMS_AND_CONDITIONS_LINK = 37;
    public static final int TYPE_STRUCTURED_POLICIES_DOWNLOADS = 29;
    public static final int TYPE_STRUCTURED_POLICIES_PAYMENTS = 30;
    public static final int TYPE_STRUCTURED_POLICIES_PRIVACY = 32;
    public static final int TYPE_STRUCTURED_POLICIES_REFUNDS = 31;
    public static final int TYPE_STRUCTURED_POLICIES_SHIPPING = 28;
    public static final int TYPE_VACATION_BANNER = 20;
    private int mFavoriteButtonItemPosition;
    int mFeaturedListingExtraSpaceSpanSize;
    int mFeaturedListingSpanSize;
    private int mFilterPosition;
    int mIconSpanSize;
    private int mItemsTabPosition;
    private int mListingsEndPosition;
    private int mListingsStartPosition;
    int mMaxSpanSize;
    int mOwnerInfoSpanSize;
    @Nullable
    private ShopHomePage mPageData;
    private final ShopHomeRouter mRouter;
    private int mSearchBoxPosition;
    int mShopAnnouncementContentSpanSize;
    int mShopAnnouncementInfoSpanSize;
    int mShopInfoSpanSize;
    int mShopListingSpanSize;
    private final boolean mShouldShowTermsAndConditions;
    boolean mShouldUseHorizontalHeaderLayout;
    private ShopHomeStateManager mStateManager;
    private TabletSupportUtil mTabletSupportUtil;
    private int mVacationBannerPosition;

    /* renamed from: com.etsy.android.uikit.ui.shop.ShopHomeAdapter.1 */
    final class C09941 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeRouter f4111a;

        C09941(ShopHomeRouter shopHomeRouter) {
            this.f4111a = shopHomeRouter;
        }

        public void onViewClick(View view) {
            this.f4111a.navigateToReviewsPage();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.ShopHomeAdapter.2 */
    class C09952 extends SpanSizeLookup {
        final /* synthetic */ ShopHomeAdapter f4112a;

        C09952(ShopHomeAdapter shopHomeAdapter) {
            this.f4112a = shopHomeAdapter;
        }

        public int getSpanSize(int i) {
            switch (((Integer) ((Pair) this.f4112a.mItems.get(i)).first).intValue()) {
                case ShopHomeAdapter.TYPE_SHOP_ICON /*2*/:
                    return this.f4112a.mIconSpanSize;
                case ShopHomeAdapter.TYPE_SHOP_INFO /*3*/:
                    return this.f4112a.mShopInfoSpanSize;
                case ShopHomeAdapter.TYPE_OWNER_INFO /*4*/:
                    return this.f4112a.mOwnerInfoSpanSize;
                case ShopHomeAdapter.TYPE_FEATURED_LISTING /*5*/:
                    return this.f4112a.mFeaturedListingSpanSize;
                case ShopHomeAdapter.TYPE_SHOP_ANNOUNCEMENT_INFO /*6*/:
                    return this.f4112a.mShopAnnouncementInfoSpanSize;
                case ShopHomeAdapter.TYPE_SHOP_ANNOUNCEMENT_CONTENT /*7*/:
                    return this.f4112a.mShopAnnouncementContentSpanSize;
                case ShopHomeAdapter.TYPE_SHOP_LISTING /*10*/:
                    return this.f4112a.mShopListingSpanSize;
                case ShopHomeAdapter.TYPE_FEATURED_LISTINGS_EXTRA_SPACE /*27*/:
                    return this.f4112a.mFeaturedListingExtraSpaceSpanSize;
                default:
                    return this.f4112a.mMaxSpanSize;
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.ShopHomeAdapter.3 */
    final class C09963 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeStateManager f4113a;

        C09963(ShopHomeStateManager shopHomeStateManager) {
            this.f4113a = shopHomeStateManager;
        }

        public void onViewClick(View view) {
            this.f4113a.m5606j();
        }
    }

    static {
        DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        DEFAULT_PAYLOAD = new Object();
    }

    public ShopHomeAdapter(@NonNull FragmentActivity fragmentActivity, @NonNull ImageBatch imageBatch, @NonNull ad adVar, @NonNull ShopHomeRouter shopHomeRouter) {
        super(fragmentActivity, imageBatch);
        this.mFavoriteButtonItemPosition = -1;
        this.mSearchBoxPosition = -1;
        this.mFilterPosition = -1;
        this.mListingsStartPosition = -1;
        this.mListingsEndPosition = -1;
        this.mItemsTabPosition = -1;
        this.mVacationBannerPosition = -1;
        deriveConfiguration();
        this.mRouter = shopHomeRouter;
        this.mShouldShowTermsAndConditions = adVar.m1864f().m885c(EtsyConfigKeys.f1258d);
    }

    public int getListItemViewType(int i) {
        return ((Integer) ((Pair) this.mItems.get(i)).first).intValue();
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_SHOP_COVER_PHOTO /*1*/:
                return new ShopCoverPhotoViewHolder((FullImageView) this.mInflater.inflate(R.shop_cover_photo, viewGroup, false), this.mImageBatch);
            case TYPE_SHOP_ICON /*2*/:
                return new ShopIconViewHolder(this.mInflater.inflate(R.shop_home_icon, viewGroup, false), this.mImageBatch);
            case TYPE_SHOP_INFO /*3*/:
                return new ShopHomeInfoViewHolder(this.mInflater.inflate(R.shop_home_info, viewGroup, false), this.mRouter, this.mShouldUseHorizontalHeaderLayout);
            case TYPE_OWNER_INFO /*4*/:
                return new ShopHomeOwnerViewHolder(this.mInflater.inflate(R.shop_owner_info, viewGroup, false), this.mImageBatch);
            case TYPE_FEATURED_LISTING /*5*/:
            case TYPE_SHOP_LISTING /*10*/:
                return new ShopHomeListingCardViewHolder(this.mInflater, viewGroup, this.mRouter.getListingCardClickListener(), this.mImageBatch);
            case TYPE_SHOP_ANNOUNCEMENT_INFO /*6*/:
                return new TitleAndDescriptionViewHolder(this.mInflater.inflate(R.shop2_heading_and_description, viewGroup, false));
            case TYPE_SHOP_ANNOUNCEMENT_CONTENT /*7*/:
                return new ShopHomeAnnouncementContentViewHolder(this.mInflater.inflate(R.shop_announcement_content, viewGroup, false), this.mRouter);
            case TYPE_SHOP_ITEMS_SEARCH /*8*/:
                return new ShopItemsSearchViewHolder(this.mInflater.inflate(R.shop_home_search, viewGroup, false));
            case TYPE_SHOP_ITEMS_FILTER /*9*/:
                return new ShopItemsFilterViewHolder(this.mInflater.inflate(R.shop_home_items_filter, viewGroup, false));
            case TYPE_SHOP_REVIEWS_DESCRIPTION /*11*/:
                return new ShopReviewsDescriptionViewHolder(this.mInflater.inflate(R.shop_review_description, viewGroup, false));
            case TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                return new ButtonAndDescriptionViewHolder(this.mInflater.inflate(R.button_blue_with_description, viewGroup, false));
            case TYPE_SHOP_SECTION_HEADING /*13*/:
                return new ShopHomeSectionHeadingViewHolder(this.mInflater.inflate(R.shop_home_section_heading, viewGroup, false));
            case TYPE_SHOP_ABOUT_IMAGES /*14*/:
                return new ShopHomeAboutImagesViewHolder(this.mInflater.inflate(R.shop_home_about_images, viewGroup, false), this.mImageBatch, this.mRouter);
            case TYPE_SHOP_SECTION_CONTENT /*15*/:
                ViewHolder textViewHolder = new TextViewHolder(this.mInflater, viewGroup, R.shop_home_section_content_text, 16908308);
                textViewHolder.getTextView().setOnTouchListener(new ClickableSpanTouchListener());
                return textViewHolder;
            case TYPE_RELATED_LINK /*16*/:
                return new ShopHomeRelatedLinkViewHolder(this.mInflater.inflate(R.shop_related_links, viewGroup, false), this.mRouter);
            case TYPE_SHOP_SUBSECTION_HEADING /*17*/:
                return new TextViewHolder(this.mInflater, viewGroup, R.shop_home_subsection_title, R.title);
            case TYPE_IMAGE_AND_HEADING /*18*/:
                return new HorizontalImageAndHeadingViewHolder(this.mInflater.inflate(R.horizontal_image_and_heading, viewGroup, false), this.mImageBatch);
            case TYPE_LOCAL_MAP /*19*/:
                return new ShopHomeLocalMapViewHolder(this.mInflater.inflate(R.shop_home_local_map, viewGroup, false));
            case TYPE_VACATION_BANNER /*20*/:
                return new ShopHomeVacationModeBannerViewHolder(this.mInflater.inflate(R.shop_vacation_banner, viewGroup, false), this.mRouter);
            case TYPE_REVIEW_RATING /*22*/:
                return new ShopHomeReviewRatingViewHolder(this.mInflater.inflate(R.shop_home_review_rating, viewGroup, false));
            case TYPE_REVIEW_APPRECIATION_PHOTO /*23*/:
                return new ShopHomeAppreciationPhotoViewHolder(this.mInflater.inflate(R.shop_home_ap, viewGroup, false), this.mImageBatch, this.mRouter);
            case TYPE_REVIEW_MESSAGE /*24*/:
                return new ShopHomeReviewMessageViewHolder(this.mInflater.inflate(R.shop_home_review_message, viewGroup, false));
            case TYPE_REVIEW_LISTING_INFO /*25*/:
                return new ShopReviewListingInfoViewHolder(this.mInflater.inflate(R.shop_home_review_listing_info, viewGroup, false), this.mImageBatch);
            case TYPE_ACTION_BUTTONS /*26*/:
                return new ShopHomeActionButtonsViewHolder(this.mInflater.inflate(R.shop_home_action_buttons, viewGroup, false), this.mRouter);
            case TYPE_FEATURED_LISTINGS_EXTRA_SPACE /*27*/:
            case TYPE_SECTION_EXTRA_SPACE /*36*/:
                return new SpaceViewHolder(this.mContext, viewGroup);
            case TYPE_STRUCTURED_POLICIES_SHIPPING /*28*/:
                return new ShopStructuredPolicyShippingViewHolder(this.mInflater.inflate(R.structured_policies_section_shipping, viewGroup, false));
            case TYPE_STRUCTURED_POLICIES_DOWNLOADS /*29*/:
                return new BaseViewHolder(this.mInflater.inflate(R.structured_policies_section_downloads, viewGroup, false));
            case TYPE_STRUCTURED_POLICIES_PAYMENTS /*30*/:
                return new ShopStructuredPoliciesPaymentsViewHolder(this.mInflater.inflate(R.structured_policies_section_payments, viewGroup, false));
            case TYPE_STRUCTURED_POLICIES_REFUNDS /*31*/:
                return new ShopStructuredPolicyRefundsViewHolder(this.mInflater.inflate(R.structured_policies_section_refunds, viewGroup, false));
            case TYPE_STRUCTURED_POLICIES_PRIVACY /*32*/:
                return new ShopStructuredPolicyPrivacyViewHolder(this.mInflater.inflate(R.structured_policies_section_privacy, viewGroup, false));
            case TYPE_SELLER_DETAILS /*33*/:
                return new ShopHomeSellerDetailsViewHolder(this.mInflater.inflate(R.shop_home_section_content_text, viewGroup, false));
            case TYPE_FAQ_CONTENT /*34*/:
                return new ShopHomeFAQViewHolder(this.mInflater.inflate(R.shop_home_faq_content, viewGroup, false));
            case TYPE_EMPTY_LAYOUT /*35*/:
                return new EmptyLayoutViewHolder(this.mInflater.inflate(R.empty_layout, viewGroup, false));
            case TYPE_SHOP_TERMS_AND_CONDITIONS_LINK /*37*/:
                return new TextViewHolder(this.mInflater, viewGroup, R.link_text, R.link_text);
            case TYPE_SHOP_FAQ_SUBSECTION_HEADING /*39*/:
                return new TranslationToggleViewHolder(this.mInflater, viewGroup, R.shop_home_subsection_with_translate, R.title);
            default:
                return new TextViewHolder(this.mInflater, viewGroup, R.loading_with_description, R.loading_description);
        }
    }

    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        int intValue = ((Integer) ((Pair) this.mItems.get(i)).first).intValue();
        Object obj = ((Pair) this.mItems.get(i)).second;
        switch (intValue) {
            case TYPE_FEATURED_LISTING /*5*/:
            case TYPE_SHOP_LISTING /*10*/:
                ((ListingCardViewHolder) viewHolder).bind((ListingCard) obj, false, false);
            default:
                ((BaseViewHolder) viewHolder).bind(obj);
        }
    }

    public void configureData(@NonNull TabLayout tabLayout, @NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager) {
        int min;
        int i;
        List shopListings;
        List list = this.mItems;
        Context context = this.mContext;
        Resources resources = context.getResources();
        boolean z = this.mShouldUseHorizontalHeaderLayout;
        ShopHomeRouter shopHomeRouter = this.mRouter;
        list.clear();
        shopHomeStateManager.m5608l();
        tabLayout.removeAllTabs();
        ShopHomeTabsUtil.m5617a(tabLayout, resources, ActivityFeedEntity.SHOP, 0);
        ShopV3 shop = shopHomePage.getShop();
        List modules = shop.getModules();
        configureAdapterItemsForHeader(list, shop);
        List featuredListings = shopHomePage.getFeaturedListings();
        int i2 = this.mMaxSpanSize;
        int size = featuredListings.size();
        if (modules.contains(ShopV3.MODULE_FEATURED_ITEMS) && size > 0 && !shop.isVacation()) {
            TabletSupportUtil tabletSupportUtil = this.mTabletSupportUtil;
            if (tabletSupportUtil.m5623c() || tabletSupportUtil.m5626f()) {
                min = Math.min(i2 / TYPE_SHOP_INFO, i2 / featuredListings.size());
            } else {
                min = i2 >> TYPE_SHOP_COVER_PHOTO;
            }
            if (min == (i2 >> TYPE_SHOP_ICON)) {
                i = 0;
            } else {
                i = i2 - (min * size);
            }
            configureAdapterItemsForFeaturedListings(list, featuredListings, shopHomeStateManager, i);
            this.mFeaturedListingSpanSize = min;
            this.mFeaturedListingExtraSpaceSpanSize = i;
        }
        configureAdapterItemsForOwnerSection(list, shop, shopHomeRouter, shopHomeStateManager, context, this, z);
        min = list.size();
        if (((Integer) ((Pair) list.get(min - 1)).first).intValue() == TYPE_ACTION_BUTTONS) {
            this.mFavoriteButtonItemPosition = min - 1;
        }
        if (shop.isVacation()) {
            this.mVacationBannerPosition = list.size();
            list.add(new Pair(Integer.valueOf(TYPE_VACATION_BANNER), new ShopVacationBannerViewData(shop, shopHomeStateManager, resources)));
        } else {
            this.mItemsTabPosition = tabLayout.getTabCount();
            i = list.size();
            ShopHomeTabsUtil.m5617a(tabLayout, resources, ResponseConstants.ITEMS, i);
            if (shop.getActiveListingCount() <= 0) {
                list.add(new Pair(Integer.valueOf(TYPE_EMPTY_LAYOUT), new a(R.shop_home_empty_items, resources.getString(R.shop_home_no_listings), 0)));
            } else {
                configureAdapterItemsForSearchAndFilter(list, shopHomeStateManager);
                this.mSearchBoxPosition = i;
                this.mFilterPosition = i + TYPE_SHOP_COVER_PHOTO;
                shopListings = shopHomePage.getShopListings();
                min = list.size();
                int size2 = shopListings.size();
                if (shopHomeStateManager.m5605i() < 0) {
                    shopHomeStateManager.m5593b(size2);
                }
                if (shopHomeStateManager.m5604h() < 0) {
                    shopHomeStateManager.m5578a(shop.getActiveListingCount());
                }
                configureAdapterItemsForShopListings(list, shopListings, shopHomeStateManager, resources, min);
                this.mListingsEndPosition = list.size() - 1;
                this.mListingsStartPosition = min;
            }
        }
        ShopReviewsResult shopReviews = shopHomePage.getShopReviews();
        ShopHomeTabsUtil.m5617a(tabLayout, resources, ResponseConstants.REVIEWS, list.size());
        configureAdapterItemsForShopReviews(list, shop, shopReviews, context, shopHomeRouter, shopHomeStateManager);
        ShopAbout shopAbout = shopHomePage.getShopAbout();
        if (modules.contains(ShopV3.MODULE_ABOUT) && shopAbout != null) {
            ShopHomeTabsUtil.m5617a(tabLayout, resources, ResponseConstants.ABOUT, list.size());
            configureAdapterItemsForShopAbout(list, shopHomePage, shopAbout, context);
        }
        shopListings = shopHomePage.getLocalMarkets();
        if (modules.contains(ShopV3.MODULE_LOCAL) && shopListings.size() > 0) {
            ShopHomeTabsUtil.m5617a(tabLayout, resources, HomescreenTabsActivity.TAB_PATH_LOCAL, list.size());
            configureAdapterItemsForLocalMarkets(list, shopListings, shopHomeRouter, this.mContext);
        }
        if (shop.isUsingStructuredPolicies()) {
            StructuredShopPolicies structuredShopPolicies = shopHomePage.getStructuredShopPolicies();
            if (structuredShopPolicies != null) {
                ShopHomeTabsUtil.m5617a(tabLayout, resources, ResponseConstants.POLICIES, list.size());
                configureAdapterItemsForStructuredShopPolicies(list, structuredShopPolicies, shop, shopHomeRouter, context, this.mShouldShowTermsAndConditions, shopHomeStateManager);
            }
        } else {
            ShopPolicy shopPolicy = shopHomePage.getShopPolicy();
            if (!(shopPolicy == null || shopPolicy.isEmpty())) {
                ShopHomeTabsUtil.m5617a(tabLayout, resources, ResponseConstants.POLICIES, list.size());
                configureAdapterItemsForShopPolicies(list, shopPolicy, context);
            }
        }
        SellerDetails sellerDetails = shopHomePage.getSellerDetails();
        FAQs fAQs = shopHomePage.getFAQs();
        if ((sellerDetails != null && sellerDetails.hasDetails()) || !fAQs.isEmpty()) {
            ShopHomeTabsUtil.m5617a(tabLayout, resources, "more", list.size());
            configureAdapterItemsForMoreInformation(list, sellerDetails, fAQs, shop, shopHomeRouter, context, shopHomeStateManager);
        }
        list.add(new Pair(Integer.valueOf(TYPE_SECTION_EXTRA_SPACE), ShopHomeSpacingUtil.m5498c(resources)));
        this.mPageData = shopHomePage;
        this.mStateManager = shopHomeStateManager;
        notifyDataSetChanged();
    }

    private static void configureAdapterItemsForHeader(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopV3 shopV3) {
        Image largeBanner = shopV3.getLargeBanner();
        if (shopV3.shouldShowLargeBanner()) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_COVER_PHOTO), largeBanner));
        }
        ShopIconV3 shopIcon = shopV3.getShopIcon();
        if (shopIcon != null) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_ICON), shopIcon));
        }
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_INFO), shopV3));
    }

    private static void configureAdapterItemsForFeaturedListings(@NonNull List<Pair<Integer, ?>> list, @NonNull List<ListingCard> list2, @NonNull ShopHomeStateManager shopHomeStateManager, int i) {
        int size = list2.size();
        int size2 = list.size();
        int i2 = 0;
        while (i2 < size) {
            ListingCard listingCard = (ListingCard) list2.get(i2);
            list.add(new Pair(Integer.valueOf(TYPE_FEATURED_LISTING), listingCard));
            shopHomeStateManager.m5581a(listingCard.getListingId(), size2);
            i2 += TYPE_SHOP_COVER_PHOTO;
            size2 += TYPE_SHOP_COVER_PHOTO;
        }
        List[] listArr = new List[TYPE_SHOP_COVER_PHOTO];
        listArr[0] = list2;
        shopHomeStateManager.m5590a(listArr);
        if (i > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_FEATURED_LISTINGS_EXTRA_SPACE), new g(0, -1, -2)));
        }
    }

    private static void configureAdapterItemsForOwnerSection(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopV3 shopV3, @NonNull ShopHomeRouter shopHomeRouter, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Context context, @NonNull c cVar, boolean z) {
        list.add(new Pair(Integer.valueOf(TYPE_IMAGE_AND_HEADING), new ShopOwnerViewModel(shopHomeRouter, shopV3, context)));
        Resources resources = context.getResources();
        String trim = shopV3.getMessage().trim();
        if (bh.m3343b(trim)) {
            if (z) {
                CharSequence string = resources.getString(R.announcement);
                Date messageUpdateDate = shopV3.getMessageUpdateDate();
                CharSequence charSequence = null;
                if (messageUpdateDate.getTime() > 0) {
                    int i = R.last_updated_on;
                    Object[] objArr = new Object[TYPE_SHOP_COVER_PHOTO];
                    objArr[0] = DATE_FORMAT.format(messageUpdateDate);
                    charSequence = resources.getString(i, objArr);
                }
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_ANNOUNCEMENT_INFO), new k(string, charSequence)));
            }
            Spanned spannableString = new SpannableString(trim);
            Linkify.addLinks(spannableString, TYPE_SHOP_COVER_PHOTO);
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_ANNOUNCEMENT_CONTENT), new b(EtsyLinkify.m5481a(context, spannableString, true, true, null), cVar)));
        }
        list.add(new Pair(Integer.valueOf(TYPE_ACTION_BUTTONS), shopHomeStateManager));
    }

    private static void configureAdapterItemsForSearchAndFilter(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopHomeStateManager shopHomeStateManager) {
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_ITEMS_SEARCH), shopHomeStateManager));
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_ITEMS_FILTER), shopHomeStateManager));
    }

    private static void configureAdapterItemsForShopListings(@NonNull List<Pair<Integer, ?>> list, @NonNull List<ListingCard> list2, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Resources resources, int i) {
        if (list2.size() == 0) {
            list.add(i, new Pair(Integer.valueOf(TYPE_EMPTY_LAYOUT), new a(R.shop_home_empty_items, resources.getString(R.shop_home_no_search_results), 0)));
            return;
        }
        int size = list2.size();
        for (int i2 = 0; i2 < size; i2 += TYPE_SHOP_COVER_PHOTO) {
            ListingCard listingCard = (ListingCard) list2.get(i2);
            list.add(i, new Pair(Integer.valueOf(TYPE_SHOP_LISTING), listingCard));
            shopHomeStateManager.m5581a(listingCard.getListingId(), i);
            i += TYPE_SHOP_COVER_PHOTO;
        }
        List[] listArr = new List[TYPE_SHOP_COVER_PHOTO];
        listArr[0] = list2;
        shopHomeStateManager.m5590a(listArr);
        list.add(i, new Pair(Integer.valueOf(TYPE_BUTTON_BLUE_WITH_DESCRIPTION), getItemsSectionFooter(shopHomeStateManager, resources)));
    }

    private static void configureAdapterItemsForShopAbout(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopHomePage shopHomePage, @NonNull ShopAbout shopAbout, @NonNull Context context) {
        int size;
        ShopV3 shop = shopHomePage.getShop();
        Resources resources = context.getResources();
        int i = R.shop_about_heading;
        Object[] objArr = new Object[TYPE_SHOP_COVER_PHOTO];
        objArr[0] = shop.getName();
        Object string = resources.getString(i, objArr);
        CharSequence spannableString = new SpannableString(string);
        spannableString.setSpan(GraphikUtil.m5544a(context), 0, string.length(), TYPE_SELLER_DETAILS);
        ShopHomeAboutSectionViewModel shopHomeAboutSectionViewModel = new ShopHomeAboutSectionViewModel(context, shopAbout, spannableString, shop.getFavoritesCount());
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_HEADING), shopHomeAboutSectionViewModel));
        if (shopAbout.getImages().size() > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_ABOUT_IMAGES), shopHomeAboutSectionViewModel));
        }
        if (!TextUtils.isEmpty(shopHomeAboutSectionViewModel.getText())) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_CONTENT), shopHomeAboutSectionViewModel));
        }
        List links = shopAbout.getLinks();
        if (shopAbout.getLinks().size() > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.around_the_web))));
            size = links.size();
            int i2 = 0;
            while (i2 < size) {
                boolean z = i2 < size + -1;
                Link link = (Link) links.get(i2);
                if (link.isValid()) {
                    list.add(new Pair(Integer.valueOf(TYPE_RELATED_LINK), new ShopHomeRelatedLinkViewModel(link, z, resources)));
                }
                i2 += TYPE_SHOP_COVER_PHOTO;
            }
        }
        List members = shopAbout.getMembers();
        if (members.size() > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.shop_about_member_title))));
            int size2 = members.size();
            for (i = 0; i < size2; i += TYPE_SHOP_COVER_PHOTO) {
                list.add(new Pair(Integer.valueOf(TYPE_IMAGE_AND_HEADING), new ShopAboutMemberViewModel((ShopAboutMember) members.get(i), context)));
            }
            list.add(new Pair(Integer.valueOf(TYPE_SECTION_EXTRA_SPACE), ShopHomeSpacingUtil.m5498c(resources)));
        }
        members = shopHomePage.getManufacturers();
        if (members.size() > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.shop_home_manufacturers_title))));
            Drawable a = IconDrawable.m775a(resources).m780a(EtsyFontIcons.LOCATION).m781b(R.text_mid_grey).m782c(R.text_small).m777a();
            a.setBounds(0, 0, a.getIntrinsicWidth(), a.getIntrinsicHeight());
            size = members.size();
            for (i = 0; i < size; i += TYPE_SHOP_COVER_PHOTO) {
                Manufacturer manufacturer = (Manufacturer) members.get(i);
                Object capitalize = WordUtils.capitalize(manufacturer.getName());
                Object location = manufacturer.getLocation();
                CharSequence trim = manufacturer.getDescription().trim();
                CharSequence spannableStringBuilder = new SpannableStringBuilder(capitalize);
                int length = capitalize.length();
                spannableStringBuilder.setSpan(GraphikUtil.m5544a(context), 0, length, TYPE_SELLER_DETAILS);
                spannableStringBuilder.append("\n  ");
                spannableStringBuilder.setSpan(new ImageSpan(a, TYPE_SHOP_COVER_PHOTO), length + TYPE_SHOP_COVER_PHOTO, length + TYPE_SHOP_ICON, TYPE_SELLER_DETAILS);
                spannableStringBuilder.append(location);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(R.text_small)), length + TYPE_SHOP_ICON, (length + TYPE_SHOP_INFO) + location.length(), TYPE_SELLER_DETAILS);
                if (trim.length() > 0) {
                    spannableStringBuilder.append("\n").append(trim);
                }
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_CONTENT), new h(spannableStringBuilder)));
            }
            list.add(new Pair(Integer.valueOf(TYPE_SECTION_EXTRA_SPACE), ShopHomeSpacingUtil.m5498c(resources)));
        }
    }

    private static void configureAdapterItemsForShopReviews(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopV3 shopV3, @Nullable ShopReviewsResult shopReviewsResult, @NonNull Context context, @NonNull ShopHomeRouter shopHomeRouter, @NonNull ShopHomeStateManager shopHomeStateManager) {
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_REVIEWS_DESCRIPTION), new com.etsy.android.uikit.viewholder.shop.g((float) shopV3.getAverageRating(), shopV3.getTotalRatingCount())));
        Resources resources = context.getResources();
        if (shopReviewsResult == null || shopReviewsResult.getCount() <= 0) {
            list.add(new Pair(Integer.valueOf(TYPE_EMPTY_LAYOUT), new a(R.shop_home_empty_reviews, resources.getString(R.shop_home_empty_reviews), -1)));
            return;
        }
        List reviews = shopReviewsResult.getReviews();
        int size = reviews.size();
        int size2 = reviews.size();
        for (int i = 0; i < size2; i += TYPE_SHOP_COVER_PHOTO) {
            ReceiptReview receiptReview = (ReceiptReview) reviews.get(i);
            String format = DATE_FORMAT.format(receiptReview.getDate());
            EtsyId userId = receiptReview.getUserId();
            String userDisplayName = receiptReview.getUserDisplayName();
            String userAvatarUrl = receiptReview.getUserAvatarUrl();
            List reviews2 = receiptReview.getReviews();
            int size3 = reviews2.size();
            for (int i2 = 0; i2 < size3; i2 += TYPE_SHOP_COVER_PHOTO) {
                Review review = (Review) reviews2.get(i2);
                AppreciationPhoto appreciationPhoto = review.getAppreciationPhoto();
                ShopHomeReviewViewModel shopHomeReviewViewModel = new ShopHomeReviewViewModel(review, userId, userDisplayName, userAvatarUrl, format, shopHomeRouter, shopHomeStateManager, context);
                list.add(new Pair(Integer.valueOf(TYPE_IMAGE_AND_HEADING), shopHomeReviewViewModel));
                if (review.hasAppreciationPhoto()) {
                    list.add(new Pair(Integer.valueOf(TYPE_REVIEW_APPRECIATION_PHOTO), appreciationPhoto));
                }
                list.add(new Pair(Integer.valueOf(TYPE_REVIEW_RATING), shopHomeReviewViewModel));
                if (bh.m3343b(review.getReviewMessage())) {
                    list.add(new Pair(Integer.valueOf(TYPE_REVIEW_MESSAGE), shopHomeReviewViewModel));
                }
                if (review.hasResponse()) {
                    list.add(new Pair(Integer.valueOf(TYPE_IMAGE_AND_HEADING), new ShopHomeReviewResponseViewData(context, DATE_FORMAT, shopHomeRouter, shopV3.getUserId(), shopV3.getSellerName(), shopV3.getSellerAvatarUrl(), review.getReviewResponse())));
                }
                if (review.isListingDisplayable()) {
                    list.add(new Pair(Integer.valueOf(TYPE_REVIEW_LISTING_INFO), shopHomeReviewViewModel));
                }
            }
        }
        if ((shopReviewsResult.getCount() > size ? TYPE_SHOP_COVER_PHOTO : null) != null) {
            com.etsy.android.uikit.viewholder.a aVar = new com.etsy.android.uikit.viewholder.a(resources.getString(R.all_reviews), new C09941(shopHomeRouter), null);
            aVar.a(TYPE_SHOP_COVER_PHOTO);
            aVar.c = ContextCompat.getColor(context, R.white);
            list.add(new Pair(Integer.valueOf(TYPE_BUTTON_BLUE_WITH_DESCRIPTION), aVar));
        }
    }

    private static void configureAdapterItemsForLocalMarkets(@NonNull List<Pair<Integer, ?>> list, @NonNull List<LocalMarket> list2, @NonNull ShopHomeRouter shopHomeRouter, @NonNull Context context) {
        o.a(context);
        Resources resources = context.getResources();
        CharSequence spannableString = new SpannableString(resources.getString(R.local_header));
        spannableString.setSpan(GraphikUtil.m5544a(context), 0, spannableString.length(), TYPE_SELLER_DETAILS);
        ShopHomeLocalMarketsSectionViewModel shopHomeLocalMarketsSectionViewModel = new ShopHomeLocalMarketsSectionViewModel(spannableString, list2);
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_HEADING), shopHomeLocalMarketsSectionViewModel));
        list.add(new Pair(Integer.valueOf(TYPE_LOCAL_MAP), shopHomeLocalMarketsSectionViewModel));
        int size = list2.size();
        for (int i = 0; i < size; i += TYPE_SHOP_COVER_PHOTO) {
            list.add(new Pair(Integer.valueOf(TYPE_IMAGE_AND_HEADING), new ShopHomeLocalMarketViewModel((LocalMarket) list2.get(i), shopHomeRouter, context)));
        }
        list.add(new Pair(Integer.valueOf(TYPE_SECTION_EXTRA_SPACE), ShopHomeSpacingUtil.m5498c(resources)));
    }

    private static void configureAdapterItemsForShopPolicies(@NonNull List<Pair<Integer, ?>> list, @NonNull ShopPolicy shopPolicy, @NonNull Context context) {
        Resources resources = context.getResources();
        String string = resources.getString(R.policies);
        int i = R.last_updated_on;
        Object[] objArr = new Object[TYPE_SHOP_COVER_PHOTO];
        objArr[0] = DATE_FORMAT.format(shopPolicy.getUpdateDate());
        CharSequence spannableString = new SpannableString(string + "\n" + resources.getString(i, objArr));
        int length = string.length();
        spannableString.setSpan(GraphikUtil.m5544a(context), 0, length, TYPE_SELLER_DETAILS);
        spannableString.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(R.text_small)), length, spannableString.length(), TYPE_SELLER_DETAILS);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.light_grey)), length, spannableString.length(), TYPE_SELLER_DETAILS);
        ShopHomePoliciesSectionViewModel shopHomePoliciesSectionViewModel = new ShopHomePoliciesSectionViewModel(spannableString, shopPolicy, context);
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_HEADING), shopHomePoliciesSectionViewModel));
        if (!TextUtils.isEmpty(shopHomePoliciesSectionViewModel.getText())) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_CONTENT), shopHomePoliciesSectionViewModel));
        }
        List policySectionsInfo = shopHomePoliciesSectionViewModel.getPolicySectionsInfo();
        int size = policySectionsInfo.size();
        for (length = 0; length < size; length += TYPE_SHOP_COVER_PHOTO) {
            Pair pair = (Pair) policySectionsInfo.get(length);
            String str = (String) pair.first;
            CharSequence charSequence = (CharSequence) pair.second;
            if (!TextUtils.isEmpty(charSequence)) {
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(str)));
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_CONTENT), new h(charSequence)));
            }
        }
    }

    private static void configureAdapterItemsForStructuredShopPolicies(@NonNull List<Pair<Integer, ?>> list, @NonNull StructuredShopPolicies structuredShopPolicies, @NonNull ShopV3 shopV3, @NonNull ShopHomeRouter shopHomeRouter, @NonNull Context context, boolean z, @NonNull ShopHomeStateManager shopHomeStateManager) {
        Resources resources = context.getResources();
        Object string = resources.getString(R.shop_structured_policies_heading);
        int i = R.last_updated_on;
        Object[] objArr = new Object[TYPE_SHOP_COVER_PHOTO];
        objArr[0] = DATE_FORMAT.format(structuredShopPolicies.getLastUpdatedDate());
        CharSequence string2 = resources.getString(i, objArr);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.append("\n").append(string2);
        int length = string.length();
        spannableStringBuilder.setSpan(GraphikUtil.m5544a(context), 0, length, TYPE_SELLER_DETAILS);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(R.text_small)), length + TYPE_SHOP_COVER_PHOTO, spannableStringBuilder.length(), TYPE_SELLER_DETAILS);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.light_grey)), length + TYPE_SHOP_COVER_PHOTO, spannableStringBuilder.length(), TYPE_SELLER_DETAILS);
        ShopHomeStructuredPoliciesSectionViewModel shopHomeStructuredPoliciesSectionViewModel = new ShopHomeStructuredPoliciesSectionViewModel(spannableStringBuilder, structuredShopPolicies, shopV3, shopHomeRouter.getStructuredPoliciesViewClickListener(), shopHomeStateManager);
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_HEADING), shopHomeStructuredPoliciesSectionViewModel));
        if (!(shopV3.hasOnlyDigitalListings() || structuredShopPolicies.getShipping() == null)) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.structured_shipping))));
            list.add(new Pair(Integer.valueOf(TYPE_STRUCTURED_POLICIES_SHIPPING), shopHomeStructuredPoliciesSectionViewModel));
        }
        if (shopV3.getDigitalListingCount() > 0) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.structured_shipping_digital_title))));
            list.add(new Pair(Integer.valueOf(TYPE_STRUCTURED_POLICIES_DOWNLOADS), shopHomeStructuredPoliciesSectionViewModel));
        }
        StructuredShopPayments payments = structuredShopPolicies.getPayments();
        if (payments != null && payments.hasPaymentMethods()) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.structured_payment))));
            list.add(new Pair(Integer.valueOf(TYPE_STRUCTURED_POLICIES_PAYMENTS), shopHomeStructuredPoliciesSectionViewModel));
        }
        if (structuredShopPolicies.getRefunds() != null) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.structured_returns_and_exchanges))));
            list.add(new Pair(Integer.valueOf(TYPE_STRUCTURED_POLICIES_REFUNDS), shopHomeStructuredPoliciesSectionViewModel));
        }
        StructuredShopPrivacy privacy = structuredShopPolicies.getPrivacy();
        if (privacy != null && privacy.hasAnyEnabledFlags()) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.structured_privacy_policy))));
            list.add(new Pair(Integer.valueOf(TYPE_STRUCTURED_POLICIES_PRIVACY), shopHomeStructuredPoliciesSectionViewModel));
        }
        if (z) {
            String termsAndConditions = structuredShopPolicies.getTermsAndConditions();
            if (bh.m3343b(termsAndConditions)) {
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.terms_and_conditions_title))));
                list.add(new Pair(Integer.valueOf(TYPE_SHOP_TERMS_AND_CONDITIONS_LINK), new TermsAndConditionsSectionViewModel(resources, termsAndConditions, shopV3, shopHomeRouter)));
            }
        }
    }

    private static void configureAdapterItemsForMoreInformation(@NonNull List<Pair<Integer, ?>> list, @Nullable SellerDetails sellerDetails, @Nullable FAQs fAQs, @NonNull ShopV3 shopV3, @NonNull ShopHomeRouter shopHomeRouter, @NonNull Context context, @NonNull ShopHomeStateManager shopHomeStateManager) {
        Resources resources = context.getResources();
        CharSequence spannableString = new SpannableString(resources.getString(R.shop_section_more_information));
        spannableString.setSpan(GraphikUtil.m5544a(context), 0, spannableString.length(), TYPE_SELLER_DETAILS);
        ShopHomeMoreInfoSectionViewModel shopHomeMoreInfoSectionViewModel = new ShopHomeMoreInfoSectionViewModel(spannableString, sellerDetails, shopV3, shopHomeRouter.getStructuredPoliciesViewClickListener(), resources);
        list.add(new Pair(Integer.valueOf(TYPE_SHOP_SECTION_HEADING), shopHomeMoreInfoSectionViewModel));
        if (!(fAQs == null || fAQs.isEmpty())) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_FAQ_SUBSECTION_HEADING), new FAQTranslationData(resources.getString(R.faq_title), fAQs, shopHomeStateManager)));
            Iterator it = fAQs.iterator();
            while (it.hasNext()) {
                list.add(new Pair(Integer.valueOf(TYPE_FAQ_CONTENT), (FAQ) it.next()));
            }
        }
        if (shopHomeMoreInfoSectionViewModel.getFormattedSellerDetails() != null) {
            list.add(new Pair(Integer.valueOf(TYPE_SHOP_SUBSECTION_HEADING), new h(resources.getString(R.shop_home_section_seller_details))));
            list.add(new Pair(Integer.valueOf(TYPE_SELLER_DETAILS), shopHomeMoreInfoSectionViewModel));
        }
    }

    @Nullable
    public ShopHomePage getPageData() {
        return this.mPageData;
    }

    @Nullable
    public ShopHomeStateManager getStateManager() {
        return this.mStateManager;
    }

    private void deriveConfiguration() {
        int i;
        Context context = this.mContext;
        int integer = context.getResources().getInteger(R.shop_home_max_span_size);
        TabletSupportUtil tabletSupportUtil = new TabletSupportUtil(context);
        boolean d = tabletSupportUtil.m5624d();
        boolean a = tabletSupportUtil.m5621a();
        boolean z = d || a;
        if (z) {
            i = integer >> TYPE_SHOP_ICON;
        } else {
            i = integer;
        }
        this.mOwnerInfoSpanSize = i;
        this.mShopAnnouncementInfoSpanSize = integer >> TYPE_SHOP_ICON;
        if (z) {
            i = integer - this.mShopAnnouncementInfoSpanSize;
        } else {
            i = integer;
        }
        this.mShopAnnouncementContentSpanSize = i;
        i = ((d && a) || tabletSupportUtil.m5623c()) ? integer / TYPE_SHOP_INFO : integer >> TYPE_SHOP_COVER_PHOTO;
        this.mShopListingSpanSize = i;
        if (d && a) {
            this.mIconSpanSize = integer >> TYPE_SHOP_ICON;
        } else if (z) {
            this.mIconSpanSize = integer / TYPE_SHOP_INFO;
        } else {
            this.mIconSpanSize = integer;
        }
        if (z) {
            i = integer - this.mIconSpanSize;
        } else {
            i = integer;
        }
        this.mShopInfoSpanSize = i;
        this.mMaxSpanSize = integer;
        this.mShouldUseHorizontalHeaderLayout = z;
        this.mTabletSupportUtil = tabletSupportUtil;
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        ((BaseViewHolder) viewHolder).recycle();
    }

    public GridLayoutManager getLayoutManager() {
        GridLayoutManager shopHomeLayoutManager = new ShopHomeLayoutManager(this.mContext, this.mMaxSpanSize);
        SpanSizeLookup c09952 = new C09952(this);
        c09952.setSpanIndexCacheEnabled(true);
        shopHomeLayoutManager.setSpanSizeLookup(c09952);
        return shopHomeLayoutManager;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i);
        } else {
            handlePayloadUpdates(viewHolder, i, list);
        }
    }

    private void handlePayloadUpdates(ViewHolder viewHolder, int i, List<Object> list) {
        int intValue = ((Integer) ((Pair) this.mItems.get(i)).first).intValue();
        int size = list.size();
        switch (intValue) {
            case TYPE_SHOP_ITEMS_SEARCH /*8*/:
                ((ShopItemsSearchViewHolder) viewHolder).clearSearch();
            case TYPE_SHOP_ITEMS_FILTER /*9*/:
                for (int i2 = 0; i2 < size; i2 += TYPE_SHOP_COVER_PHOTO) {
                    Object obj = list.get(i2);
                    ShopItemsFilterViewHolder shopItemsFilterViewHolder = (ShopItemsFilterViewHolder) viewHolder;
                    if (obj instanceof Integer) {
                        shopItemsFilterViewHolder.displaySearchDescription(((Integer) obj).intValue());
                    } else if ((obj instanceof String) && HIDE_SEARCH_DESCRIPTION_PAYLOAD.equals(obj)) {
                        shopItemsFilterViewHolder.hideSearchDescription();
                    } else {
                        shopItemsFilterViewHolder.resetFilters();
                    }
                }
            default:
                super.onBindViewHolder(viewHolder, i, list);
        }
    }

    public boolean canRemoveItems() {
        return false;
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public void onRemoveItem(int i) {
    }

    public void configureForNewListingResults(@NonNull ShopListingsSearchResult shopListingsSearchResult, @NonNull TabLayout tabLayout, boolean z, int i) {
        List list = this.mItems;
        String sortOrderId = shopListingsSearchResult.getSortOrderId();
        Collection listings = shopListingsSearchResult.getListings();
        int i2 = this.mListingsStartPosition;
        int i3 = this.mListingsEndPosition;
        ShopHomeStateManager shopHomeStateManager = this.mStateManager;
        if (!z || i2 < 0 || i3 < 0) {
            list.remove(i3);
            notifyItemRemoved(i3);
        } else {
            list.subList(i2, i3 + TYPE_SHOP_COVER_PHOTO).clear();
            notifyItemRangeRemoved(i2, (i3 - i2) + TYPE_SHOP_COVER_PHOTO);
            shopHomeStateManager.m5609m();
        }
        int size = listings.size();
        i3 = z ? size : shopHomeStateManager.m5605i() + size;
        int i4 = i2 + i3;
        if (!z) {
            i2 += shopHomeStateManager.m5605i();
        }
        shopHomeStateManager.m5593b(i3);
        shopHomeStateManager.m5578a(i);
        configureAdapterItemsForShopListings(list, listings, shopHomeStateManager, this.mContext.getResources(), i2);
        notifyItemRangeInserted(i2, size + TYPE_SHOP_COVER_PHOTO);
        i2 = this.mItemsTabPosition;
        if (i2 > 0 && i2 < tabLayout.getTabCount()) {
            Tab tabAt = tabLayout.getTabAt(i2);
            if (tabAt != null) {
                ShopHomeTabsUtil.m5618a(tabLayout, tabAt, i4);
                if (z) {
                    this.mRouter.navigateToTabSection(tabAt, true);
                }
            }
        }
        List shopListings = this.mPageData.getShopListings();
        if (z) {
            this.mPageData.setShopListings(listings);
        } else {
            shopListings.addAll(listings);
        }
        this.mListingsEndPosition = i4;
        shopHomeStateManager.m5597c(sortOrderId);
        notifyItemChanged(this.mFilterPosition);
    }

    public void configureForMemberData(@NonNull ShopHomeMemberData shopHomeMemberData) {
        ShopHomeStateManager shopHomeStateManager = this.mStateManager;
        shopHomeStateManager.m5600d(true);
        shopHomeStateManager.m5589a(shopHomeMemberData.isFavorer());
        notifyItemChanged(this.mFavoriteButtonItemPosition);
        applyListingsMemberInfo(shopHomeMemberData.getListingsInfo());
        if (this.mPageData != null && this.mPageData.getShop().isVacation()) {
            handleVacationSubscriptionStatusChange(shopHomeMemberData.isSubscribedToVacationNotification());
        }
    }

    public void applyListingsMemberInfo(List<ListingMemberData> list) {
        ShopHomeStateManager shopHomeStateManager = this.mStateManager;
        shopHomeStateManager.m5588a((List) list);
        List list2 = this.mItems;
        int size = list2.size();
        int size2 = list.size();
        for (int i = 0; i < size2; i += TYPE_SHOP_COVER_PHOTO) {
            ListingMemberData listingMemberData = (ListingMemberData) list.get(i);
            CircularIntArray b = shopHomeStateManager.m5591b(listingMemberData.getListingId());
            if (b != null) {
                int size3 = b.size();
                for (int i2 = 0; i2 < size3; i2 += TYPE_SHOP_COVER_PHOTO) {
                    int i3 = b.get(i2);
                    if (i3 < size) {
                        Object obj = ((Pair) list2.get(i3)).second;
                        if (obj instanceof ListingLike) {
                            ListingLike listingLike = (ListingLike) obj;
                            boolean hasCollections = listingMemberData.hasCollections();
                            boolean isFavorite = listingMemberData.isFavorite();
                            Object obj2 = ((listingLike.hasCollections() ^ hasCollections) == 0 && (listingLike.isFavorite() ^ isFavorite) == 0) ? null : TYPE_SHOP_COVER_PHOTO;
                            listingLike.setHasCollections(hasCollections);
                            listingLike.setIsFavorite(isFavorite);
                            if (obj2 != null) {
                                notifyItemChanged(i3);
                            }
                        }
                    }
                }
            }
        }
    }

    public void refreshSearchBox() {
        int i = this.mSearchBoxPosition;
        if (i >= 0) {
            notifyItemChanged(i, DEFAULT_PAYLOAD);
        }
    }

    public void hideSearchDescription() {
        int i = this.mFilterPosition;
        if (i >= 0) {
            notifyItemChanged(i, HIDE_SEARCH_DESCRIPTION_PAYLOAD);
        }
    }

    public void refreshFilterSpinners() {
        int i = this.mFilterPosition;
        if (i >= 0) {
            notifyItemChanged(i, DEFAULT_PAYLOAD);
        }
    }

    public void onReadMoreClicked(int i) {
        notifyItemChanged(i);
    }

    public void listingStateChanged(@NonNull EtsyId etsyId, @NonNull Bundle bundle) {
        ShopHomeStateManager shopHomeStateManager = this.mStateManager;
        if (shopHomeStateManager != null) {
            CircularIntArray b = shopHomeStateManager.m5591b(etsyId);
            if (b != null && !b.isEmpty()) {
                List list = this.mItems;
                int size = list.size();
                int size2 = b.size();
                for (int i = 0; i < size2; i += TYPE_SHOP_COVER_PHOTO) {
                    int i2 = b.get(i);
                    if (i2 < size) {
                        Object obj = ((Pair) list.get(i2)).second;
                        if (obj instanceof ListingLike) {
                            ListingLike listingLike = (ListingLike) obj;
                            if (listingLike.getListingId().equals(etsyId)) {
                                boolean z;
                                ListingMemberData a = shopHomeStateManager.m5576a(etsyId);
                                if (bundle.containsKey(EtsyAction.STATE_FAVORITE)) {
                                    z = bundle.getBoolean(EtsyAction.STATE_FAVORITE);
                                    listingLike.setIsFavorite(z);
                                    if (a != null) {
                                        a.setIsFavorite(z);
                                    }
                                }
                                if (bundle.containsKey(EtsyAction.STATE_COLLECTIONS)) {
                                    z = bundle.getBoolean(EtsyAction.STATE_COLLECTIONS);
                                    listingLike.setHasCollections(z);
                                    if (a != null) {
                                        a.setHasCollections(z);
                                    }
                                }
                                notifyItemChanged(i2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void handleVacationSubscriptionStatusChange(boolean z) {
        this.mStateManager.m5595b(z);
        int i = this.mVacationBannerPosition;
        List list = this.mItems;
        if (i >= 0 && i < list.size()) {
            Object obj = ((Pair) list.get(i)).second;
            if (obj instanceof ShopVacationBannerViewData) {
                ((ShopVacationBannerViewData) obj).setUserIsSubscribed(z);
                notifyItemChanged(i);
            }
        }
    }

    public void startedLoadingMoreListings() {
        int i = this.mListingsEndPosition;
        this.mItems.set(i, new Pair(Integer.valueOf(TYPE_LOADING_WITH_DESCRIPTION), new h(this.mContext.getString(R.loading))));
        notifyItemRemoved(i);
        notifyItemInserted(i);
    }

    public void loadMoreListingsDidFail() {
        int i = this.mListingsEndPosition;
        this.mItems.set(i, new Pair(Integer.valueOf(TYPE_BUTTON_BLUE_WITH_DESCRIPTION), getItemsSectionFooter(this.mStateManager, this.mContext.getResources())));
        notifyItemRemoved(i);
        notifyItemInserted(i);
    }

    public static com.etsy.android.uikit.viewholder.a getItemsSectionFooter(@NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Resources resources) {
        TrackingOnClickListener c09963;
        int i;
        int h = shopHomeStateManager.m5604h();
        if ((h > shopHomeStateManager.m5605i() ? TYPE_SHOP_COVER_PHOTO : 0) != 0) {
            c09963 = new C09963(shopHomeStateManager);
            i = TYPE_SHOP_INFO;
        } else {
            c09963 = null;
            i = TYPE_SHOP_ICON;
        }
        String string = resources.getString(R.more_items);
        int i2 = R.shop_home_items_count;
        Object[] objArr = new Object[TYPE_SHOP_ICON];
        objArr[0] = Integer.toString(shopHomeStateManager.m5605i());
        objArr[TYPE_SHOP_COVER_PHOTO] = Integer.toString(h);
        com.etsy.android.uikit.viewholder.a aVar = new com.etsy.android.uikit.viewholder.a(string, c09963, resources.getString(i2, objArr));
        aVar.a(i);
        return aVar;
    }

    public boolean isUsingHorizontalHeaderLayout() {
        return this.mShouldUseHorizontalHeaderLayout;
    }

    public int getAdapterPositionFor(int i, @Nullable Object obj) {
        int itemCount = getItemCount();
        for (int i2 = 0; i2 < itemCount; i2 += TYPE_SHOP_COVER_PHOTO) {
            Pair pair = (Pair) getItem(i2);
            if (pair != null && ((Integer) pair.first).intValue() == i && pair.second == obj) {
                return i2;
            }
        }
        return -1;
    }

    public void notifyShopHomeItemChanged(int i, @Nullable Object obj) {
        int adapterPositionFor = getAdapterPositionFor(i, obj);
        if (adapterPositionFor != -1) {
            notifyItemChanged(adapterPositionFor + getHeaderCount());
        }
    }
}
