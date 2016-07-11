package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModel;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;

public abstract class BaseModelArrayAdapter<T extends BaseModel> extends ArrayAdapter<T> {
    private Reference<Activity> mActivity;
    private ImageBatch mImageBatch;
    private int mLayoutId;

    public BaseModelArrayAdapter(Activity activity, int i, ImageBatch imageBatch) {
        super(activity.getApplicationContext(), i);
        this.mActivity = new WeakReference(activity);
        this.mLayoutId = i;
        this.mImageBatch = imageBatch;
    }

    public T getItem(int i) {
        return (BaseModel) super.getItem(i);
    }

    public void addAll(Collection<? extends T> collection) {
        if (VERSION.SDK_INT >= 11) {
            super.addAll(collection);
        } else if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                add((BaseModel) it.next());
            }
        }
    }

    public void addAll(T... tArr) {
        if (VERSION.SDK_INT >= 11) {
            super.addAll(tArr);
        } else if (tArr != null) {
            for (Object add : tArr) {
                add(add);
            }
        }
    }

    public Activity getActivityContext() {
        return (Activity) this.mActivity.get();
    }

    public ImageBatch getImageBatch() {
        return this.mImageBatch;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from((Context) this.mActivity.get());
    }

    public boolean isValidPosition(int i) {
        return i >= 0 && i < getCount() && getItem(i) != null;
    }

    @CallSuper
    public void refreshActivity(Activity activity) {
        this.mActivity = new WeakReference(activity);
    }

    public int getRealCount() {
        return getCount();
    }
}
