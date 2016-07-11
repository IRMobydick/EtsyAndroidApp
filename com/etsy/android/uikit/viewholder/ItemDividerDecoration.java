package com.etsy.android.uikit.viewholder;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.etsy.android.lib.util.ab;
import com.google.android.gms.gcm.Task;

public class ItemDividerDecoration extends ItemDecoration {
    private static e sDefaultConfiguration;
    private e mConfiguration;
    private final Drawable mDrawable;
    private int mWidth;

    /* renamed from: com.etsy.android.uikit.viewholder.ItemDividerDecoration.1 */
    final class C10461 extends e {
        C10461() {
        }

        public Alignment m5673a(int i, int i2) {
            return Alignment.ALIGN_PARENT;
        }

        public boolean m5674b(int i, int i2) {
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.viewholder.ItemDividerDecoration.2 */
    /* synthetic */ class C10472 {
        static final /* synthetic */ int[] f4298a;

        static {
            f4298a = new int[Alignment.values().length];
            try {
                f4298a[Alignment.ALIGN_CHILD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4298a[Alignment.ALIGN_SCREEN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4298a[Alignment.ALIGN_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum Alignment {
        ALIGN_CHILD,
        ALIGN_PARENT,
        ALIGN_SCREEN
    }

    static {
        sDefaultConfiguration = new C10461();
    }

    public ItemDividerDecoration(Drawable drawable, e eVar) {
        this.mDrawable = drawable;
        this.mConfiguration = eVar;
    }

    public ItemDividerDecoration(Drawable drawable) {
        this(drawable, sDefaultConfiguration);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        if (this.mWidth == 0) {
            this.mWidth = new ab(recyclerView.getContext()).m3182e();
        }
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i = this.mWidth;
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int itemViewType = recyclerView.getChildViewHolder(childAt).getItemViewType();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            if (this.mConfiguration.b(itemViewType, childAdapterPosition)) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                switch (C10472.f4298a[this.mConfiguration.a(itemViewType, childAdapterPosition).ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        childAdapterPosition = layoutManager.getLeftDecorationWidth(childAt) + childAt.getLeft();
                        itemViewType = childAt.getRight() + layoutManager.getRightDecorationWidth(childAt);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        itemViewType = i;
                        childAdapterPosition = 0;
                        break;
                    default:
                        itemViewType = width;
                        childAdapterPosition = paddingLeft;
                        break;
                }
                int bottom = (layoutParams.bottomMargin + childAt.getBottom()) + layoutManager.getBottomDecorationHeight(childAt);
                this.mDrawable.setBounds(childAdapterPosition, bottom, itemViewType, this.mDrawable.getIntrinsicHeight() + bottom);
                this.mDrawable.draw(canvas);
            }
        }
    }
}
