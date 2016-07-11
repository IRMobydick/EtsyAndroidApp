package com.etsy.android.ui.favorites;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class MyFavoriteLocalShopsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.local_selling_nearby);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4653h();
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }
}
