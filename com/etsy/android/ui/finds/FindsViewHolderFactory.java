package com.etsy.android.ui.finds;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder;
import com.etsy.android.ui.finds.cardview.FindsCategoryViewHolder;
import com.etsy.android.ui.finds.cardview.FindsHeadingViewHolder;
import com.etsy.android.ui.finds.cardview.FindsListingCardViewHolder;
import com.etsy.android.ui.finds.cardview.FindsSpacerViewHolder;
import com.etsy.android.ui.finds.cardview.FindsTwoTitledListingFooterViewHolder;
import com.etsy.android.ui.finds.cardview.GridShopCardViewHolder;
import com.etsy.android.ui.finds.cardview.listener.FindsHeroBannerViewHolder;
import com.etsy.android.ui.finds.cardview.listener.FindsUrlClickHandler;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.ui.finds.a */
public class FindsViewHolderFactory extends CardViewHolderFactory {
    private static final String f2971h;
    private FindsRecyclerViewAdapter f2972i;
    private Context f2973j;

    static {
        f2971h = EtsyDebug.m1891a(FindsViewHolderFactory.class);
    }

    public FindsViewHolderFactory(Context context, LayoutInflater layoutInflater, ImageBatch imageBatch, ICardViewAdapter iCardViewAdapter, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, layoutInflater, imageBatch, iCardViewAdapter, str, analyticsTracker);
        this.f2972i = (FindsRecyclerViewAdapter) iCardViewAdapter;
        this.f2973j = context;
        m3651a(false);
        this.d.put(Integer.valueOf(22), new FindsUrlClickHandler(this.e, (FragmentActivity) this.f2973j, this.f));
    }

    public BaseViewHolder m4252a(ViewGroup viewGroup, int i) {
        switch (i) {
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case ShopHomeAdapter.TYPE_REVIEW_APPRECIATION_PHOTO /*23*/:
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_REFUNDS /*31*/:
                return new FindsListingCardViewHolder(this.a, viewGroup, (ListingCardClickHandler) this.d.get(Integer.valueOf(4)), this.c);
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return new GridShopCardViewHolder(this.a, viewGroup, (ShopCardClickHandler) this.d.get(Integer.valueOf(i)), this.c, false);
            case R.Toolbar_navigationContentDescription /*21*/:
            case ShopHomeAdapter.TYPE_REVIEW_MESSAGE /*24*/:
                return new FindsHeadingViewHolder(this.a, viewGroup);
            case ShopHomeAdapter.TYPE_REVIEW_RATING /*22*/:
                return new FindsCategoryViewHolder(this.a, viewGroup, (FindsUrlClickHandler) this.d.get(Integer.valueOf(i)), this.c);
            case ShopHomeAdapter.TYPE_REVIEW_LISTING_INFO /*25*/:
                return new FindsTwoTitledListingFooterViewHolder(this.a, viewGroup, (FindsUrlClickHandler) this.d.get(Integer.valueOf(22)));
            case ShopHomeAdapter.TYPE_ACTION_BUTTONS /*26*/:
                return new FindsSpacerViewHolder(this.a, viewGroup);
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PRIVACY /*32*/:
                return new FindsHeroBannerViewHolder(this.a, viewGroup, this.c);
            case ShopHomeAdapter.TYPE_SHOP_TERMS_AND_CONDITIONS_LINK /*37*/:
                return new GiftCardBannerHolder(this.a, viewGroup, this.c);
            default:
                return super.m3650a(viewGroup, i);
        }
    }

    public int m4251a(int i, int i2, int i3) {
        boolean z = this.f2973j.getResources().getBoolean(R.is_phone);
        int i4 = this.f2973j.getResources().getConfiguration().orientation;
        int siblingCountForPosition = this.f2972i.getSiblingCountForPosition(i2);
        switch (i) {
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                switch (this.f2973j.getResources().getInteger(2131558405)) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        return i3;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        if (siblingCountForPosition % 3 == 0) {
                            return i3 / 3;
                        }
                        return i3 / 2;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        if (siblingCountForPosition % 3 == 0) {
                            return i3 / 3;
                        }
                        return i3 / 4;
                }
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return this.f2973j.getResources().getInteger(2131558420);
            case ConnectionResult.RESTRICTED_PROFILE /*20*/:
                return this.f2973j.getResources().getInteger(2131558403);
            case R.Toolbar_navigationContentDescription /*21*/:
                return i3;
            case ShopHomeAdapter.TYPE_REVIEW_RATING /*22*/:
                if (!z || i4 != 1) {
                    return siblingCountForPosition % 3 == 0 ? i3 / 3 : i3 / 4;
                } else if (siblingCountForPosition > 3) {
                    return i3 / 2;
                } else {
                    return i3;
                }
            case ShopHomeAdapter.TYPE_REVIEW_APPRECIATION_PHOTO /*23*/:
                break;
            case ShopHomeAdapter.TYPE_REVIEW_MESSAGE /*24*/:
                return this.f2973j.getResources().getInteger(2131558430);
            case ShopHomeAdapter.TYPE_REVIEW_LISTING_INFO /*25*/:
                return this.f2973j.getResources().getInteger(2131558430);
            case ShopHomeAdapter.TYPE_ACTION_BUTTONS /*26*/:
                return this.f2973j.getResources().getInteger(2131558432);
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PAYMENTS /*30*/:
                return this.f2973j.getResources().getInteger(2131558406);
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_REFUNDS /*31*/:
                return this.f2973j.getResources().getInteger(2131558404);
            default:
                return super.m3647a(i, i2, i3);
        }
        return this.f2973j.getResources().getInteger(2131558431);
    }
}
