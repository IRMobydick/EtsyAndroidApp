package com.etsy.android.ui.favorites;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.nav.DialogNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ListingCollectionsActivity extends DialogActivity {
    private ListingLike mListing;
    private EtsyId mListingId;
    private String mListingImageUrl;

    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = getIntent().getExtras();
            if (intent.hasExtra(ActivityFeedEntity.LISTING)) {
                this.mListing = (ListingLike) extras.getSerializable(ActivityFeedEntity.LISTING);
            } else if (intent.hasExtra(ResponseConstants.LISTING_ID) && intent.hasExtra(ResponseConstants.LISTING_IMAGE_URL)) {
                this.mListingId = (EtsyId) extras.getSerializable(ResponseConstants.LISTING_ID);
                this.mListingImageUrl = extras.getString(ResponseConstants.LISTING_IMAGE_URL);
            }
        }
        super.onCreate(bundle);
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        DialogNavigator a = Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener);
        if (this.mListing != null) {
            a.m4409a(this.mListing);
        } else if (this.mListingId != null && this.mListingImageUrl != null) {
            a.m4408a(this.mListingId, this.mListingImageUrl);
        }
    }
}
