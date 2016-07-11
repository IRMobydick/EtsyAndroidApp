package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.util.CleanupUtil;

@Deprecated
public abstract class BaseListFragment extends ListFragment implements IEtsyFragment {
    private static final String TAG;
    protected ImageBatch mImageBatch;

    static {
        TAG = EtsyDebug.m1891a(BaseFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EtsyDebug.m1914c(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mImageBatch = new ImageBatch();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        EtsyDebug.m1914c(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onResume() {
        super.onResume();
        EtsyDebug.m1914c(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        AdminToolbar.m2990a(getClass().getSimpleName());
    }

    public void onStart() {
        super.onStart();
        EtsyDebug.m1914c(TAG, "onStart: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onPause() {
        super.onPause();
        EtsyDebug.m1914c(TAG, "onPause: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onFragmentResume() {
        EtsyDebug.m1914c(TAG, "onFragmentResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDestroy() {
        super.onDestroy();
        EtsyDebug.m1914c(TAG, "onDestroy: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        getRequestQueue().m1700a((Object) this);
        this.mImageBatch.m1564a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EtsyDebug.m1914c(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        if (!getRetainInstance()) {
            getRequestQueue().m1700a((Object) this);
        }
        this.mImageBatch.m1564a();
        CleanupUtil.m5532a(getView());
        super.onDestroyView();
        EtsyDebug.m1914c(TAG, "onDestroyView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EtsyDebug.m1914c(TAG, "onAttach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDetach() {
        super.onDetach();
        EtsyDebug.m1914c(TAG, "onDetach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        EtsyDebug.m1914c(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public EtsyRequestQueue getRequestQueue() {
        return aj.m1101a().m1123i();
    }

    public EtsyPostManager getPostManager() {
        return aj.m1101a().m1124j();
    }

    public ImageBatch getImageBatch() {
        return this.mImageBatch;
    }

    public boolean handleBackPressed() {
        return false;
    }
}
