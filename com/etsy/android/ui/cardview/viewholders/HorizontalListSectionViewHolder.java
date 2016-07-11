package com.etsy.android.ui.cardview.viewholders;

import android.app.Activity;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.view.SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class HorizontalListSectionViewHolder extends BaseViewHolder<ListSection> {
    CardViewFactoryRecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    @Nullable
    ListSection mSection;
    SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout mSwipeToRefreshLayout;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.HorizontalListSectionViewHolder.1 */
    class C05511 extends OnScrollListener {
        final /* synthetic */ HorizontalListSectionViewHolder f2281a;

        C05511(HorizontalListSectionViewHolder horizontalListSectionViewHolder) {
            this.f2281a = horizontalListSectionViewHolder;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (i > ViewConfiguration.get(this.f2281a.mRecyclerView.getContext()).getScaledTouchSlop() && this.f2281a.mSwipeToRefreshLayout != null) {
                this.f2281a.mSwipeToRefreshLayout.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public HorizontalListSectionViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch, String str) {
        this(layoutInflater, viewGroup, imageBatch, str, true);
    }

    public HorizontalListSectionViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch, String str, boolean z) {
        super(layoutInflater.inflate(2130903296, viewGroup, false));
        this.mRecyclerView = (RecyclerView) getRootView();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), 0, false));
        this.mAdapter = new CardViewFactoryRecyclerViewAdapter(this.itemView.getContext(), imageBatch, str, this.mViewTracker);
        this.mRecyclerView.setAdapter(this.mAdapter);
        if (!z) {
            this.mRecyclerView.setItemAnimator(null);
        }
        CardViewHolderFactory cardViewHolderFactory = (CardViewHolderFactory) this.mAdapter.getViewHolderFactory();
        if (cardViewHolderFactory != null) {
            cardViewHolderFactory.m3651a(true);
        }
        this.mAdapter.setIsChildAdapter(true);
        this.mAdapter.setPageInView(str);
        Activity activity = (Activity) this.mRecyclerView.getContext();
        if (activity != null) {
            this.mSwipeToRefreshLayout = (SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout) activity.findViewById(R.swipe_refresh_layout);
            if (this.mSwipeToRefreshLayout != null) {
                this.mRecyclerView.setOnScrollListener(new C05511(this));
            }
        }
    }

    public void bind(ListSection listSection) {
        if (listSection.getItems() != null && listSection.getItems().size() > 0) {
            Class cls = ((IBaseRecyclerViewElement) listSection.getItems().get(0)).getClass();
            if (cls == Segment.class || cls == TaxonomyCategory.class) {
                this.mRecyclerView.getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131361894);
            } else if (cls == ListingCard.class) {
                this.mRecyclerView.getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131362275);
            } else if (cls == ShopCard.class || cls == AppreciationPhoto.class) {
                this.mRecyclerView.getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131361899);
            } else if (cls == FindsCard.class) {
                this.mRecyclerView.getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131362272);
            } else {
                this.mRecyclerView.getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131361851);
            }
        }
        this.mAdapter.clear();
        this.mAdapter.addListSection(listSection);
        Parcelable layoutState = listSection.getLayoutState();
        if (layoutState != null) {
            this.mRecyclerView.getLayoutManager().onRestoreInstanceState(layoutState);
        }
        this.mSection = listSection;
    }

    public void recycle() {
        if (!(this.mSection == null || this.mAdapter == null || this.mRecyclerView == null)) {
            this.mSection.setLayoutState(this.mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
        this.mSection = null;
    }
}
