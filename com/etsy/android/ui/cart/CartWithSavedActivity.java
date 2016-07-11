package com.etsy.android.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.view.FragmentViewPager;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.Serializable;
import org.parceler.Parcels;

/* renamed from: com.etsy.android.ui.cart.y */
class CartWithSavedActivity extends FragmentPagerAdapter {
    Bundle f2634a;
    int f2635b;
    int f2636c;
    CartFragment f2637d;
    SavedCartItemsFragment f2638e;
    final /* synthetic */ CartWithSavedActivity f2639f;

    public CartWithSavedActivity(CartWithSavedActivity cartWithSavedActivity, CartPage cartPage, String str) {
        this.f2639f = cartWithSavedActivity;
        super(cartWithSavedActivity.getSupportFragmentManager());
        this.f2635b = 0;
        this.f2636c = 0;
        if (cartPage != null) {
            this.f2634a = new Bundle();
            this.f2634a.putParcelable(SavedCartItemsFragment.PAGE, Parcels.m7494a((Object) cartPage));
            this.f2634a.putString(SavedCartItemsFragment.NEXT_LINK, str);
            this.f2635b = cartPage.getCartCount();
            this.f2636c = cartPage.getSavedCount();
        }
    }

    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (this.f2637d == null) {
                    this.f2637d = new CartFragment();
                    bundle.putBoolean(CartFragment.ARG_SAVE_FOR_LATER_ENABLED, true);
                    Intent intent = this.f2639f.getIntent();
                    if (intent != null && intent.hasExtra(CartFragment.CHECKED_OUT_CART) && intent.hasExtra(CartFragment.LAST_ORDER_ID)) {
                        Cart cart = (Cart) intent.getSerializableExtra(CartFragment.CHECKED_OUT_CART);
                        Serializable serializable = (EtsyId) intent.getSerializableExtra(CartFragment.LAST_ORDER_ID);
                        if (serializable == null) {
                            serializable = new EtsyId();
                        }
                        bundle.putSerializable(CartFragment.CHECKED_OUT_CART, cart);
                        bundle.putSerializable(CartFragment.LAST_ORDER_ID, serializable);
                        bundle.putBoolean("should_show_social_invites_prompt", false);
                    }
                    this.f2637d.setArguments(bundle);
                }
                return this.f2637d;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (this.f2638e == null) {
                    this.f2638e = new SavedCartItemsFragment();
                    if (this.f2634a != null) {
                        this.f2638e.setArguments(this.f2634a);
                    }
                }
                return this.f2638e;
            case Task.NETWORK_STATE_ANY /*2*/:
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                bundle.putBoolean(MultiShopCartFragment.MOCK_DATA, true);
                break;
            default:
                return null;
        }
        Fragment multiShopCartFragment = new MultiShopCartFragment();
        multiShopCartFragment.setArguments(bundle);
        return multiShopCartFragment;
    }

    public int getCount() {
        if (this.f2639f.mEnableMSCO) {
            return 4;
        }
        return 2;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return this.f2639f.getString(R.tab_title_cart, new Object[]{Integer.valueOf(this.f2635b)});
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return this.f2639f.getString(R.tab_title_saved, new Object[]{Integer.valueOf(this.f2636c)});
            case Task.NETWORK_STATE_ANY /*2*/:
                return "MSCO Cart";
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "MSCO Mock Cart";
            default:
                return "<unknown>";
        }
    }

    void m3907a(int i, int i2) {
        this.f2635b = i;
        this.f2636c = i2;
    }

    @Nullable
    private Fragment m3906a(FragmentViewPager fragmentViewPager, int i) {
        return this.f2639f.getSupportFragmentManager().findFragmentByTag(FragmentViewPager.createFragmentName(fragmentViewPager.getId(), getItemId(i)));
    }
}
