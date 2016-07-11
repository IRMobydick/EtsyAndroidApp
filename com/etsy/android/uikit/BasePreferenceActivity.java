package com.etsy.android.uikit;

import android.app.ActionBar;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.AndroidIssuesUtil;
import com.etsy.android.uikit.text.typeface.TypefaceCache;

public abstract class BasePreferenceActivity extends PreferenceActivity {
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (AndroidIssuesUtil.m3164a() && menuItem.getTitleCondensed() != null) {
            menuItem.setTitleCondensed(menuItem.getTitleCondensed().toString());
        }
        return super.onMenuItemSelected(i, menuItem);
    }

    protected void setupAppBar() {
        String string = getString(R.config);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.ic_menu_e);
            actionBar.setTitle(TypefaceCache.m5414a().m5418b(this, string));
        }
    }
}
