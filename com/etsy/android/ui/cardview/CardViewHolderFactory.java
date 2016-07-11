package com.etsy.android.ui.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.SearchGroup;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.apiv3.UserCard;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.LoadingCardViewElement;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.lib.models.cardviewelement.SearchFilterHeader;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.homescreen.SeasonalSegmentCard;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.ui.cardview.p014a.CategoryCardClickHandler;
import com.etsy.android.ui.cardview.p014a.CategoryRecCardClickHandler;
import com.etsy.android.ui.cardview.p014a.DeepLinkUrlClickHandler;
import com.etsy.android.ui.cardview.p014a.FindsClickHandler;
import com.etsy.android.ui.cardview.p014a.ListSectionFooterClickHandler;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.ui.cardview.p014a.MessageCardClickHandler;
import com.etsy.android.ui.cardview.p014a.SavedCartClickHandler;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.ui.cardview.p014a.ShopShareCardClickHandler;
import com.etsy.android.ui.cardview.viewholders.BannerViewHolder;
import com.etsy.android.ui.cardview.viewholders.CategoryCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.CategoryRecCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.EtsyAsapSearchTooltipViewHolder;
import com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder;
import com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder2;
import com.etsy.android.ui.cardview.viewholders.FindsSmallCrosslinkViewHolder;
import com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder;
import com.etsy.android.ui.cardview.viewholders.HorizontalListSectionViewHolder;
import com.etsy.android.ui.cardview.viewholders.LeftAlignedAllCapsHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.ListSectionFooterViewHolder;
import com.etsy.android.ui.cardview.viewholders.ListSectionHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.LoadingCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.MessageCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.SearchFilterHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.SearchGroupViewHolder;
import com.etsy.android.ui.cardview.viewholders.SeasonalSegmentCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.TaxonomyCategoryRowViewHolder;
import com.etsy.android.ui.finds.cardview.GridShopCardViewHolder;
import com.etsy.android.ui.user.profile.viewholders.AppreciationPhotoCardViewHolder;
import com.etsy.android.uikit.cardview.EtsyAsapSearchTooltip;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.ui.cardview.b */
public class CardViewHolderFactory extends BaseViewHolderFactory<ICardViewElement> {
    final String f2259g;
    private boolean f2260h;
    private boolean f2261i;

