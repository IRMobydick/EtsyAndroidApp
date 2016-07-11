package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import com.etsy.android.lib.core.img.ImageBatch;

public abstract class BaseCursorAdapter extends CursorAdapter {
    protected FragmentActivity mActivity;
    protected final ImageBatch mImageBatch;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResourceId;

    public BaseCursorAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch) {
        super(fragmentActivity.getApplicationContext(), null, 0);
        this.mLayoutResourceId = 0;
        this.mActivity = fragmentActivity;
        this.mImageBatch = imageBatch;
        this.mLayoutInflater = LayoutInflater.from(fragmentActivity);
    }

    public BaseCursorAdapter(FragmentActivity fragmentActivity, int i, ImageBatch imageBatch) {
        this(fragmentActivity, imageBatch);
        this.mLayoutResourceId = i;
    }

    public FragmentActivity getActivityContext() {
        return this.mActivity;
    }

    public ImageBatch getImageBatch() {
        return this.mImageBatch;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mLayoutInflater;
    }

    public int getLayoutId() {
        return this.mLayoutResourceId;
    }

    public void refreshContext(FragmentActivity fragmentActivity) {
        this.mActivity = fragmentActivity;
        this.mLayoutInflater = fragmentActivity.getLayoutInflater();
    }
}
