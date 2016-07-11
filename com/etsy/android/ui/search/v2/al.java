package com.etsy.android.ui.search.v2;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: SearchTaxonomyListAdapter */
class al extends ViewHolder {
    final /* synthetic */ SearchTaxonomyListAdapter f3332a;
    private final ImageView f3333b;
    private final TextView f3334c;

    /* renamed from: com.etsy.android.ui.search.v2.al.1 */
    class SearchTaxonomyListAdapter extends TrackingOnClickListener {
        final /* synthetic */ TaxonomyNode f3330a;
        final /* synthetic */ al f3331b;

        SearchTaxonomyListAdapter(al alVar, ITrackedObject[] iTrackedObjectArr, TaxonomyNode taxonomyNode) {
            this.f3331b = alVar;
            this.f3330a = taxonomyNode;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f3331b.f3332a.mListener != null) {
                this.f3331b.f3332a.mListener.onTaxonomySelected(this.f3330a);
            }
        }
    }

    public al(SearchTaxonomyListAdapter searchTaxonomyListAdapter, View view) {
        this.f3332a = searchTaxonomyListAdapter;
        super(view);
        this.f3333b = (ImageView) view.findViewById(2131755898);
        this.f3334c = (TextView) view.findViewById(2131755899);
    }

    public void m4886a(TaxonomyNode taxonomyNode) {
        this.f3334c.setText(taxonomyNode.getName());
        this.f3333b.setImageResource(an.m4887a(taxonomyNode.getPath()));
        this.itemView.setOnClickListener(new SearchTaxonomyListAdapter(this, new ITrackedObject[]{taxonomyNode}, taxonomyNode));
    }
}
