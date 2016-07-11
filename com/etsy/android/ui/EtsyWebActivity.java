package com.etsy.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.ui.cart.CartFragment;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletHelperBase;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletWebViewHelper;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import java.util.HashMap;

public class EtsyWebActivity extends BOENavDrawerActivity {
    private static final String FRAGMENT_TAG_CART = "cartWebFragment";
    public static final int TYPE_ABOUT = 0;
    public static final int TYPE_CART = 2;
    public static final int TYPE_ELECTRONIC_COMMUNICATIONS_POLICY = 7;
    public static final int TYPE_ETSY_LOCAL_HELP = 4;
    public static final int TYPE_FOE_LEARN_MORE = 9;
    public static final int TYPE_GENERIC_ETSY_HELP = 11;
    public static final int TYPE_LEGAL = 10;
    public static final int TYPE_LISTING_MORE_INFO = 8;
    public static final int TYPE_NOTIFICATIONS = 1;
    public static final int TYPE_ORDER_TRACKING = 3;
    public static final int TYPE_PRIVACY_POLICY = 6;
    public static final int TYPE_TERMS_OF_USE = 5;
    private GoogleWalletWebViewHelper mGoogleWalletWebViewHelper;
    private int mType;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mType = getIntent().getExtras().getInt(FindsModule.FIELD_TYPE);
        if (bundle == null) {
            switch (this.mType) {
                case TYPE_ABOUT /*0*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4658j();
                    break;
                case TYPE_NOTIFICATIONS /*1*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4663o();
                    break;
                case TYPE_CART /*2*/:
                    addCheckoutFragment();
                    break;
                case TYPE_ORDER_TRACKING /*3*/:
                    if (!getIntent().getExtras().containsKey(ResponseConstants.URL)) {
                        Nav.m4682a((FragmentActivity) this).m4684b().m4632b((EtsyId) getIntent().getExtras().getSerializable(ResponseConstants.RECEIPT_ID), (EtsyId) getIntent().getExtras().getSerializable(ResponseConstants.RECEIPT_SHIPPING_ID));
                        break;
                    }
                    Nav.m4682a((FragmentActivity) this).m4684b().m4637c(getIntent().getExtras().getString(ResponseConstants.URL));
                    break;
                case TYPE_ETSY_LOCAL_HELP /*4*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4644e(getIntent().getExtras().getString(ResponseConstants.URL));
                    break;
                case TYPE_TERMS_OF_USE /*5*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4659k();
                    break;
                case TYPE_PRIVACY_POLICY /*6*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4660l();
                    break;
                case TYPE_ELECTRONIC_COMMUNICATIONS_POLICY /*7*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4662n();
                    break;
                case TYPE_LISTING_MORE_INFO /*8*/:
                case TYPE_FOE_LEARN_MORE /*9*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4655i(getIntent().getExtras().getString(ResponseConstants.URL));
                    break;
                case TYPE_LEGAL /*10*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4661m();
                    break;
                case TYPE_GENERIC_ETSY_HELP /*11*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4640d(getIntent().getExtras().getString(ResponseConstants.URL));
                    break;
            }
        }
        if (shouldUseNavStyleModal()) {
            setNavStyleModal();
        }
        setTitle();
    }

    private void addCheckoutFragment() {
        Cart cart = (Cart) getIntent().getExtras().getSerializable(CartFragment.CHECKED_OUT_CART);
        HashMap hashMap = null;
        if (cart != null && cart.getLastPaymentMethod() != null && cart.getLastPaymentMethod().isGoogleWallet() && GoogleWalletHelperBase.m3806a(getAnalyticsContext())) {
            this.mGoogleWalletWebViewHelper = new GoogleWalletWebViewHelper(this, getIntent().getExtras().getString("google_wallet_transaction_id"));
            hashMap = (HashMap) getIntent().getExtras().getSerializable("google_wallet_web_params");
        }
        Nav.m4682a((FragmentActivity) this).m4684b().m4626a(FRAGMENT_TAG_CART).m4615a(cart, hashMap);
    }

    protected void onStart() {
        super.onStart();
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.m3809b();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.m3810c();
        }
    }

    public void onDestroy() {
        this.mGoogleWalletWebViewHelper = null;
        super.onDestroy();
    }

    private void setTitle() {
        switch (this.mType) {
            case TYPE_ABOUT /*0*/:
                setTitle((int) R.about_app);
            case TYPE_NOTIFICATIONS /*1*/:
                setTitle((int) R.notifications);
            case TYPE_CART /*2*/:
                setTitle((int) R.checkout);
            case TYPE_ORDER_TRACKING /*3*/:
                setTitle((int) R.track_package);
            case TYPE_ETSY_LOCAL_HELP /*4*/:
                setTitle((int) R.local_help_page_title);
            case TYPE_TERMS_OF_USE /*5*/:
                setTitle((int) R.etsy_terms_of_use);
            case TYPE_PRIVACY_POLICY /*6*/:
                setTitle((int) R.privacy_policy);
            case TYPE_ELECTRONIC_COMMUNICATIONS_POLICY /*7*/:
                setTitle((int) R.comms_policy);
            case TYPE_LISTING_MORE_INFO /*8*/:
                setTitle((int) R.story);
            case TYPE_FOE_LEARN_MORE /*9*/:
                setTitle((int) R.fund_on_etsy);
            case TYPE_LEGAL /*10*/:
                setTitle((int) R.legal);
            case TYPE_GENERIC_ETSY_HELP /*11*/:
                setTitle((int) R.help);
            default:
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mType == TYPE_CART && menuItem.getItemId() == 16908332) {
            return FragmentBackstackUtil.m5542b(getSupportFragmentManager(), Nav.m4682a((FragmentActivity) this));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EtsyWebFragment etsyWebFragment = (EtsyWebFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_CART);
        if (this.mGoogleWalletWebViewHelper != null && etsyWebFragment != null) {
            etsyWebFragment.handleGoogleWalletResult(i, i2, intent);
        }
    }

    public boolean isTopLevelActivity() {
        if (shouldUseNavStyleModal()) {
            return false;
        }
        return super.isTopLevelActivity();
    }

    private boolean shouldUseNavStyleModal() {
        int i = getIntent().getExtras().getInt(FindsModule.FIELD_TYPE);
        return i == TYPE_CART || i == TYPE_GENERIC_ETSY_HELP;
    }

    public GoogleWalletWebViewHelper getGoogleWalletHelper() {
        return this.mGoogleWalletWebViewHelper;
    }
}
