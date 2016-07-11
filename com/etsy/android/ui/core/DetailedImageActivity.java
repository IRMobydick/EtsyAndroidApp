package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.ui.core.TransparentActivity;
import java.util.List;

public class DetailedImageActivity extends TransparentActivity implements FullImagesPagerAdapter {
    protected int getGraphikTheme() {
        return 2131428191;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.activity_frame);
        if (bundle == null) {
            if (!hasImages()) {
                finish();
            }
            navToDetailedImageFragment();
        }
    }

    protected boolean hasImages() {
        List list = (List) getIntent().getExtras().getSerializable("image_list");
        return (list == null || list.isEmpty()) ? false : true;
    }

    protected void navToDetailedImageFragment() {
        Nav.m4682a((FragmentActivity) this).m4684b().m4623a(getIntent().getExtras()).m4667s();
    }

    public void onViewUnsupportedImage(String str) {
        af.m3212c(this, str);
    }
}
