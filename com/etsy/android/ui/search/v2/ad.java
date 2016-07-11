package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.search.SortOrder;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.j;

/* compiled from: SearchSortOrderSelectView */
class ad extends Adapter<TextViewHolder> implements SearchFiltersSheet, j {
    private static final int[] f3316d;
    private final View f3317a;
    private int f3318b;
    private final ac f3319c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m4881a((TextViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m4879a(viewGroup, i);
    }

    ad(View view, int i, ac acVar) {
        this.f3317a = view;
        this.f3318b = i;
        this.f3319c = acVar;
        setHasStableIds(true);
    }

    static {
        f3316d = new int[]{1, 0, 2, 3};
    }

    public TextViewHolder m4879a(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.fixed_large);
        TextViewHolder textViewHolder = new TextViewHolder(context);
        textViewHolder.setListener(this);
        TextView textView = textViewHolder.getTextView();
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        return textViewHolder;
    }

    public long getItemId(int i) {
        return (long) f3316d[i];
    }

    public void m4881a(TextViewHolder textViewHolder, int i) {
        int i2 = f3316d[i];
        TextView textView = textViewHolder.getTextView();
        textView.setText(SortOrder.m4748a(textView.getContext(), i2));
        textView.setTextAppearance(textView.getContext(), bl.m3368c(textView.getContext(), i2 == this.f3318b ? R.textBlueLargeLight : R.textGreyLargeLight));
    }

    public int getItemCount() {
        return f3316d.length;
    }

    public void m4880a(TextViewHolder textViewHolder) {
        int i = f3316d[textViewHolder.getAdapterPosition()];
        int i2 = this.f3318b;
        this.f3318b = i;
        int i3 = 0;
        int length = f3316d.length;
        while (i3 < length) {
            if (f3316d[i3] == i || f3316d[i3] == i2) {
                notifyItemChanged(i3);
            }
            i3++;
        }
        this.f3319c.m4878a(i);
    }

    public View m4882b() {
        return this.f3317a;
    }

    public void m4883c() {
    }
}