    public CardViewHolderFactory(Context context, LayoutInflater layoutInflater, ImageBatch imageBatch, ICardViewAdapter iCardViewAdapter, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, layoutInflater, imageBatch, str, analyticsTracker);
        this.f2259g = EtsyDebug.m1891a(CardViewHolderFactory.class);
        this.f2260h = false;
        this.f2261i = false;
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        this.d.put(Integer.valueOf(4), new ListingCardClickHandler(this.e, fragmentActivity, iCardViewAdapter, this.f));
        this.d.put(Integer.valueOf(5), new ShopCardClickHandler(this.e, fragmentActivity, this.f));
        this.d.put(Integer.valueOf(11), new CategoryRecCardClickHandler(this.e, fragmentActivity, this.f));
        this.d.put(Integer.valueOf(3), new ListSectionFooterClickHandler(this.e, fragmentActivity, this.f));
        this.d.put(Integer.valueOf(9), new MessageCardClickHandler(this.e, fragmentActivity, this.f));
        this.d.put(Integer.valueOf(35), new DeepLinkUrlClickHandler(this.e, fragmentActivity, this.f));
        FindsClickHandler findsClickHandler = new FindsClickHandler(this.e, fragmentActivity, this.f);
        this.d.put(Integer.valueOf(20), findsClickHandler);
        this.d.put(Integer.valueOf(30), findsClickHandler);
        this.d.put(Integer.valueOf(29), findsClickHandler);
        this.d.put(Integer.valueOf(17), new ShopShareCardClickHandler(this.e, fragmentActivity, iCardViewAdapter, this.f));
        this.d.put(Integer.valueOf(18), new ShopShareCardClickHandler(this.e, fragmentActivity, iCardViewAdapter, this.f));
        CategoryCardClickHandler categoryCardClickHandler = new CategoryCardClickHandler(this.e, fragmentActivity, this.f);
        this.d.put(Integer.valueOf(7), categoryCardClickHandler);
        this.d.put(Integer.valueOf(10), categoryCardClickHandler);
    }

    public BaseViewHolder m3650a(ViewGroup viewGroup, int i) {
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return new LoadingCardViewHolder(this.a, viewGroup);
            case Task.NETWORK_STATE_ANY /*2*/:
                return new ListSectionHeaderViewHolder(this.a, viewGroup);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return new ListSectionFooterViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)), this.f2261i);
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case ShopHomeAdapter.TYPE_REVIEW_APPRECIATION_PHOTO /*23*/:
                return new ListingCardViewHolder(this.a, viewGroup, (ListingCardClickHandler) this.d.get(Integer.valueOf(4)), this.c, this.f2261i, m3652a());
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                if (this.f.m1847c().m885c(EtsyConfigKeys.co)) {
                    return new GridShopCardViewHolder(this.a, viewGroup, (ShopCardClickHandler) this.d.get(Integer.valueOf(i)), this.c, this.f2261i);
                }
                return new ShopCardViewHolder(this.a, viewGroup, (ShopCardClickHandler) this.d.get(Integer.valueOf(i)), this.c, this.f2261i);
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return new CategoryCardViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)), this.c, this.f2261i);
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                return new HorizontalListSectionViewHolder(this.a, viewGroup, this.c, this.e);
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return new MessageCardViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return new SeasonalSegmentCardViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)), this.c);
            case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                return new CategoryRecCardViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)), this.c);
            case CommonStatusCodes.ERROR /*13*/:
                return new SearchFilterHeaderViewHolder(this.a, (b) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.INTERRUPTED /*14*/:
                return new SearchGroupViewHolder(this.a, viewGroup, false, (b) this.d.get(Integer.valueOf(i)), this.c);
            case CommonStatusCodes.TIMEOUT /*15*/:
                return new TaxonomyCategoryRowViewHolder(this.a, viewGroup, false, (b) this.d.get(Integer.valueOf(i)), this.c);
            case CommonStatusCodes.CANCELED /*16*/:
                return new AppreciationPhotoCardViewHolder(this.a.inflate(2130903546, viewGroup, false), this.f2261i, this.e);
            case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                return new ShopShareCardViewHolder(this.a, viewGroup, (ShopShareCardClickHandler) this.d.get(Integer.valueOf(i)), this.c, 2130903306, (ListingCardClickHandler) this.d.get(Integer.valueOf(4)));
            case ConnectionResult.SERVICE_UPDATING /*18*/:
                return new ShopShareCardViewHolder(this.a, viewGroup, (ShopShareCardClickHandler) this.d.get(Integer.valueOf(i)), this.c, 2130903305, null).setShowMenuIcon(false);
            case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                return new LeftAlignedAllCapsHeaderViewHolder(this.a, viewGroup);
            case ConnectionResult.RESTRICTED_PROFILE /*20*/:
                if (this.f.m1847c().m885c(EtsyConfigKeys.f1194M)) {
                    return new FindsCrosslinkViewHolder2(this.a, viewGroup, (FindsClickHandler) this.d.get(Integer.valueOf(i)), this.c, this.f2261i, false);
                }
                return new FindsCrosslinkViewHolder(this.a, viewGroup, (FindsClickHandler) this.d.get(Integer.valueOf(i)), this.c, this.f2261i, false);
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PAYMENTS /*30*/:
                return new FindsSmallCrosslinkViewHolder(this.a, viewGroup, (FindsClickHandler) this.d.get(Integer.valueOf(i)), this.c);
            case ShopHomeAdapter.TYPE_FAQ_CONTENT /*34*/:
                return new AnchorListingCardViewHolder(this.a, viewGroup, (ListingCardClickHandler) this.d.get(Integer.valueOf(4)), this.c);
            case ShopHomeAdapter.TYPE_EMPTY_LAYOUT /*35*/:
                return new BannerViewHolder(this.a, viewGroup, (DeepLinkUrlClickHandler) this.d.get(Integer.valueOf(i)), this.c);
            case ShopHomeAdapter.TYPE_SECTION_EXTRA_SPACE /*36*/:
                return new EtsyAsapSearchTooltipViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)));
            case ShopHomeAdapter.TYPE_SHOP_TERMS_AND_CONDITIONS_LINK /*37*/:
                return new GiftCardBannerHolder(this.a, viewGroup, this.c);
            case ShopHomeAdapter.TYPE_SHOP_FAQ_SUBSECTION_HEADING /*39*/:
                return new SavedCartListingCardViewHolder(this.a, viewGroup, (SavedCartClickHandler) this.d.get(Integer.valueOf(i)), this.c);
            default:
                return new BaseViewHolder(this.a.inflate(R.list_item_group_divider, viewGroup, false));
        }
    }

    public int m3647a(int i, int i2, int i3) {
        switch (i) {
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
            case CommonStatusCodes.INTERRUPTED /*14*/:
                if (i3 == 12) {
                    return 4;
                }
                return 1;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                if (i3 == 12) {
                    return 6;
                }
                if (i3 == 4) {
                    return 2;
                }
                return i3;
            case ConnectionResult.SERVICE_UPDATING /*18*/:
                if (i3 == 12) {
                    return 6;
                }
                return i3;
            case ConnectionResult.RESTRICTED_PROFILE /*20*/:
                if (i3 == 12) {
                    return 6;
                }
                return i3;
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PAYMENTS /*30*/:
                if (i3 == 12 || i3 == 4) {
                    return i3 / 4;
                }
                return i3 / 2;
            default:
                return i3;
        }
    }

    public void m3651a(boolean z) {
        this.f2261i = z;
    }

    public int m3649a(ICardViewElement iCardViewElement) {
        int viewType = iCardViewElement.getViewType();
        return viewType != -1 ? viewType : m3653b(iCardViewElement);
    }

    @Deprecated
    public int m3653b(ICardViewElement iCardViewElement) {
        if (iCardViewElement.getClass() == LoadingCardViewElement.class) {
            return 1;
        }
        if (iCardViewElement.getClass() == BasicSectionHeader.class) {
            return 2;
        }
        if (iCardViewElement instanceof PageLink) {
            return 3;
        }
        if (iCardViewElement.getClass() == ListingCard.class) {
            return 4;
        }
        if (iCardViewElement.getClass() == UserCard.class) {
            return 6;
        }
        if (iCardViewElement.getClass() == ShopCard.class) {
            return 5;
        }
        if (iCardViewElement.getClass() == ListSection.class) {
            return 8;
        }
        if (iCardViewElement.getClass() == MessageCard.class) {
            return 9;
        }
        if (iCardViewElement.getClass() == SeasonalSegmentCard.class) {
            return 10;
        }
        if (iCardViewElement.getClass() == Segment.class) {
            return 7;
        }
        if (iCardViewElement.getClass() == CategoryRecommendationCard.class) {
            return 11;
        }
        if (iCardViewElement.getClass() == SearchFilterHeader.class) {
            return 13;
        }
        if (iCardViewElement.getClass() == SearchGroup.class) {
            return 14;
        }
        if (iCardViewElement.getClass() == TaxonomyCategory.class) {
            return 15;
        }
        if (iCardViewElement.getClass() == AppreciationPhoto.class) {
            return 16;
        }
        if (iCardViewElement.getClass() == ShopShareCard.class) {
            return 17;
        }
        if (iCardViewElement.getClass() == EtsyAsapSearchTooltip.class) {
            return 36;
        }
        if (iCardViewElement.getClass() == FindsCard.class) {
            return 20;
        }
        return 0;
    }

    public void m3654b(boolean z) {
        this.f2260h = z;
    }

    public boolean m3652a() {
        return this.f2260h;
    }

    public static int m3646a(Context context) {
        return context.getResources().getInteger(2131558407);
    }
}
