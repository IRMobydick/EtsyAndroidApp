package com.etsy.android.ui.cardview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.HashMap;

public class CardViewFactoryRecyclerViewAdapter extends BaseViewHolderFactoryRecyclerViewAdapter implements ICardViewAdapter {
    private static final String TAG;
    BroadcastReceiver mBroadcastReceiver;
    private boolean mCanRemoveItems;
    private boolean mIsChildAdapter;
    HashMap<String, Integer> mListingToPositionMap;
    private int mMaxSpanSize;
    private SpanSizeLookup mSpanSizeLookup;

    /* renamed from: com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter.1 */
    class C05391 extends SpanSizeLookup {
        final /* synthetic */ CardViewFactoryRecyclerViewAdapter f2231a;

        C05391(CardViewFactoryRecyclerViewAdapter cardViewFactoryRecyclerViewAdapter) {
            this.f2231a = cardViewFactoryRecyclerViewAdapter;
        }

        public int getSpanSize(int i) {
            if (i >= this.f2231a.mItems.size()) {
                return this.f2231a.mMaxSpanSize;
            }
            return this.f2231a.getViewHolderFactory().m3641a(this.f2231a.getItemViewType(i), i, this.f2231a.mMaxSpanSize);
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter.2 */
    class C05402 extends BroadcastReceiver {
        final /* synthetic */ CardViewFactoryRecyclerViewAdapter f2232a;

        C05402(CardViewFactoryRecyclerViewAdapter cardViewFactoryRecyclerViewAdapter) {
            this.f2232a = cardViewFactoryRecyclerViewAdapter;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                this.f2232a.listingStateChanged(intent.getExtras());
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(CardViewFactoryRecyclerViewAdapter.class);
    }

    public CardViewFactoryRecyclerViewAdapter(Context context, ImageBatch imageBatch, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, imageBatch, str, analyticsTracker);
        this.mMaxSpanSize = 1;
        this.mCanRemoveItems = false;
        this.mListingToPositionMap = new HashMap();
        this.mBroadcastReceiver = new C05402(this);
        this.mSpanSizeLookup = new C05391(this);
        this.mSpanSizeLookup.setSpanIndexCacheEnabled(true);
    }

    protected BaseViewHolderFactory createViewHolderFactory() {
        return new CardViewHolderFactory(this.mContext, this.mInflater, this.mImageBatch, this, this.mPageInView, this.mViewTracker);
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).recycle();
        }
    }

    public void addPage(Page page) {
        for (ListSection addListSection : page.getListSections()) {
            addListSection(addListSection);
        }
        notifyDataSetChanged();
    }

    public void addListSection(ListSection listSection) {
        if (listSection.getItems() != null && listSection.getItems().size() > 0) {
            BasicSectionHeader header = listSection.getHeader();
            if (!(header == null || this.mIsChildAdapter)) {
                addItem(header);
            }
            Object pageLink = listSection.getPageLink();
            if (!listSection.isHorizontal() || this.mIsChildAdapter) {
                addItems(listSection.getItems());
            } else {
                pageLink = null;
                addItem(listSection);
            }
            if (pageLink != null) {
                addItem(pageLink);
            }
        }
    }

    public void setMaxSpanSize(int i) {
        this.mMaxSpanSize = i;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    public void setCanRemoveItems(boolean z) {
        this.mCanRemoveItems = z;
    }

    public boolean getCanRemoveItems() {
        return this.mCanRemoveItems;
    }

    public void setIsChildAdapter(boolean z) {
        this.mIsChildAdapter = z;
    }

    public void removeItem(int i) {
        super.removeItem(i);
        this.mListingToPositionMap.clear();
    }

    public void listingStateChanged(Bundle bundle) {
        String string = bundle.getString(ResponseConstants.ID);
        if (string != null) {
            int i;
            if (this.mListingToPositionMap.size() == 0) {
                for (i = 0; i < this.mItems.size(); i++) {
                    if (((ICardViewElement) this.mItems.get(i)) instanceof ListingLike) {
                        ListingLike listingLike = (ListingLike) this.mItems.get(i);
                        if (listingLike.getListingId().getId().equals(string)) {
                            updateListingState(listingLike, bundle);
                            notifyItemChanged(i);
                        }
                        this.mListingToPositionMap.put(listingLike.getListingId().toString(), Integer.valueOf(i));
                    }
                }
            } else if (this.mListingToPositionMap.containsKey(string)) {
                i = ((Integer) this.mListingToPositionMap.get(string)).intValue();
                updateListingState((ListingLike) this.mItems.get(i), bundle);
                notifyItemChanged(i);
            }
        }
    }

    private void updateListingState(ListingLike listingLike, Bundle bundle) {
        if (bundle.containsKey(EtsyAction.STATE_FAVORITE)) {
            listingLike.setIsFavorite(bundle.getBoolean(EtsyAction.STATE_FAVORITE));
        }
        if (bundle.containsKey(EtsyAction.STATE_COLLECTIONS)) {
            listingLike.setHasCollections(bundle.getBoolean(EtsyAction.STATE_COLLECTIONS));
        }
    }

    public BroadcastReceiver getStateBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public boolean canRemoveItems() {
        return this.mCanRemoveItems;
    }

    public void onRemoveItem(int i) {
        removeItem(i);
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public void addFooter(int i) {
        throw new RuntimeException("The CardViewFactoryRecyclerViewAdapter does not support footers. Put the items in the list itself and define them as a mapping in the factory.");
    }

    public void addHeader(int i) {
        throw new RuntimeException("The CardViewFactoryRecyclerViewAdapter does not support headers. Put the items in the list itself and define them as a mapping in the factory.");
    }

    public void clear() {
        super.clear();
        this.mListingToPositionMap.clear();
    }

    public void clearData() {
        super.clearData();
        this.mListingToPositionMap.clear();
    }
}
