package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.finds.FindsModule;

public class TextDialogFragment extends TrackingBaseDialogFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (!getArguments().containsKey(FindsModule.FIELD_TEXT)) {
            EtsyDebug.m1894a(new RuntimeException("No text to show in dialog"));
            goBack();
        }
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_text, viewGroup, true);
        ((TextView) inflate.findViewById(R.text)).setText(getArguments().getString(FindsModule.FIELD_TEXT));
        return inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                goBack();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
