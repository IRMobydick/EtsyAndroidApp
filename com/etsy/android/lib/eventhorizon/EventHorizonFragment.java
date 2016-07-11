package com.etsy.android.lib.eventhorizon;

import android.os.Bundle;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class EventHorizonFragment extends BaseRecyclerViewListFragment<JSONObject> {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new EventHorizonAdapter((EventHorizonActivity) getActivity(), getImageBatch());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        onRefresh();
    }

    protected void onLoadContent() {
        Iterator descendingIterator = EventHorizon.m1756c().descendingIterator();
        List arrayList = new ArrayList();
        while (descendingIterator.hasNext()) {
            arrayList.add(descendingIterator.next());
        }
        onLoadSuccess(arrayList, arrayList.size());
    }
}
