package com.etsy.android.ui.search.v2;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: SearchTaxonomyListAdapter */
class ai extends ViewHolder {
    final /* synthetic */ SearchTaxonomyListAdapter f3324a;
    private final ImageView f3325b;
    private final TextView f3326c;
    private final TextView f3327d;

    /* renamed from: com.etsy.android.ui.search.v2.ai.1 */
    class SearchTaxonomyListAdapter extends TrackingOnClickListener {
        final /* synthetic */ ai f3323a;

        SearchTaxonomyListAdapter(ai aiVar) {
            this.f3323a = aiVar;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4533h();
            EtsyLogger.m1966a().m2001d("open_create_gift_card", "search");
        }
    }

    public ai(SearchTaxonomyListAdapter searchTaxonomyListAdapter, ViewGroup viewGroup) {
        this.f3324a = searchTaxonomyListAdapter;
        super(searchTaxonomyListAdapter.mInflater.inflate(2130903271, viewGroup, false));
        this.f3325b = (ImageView) this.itemView.findViewById(2131755901);
        this.f3326c = (TextView) this.itemView.findViewById(2131755902);
        this.f3327d = (TextView) this.itemView.findViewById(2131755903);
    }

    public void m4885a() {
        if (getItemViewType() == 3) {
            this.f3325b.setImageResource(2130837954);
            this.f3326c.getContext().getResources();
            this.f3326c.setText(R.etsy_gift_cards);
            this.f3327d.setVisibility(8);
            this.itemView.setOnClickListener(new SearchTaxonomyListAdapter(this));
        }
    }
}
