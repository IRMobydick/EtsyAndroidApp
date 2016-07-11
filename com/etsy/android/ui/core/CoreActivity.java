package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.google.android.gms.gcm.Task;

public class CoreActivity extends BOENavDrawerActivity {
    private static final String TAG;

    /* renamed from: com.etsy.android.ui.core.CoreActivity.1 */
    /* synthetic */ class C06511 {
        static final /* synthetic */ int[] f2691a;

        static {
            f2691a = new int[EtsyAction.values().length];
            try {
                f2691a[EtsyAction.VIEW_ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(CoreActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            launchFragment();
        }
    }

    private void launchFragment() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("referral_args");
            if (extras.containsKey(ResponseConstants.SHOP_ID)) {
                if (bundle != null) {
                    extras.putAll(bundle);
                }
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(extras).m4612a((EtsyId) extras.getSerializable(ResponseConstants.SHOP_ID), (EtsyId) extras.getSerializable(ResponseConstants.USER_ID));
                return;
            } else if (extras.containsKey(ResponseConstants.SHOP_NAME)) {
                if (bundle != null) {
                    extras.putAll(bundle);
                }
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(extras).m4631b(extras.getString(ResponseConstants.SHOP_NAME));
                return;
            } else if (extras.containsKey(ResponseConstants.LISTING_ID)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(bundle).m4619a((EtsyId) extras.getSerializable(ResponseConstants.LISTING_ID));
                return;
            } else if (extras.containsKey("treasury_id")) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(extras).m4648f(extras.getString("treasury_id"));
                return;
            } else if (extras.containsKey(Collection.TYPE_COLLECTION)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(extras).m4633b((Collection) extras.getSerializable(Collection.TYPE_COLLECTION));
                return;
            } else if (extras.containsKey(ResponseConstants.USER_ID)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(bundle).m4638c((EtsyId) extras.getSerializable(ResponseConstants.USER_ID));
                return;
            } else if (extras.containsKey(ResponseConstants.RECEIPT_ID)) {
                requireSignIn(EtsyAction.VIEW_ORDER);
                return;
            } else {
                EtsyDebug.m1919e(TAG, "Started CoreActivity without valid fragment arguments.");
                finish();
                return;
            }
        }
        EtsyDebug.m1919e(TAG, "Started CoreActivity with no launch fragment arguments.");
        finish();
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820552, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                return navigateUpAsBack();
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onUserSignedInForAction(EtsyAction etsyAction) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("referral_args");
            switch (C06511.f2691a[etsyAction.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    Nav.m4682a((FragmentActivity) this).m4684b().m4623a(bundle).m4642d((EtsyId) extras.getSerializable(ResponseConstants.RECEIPT_ID));
                default:
            }
        }
    }
}
