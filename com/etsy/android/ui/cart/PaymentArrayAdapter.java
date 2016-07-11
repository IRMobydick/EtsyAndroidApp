package com.etsy.android.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.PaymentMethod;
import java.util.List;

public class PaymentArrayAdapter extends ArrayAdapter<PaymentMethod> {
    private int mResourceId;

    public PaymentArrayAdapter(Context context, int i, List<PaymentMethod> list) {
        super(context, i, list);
        this.mResourceId = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return buildView(i, view, this.mResourceId, viewGroup);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return buildView(i, view, R.spinner_dropdown_item, viewGroup);
    }

    private View buildView(int i, View view, int i2, ViewGroup viewGroup) {
        ad adVar;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(i2, viewGroup, false);
            ad adVar2 = new ad(view);
            view.setTag(adVar2);
            adVar = adVar2;
        } else {
            adVar = (ad) view.getTag();
        }
        adVar.f2396a.setText(((PaymentMethod) getItem(i)).getName());
        return view;
    }
}
