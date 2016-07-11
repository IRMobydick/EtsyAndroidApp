package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.bl;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.List;

/* renamed from: com.etsy.android.ui.cardview.a.h */
public class SavedCartClickHandler extends b<SavedCart> {
    private CardViewFactoryRecyclerViewAdapter f2244a;

    /* renamed from: com.etsy.android.ui.cardview.a.h.1 */
    class SavedCartClickHandler extends EtsyTrioDialogFragment {
        final /* synthetic */ SavedCart f2237a;
        final /* synthetic */ int f2238b;
        final /* synthetic */ SavedCartClickHandler f2239c;

        SavedCartClickHandler(SavedCartClickHandler savedCartClickHandler, SavedCart savedCart, int i) {
            this.f2239c = savedCartClickHandler;
            this.f2237a = savedCart;
            this.f2238b = i;
        }

        public void m3599a() {
            this.f2239c.m3611a(this.f2237a, this.f2238b);
        }

        public void m3600b() {
            this.f2239c.m3614b(this.f2237a, this.f2238b);
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.a.h.2 */
    class SavedCartClickHandler extends EtsyApiV3RequestJob<CartPage> {
        final /* synthetic */ SavedCart f2240a;
        final /* synthetic */ String f2241b;
        final /* synthetic */ int f2242c;
        final /* synthetic */ SavedCartClickHandler f2243d;

        SavedCartClickHandler(SavedCartClickHandler savedCartClickHandler, SavedCart savedCart, String str, int i) {
            this.f2243d = savedCartClickHandler;
            this.f2240a = savedCart;
            this.f2241b = str;
            this.f2242c = i;
        }

        public void m3603a(@NonNull List<CartPage> list, int i, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            this.f2240a.getViewState().setIsLoading(false);
            if (!TextUtils.isEmpty(this.f2241b)) {
                bl.m3356a(this.f2243d.c, this.f2241b);
            }
            this.f2243d.f2244a.removeItem((Object) this.f2240a);
            CartPage cartPage = (CartPage) etsyV3Result.m1057h();
            CartRefreshDelegate.sendBroadcast(this.f2243d.c, cartPage.getCartCount(), cartPage.getSavedCount(), true, 2);
        }

        public void m3601a(int i, @Nullable String str, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            this.f2240a.getViewState().setIsLoading(false);
            this.f2243d.f2244a.notifyItemChanged(this.f2242c);
        }
    }

    public SavedCartClickHandler(String str, FragmentActivity fragmentActivity, CardViewFactoryRecyclerViewAdapter cardViewFactoryRecyclerViewAdapter, @NonNull ad adVar) {
        super(str, fragmentActivity, adVar);
        this.f2244a = cardViewFactoryRecyclerViewAdapter;
    }

    public void m3610a(SavedCart savedCart) {
    }

    public void m3609a(CartListing cartListing) {
        Nav.m4682a(this.c).m4683a().m4467a(cartListing.getListingId());
    }

    protected void m3611a(SavedCart savedCart, int i) {
        m3606a(savedCart, i, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s", null);
    }

    public void m3614b(SavedCart savedCart, int i) {
        m3606a(savedCart, i, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s/to-favorites", this.c.getString(R.toast_move_to_favorites));
    }

    public void m3615c(SavedCart savedCart, int i) {
        m3606a(savedCart, i, "/etsyapps/v3/bespoke/member/carts/saved-for-later/%s/to-cart", this.c.getString(R.toast_move_to_cart));
    }

    public void m3616d(SavedCart savedCart, int i) {
        Nav.m4682a(this.c).m4686d().m4412a(new SavedCartClickHandler(this, savedCart, i), (int) R.yes, (int) R.no, (int) R.move_item_to_favorites, this.c.getString(R.remove_item_confirm_msg));
    }

    private void m3606a(SavedCart savedCart, int i, String str, String str2) {
        savedCart.getViewState().setIsLoading(true);
        this.f2244a.notifyItemChanged(i);
        aj.m1101a().m1123i().m1696a((Object) this, (EtsyApiV3RequestJob) ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(CartPage.class, String.format(str, new Object[]{savedCart.getId().getId()})).m1382a(3)).m1393d()).m1422a(new SavedCartClickHandler(this, savedCart, str2, i), this.c)).m1426c());
    }

    public void m3612a(EtsyId etsyId) {
        Nav.m4681a(this.c).m4501b(etsyId);
    }
}
