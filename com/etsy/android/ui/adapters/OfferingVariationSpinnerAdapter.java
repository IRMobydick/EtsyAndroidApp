package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import java.util.List;

public class OfferingVariationSpinnerAdapter extends BaseModelArrayAdapter<OfferingOption> implements SpinnerAdapter {
    private final List<OfferingOption> mOptions;
    private int mStoredSelection;

    public OfferingVariationSpinnerAdapter(FragmentActivity fragmentActivity, List<OfferingOption> list) {
        super(fragmentActivity, 0, null);
        this.mStoredSelection = -1;
        this.mOptions = list;
    }

    public void setSelection(int i) {
        this.mStoredSelection = i;
    }

    public int getSelection() {
        return this.mStoredSelection;
    }

    public int getCount() {
        return this.mOptions.size();
    }

    public OfferingOption getItem(int i) {
        return (OfferingOption) this.mOptions.get(i);
    }

    public long getItemId(int i) {
        if (i > this.mOptions.size() - 1) {
            return ((OfferingOption) this.mOptions.get(this.mOptions.size() - 1)).getValue().getIdAsLong();
        }
        return ((OfferingOption) this.mOptions.get(i)).getValue().getIdAsLong();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        OfferingOption offeringOption;
        View view2;
        view = (TextView) view;
        if (i > this.mOptions.size() - 1) {
            offeringOption = (OfferingOption) this.mOptions.get(this.mOptions.size() - 1);
        } else {
            offeringOption = (OfferingOption) this.mOptions.get(i);
        }
        if (view == null) {
            view2 = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.spinner_item_black, viewGroup, false);
        } else {
            view2 = view;
        }
        view2.setText(offeringOption.getFormattedDisplayValue());
        return view2;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View view2;
        view = (TextView) view;
        OfferingOption offeringOption = (OfferingOption) this.mOptions.get(i);
        if (view == null) {
            view2 = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.spinner_dropdown_item, viewGroup, false);
        } else {
            view2 = view;
        }
        view2.setText(offeringOption.getFormattedDisplayValue());
        if (offeringOption.isEnabled()) {
            view2.setEnabled(true);
            view2.setClickable(false);
        } else {
            view2.setEnabled(false);
            view2.setClickable(true);
        }
        return view2;
    }
}
