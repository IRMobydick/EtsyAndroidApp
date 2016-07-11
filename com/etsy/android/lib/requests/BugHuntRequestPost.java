package com.etsy.android.lib.requests;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.posts.EtsyRequestPost;
import com.etsy.android.lib.models.apiv3.bughunt.IssueResult;

public class BugHuntRequestPost extends EtsyRequestPost<IssueResult> {
    public static final String BROADCAST_BUGHUNT_POST = "BUGHUNT_POST";
    public static final String EXTRA_WAS_SUCCESS = "WAS_SUCCESS";
    private static final long serialVersionUID = 6882455025492100746L;

    public BugHuntRequestPost(BugHuntRequest bugHuntRequest) {
        super(bugHuntRequest);
    }

    public int getVersionCode() {
        return 1;
    }

    public void onSuccess(Context context, EtsyResult<IssueResult> etsyResult) {
        broadcastResult(context, true);
    }

    public boolean onError(Context context, EtsyResult<IssueResult> etsyResult) {
        broadcastResult(context, false);
        return false;
    }

    private void broadcastResult(Context context, boolean z) {
        Intent intent = new Intent(BROADCAST_BUGHUNT_POST);
        intent.putExtra(EXTRA_WAS_SUCCESS, z);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
