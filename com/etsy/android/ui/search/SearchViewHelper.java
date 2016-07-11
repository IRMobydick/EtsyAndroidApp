package com.etsy.android.ui.search;

import android.support.annotation.StringRes;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.View.OnFocusChangeListener;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.search.v2.SearchV2Activity;

/* renamed from: com.etsy.android.ui.search.e */
public class SearchViewHelper {
    private BOENavDrawerActivity f3233a;
    private boolean f3234b;
    private boolean f3235c;
    private boolean f3236d;
    private int f3237e;
    private OnQueryTextListener f3238f;
    private OnFocusChangeListener f3239g;
    private Class f3240h;
    private boolean f3241i;
    private boolean f3242j;
    private int f3243k;

    public SearchViewHelper(BOENavDrawerActivity bOENavDrawerActivity) {
        this.f3234b = true;
        this.f3235c = false;
        this.f3236d = true;
        this.f3237e = R.search_etsy_hint;
        this.f3240h = SearchV2Activity.class;
        this.f3241i = false;
        this.f3242j = true;
        this.f3233a = bOENavDrawerActivity;
        this.f3243k = bOENavDrawerActivity.getResources().getDimensionPixelSize(2131361922);
    }

    public SearchViewHelper m4739a() {
        this.f3234b = false;
        return this;
    }

    public SearchViewHelper m4740a(@StringRes int i) {
        this.f3237e = i;
        return this;
    }

    public SearchViewHelper m4745b() {
        this.f3235c = true;
        return this;
    }

    public SearchViewHelper m4741a(OnQueryTextListener onQueryTextListener) {
        this.f3238f = onQueryTextListener;
        return this;
    }

    public SearchViewHelper m4742a(OnFocusChangeListener onFocusChangeListener) {
        this.f3239g = onFocusChangeListener;
        return this;
    }

    public SearchViewHelper m4743a(Class cls) {
        this.f3240h = cls;
        return this;
    }

    public SearchViewHelper m4744a(boolean z) {
        this.f3242j = z;
        return this;
    }

    public SearchViewHelper m4746b(int i) {
        this.f3243k = i;
        return this;
    }

    public SearchViewHelper m4747c() {
        return new SearchViewHelper(this.f3236d, this.f3234b, this.f3235c, this.f3241i, this.f3242j, this.f3243k, this.f3237e, this.f3238f, this.f3239g, this.f3240h, null);
    }
}
