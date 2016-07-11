package com.etsy.android.uikit.listwrapper;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public abstract class ObservableEndlessListViewWrapper extends ListViewEndlessWrapper {
    private static final boolean DBG = false;
    private static final String TAG;
    private int mCachedVerticalScrollRange;
    private ArrayList<ObservableEndlessListViewWrapper> mCallbacks;
    private int mHeight;
    private boolean mIsScrollComputed;
    private int[] mItemOffsetY;
    private int mLastTopY;

    public enum ScrollDirection {
        UP,
        DOWN
    }

    static {
        TAG = EtsyDebug.m1891a(ObservableEndlessListViewWrapper.class);
    }

    public ObservableEndlessListViewWrapper(ListView listView) {
        super(listView, R.endless_footer, R.endless_error, R.btn_retry_endless);
        this.mIsScrollComputed = false;
        this.mCallbacks = new ArrayList();
    }

    public void addCallbacks(ObservableEndlessListViewWrapper observableEndlessListViewWrapper) {
        this.mCallbacks.add(observableEndlessListViewWrapper);
    }

    public void reset() {
        this.mCachedVerticalScrollRange = 0;
        this.mLastTopY = 0;
    }

    protected void computeScrollY() {
        int i = 0;
        ListAdapter adapter = this.mListView.getAdapter();
        this.mHeight = 0;
        int count = adapter.getCount();
        if (this.mItemOffsetY == null || this.mItemOffsetY.length != count) {
            this.mItemOffsetY = new int[count];
        }
        int viewTypeCount = adapter.getViewTypeCount();
        int[] iArr = new int[viewTypeCount];
        while (i < count) {
            this.mItemOffsetY[i] = this.mHeight;
            int itemViewType = adapter.getItemViewType(i);
            if (itemViewType < 0 || itemViewType >= viewTypeCount) {
                this.mHeight += getAdapterItemHeight(i);
            } else {
                if (iArr[itemViewType] == 0) {
                    iArr[itemViewType] = getAdapterItemHeight(i);
                }
                this.mHeight = iArr[itemViewType] + this.mHeight;
            }
            i++;
        }
        this.mCachedVerticalScrollRange = this.mHeight;
        this.mIsScrollComputed = true;
    }

    protected int calculateMeasuredHeight(View view) {
        int i = 0;
        try {
            view.measure(MeasureSpec.makeMeasureSpec(i, i), MeasureSpec.makeMeasureSpec(i, i));
            i = view.getMeasuredHeight();
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Error measuring view height", e);
        }
        return i;
    }

    protected int getHeight() {
        return this.mHeight;
    }

    protected int getCachedVerticalScrollRange() {
        return this.mCachedVerticalScrollRange;
    }

    protected void setCachedVerticalScrollRangeToCalculatedHeight() {
        this.mCachedVerticalScrollRange = this.mHeight;
    }

    private int getAdapterItemHeight(int i) {
        return calculateMeasuredHeight(this.mListView.getAdapter().getView(i, null, this.mListView));
    }

    private boolean scrollYIsComputed() {
        return this.mIsScrollComputed;
    }

    private int getComputedTopScrollY() {
        int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
        View childAt = this.mListView.getChildAt(0);
        if (childAt == null || firstVisiblePosition >= this.mItemOffsetY.length) {
            return 0;
        }
        return this.mItemOffsetY[firstVisiblePosition] - childAt.getTop();
    }

    private int getComputedBottomScrollY() {
        int i;
        if (isEndlessLoading()) {
            i = this.mCachedVerticalScrollRange;
        } else {
            i = this.mListView.getLastVisiblePosition();
            View childAt = this.mListView.getChildAt(i - this.mListView.getFirstVisiblePosition());
            if (childAt == null || i >= this.mItemOffsetY.length) {
                i = 0;
            } else {
                int top = childAt.getTop();
                i = (this.mCachedVerticalScrollRange - this.mItemOffsetY[i]) - (this.mListView.getBottom() - top);
            }
        }
        if (i >= 0) {
            return i;
        }
        return 0;
    }

    private ScrollDirection getComputedScrollDirection(int i) {
        ScrollDirection scrollDirection = ScrollDirection.DOWN;
        if (i < this.mLastTopY) {
            scrollDirection = ScrollDirection.UP;
        }
        this.mLastTopY = i;
        return scrollDirection;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int computedTopScrollY;
        int computedBottomScrollY;
        super.onScroll(absListView, i, i2, i3);
        if (scrollYIsComputed()) {
            computedTopScrollY = getComputedTopScrollY();
            computedBottomScrollY = getComputedBottomScrollY();
        } else {
            computedBottomScrollY = 0;
            computedTopScrollY = 0;
        }
        ScrollDirection computedScrollDirection = getComputedScrollDirection(computedTopScrollY);
        if (this.mCallbacks != null) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((ObservableEndlessListViewWrapper) it.next()).m5351a(computedTopScrollY, computedBottomScrollY, computedScrollDirection);
            }
        }
    }
}
