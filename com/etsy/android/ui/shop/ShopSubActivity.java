package com.etsy.android.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.core.CoreActivity;
import com.etsy.android.ui.nav.Nav;

public class ShopSubActivity extends BOENavDrawerActivity {
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
            if (extras.containsKey(ActivityFeedEntity.SHOP)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(bundle).m4627a((Shop) extras.getSerializable(ActivityFeedEntity.SHOP));
            } else if (extras.containsKey(ResponseConstants.SHOP_ID)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(bundle).m4634b((EtsyId) extras.getSerializable(ResponseConstants.SHOP_ID));
            }
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820552, menu);
        menu.removeItem(R.menu_share);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (!isTopLevelActivity()) {
                    return upNavigationToShop();
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected boolean upNavigationToShop() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1 || getIntent() == null || !getIntent().getBooleanExtra("NAV_UP_TO_PARENT", false)) {
            return popOrGoBack();
        }
        Intent intent = new Intent(this, CoreActivity.class);
        Shop shop = (Shop) getIntent().getSerializableExtra(ActivityFeedEntity.SHOP);
        if (shop != null) {
            intent.putExtra(ResponseConstants.SHOP_ID, shop.getShopId());
        }
        intent.putExtras(getIntent().getExtras());
        Nav.m4682a((FragmentActivity) this).m4676a(intent);
        return true;
    }
}
