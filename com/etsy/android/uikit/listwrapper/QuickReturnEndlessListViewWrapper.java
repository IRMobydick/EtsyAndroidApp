package com.etsy.android.uikit.listwrapper;

import android.annotation.TargetApi;
import android.database.DataSetObserver;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection;

@Deprecated
public class QuickReturnEndlessListViewWrapper extends ObservableEndlessListViewWrapper {
    private static final boolean DBG = false;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_RETURNING_BOTTOM = 3;
    private static final int STATE_RETURNING_TOP = 2;
    private static final String TAG;
    private DataSetObserver mDataSetObserver;
    private int mFooterY;
    private int mHeaderY;
    private boolean mIsFooterPaddingAdded;
    private boolean mIsHeaderPaddingAdded;
    private View mListViewHeaderOffsetView;
    private int mMinRawYFooter;
    private int mMinRawYHeader;
    private ObservableEndlessListViewWrapper mQuickReturnCallbacks;
    private View mQuickReturnFooter;
    private int mQuickReturnFooterHeight;
    private View mQuickReturnHeader;
    private int mQuickReturnHeaderHeight;
    private int mQuickReturnHeaderOffset;
    private int mStateFooter;
    private int mStateHeader;
    private View mViewFooterPadding;
    private View mViewHeaderPadding;

    /* renamed from: com.etsy.android.uikit.listwrapper.QuickReturnEndlessListViewWrapper.1 */
    class C09321 extends DataSetObserver {
        final /* synthetic */ QuickReturnEndlessListViewWrapper f3979a;

        C09321(QuickReturnEndlessListViewWrapper quickReturnEndlessListViewWrapper) {
            this.f3979a = quickReturnEndlessListViewWrapper;
        }

        public void onChanged() {
            super.onChanged();
            this.f3979a.runLayout();
        }
    }

    /* renamed from: com.etsy.android.uikit.listwrapper.QuickReturnEndlessListViewWrapper.2 */
    class C09332 implements ObservableEndlessListViewWrapper {
        final /* synthetic */ QuickReturnEndlessListViewWrapper f3980a;

        C09332(QuickReturnEndlessListViewWrapper quickReturnEndlessListViewWrapper) {
            this.f3980a = quickReturnEndlessListViewWrapper;
        }

