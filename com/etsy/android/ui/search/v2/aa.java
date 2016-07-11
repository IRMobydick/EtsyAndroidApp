package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconSelectorDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* compiled from: SearchShopLocationSelectView */
class aa extends TrackingOnClickListener implements OnFocusChangeListener, OnKeyListener, OnEditorActionListener, SearchFiltersSheet {
    private final View f3310a;
    private final EditText f3311b;
    private final TextView f3312c;
    private final TextView f3313d;
    private final SearchShopLocationSelectView f3314e;
    private int f3315f;

    aa(View view, SearchOptions searchOptions, SearchShopLocationSelectView searchShopLocationSelectView) {
        this.f3314e = searchShopLocationSelectView;
        this.f3310a = view;
        this.f3315f = searchOptions.m4890b();
        this.f3312c = (TextView) view.findViewById(2131756303);
        this.f3312c.setOnClickListener(this);
        this.f3313d = (TextView) view.findViewById(2131756304);
        this.f3313d.setOnClickListener(this);
        this.f3313d.setText(Locale.getDefault().getDisplayCountry());
        this.f3311b = (EditText) view.findViewById(2131756305);
        this.f3311b.setOnEditorActionListener(this);
        this.f3311b.setOnKeyListener(this);
        this.f3311b.setOnFocusChangeListener(this);
        if (searchOptions.m4890b() == 2) {
            this.f3311b.setText(searchOptions.m4888a(view.getResources()));
        }
        m4873a(view.getResources());
        m4874d();
    }

    private void m4873a(Resources resources) {
        this.f3311b.setCompoundDrawablesWithIntrinsicBounds(IconSelectorDrawable.create(IconDrawable.m775a(resources).m779a(resources.getColor(2131624202)).m784e(17).m780a(EtsyFontIcons.LOCATION).m782c(R.text_large)), null, null, null);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case 2131756303:
                m4872a(0, StringUtils.EMPTY);
            case 2131756304:
                m4872a(1, Locale.getDefault().getDisplayCountry());
            default:
        }
    }

    private void m4871a() {
        ai.m3225a(this.f3311b.getContext(), this.f3311b);
        this.f3311b.clearFocus();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        m4872a(2, this.f3311b.getText().toString());
        return true;
    }

    private static int m4870a(Context context, boolean z) {
        return bl.m3368c(context, z ? R.textBlueLargeLight : R.textGreyLargeLight);
    }

    private void m4872a(int i, String str) {
        if (i == 2 && TextUtils.isEmpty(str)) {
            i = 0;
        }
        this.f3315f = i;
        this.f3314e.m4925a(i, str);
        m4871a();
        m4874d();
    }

    private void m4874d() {
        boolean z = true;
        this.f3312c.setTextAppearance(this.f3312c.getContext(), m4870a(this.f3312c.getContext(), this.f3315f == 0));
        TextView textView = this.f3313d;
        Context context = this.f3313d.getContext();
        Context context2 = this.f3312c.getContext();
        if (this.f3315f != 1) {
            z = false;
        }
        textView.setTextAppearance(context, m4870a(context2, z));
        if (this.f3315f != 2) {
            this.f3311b.setText(StringUtils.EMPTY);
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 66) {
            return false;
        }
        this.f3311b.onEditorAction(6);
        return true;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            this.f3315f = 2;
            m4874d();
        }
    }

    public View m4875b() {
        return this.f3310a;
    }

    public void m4876c() {
        if (this.f3311b.hasFocus()) {
            m4872a(2, this.f3311b.getText().toString().trim());
        }
    }
}
