package com.etsy.android.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.adapters.AppreciationPhotoAdapter;
import com.etsy.android.uikit.EndlessRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.List;
import org.parceler.Parcels;

public class AppreciationPhotoListFragment extends EndlessRecyclerViewListFragment<AppreciationPhoto> {
    private static final String PHOTOS = "photos";
    private EtsyId mUserId;

    /* renamed from: com.etsy.android.ui.user.AppreciationPhotoListFragment.1 */
    class C08151 extends SpanSizeLookup {
        final /* synthetic */ AppreciationPhotoListFragment f3439a;

        C08151(AppreciationPhotoListFragment appreciationPhotoListFragment) {
            this.f3439a = appreciationPhotoListFragment;
        }

        public int getSpanSize(int i) {
            return 1;
        }
    }

    /* renamed from: com.etsy.android.ui.user.AppreciationPhotoListFragment.2 */
    class C08162 implements EtsyJobResponse {
        final /* synthetic */ AppreciationPhotoListFragment f3440a;

        C08162(AppreciationPhotoListFragment appreciationPhotoListFragment) {
            this.f3440a = appreciationPhotoListFragment;
        }

        public void m4997a(EtsyResult etsyResult) {
            this.f3440a.onLoadSuccess(etsyResult.m1056g(), etsyResult.m1055f());
        }
    }

    /* renamed from: com.etsy.android.ui.user.AppreciationPhotoListFragment.3 */
    class C08173 implements EtsyJobResponse {
        final /* synthetic */ AppreciationPhotoListFragment f3441a;

        C08173(AppreciationPhotoListFragment appreciationPhotoListFragment) {
            this.f3441a = appreciationPhotoListFragment;
        }

        public void m4998a(int i, String str, EtsyResult etsyResult) {
            this.f3441a.onLoadFailure();
        }
    }

    /* renamed from: com.etsy.android.ui.user.AppreciationPhotoListFragment.4 */
    class C08184 implements EtsyJobResponse<AppreciationPhoto> {
        final /* synthetic */ AppreciationPhotoListFragment f3442a;

        C08184(AppreciationPhotoListFragment appreciationPhotoListFragment) {
            this.f3442a = appreciationPhotoListFragment;
        }

        public void m4999a(List<AppreciationPhoto> list, int i, EtsyResult<AppreciationPhoto> etsyResult) {
            this.f3442a.onLoadSuccess(list, i);
        }
    }

    public int getLayoutId() {
        return R.fragment_recyclerview_list;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserId = (EtsyId) getArguments().getSerializable(ResponseConstants.USER_ID);
        Context activity = getActivity();
        BaseRecyclerViewAdapter appreciationPhotoAdapter = new AppreciationPhotoAdapter(activity, getImageBatch());
        appreciationPhotoAdapter.setScrollLoadTriggerListener(this);
        this.mAdapter = appreciationPhotoAdapter;
        LayoutManager gridLayoutManager = new GridLayoutManager(activity, activity.getResources().getInteger(R.appreciation_photo_page_max_span_count));
        gridLayoutManager.setSpanSizeLookup(new C08151(this));
        this.mLayoutManager = gridLayoutManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mSwipeRefreshLayout.setColorSchemeResources(R.orange);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setItemAnimator(null);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null || !bundle.containsKey(PHOTOS)) {
            loadContent();
            return;
        }
        this.mAdapter.addItems((List) Parcels.m7495a(bundle.getParcelable(PHOTOS)));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Object items = this.mAdapter.getItems();
        if (this.mAdapter.getItems().size() > 0) {
            bundle.putParcelable(PHOTOS, Parcels.m7494a(items));
        }
    }

    public void onLoadContent() {
        getRequestQueue().m1697a((Object) this, HttpRequestJobBuilder.m1712a(AppreciationPhoto.class, String.format("/etsyapps/v3/public/users/%s/appreciation-photos", new Object[]{this.mUserId})).m1738a(getApiOffset()).m1747b(24).m1743a(new C08184(this)).m1742a(new C08173(this)).m1741a(new C08162(this)).m1737a());
    }
}
