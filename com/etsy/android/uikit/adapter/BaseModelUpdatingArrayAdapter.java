package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModel;
import java.util.Collection;
import java.util.Iterator;

@Deprecated
public abstract class BaseModelUpdatingArrayAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    public BaseModelUpdatingArrayAdapter(FragmentActivity fragmentActivity, int i, ImageBatch imageBatch) {
        super(fragmentActivity, i, imageBatch);
    }

    public void insert(T t, int i) {
        if (super.getPosition(t) > -1) {
            super.remove(t);
        }
        super.insert(t, i);
    }

    public void add(T t) {
        int position = super.getPosition(t);
        if (position > -1) {
            super.remove(t);
            super.insert(t, position);
            return;
        }
        super.add(t);
    }

    public void addAll(T... tArr) {
        if (tArr != null) {
            for (BaseModel add : tArr) {
                add(add);
            }
        }
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                add((BaseModel) it.next());
            }
        }
    }

    public boolean hasStableIds() {
        return true;
    }
}
