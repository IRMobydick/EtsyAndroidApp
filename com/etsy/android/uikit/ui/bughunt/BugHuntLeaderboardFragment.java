package com.etsy.android.uikit.ui.bughunt;

import android.app.Activity;
import android.os.Bundle;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.apiv3.bughunt.Leader;
import com.etsy.android.lib.requests.apiv3.BugHuntLeaderboardRequest;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BugHuntLeaderboardAdapter;
import java.util.List;

public class BugHuntLeaderboardFragment extends BaseRecyclerViewListFragment<Leader> {
    BugHuntActivity mBugHuntActivity;
    String mSignedInUser;

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntLeaderboardFragment.1 */
    class C09621 implements EtsyJobResponse {
        final /* synthetic */ BugHuntLeaderboardFragment f4051a;

        C09621(BugHuntLeaderboardFragment bugHuntLeaderboardFragment) {
            this.f4051a = bugHuntLeaderboardFragment;
        }

        public void m5419a(int i, String str, EtsyResult etsyResult) {
            this.f4051a.onLoadFailure();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntLeaderboardFragment.2 */
    class C09632 implements EtsyJobResponse<Leader> {
        final /* synthetic */ BugHuntLeaderboardFragment f4052a;

        C09632(BugHuntLeaderboardFragment bugHuntLeaderboardFragment) {
            this.f4052a = bugHuntLeaderboardFragment;
        }

        public void m5420a(List<Leader> list, int i, EtsyResult<Leader> etsyResult) {
            this.f4052a.onLoadSuccess(list, i);
        }
    }

    public static BugHuntLeaderboardFragment newInstance() {
        return new BugHuntLeaderboardFragment();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBugHuntActivity = (BugHuntActivity) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (aj.m1101a().m1118d()) {
            this.mSignedInUser = SharedPreferencesUtility.m3140e(getActivity());
            this.mAdapter = new BugHuntLeaderboardAdapter(getActivity(), this.mSignedInUser, null, getImageBatch());
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mSignedInUser != null) {
            onRefresh();
            return;
        }
        this.mEmptyText.setText("Sign In to View Leaderboard");
        this.mEmptySubtext.setText("You can still submit bug reports by tapping the Bug Icon");
        showEmptyView();
    }

    protected void onLoadContent() {
        setRefreshing(true);
        showLoadingView();
        getRequestQueue().m1699a(EtsyJobBuilder.m1307a(new BugHuntLeaderboardRequest()).m1321a(new C09632(this)).m1320a(new C09621(this)).m1324a());
    }
}
