package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.etsy.android.ui.search.v2.x */
class SearchShipToSelectView extends Adapter<TextViewHolder> implements SearchFiltersSheet, j, Comparator<Country> {
    private List<Country> f3377a;
    private final SearchShipToSelectView f3378b;
    private final View f3379c;
    private int f3380d;

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m4954a((Country) obj, (Country) obj2);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m4957a((TextViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m4955a(viewGroup, i);
    }

    SearchShipToSelectView(View view, SearchShipToSelectView searchShipToSelectView) {
        this.f3379c = view;
        this.f3378b = searchShipToSelectView;
        setHasStableIds(true);
    }

    public long getItemId(int i) {
        return (long) ((Country) this.f3377a.get(i)).getCountryId();
    }

    public TextViewHolder m4955a(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.fixed_large);
        TextViewHolder textViewHolder = new TextViewHolder(context);
        textViewHolder.setListener(this);
        TextView textView = textViewHolder.getTextView();
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        return textViewHolder;
    }

    public void m4957a(TextViewHolder textViewHolder, int i) {
        TextView textView = textViewHolder.getTextView();
        textView.setText(((Country) this.f3377a.get(i)).getName());
        textView.setTextAppearance(textView.getContext(), bl.m3368c(textView.getContext(), i == this.f3380d ? R.textBlueLargeLight : R.textGreyLargeLight));
    }

    public int getItemCount() {
        return this.f3377a == null ? 0 : this.f3377a.size();
    }

    private void m4953a(List<Country> list, String str) {
        this.f3377a = new ArrayList(list);
        Collections.sort(this.f3377a, this);
        int size = this.f3377a.size();
        for (int i = 0; i < size; i++) {
            if (((Country) this.f3377a.get(i)).getIsoCountryCode().equals(str)) {
                this.f3380d = i;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public int m4954a(Country country, Country country2) {
        String country3 = Locale.getDefault().getCountry();
        if (country3.equals(country.getIsoCountryCode())) {
            return -1;
        }
        if (country3.equals(country2.getIsoCountryCode())) {
            return 1;
        }
        return country.compareTo(country2);
    }

    public void m4956a(TextViewHolder textViewHolder) {
        int i = this.f3380d;
        int adapterPosition = textViewHolder.getAdapterPosition();
        this.f3380d = adapterPosition;
        notifyItemChanged(i);
        notifyItemChanged(adapterPosition);
        this.f3378b.m4924a((Country) this.f3377a.get(this.f3380d));
    }

    public View m4958b() {
        return this.f3379c;
    }

    public void m4959c() {
    }
}
