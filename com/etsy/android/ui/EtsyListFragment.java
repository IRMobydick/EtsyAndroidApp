package com.etsy.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.ui.core.TrackingBaseListFragment;
import com.etsy.android.util.BOEOptionsMenuItemHelper;

@Deprecated
public abstract class EtsyListFragment extends TrackingBaseListFragment {
    protected BaseActivity mActivity;
    protected Context mContext;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity().getApplicationContext();
        this.mActivity = (BaseActivity) getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (BaseActivity) getActivity();
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        onCreateOptionsMenuWithIcons(menu, menuInflater);
        BOEOptionsMenuItemHelper.m5676a(EtsyApplication.get().getResources(), menu);
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
    }

    public void onFragmentResume() {
    }
}
