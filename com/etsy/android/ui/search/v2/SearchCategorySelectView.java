package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* renamed from: com.etsy.android.ui.search.v2.d */
class SearchCategorySelectView extends ViewHolder {
    private final SearchCategorySelectView f3341a;
    private FacetCount f3342b;

    /* renamed from: com.etsy.android.ui.search.v2.d.1 */
    class SearchCategorySelectView extends TrackingOnClickListener {
        final /* synthetic */ SearchCategorySelectView f3340a;

        SearchCategorySelectView(SearchCategorySelectView searchCategorySelectView) {
            this.f3340a = searchCategorySelectView;
        }

        public void onViewClick(View view) {
            this.f3340a.f3341a.m4915a(this.f3340a.f3342b, this.f3340a.getAdapterPosition());
        }
    }

    private static TextView m4906a(Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        return textView;
    }

    public SearchCategorySelectView(Context context, SearchCategorySelectView searchCategorySelectView) {
        super(SearchCategorySelectView.m4906a(context));
        this.f3341a = searchCategorySelectView;
        this.itemView.setOnClickListener(m4905a());
    }

    void m4909a(FacetCount facetCount, boolean z, boolean z2) {
        this.f3342b = facetCount;
        TextView textView = (TextView) this.itemView;
        textView.setText(facetCount.getName());
        textView.setTextAppearance(this.itemView.getContext(), bl.m3368c(this.itemView.getContext(), z ? R.textBlueLargeLight : R.textGreyLargeLight));
        Resources resources = this.itemView.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.fixed_large);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.fixed_xlarge);
        if (!z2) {
            dimensionPixelOffset2 = dimensionPixelOffset;
        }
        textView.setPadding(dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
    }

    private OnClickListener m4905a() {
        return new SearchCategorySelectView(this);
    }
}
