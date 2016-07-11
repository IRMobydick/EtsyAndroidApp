package com.etsy.android.uikit.share;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.nav.NavBase;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;

public abstract class SocialShareBrokerFragment extends ShareBrokerFragment {
    private static final int DEFAULT_SPAN_COUNT = 4;
    private View mHeaderView;
    private TabletSupportUtil mTabletSupportUtil;

    /* renamed from: com.etsy.android.uikit.share.SocialShareBrokerFragment.1 */
    class C09491 implements OnTouchListener {
        final /* synthetic */ SocialShareBrokerFragment f4015a;

        C09491(SocialShareBrokerFragment socialShareBrokerFragment) {
            this.f4015a = socialShareBrokerFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                this.f4015a.dismiss();
            }
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.share.SocialShareBrokerFragment.2 */
    class C09502 implements OnTouchListener {
        final /* synthetic */ SocialShareBrokerFragment f4016a;

        C09502(SocialShareBrokerFragment socialShareBrokerFragment) {
            this.f4016a = socialShareBrokerFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.share.SocialShareBrokerFragment.3 */
    class C09513 implements Runnable {
        final /* synthetic */ SocialShareBrokerFragment f4017a;

        C09513(SocialShareBrokerFragment socialShareBrokerFragment) {
            this.f4017a = socialShareBrokerFragment;
        }

        public void run() {
            if (this.f4017a.getActivity() != null) {
                NavBase.m4675b(this.f4017a.getActivity()).m4679f();
            }
        }
    }

    public class SocialShareGridLayoutManager extends GridLayoutManager {
        private int[] mMeasuredDimension;

        public SocialShareGridLayoutManager(Context context, int i) {
            super(context, i);
            this.mMeasuredDimension = new int[2];
            setAutoMeasureEnabled(false);
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (((double) i3) < Math.ceil(((double) getItemCount()) / ((double) getSpanCount()))) {
                int i6;
                int i7;
                measureScrapChild(recycler, i3, MeasureSpec.makeMeasureSpec(i3, 0), MeasureSpec.makeMeasureSpec(i3, 0), this.mMeasuredDimension);
                if (getOrientation() == 0) {
                    i6 = i5 + this.mMeasuredDimension[0];
                    i7 = i3 == 0 ? this.mMeasuredDimension[1] : i4;
                } else {
                    i7 = this.mMeasuredDimension[1] + i4;
                    i6 = i3 == 0 ? this.mMeasuredDimension[0] : i5;
                }
                i3++;
                i4 = i7;
                i5 = i6;
            }
            switch (mode) {
                case 1073741824:
                    i5 = size;
                    break;
            }
            switch (mode2) {
                case 1073741824:
                    i4 = size2;
                    break;
            }
            setMeasuredDimension(i5, i4);
        }

        private void measureScrapChild(Recycler recycler, int i, int i2, int i3, int[] iArr) {
            View viewForPosition = recycler.getViewForPosition(i);
            if (viewForPosition != null) {
                LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = (viewForPosition.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                iArr[1] = layoutParams.topMargin + (viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin);
                recycler.recycleView(viewForPosition);
            }
        }

        public boolean canScrollVertically() {
            return false;
        }
    }

    public abstract View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mUrl != null) {
            this.mUrl = SocialShareUtil.m5153a(this.mUrl);
            shortenUrl();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mTabletSupportUtil = new TabletSupportUtil(getActivity());
        hideHeaderInLandscape();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        hideHeaderInLandscape();
    }

    private void hideHeaderInLandscape() {
        int i = 0;
        if (this.mTabletSupportUtil != null && this.mHeaderView != null) {
            int i2;
            if (this.mTabletSupportUtil.m5622b() && this.mTabletSupportUtil.m5624d()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            View view = this.mHeaderView;
            if (i2 != 0) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_social_share, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.share_layout);
        ViewGroup viewGroup3 = (ViewGroup) inflate.findViewById(R.social_share_content);
        this.mHeaderView = onCreateHeaderView(layoutInflater, viewGroup2, bundle);
        if (this.mHeaderView != null) {
            viewGroup2.addView(this.mHeaderView, 0);
        }
        viewGroup2.setOnTouchListener(new C09491(this));
        viewGroup3.setOnTouchListener(new C09502(this));
        hideHeaderInLandscape();
        return inflate;
    }

    protected LayoutManager createLayoutManager() {
        return new SocialShareGridLayoutManager(getActivity(), DEFAULT_SPAN_COUNT);
    }

    protected BaseRecyclerViewAdapter<ResolveInfo> createAdapter() {
        return new ShareIntentListAdapter(getActivity(), R.standard_image_grid_item);
    }

    protected void dismiss() {
        EventTracker.m2041d(getAnalyticsContext());
        EtsyGraphite.m1807a(SocialShareUtil.m5154a("share_sheet_dismissed"));
        getFragmentManager().beginTransaction().setCustomAnimations(0, R.nav_frag_bottom_pop_exit).hide(this).commit();
        if (getView() != null) {
            getView().postDelayed(new C09513(this), 200);
        }
    }

    public void onIntentItemClick(int i) {
        super.onIntentItemClick(i);
        EventTracker.m2044d(((ResolveInfo) this.mAdapter.getItem(i)).activityInfo.packageName);
        EtsyGraphite.m1807a(SocialShareUtil.m5154a("action_tapped", ((ResolveInfo) this.mAdapter.getItem(i)).activityInfo.packageName));
    }

    protected void onShareError(ResolveInfo resolveInfo) {
        super.onShareError(resolveInfo);
        EventTracker.m2029b(getAnalyticsContext(), resolveInfo.activityInfo.packageName);
        EtsyGraphite.m1807a(SocialShareUtil.m5154a("action_failure", resolveInfo.activityInfo.packageName));
    }
}
