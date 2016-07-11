package com.etsy.android.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import java.util.List;

public class ShippingOptionArrayAdapter extends ArrayAdapter<ShippingOption> {
    private int mResourceId;

    public ShippingOptionArrayAdapter(Context context, int i, List<ShippingOption> list) {
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
        ae aeVar;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(i2, viewGroup, false);
            ae aeVar2 = new ae(view);
            view.setTag(aeVar2);
            aeVar = aeVar2;
        } else {
            aeVar = (ae) view.getTag();
        }
        ShippingOption shippingOption = (ShippingOption) getItem(i);
        aeVar.f2397a.setText(shippingOption.getName() + " (" + shippingOption.getCost().format() + ")");
        return view;
    }
}
