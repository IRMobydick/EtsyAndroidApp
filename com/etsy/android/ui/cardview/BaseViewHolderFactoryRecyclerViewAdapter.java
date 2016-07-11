package com.etsy.android.ui.cardview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.cardviewelement.LoadingCardViewElement;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

public abstract class BaseViewHolderFactoryRecyclerViewAdapter extends EndlessRecyclerViewAdapter<IBaseRecyclerViewElement> {
    private static final String KEY_ADAPTER_CONTENTS = "key_adapter_contents";
    private static final String TAG;
    protected String mPageInView;
    private BaseViewHolderFactory mViewHolderFactory;
    @NonNull
    protected AnalyticsTracker mViewTracker;

    @NonNull
    protected abstract BaseViewHolderFactory createViewHolderFactory();

    static {
        TAG = EtsyDebug.m1891a(BaseViewHolderFactoryRecyclerViewAdapter.class);
    }

    protected BaseViewHolderFactoryRecyclerViewAdapter(Context context, ImageBatch imageBatch, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, imageBatch);
        this.mPageInView = str;
        this.mViewTracker = analyticsTracker;
        this.mViewHolderFactory = createViewHolderFactory();
    }

    public int getListItemViewType(int i) {
        return this.mViewHolderFactory.m3642a((IBaseRecyclerViewElement) getItems().get(i));
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).bind(getItems().get(i));
        }
    }

    public BaseViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return this.mViewHolderFactory.m3643a(viewGroup, i);
    }

    public void addLoadingIndicator() {
        addItem(new LoadingCardViewElement());
    }

    public void removeLoadingIndicator() {
        List list = this.mItems;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (((IBaseRecyclerViewElement) list.get(size)).getClass().equals(LoadingCardViewElement.class)) {
                removeItem(size);
                return;
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        removeLoadingIndicator();
        try {
            bundle.putParcelable(KEY_ADAPTER_CONTENTS, Parcels.m7494a(this.mItems));
        } catch (Exception e) {
            EtsyDebug.m1919e(TAG, "One of the ICardViewElements is not parcelable: " + e);
            e.printStackTrace();
        }
    }

    public boolean onRestoreInstanceState(Bundle bundle) {
        Parcelable parcelable = bundle.getParcelable(KEY_ADAPTER_CONTENTS);
        if (parcelable == null) {
            return false;
        }
        this.mItems.clear();
        addItems((ArrayList) Parcels.m7495a(parcelable));
        return true;
    }

    @NonNull
    public BaseViewHolderFactory getViewHolderFactory() {
        return this.mViewHolderFactory;
    }

    public String getPageInView() {
        return this.mPageInView;
    }

    public void setPageInView(String str) {
        this.mPageInView = str;
        this.mViewHolderFactory.m3645a(str);
    }

    public void registerClickListener(int i, b bVar) {
        this.mViewHolderFactory.m3644a(i, bVar);
    }
}
