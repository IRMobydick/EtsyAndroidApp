package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.IEtsyFragment;

public abstract class BaseFragment extends Fragment implements IEtsyFragment {
    private static final String TAG;
    private BaseFragmentDelegate mBaseFragmentDelegate;

    static {
        TAG = EtsyDebug.m1891a(BaseFragment.class);
    }

    public BaseFragment() {
        this.mBaseFragmentDelegate = new BaseFragmentDelegate(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EtsyDebug.m1914c(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.m5441a(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        EtsyDebug.m1914c(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onResume() {
        super.onResume();
        EtsyDebug.m1914c(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.m5440a();
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
        this.mBaseFragmentDelegate.m5442b();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EtsyDebug.m1914c(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        this.mBaseFragmentDelegate.m5444c();
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
        this.mBaseFragmentDelegate.m5443b(bundle);
        EtsyDebug.m1914c(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public EtsyRequestQueue getRequestQueue() {
        return this.mBaseFragmentDelegate.m5445d();
    }

    public EtsyPostManager getPostManager() {
        return this.mBaseFragmentDelegate.m5446e();
    }

    public ImageBatch getImageBatch() {
        return this.mBaseFragmentDelegate.m5447f();
    }

    public boolean handleBackPressed() {
        return this.mBaseFragmentDelegate.handleBackPressed();
    }
}
