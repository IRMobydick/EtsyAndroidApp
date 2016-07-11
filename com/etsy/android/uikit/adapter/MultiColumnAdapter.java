package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import java.util.ArrayList;
import java.util.Collection;

@Deprecated
public abstract class MultiColumnAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    private static final String TAG;
    protected BaseCardRowGenerator mCardRowGenerator;
    protected ArrayList<T> mResults;

    protected abstract void bindRow(int i, int i2, Object obj);

    static {
        TAG = EtsyDebug.m1891a(MultiColumnAdapter.class);
    }

    public MultiColumnAdapter(Activity activity, ImageBatch imageBatch, int i, BaseCardRowGenerator baseCardRowGenerator) {
        super(activity, i, imageBatch);
        this.mCardRowGenerator = baseCardRowGenerator;
        this.mResults = new ArrayList(0);
    }

    public void addAll(Collection<? extends T> collection) {
        this.mResults.addAll(collection);
        super.addAll((Collection) collection);
    }

    public void notifyDataSetChanged() {
        this.mCardRowGenerator.m3505b();
        super.notifyDataSetChanged();
    }

    @CallSuper
    public void refreshActivity(Activity activity) {
        super.refreshActivity(activity);
        this.mCardRowGenerator.m3504a(activity);
    }

    public int getRealCount() {
        return this.mResults.size();
    }

    public int getCount() {
        return (int) Math.ceil((double) (((float) this.mResults.size()) / ((float) this.mCardRowGenerator.m3502a())));
    }

    public void remove(T t) {
        super.remove(t);
        this.mResults.remove(t);
    }

    public void add(T t) {
        super.add(t);
        this.mResults.add(t);
    }

    public void insert(T t, int i) {
        super.insert(t, i);
        this.mResults.add(i, t);
    }

    public void clear() {
        this.mResults.clear();
        notifyDataSetChanged();
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i) + (super.getViewTypeCount() * this.mCardRowGenerator.m3514k());
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount() * 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return getDefaultView(i, view);
    }

    protected View getDefaultView(int i, View view) {
        int a = this.mCardRowGenerator.m3502a();
        View a2 = this.mCardRowGenerator.m3503a(view);
        bindRow(i, a, a2.getTag());
        return a2;
    }
}
