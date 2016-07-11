package com.etsy.android.ui.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.apiv3.cart.IMultiShopCartElement;
import com.etsy.android.ui.cardview.BaseViewHolderFactory;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.ui.cart.p015a.CartListingClickHandler;
import com.etsy.android.ui.cart.viewholders.CartListingViewHolder;
import com.etsy.android.ui.cart.viewholders.CheckoutSectionViewHolder;
import com.etsy.android.ui.cart.viewholders.HorizontalCartGroupViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageBannerViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageBubbleViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageToSellerViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentAddCouponViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentAppliedCouponViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentApplyGiftCardViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentHeaderViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentOptionsDividerViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentOptionsViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentShippingDestinationViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentTotalsLineItemViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentTotalsNoteViewHolder;
import com.etsy.android.ui.cart.viewholders.ShippingDetailsViewHolder;
import com.etsy.android.ui.cart.viewholders.ShopCartHeaderViewHolder;
import com.etsy.android.ui.cart.viewholders.VerticalCartGroupViewHolder;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* compiled from: MultiShopCartViewHolderFactory */
public class ab extends BaseViewHolderFactory<IMultiShopCartElement> {
    protected RecycledViewPool f2392g;
    protected TabletSupportUtil f2393h;
    protected aa f2394i;
    @NonNull
    private final Context f2395j;

    public ab(@NonNull Context context, @NonNull LayoutInflater layoutInflater, @NonNull ImageBatch imageBatch, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, layoutInflater, imageBatch, str, analyticsTracker);
        this.f2395j = context;
        this.f2393h = new TabletSupportUtil(context);
    }

    public void m3737a(@NonNull aa aaVar) {
        FragmentActivity fragmentActivity = (FragmentActivity) this.f2395j;
        this.f2394i = aaVar;
        this.d.put(Integer.valueOf(1), new ShopCardClickHandler(this.e, fragmentActivity, this.f));
        this.d.put(Integer.valueOf(2), new CartListingClickHandler(this.f2394i, this.e, fragmentActivity, this.f));
        CartGroupActionClickHandler cartGroupActionClickHandler = new CartGroupActionClickHandler(this.f2394i, this.e, fragmentActivity, this.f);
        this.d.put(Integer.valueOf(4), cartGroupActionClickHandler);
        this.d.put(Integer.valueOf(3), cartGroupActionClickHandler);
        this.d.put(Integer.valueOf(8), cartGroupActionClickHandler);
        this.d.put(Integer.valueOf(14), cartGroupActionClickHandler);
        this.d.put(Integer.valueOf(10), cartGroupActionClickHandler);
        this.d.put(Integer.valueOf(13), cartGroupActionClickHandler);
    }

    public BaseViewHolder m3736a(ViewGroup viewGroup, int i) {
        boolean z = true;
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return new ShopCartHeaderViewHolder(this.a, viewGroup, this.c, (ShopCardClickHandler) this.d.get(Integer.valueOf(i)));
            case Task.NETWORK_STATE_ANY /*2*/:
            case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                LayoutInflater layoutInflater = this.a;
                ImageBatch imageBatch = this.c;
                CartListingClickHandler cartListingClickHandler = (CartListingClickHandler) this.d.get(Integer.valueOf(i));
                if (i != 2) {
                    z = false;
                }
                return new CartListingViewHolder(layoutInflater, viewGroup, imageBatch, cartListingClickHandler, z);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return new ShippingDetailsViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return new MessageToSellerViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return new PaymentOptionsViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
            case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                LayoutInflater layoutInflater2 = this.a;
                b bVar = (b) this.d.get(Integer.valueOf(i));
                if (i != 11) {
                    z = false;
                }
                return new PaymentTotalsLineItemViewHolder(layoutInflater2, viewGroup, bVar, z);
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return new PaymentAppliedCouponViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                return new PaymentAddCouponViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return new CheckoutSectionViewHolder(this.a, viewGroup, (b) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return new MessageBubbleViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                return new PaymentTotalsNoteViewHolder(this.a, viewGroup);
            case CommonStatusCodes.ERROR /*13*/:
                return new MessageBannerViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.INTERRUPTED /*14*/:
                return new PaymentApplyGiftCardViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            case CommonStatusCodes.TIMEOUT /*15*/:
                return new PaymentHeaderViewHolder(this.a, viewGroup);
            case CommonStatusCodes.CANCELED /*16*/:
                return new PaymentOptionsDividerViewHolder(this.a, viewGroup);
            case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                if (this.f2392g == null) {
                    this.f2392g = new RecycledViewPool();
                }
                if (this.f2393h.m5624d()) {
                    return new HorizontalCartGroupViewHolder(this.a, viewGroup, this);
                }
                return new VerticalCartGroupViewHolder(this.a, viewGroup, this);
            case ConnectionResult.SERVICE_UPDATING /*18*/:
                return new PaymentShippingDestinationViewHolder(this.a, viewGroup, (CartGroupActionClickHandler) this.d.get(Integer.valueOf(i)));
            default:
                return new BaseViewHolder(this.a.inflate(R.list_item_group_divider, viewGroup, false));
        }
    }

    public RecycledViewPool m3735a() {
        return this.f2392g;
    }

    public int m3734a(int i, int i2, int i3) {
        return 1;
    }
}
