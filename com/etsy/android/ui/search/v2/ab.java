package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;

/* compiled from: SearchSortOrderSelectView */
public class ab {
    public static void m4877a(SelectView selectView, int i, ac acVar) {
        Context context = selectView.getContext();
        View recyclerView = new RecyclerView(context);
        Object adVar = new ad(recyclerView, i, acVar);
        recyclerView.setAdapter(adVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDividerDecoration(recyclerView.getResources().getDrawable(R.list_divider_light)));
        selectView.showWith(adVar);
    }
}
