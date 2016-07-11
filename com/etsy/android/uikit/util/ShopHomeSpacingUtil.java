package com.etsy.android.uikit.util;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.viewholder.a;
import com.etsy.android.uikit.viewholder.g;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class ShopHomeSpacingUtil {

    public class ItemDecoration extends android.support.v7.widget.RecyclerView.ItemDecoration {
        private final int mGenericVerticalOffset;
        private final int mIconNegativeTopMargin;
        private final int mListingCardSpacing;
        private final GradientDrawable mMarginColoring;

        public ItemDecoration(@NonNull Resources resources) {
            this.mListingCardSpacing = resources.getDimensionPixelOffset(R.fixed_large);
            this.mIconNegativeTopMargin = resources.getDimensionPixelOffset(R.shop2_home_neg_icon_margin);
            this.mGenericVerticalOffset = resources.getDimensionPixelOffset(R.margin_medium);
            this.mMarginColoring = new GradientDrawable();
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
            switch (childViewHolder.getItemViewType()) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    rect.left = -recyclerView.getPaddingLeft();
                    rect.right = -recyclerView.getPaddingRight();
                case Task.NETWORK_STATE_ANY /*2*/:
                    ShopHomeSpacingUtil.m5502f(childViewHolder, recyclerView, rect, this.mIconNegativeTopMargin);
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    ShopHomeSpacingUtil.m5504h(childViewHolder, recyclerView, rect, this.mGenericVerticalOffset);
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                    ShopHomeSpacingUtil.m5501e(childViewHolder, recyclerView, rect, this.mListingCardSpacing);
                case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                    ShopHomeSpacingUtil.m5503g(childViewHolder, recyclerView, rect, this.mGenericVerticalOffset);
                default:
            }
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                Drawable background = childAt.getBackground();
                if (background instanceof ColorDrawable) {
                    ColorDrawable colorDrawable = (ColorDrawable) background;
                    if (colorDrawable.getColor() != 0) {
                        int round = Math.round(childAt.getTranslationY());
                        GradientDrawable gradientDrawable = this.mMarginColoring;
                        gradientDrawable.setColor(colorDrawable.getColor());
                        int top = childAt.getTop() + round;
                        round += childAt.getBottom();
                        gradientDrawable.setBounds(0, top, childAt.getLeft(), round);
                        gradientDrawable.draw(canvas);
                        gradientDrawable.setBounds(childAt.getRight(), top, recyclerView.getRight(), round);
                        gradientDrawable.draw(canvas);
                    }
                }
            }
        }
    }

    public static void m5495a(@NonNull View view) {
        view.setPadding(m5493a(view.getResources()) + view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static int m5493a(@NonNull Resources resources) {
        return (resources.getDimensionPixelSize(R.shop2_horizontal_circular_image_size) + resources.getDimensionPixelOffset(R.fixed_medium_large)) + resources.getDimensionPixelOffset(R.fixed_large);
    }

    public static int m5496b(@NonNull Resources resources) {
        return resources.getDimensionPixelOffset(R.fixed_large) + (resources.getDimensionPixelOffset(R.shop2_horizontal_circular_image_size) >> 1);
    }

    public static g m5498c(@NonNull Resources resources) {
        return new g(-1, -1, resources.getDimensionPixelOffset(R.fixed_large));
    }

    private static void m5501e(@NonNull ViewHolder viewHolder, @NonNull RecyclerView recyclerView, @NonNull Rect rect, int i) {
        LayoutParams layoutParams = (LayoutParams) viewHolder.itemView.getLayoutParams();
        int spanIndex = layoutParams.getSpanIndex();
        int spanSize = layoutParams.getSpanSize();
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        spanIndex %= spanCount;
        rect.left = ((spanCount - spanIndex) * i) / spanCount;
        rect.right = ((spanIndex + spanSize) * i) / spanCount;
        spanCount = i >> 1;
        rect.bottom = spanCount;
        rect.top = spanCount;
    }

    private static void m5502f(@NonNull ViewHolder viewHolder, @NonNull RecyclerView recyclerView, @NonNull Rect rect, int i) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) recyclerView.getAdapter();
        int adapterPosition = viewHolder.getAdapterPosition();
        if (!shopHomeAdapter.isUsingHorizontalHeaderLayout() && adapterPosition - 1 >= 0 && shopHomeAdapter.getItemViewType(adapterPosition - 1) == 1) {
            rect.set(0, i, 0, 0);
        }
    }

    private static void m5503g(@NonNull ViewHolder viewHolder, @NonNull RecyclerView recyclerView, @NonNull Rect rect, int i) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) recyclerView.getAdapter();
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            Object obj = ((Pair) shopHomeAdapter.getItem(adapterPosition)).second;
            if ((obj instanceof a) && (((a) obj).a() & 1) != 1) {
                rect.set(0, 0, 0, i);
            }
        }
    }

    private static void m5504h(@NonNull ViewHolder viewHolder, @NonNull RecyclerView recyclerView, @NonNull Rect rect, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();
        Adapter adapter = recyclerView.getAdapter();
        if (adapterPosition != -1 && adapterPosition + 1 < adapter.getItemCount() && adapter.getItemViewType(adapterPosition + 1) == 5) {
            rect.bottom = i;
        }
    }
}
