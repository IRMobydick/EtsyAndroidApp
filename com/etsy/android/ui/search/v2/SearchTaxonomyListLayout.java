package com.etsy.android.ui.search.v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

public class SearchTaxonomyListLayout extends LinearLayout {
    private static final String STATE_DATA = "data";
    private static final String STATE_SUPER = "super";
    private View mError;
    private RecyclerView mList;
    private am mListener;
    private View mLoading;
    private ArrayList<TaxonomyNode> mTaxonomyData;

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyListLayout.1 */
    class C07991 implements EtsyJobResponse {
        final /* synthetic */ SearchTaxonomyListLayout f3308a;

        C07991(SearchTaxonomyListLayout searchTaxonomyListLayout) {
            this.f3308a = searchTaxonomyListLayout;
        }

        public void m4866a(int i, String str, EtsyResult etsyResult) {
            this.f3308a.displayError();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyListLayout.2 */
    class C08002 implements EtsyJobResponse<TaxonomyNode> {
        final /* synthetic */ SearchTaxonomyListLayout f3309a;

        C08002(SearchTaxonomyListLayout searchTaxonomyListLayout) {
            this.f3309a = searchTaxonomyListLayout;
        }

        public void m4867a(List<TaxonomyNode> list, int i, EtsyResult<TaxonomyNode> etsyResult) {
            this.f3309a.setTaxonomyData(list);
            this.f3309a.displayTaxonomy();
        }
    }

    public SearchTaxonomyListLayout(Context context) {
        super(context);
        init(context);
    }

    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(2130903276, this, true);
        this.mLoading = findViewById(R.loading_indicator);
        this.mError = findViewById(R.error_view);
        this.mList = (RecyclerView) findViewById(2131755910);
        this.mList.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.mList.addItemDecoration(new ItemDividerDecoration(new ColorDrawable(0)));
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            if (bundle.containsKey(STATE_DATA)) {
                setTaxonomyData((List) Parcels.m7495a(bundle.getParcelable(STATE_DATA)));
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        displayLoading();
        if (this.mTaxonomyData == null) {
            loadTaxonomy();
        } else {
            displayTaxonomy();
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        if (this.mTaxonomyData != null) {
            bundle.putParcelable(STATE_DATA, Parcels.m7494a(this.mTaxonomyData));
        }
        return bundle;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        aj.m1101a().m1123i().m1700a((Object) this);
    }

    private void loadTaxonomy() {
        aj.m1101a().m1123i().m1697a((Object) this, HttpRequestJobBuilder.m1712a(TaxonomyNode.class, "/etsyapps/v3/public/taxonomy/buyer").m1746a(true).m1744a("roots_only", Boolean.toString(true)).m1743a(new C08002(this)).m1742a(new C07991(this)).m1737a());
    }

    private void setTaxonomyData(List<TaxonomyNode> list) {
        this.mTaxonomyData = new ArrayList(list);
    }

    private void displayLoading() {
        this.mLoading.setVisibility(0);
        this.mError.setVisibility(8);
        this.mList.setVisibility(8);
    }

    private void displayTaxonomy() {
        this.mList.setAdapter(new SearchTaxonomyListAdapter(getContext(), this.mTaxonomyData, this.mListener));
        this.mList.setVisibility(0);
        this.mLoading.setVisibility(8);
        this.mError.setVisibility(8);
    }

    private void displayError() {
        this.mList.setVisibility(8);
        this.mLoading.setVisibility(8);
        this.mError.setVisibility(0);
    }

    public void setListener(am amVar) {
        this.mListener = amVar;
    }
}
