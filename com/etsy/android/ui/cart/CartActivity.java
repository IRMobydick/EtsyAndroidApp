package com.etsy.android.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletCartHelper;
import com.etsy.android.ui.nav.Nav;

public class CartActivity extends BOENavDrawerActivity implements GoogleWalletCartHelper {
    private static final String FRAGMENT_TAG = "cartFragment";
    public static final String INT_CART_ID = "cart_id";
    public static final String OBJ_ALTERNATIVE_PAYMENT_METHOD = "alternative_payment_method";
    @Deprecated
    public static final String TEXT_CART_PAYMENT_METHOD = "payment_method";
    private GoogleWalletCartHelper mGoogleWalletHelper;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CartFragment.CHECKED_OUT_CART) && intent.hasExtra(CartFragment.LAST_ORDER_ID)) {
            Cart cart = (Cart) intent.getSerializableExtra(CartFragment.CHECKED_OUT_CART);
            EtsyId etsyId = (EtsyId) intent.getSerializableExtra(CartFragment.LAST_ORDER_ID);
            boolean booleanExtra = intent.getBooleanExtra("should_show_social_invites_prompt", false);
            if (etsyId == null) {
                etsyId = new EtsyId();
            }
            Nav.m4682a((FragmentActivity) this).m4684b().m4626a(FRAGMENT_TAG).m4616a(cart, etsyId, booleanExtra);
        } else {
            Nav.m4682a((FragmentActivity) this).m4684b().m4626a(FRAGMENT_TAG).m4656i();
        }
        if (getConfigMap().m885c(EtsyConfigKeys.f1239a)) {
            this.mGoogleWalletHelper = new GoogleWalletCartHelper(this);
        }
    }

    protected void onStart() {
        super.onStart();
        if (this.mGoogleWalletHelper != null) {
            this.mGoogleWalletHelper.m3809b();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.mGoogleWalletHelper != null) {
            this.mGoogleWalletHelper.m3810c();
        }
    }

    public void onDestroy() {
        this.mGoogleWalletHelper = null;
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        boolean handleActivityResult;
        CartFragment cartFragment = (CartFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (cartFragment != null) {
            handleActivityResult = cartFragment.handleActivityResult(i, i2, intent);
        } else {
            handleActivityResult = false;
        }
        if (!handleActivityResult) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public GoogleWalletCartHelper getGoogleWalletHelper() {
        return this.mGoogleWalletHelper;
    }
}
