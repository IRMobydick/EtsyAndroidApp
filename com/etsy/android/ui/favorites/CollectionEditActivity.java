package com.etsy.android.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class CollectionEditActivity extends TransparentActivity {
    private Collection mCollection;

    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Collection.TYPE_COLLECTION)) {
            this.mCollection = (Collection) getIntent().getExtras().getSerializable(Collection.TYPE_COLLECTION);
        }
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4645e().m4620a(this.mCollection);
        }
    }
}
