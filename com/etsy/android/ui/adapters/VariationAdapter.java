package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import java.util.Collection;
import java.util.List;

public class VariationAdapter extends BaseModelArrayAdapter<Option> {
    public VariationAdapter(FragmentActivity fragmentActivity, List<Option> list) {
        super(fragmentActivity, R.list_item_text, null);
        addAll((Collection) list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        boolean isAvailable;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
        }
        Option option = (Option) getItem(i);
        View view2 = (TextView) view.findViewById(R.item_label);
        if (option != null) {
            view2.setText(option.getFormattedValue());
            isAvailable = option.isAvailable();
        } else {
            isAvailable = false;
        }
        if (isAvailable) {
            view2.setEnabled(true);
            view2.setClickable(false);
        } else {
            view2.setEnabled(false);
            view2.setClickable(true);
            bl.m3358a(view2, 0.6f);
        }
        return view;
    }
}
