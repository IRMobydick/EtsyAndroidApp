package com.etsy.android.ui.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.ui.cardview.BaseViewHolderFactory;
import com.etsy.android.ui.cardview.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;

public class MultiShopCartAdapter extends BaseViewHolderFactoryRecyclerViewAdapter {
    public MultiShopCartAdapter(Context context, ImageBatch imageBatch, @NonNull AnalyticsTracker analyticsTracker, @NonNull aa aaVar) {
        super(context, imageBatch, "cart_view", analyticsTracker);
        ((ab) getViewHolderFactory()).m3737a(aaVar);
    }

    public void addPage(Page page) {
        for (ListSection addListSection : page.getListSections()) {
            addListSection(addListSection);
        }
    }

    public void addListSection(ListSection listSection) {
        addItems(listSection.getItems());
    }

    protected BaseViewHolderFactory createViewHolderFactory() {
        return new ab(this.mContext, this.mInflater, this.mImageBatch, this.mPageInView, this.mViewTracker);
    }

    public void addLoadingIndicator() {
    }

    public void removeLoadingIndicator() {
    }

    public void updatePage(int i, Page page) {
        if (page == null) {
            removeItem(i);
        } else if (page.getListSections().size() > 0) {
            Object obj = null;
            for (ListSection listSection : page.getListSections()) {
                Object obj2;
                if (obj == null) {
                    for (IBaseRecyclerViewElement iBaseRecyclerViewElement : listSection.getItems()) {
                        if (obj == null) {
                            replaceItem(i, iBaseRecyclerViewElement);
                            obj2 = 1;
                            break;
                        }
                    }
                }
                obj2 = obj;
                obj = obj2;
            }
        } else {
            removeItem(i);
        }
    }
}
