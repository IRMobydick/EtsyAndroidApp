package com.etsy.android.ui.search.v2;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RangedProgressBar;
import java.util.List;

/* renamed from: com.etsy.android.ui.search.v2.j */
class SearchFiltersSheet extends TrackingOnClickListener implements AnimatorListener, OnTouchListener, OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, ac, SearchCategorySelectView, SearchShipToSelectView, SearchShopLocationSelectView, RangedProgressBar {
    final /* synthetic */ SearchFiltersSheet f3350a;
    private int f3351b;
    private int f3352c;

    private SearchFiltersSheet(SearchFiltersSheet searchFiltersSheet) {
        this.f3350a = searchFiltersSheet;
    }

    public void onAnimationStart(Animator animator) {
        this.f3351b = this.f3350a.f3258a.getLayerType();
        this.f3352c = this.f3350a.f3259b.getLayerType();
        this.f3350a.f3258a.setLayerType(2, null);
        this.f3350a.f3259b.setLayerType(2, null);
    }

    public void onAnimationEnd(Animator animator) {
        this.f3350a.f3258a.setVisibility(8);
        this.f3350a.f3259b.setVisibility(8);
        this.f3350a.f3258a.setLayerType(this.f3351b, null);
        this.f3350a.f3259b.setLayerType(this.f3352c, null);
        this.f3350a.f3280w.destroy();
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case 2131756276:
                this.f3350a.f3265h.m4790w();
                this.f3350a.m4824i();
            case 2131756277:
            case 2131756301:
                this.f3350a.m4835b();
            case 2131756281:
                m4929e();
            case 2131756283:
                m4936b();
            case 2131756296:
                m4927c();
            case 2131756298:
                m4928d();
            default:
        }
    }

    private void m4927c() {
        this.f3350a.f3266i.setText(R.search_filter_location_title);
        m4816c(this.f3350a.f3267j, this.f3350a.f3264g);
        SearchShopLocationSelectView.m4960a(this.f3350a.f3280w, this.f3350a.f3265h.m4774g(), this);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case 2131756285:
                this.f3350a.f3265h.m4771d(m4814c(i));
                this.f3350a.m4824i();
            default:
        }
    }

    public void m4932a(int i, int i2) {
        this.f3350a.f3265h.m4757a(i);
        this.f3350a.f3265h.m4764b(i2);
        this.f3350a.f3273p.setText(m4808b(this.f3350a.f3259b.getResources(), this.f3350a.f3265h, this.f3350a.f3272o.getMinValue(), this.f3350a.f3272o.getMaxValue()));
        this.f3350a.m4826j();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case 2131756302:
                return true;
            default:
                return false;
        }
    }

    private void m4928d() {
        this.f3350a.f3266i.setText(R.search_filter_ships_to_title);
        m4816c(this.f3350a.f3267j, this.f3350a.f3264g);
        SearchShipToSelectView.m4951a(this.f3350a.f3280w, this.f3350a.f3265h.m4781n(), this);
    }

    void m4936b() {
        this.f3350a.f3257A = true;
        this.f3350a.f3266i.setText(R.new_search_filter_categories);
        m4816c(this.f3350a.f3267j, this.f3350a.f3264g);
        SearchCategorySelectView.m4796a(this.f3350a.f3280w, this.f3350a.f3265h.m4763b(), this.f3350a.f3283z, this, true);
    }

    private void m4929e() {
        this.f3350a.f3266i.setText(R.new_search_filter_sort_by);
        m4816c(this.f3350a.f3267j, this.f3350a.f3264g);
        ab.m4877a(this.f3350a.f3280w, this.f3350a.f3265h.m4785r(), this);
    }

    public void m4931a(int i) {
        if (i != this.f3350a.f3265h.m4785r()) {
            this.f3350a.f3265h.m4767c(i);
            this.f3350a.m4824i();
        }
    }

    public void m4935a(List<FacetCount> list) {
        if (!list.equals(this.f3350a.f3265h.m4763b())) {
            this.f3350a.f3265h.m4761a((List) list);
            this.f3350a.m4824i();
        }
    }

    public void m4930a() {
        this.f3350a.f3257A = false;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case 2131756295:
                this.f3350a.f3265h.m4766b(z);
                this.f3350a.m4824i();
            case 2131756300:
                this.f3350a.f3265h.m4762a(z);
                this.f3350a.m4824i();
            default:
        }
    }

    public void m4934a(Country country) {
        this.f3350a.f3265h.m4760a(country.getIsoCountryCode(), country.getName());
        this.f3350a.m4824i();
    }

    public void m4933a(int i, String str) {
        this.f3350a.f3265h.m4775h().m4898a(i, str);
        this.f3350a.m4824i();
    }
}