        public void m5352a(int i, int i2, ScrollDirection scrollDirection) {
            if (this.f3980a.mQuickReturnHeader != null) {
                this.f3980a.translateHeader(i, i2, scrollDirection);
            }
            if (this.f3980a.mQuickReturnFooter != null) {
                this.f3980a.translateFooter(i, i2, scrollDirection);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.listwrapper.QuickReturnEndlessListViewWrapper.3 */
    class C09343 implements OnGlobalLayoutListener {
        final /* synthetic */ QuickReturnEndlessListViewWrapper f3981a;

        C09343(QuickReturnEndlessListViewWrapper quickReturnEndlessListViewWrapper) {
            this.f3981a = quickReturnEndlessListViewWrapper;
        }

        public void onGlobalLayout() {
            if (this.f3981a.mQuickReturnHeader != null) {
                this.f3981a.mQuickReturnHeaderHeight = this.f3981a.mQuickReturnHeader.getHeight();
                this.f3981a.setCachedVerticalScrollRangeToCalculatedHeight();
                if (this.f3981a.mQuickReturnHeaderHeight > 0) {
                    this.f3981a.mViewHeaderPadding.setLayoutParams(new LayoutParams(-1, this.f3981a.mQuickReturnHeaderHeight));
                }
            } else {
                this.f3981a.mViewHeaderPadding.setLayoutParams(new LayoutParams(-1, QuickReturnEndlessListViewWrapper.STATE_ONSCREEN));
            }
            if (this.f3981a.mQuickReturnFooter != null) {
                this.f3981a.mQuickReturnFooterHeight = this.f3981a.mQuickReturnFooter.getHeight();
                if (this.f3981a.mQuickReturnFooterHeight > 0) {
                    this.f3981a.mViewFooterPadding.setLayoutParams(new LayoutParams(-1, this.f3981a.mQuickReturnFooterHeight));
                }
            } else {
                this.f3981a.mListView.removeFooterView(this.f3981a.mViewFooterPadding);
            }
            this.f3981a.computeHeaderOffset();
            this.f3981a.computeScrollY();
            if (VERSION.SDK_INT < 16) {
                this.f3981a.removeViewTreeMinSdk(this.f3981a.mListView, this);
            } else {
                this.f3981a.removeViewTree(this.f3981a.mListView, this);
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(QuickReturnEndlessListViewWrapper.class);
    }

    public QuickReturnEndlessListViewWrapper(ListView listView) {
        super(listView);
        this.mStateFooter = STATE_ONSCREEN;
        this.mMinRawYFooter = STATE_ONSCREEN;
        this.mStateHeader = STATE_ONSCREEN;
        this.mMinRawYHeader = STATE_ONSCREEN;
        this.mDataSetObserver = new C09321(this);
        this.mQuickReturnCallbacks = new C09332(this);
        LayoutInflater from = LayoutInflater.from(listView.getContext());
        this.mViewHeaderPadding = from.inflate(R.list_item_padding, null);
        this.mViewFooterPadding = from.inflate(R.list_item_padding, null);
        listView.addHeaderView(this.mViewHeaderPadding);
        this.mIsHeaderPaddingAdded = true;
        addCallbacks(this.mQuickReturnCallbacks);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.mListView.getAdapter() != null) {
            this.mListView.getAdapter().unregisterDataSetObserver(this.mDataSetObserver);
        }
        super.setAdapter(listAdapter);
        if (listAdapter != null) {
            runLayout();
            listAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    public void onDestroyView() {
        if (this.mListView.getAdapter() != null) {
            this.mListView.getAdapter().unregisterDataSetObserver(this.mDataSetObserver);
        }
    }

    private void runLayout() {
        if (this.mListView.getViewTreeObserver().isAlive()) {
            this.mListView.getViewTreeObserver().addOnGlobalLayoutListener(new C09343(this));
        }
    }

    private void removeViewTreeMinSdk(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    @TargetApi(16)
    private void removeViewTree(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public void startEndless() {
        super.startEndless();
        if (this.mQuickReturnFooter != null && this.mIsFooterPaddingAdded) {
            this.mListView.removeFooterView(this.mViewFooterPadding);
            this.mIsFooterPaddingAdded = DBG;
            computeScrollY();
        }
    }

    public void stopEndless() {
        super.stopEndless();
        if (this.mQuickReturnFooter != null && !this.mIsFooterPaddingAdded) {
            this.mIsFooterPaddingAdded = true;
            this.mListView.addFooterView(this.mViewFooterPadding);
            computeScrollY();
        }
    }

    public void addHeaderViewAndOffset(View view) {
        this.mListView.addHeaderView(view);
        this.mListViewHeaderOffsetView = view;
    }

    private void computeHeaderOffset() {
        if (this.mListViewHeaderOffsetView != null) {
            this.mQuickReturnHeaderOffset = this.mListViewHeaderOffsetView.getHeight();
        }
    }

    public void setQuickReturnHeader(View view) {
        this.mQuickReturnHeader = view;
    }

    public void setQuickReturnFooter(View view) {
        this.mQuickReturnFooter = view;
    }

    public void reset() {
        this.mStateFooter = STATE_ONSCREEN;
        this.mMinRawYFooter = STATE_ONSCREEN;
        this.mStateHeader = STATE_ONSCREEN;
        this.mMinRawYHeader = STATE_ONSCREEN;
        super.reset();
    }

    protected int calculateMeasuredHeight(View view) {
        if (this.mIsHeaderPaddingAdded && view == this.mViewHeaderPadding) {
            return this.mQuickReturnHeaderHeight;
        }
        if (this.mIsFooterPaddingAdded && view == this.mViewFooterPadding) {
            return this.mQuickReturnFooterHeight;
        }
        return super.calculateMeasuredHeight(view);
    }

    private void translateHeader(int i, int i2, ScrollDirection scrollDirection) {
        int i3 = STATE_ONSCREEN;
        int top = this.mQuickReturnHeader.getTop() - Math.min(getCachedVerticalScrollRange() - (this.mListView.getHeight() < 0 ? this.mListView.getHeight() : STATE_ONSCREEN), i);
        if (top > 0) {
            top = STATE_ONSCREEN;
        }
        int i4;
        switch (this.mStateHeader) {
            case STATE_ONSCREEN /*0*/:
                if (top < (-this.mQuickReturnHeaderHeight) - this.mQuickReturnHeaderOffset) {
                    this.mStateHeader = STATE_OFFSCREEN;
                    this.mMinRawYHeader = top;
                } else {
                    top += this.mQuickReturnHeaderOffset;
                }
                i3 = top;
                break;
            case STATE_OFFSCREEN /*1*/:
                if (top <= this.mMinRawYHeader) {
                    this.mMinRawYHeader = top;
                    if (i2 <= this.mQuickReturnHeaderHeight) {
                        this.mStateHeader = STATE_RETURNING_BOTTOM;
                    }
                } else if (i2 <= this.mQuickReturnHeaderHeight) {
                    this.mStateHeader = STATE_RETURNING_BOTTOM;
                } else {
                    this.mStateHeader = STATE_RETURNING_TOP;
                }
                i3 = top;
                break;
            case STATE_RETURNING_TOP /*2*/:
                i4 = (top - this.mMinRawYHeader) - this.mQuickReturnHeaderHeight;
                if (i2 <= this.mQuickReturnHeaderHeight && scrollDirection == ScrollDirection.DOWN) {
                    this.mStateHeader = STATE_RETURNING_BOTTOM;
                    i3 = i4;
                    break;
                }
                if (i4 > 0) {
                    this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                    i4 = STATE_ONSCREEN;
                }
                if (top >= 0 - this.mQuickReturnHeaderOffset) {
                    this.mStateHeader = STATE_ONSCREEN;
                    i4 = top + this.mQuickReturnHeaderOffset;
                }
                if (i4 >= (-this.mQuickReturnHeaderHeight)) {
                    i3 = i4;
                    break;
                }
                this.mStateHeader = STATE_OFFSCREEN;
                this.mMinRawYHeader = top;
                i3 = i4;
                break;
            case STATE_RETURNING_BOTTOM /*3*/:
                i4 = (top - this.mMinRawYHeader) - this.mQuickReturnHeaderHeight;
                if (i2 <= this.mQuickReturnHeaderHeight || scrollDirection != ScrollDirection.UP) {
                    if (scrollDirection != ScrollDirection.DOWN) {
                        if (i4 <= 0 && i2 != 0) {
                            i3 = i4;
                        }
                        this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                        break;
                    }
                    if (i2 < this.mQuickReturnHeaderHeight) {
                        i4 = -i2;
                    }
                    if (i4 > 0 || i2 == 0) {
                        this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                        i4 = STATE_ONSCREEN;
                    }
                    if (top < 0 - this.mQuickReturnHeaderOffset) {
                        i3 = i4;
                    }
                    if (i3 < (-this.mQuickReturnHeaderHeight)) {
                        this.mMinRawYHeader = top;
                        break;
                    }
                }
                this.mStateHeader = STATE_RETURNING_TOP;
                this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                break;
                break;
        }
        if (this.mHeaderY != i3) {
            this.mHeaderY = i3;
            this.mQuickReturnHeader.setTranslationY((float) i3);
        }
    }

    private void translateFooter(int i, int i2, ScrollDirection scrollDirection) {
        int i3 = STATE_OFFSCREEN;
        int i4 = STATE_ONSCREEN;
        int i5;
        switch (this.mStateFooter) {
            case STATE_ONSCREEN /*0*/:
                if (i2 < this.mQuickReturnFooterHeight) {
                    if (scrollDirection != ScrollDirection.UP) {
                        this.mStateFooter = STATE_RETURNING_BOTTOM;
                        i4 = i2;
                        break;
                    }
                    this.mMinRawYFooter = this.mQuickReturnFooterHeight + i;
                    this.mStateFooter = STATE_RETURNING_TOP;
                    break;
                }
                if (i > this.mQuickReturnFooterHeight) {
                    this.mStateFooter = STATE_OFFSCREEN;
                    this.mMinRawYFooter = i;
                }
                i4 = i;
                break;
            case STATE_OFFSCREEN /*1*/:
                if (i < this.mMinRawYFooter) {
                    if (i2 > this.mQuickReturnFooterHeight) {
                        this.mStateFooter = STATE_RETURNING_TOP;
                        i4 = i;
                        break;
                    }
                    this.mStateFooter = STATE_RETURNING_BOTTOM;
                    i4 = i2;
                    break;
                }
                this.mMinRawYFooter = i;
                if (i2 > this.mQuickReturnFooterHeight) {
                    i4 = i;
                    break;
                }
                this.mStateFooter = STATE_RETURNING_BOTTOM;
                i4 = i2;
                break;
            case STATE_RETURNING_TOP /*2*/:
                i5 = (i - this.mMinRawYFooter) + this.mQuickReturnFooterHeight;
                if (i2 <= this.mQuickReturnFooterHeight && scrollDirection == ScrollDirection.DOWN) {
                    this.mStateFooter = STATE_RETURNING_BOTTOM;
                    i4 = i5;
                    break;
                }
                if (i5 < 0) {
                    this.mMinRawYFooter = this.mQuickReturnFooterHeight + i;
                    i5 = STATE_ONSCREEN;
                }
                if (i == 0) {
                    this.mStateFooter = STATE_ONSCREEN;
                } else {
                    i4 = i5;
                }
                if (i4 > this.mQuickReturnFooterHeight) {
                    this.mStateFooter = STATE_OFFSCREEN;
                    this.mMinRawYFooter = i;
                    break;
                }
                break;
            case STATE_RETURNING_BOTTOM /*3*/:
                int min = Math.min(i2, (i - this.mMinRawYFooter) + this.mQuickReturnFooterHeight);
                i5 = i2 >= this.mQuickReturnFooterHeight ? STATE_OFFSCREEN : STATE_ONSCREEN;
                if (scrollDirection != ScrollDirection.UP) {
                    i3 = STATE_ONSCREEN;
                }
                if ((i5 & i3) != 0) {
                    this.mStateFooter = STATE_RETURNING_TOP;
                }
                if (min >= 0 && i2 != 0) {
                    i4 = min;
                    break;
                } else {
                    this.mMinRawYFooter = this.mQuickReturnFooterHeight + i;
                    break;
                }
                break;
        }
        if (this.mFooterY != i4) {
            this.mFooterY = i4;
            this.mQuickReturnFooter.setTranslationY((float) i4);
        }
    }
}
