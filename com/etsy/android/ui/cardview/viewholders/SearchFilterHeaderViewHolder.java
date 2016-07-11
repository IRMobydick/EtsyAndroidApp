package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.SearchFilterHeader;
import com.etsy.android.ui.search.v2.SearchFacetCountUtils;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class SearchFilterHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    private final TextView mCategoryPathView;
    private final b mClickHandler;
    private final TextView mFilterView;
    private final TextView mResultCountView;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SearchFilterHeaderViewHolder.1 */
    class C05661 extends TrackingOnClickListener {
        final /* synthetic */ SearchFilterHeader f2310a;
        final /* synthetic */ SearchFilterHeaderViewHolder f2311b;

        C05661(SearchFilterHeaderViewHolder searchFilterHeaderViewHolder, SearchFilterHeader searchFilterHeader) {
            this.f2311b = searchFilterHeaderViewHolder;
            this.f2310a = searchFilterHeader;
        }

        public void onViewClick(View view) {
            if (this.f2311b.mClickHandler != null) {
                this.f2311b.mClickHandler.a(this.f2310a);
            }
        }
    }

    public SearchFilterHeaderViewHolder(LayoutInflater layoutInflater, b bVar) {
        super(layoutInflater.inflate(2130903407, null));
        this.mResultCountView = (TextView) this.itemView.findViewById(2131756272);
        this.mFilterView = (TextView) this.itemView.findViewById(2131756274);
        this.mClickHandler = bVar;
        this.mCategoryPathView = (TextView) this.itemView.findViewById(2131756273);
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        if (basicSectionHeader instanceof SearchFilterHeader) {
            SearchFilterHeader searchFilterHeader = (SearchFilterHeader) basicSectionHeader;
            this.mFilterView.setText(searchFilterHeader.getActionTitle());
            this.mResultCountView.setText(searchFilterHeader.getTitle());
            Object subtitle = searchFilterHeader.getSubtitle();
            if (TextUtils.isEmpty(subtitle)) {
                this.mCategoryPathView.setVisibility(8);
            } else {
                this.mCategoryPathView.setVisibility(0);
                Context context = this.mCategoryPathView.getContext();
                this.mCategoryPathView.setText(SearchFacetCountUtils.m4919a(context, subtitle, context.getResources().getColor(R.text_grey_white)));
            }
            this.mFilterView.setOnClickListener(new C05661(this, searchFilterHeader));
            return;
        }
        this.mFilterView.setVisibility(8);
        this.mCategoryPathView.setVisibility(0);
        this.mCategoryPathView.setText(basicSectionHeader.getTitle());
        this.mResultCountView.setText(basicSectionHeader.getSubtitle());
    }
}
