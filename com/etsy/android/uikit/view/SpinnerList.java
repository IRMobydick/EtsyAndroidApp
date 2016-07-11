package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class SpinnerList extends LinearLayout {
    private LayoutInflater mInflater;
    private ArrayList<String> mOptions;
    private String mPrompt;
    private AppCompatSpinner mSpinner;

    /* renamed from: com.etsy.android.uikit.view.SpinnerList.1 */
    class C10281 implements OnItemSelectedListener {
        final /* synthetic */ SpinnerList f4224a;

        C10281(SpinnerList spinnerList) {
            this.f4224a = spinnerList;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != 0) {
                this.f4224a.addItemView(adapterView.getAdapter().getItem(i).toString());
                this.f4224a.mSpinner.setSelection(0);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.uikit.view.SpinnerList.2 */
    class C10292 implements OnClickListener {
        final /* synthetic */ String f4225a;
        final /* synthetic */ LinearLayout f4226b;
        final /* synthetic */ SpinnerList f4227c;

        C10292(SpinnerList spinnerList, String str, LinearLayout linearLayout) {
            this.f4227c = spinnerList;
            this.f4225a = str;
            this.f4226b = linearLayout;
        }

        public void onClick(View view) {
            this.f4227c.mOptions.add(this.f4225a);
            this.f4227c.removeView(this.f4226b);
            ((SpinnerList) this.f4227c.mSpinner.getAdapter()).notifyDataSetChanged();
            this.f4227c.mSpinner.setVisibility(0);
        }
    }

    public SpinnerList(Context context) {
        super(context);
        this.mOptions = new ArrayList();
        init();
    }

    public SpinnerList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOptions = new ArrayList();
        init();
    }

    public SpinnerList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOptions = new ArrayList();
        init();
    }

    @TargetApi(21)
    public SpinnerList(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mOptions = new ArrayList();
        init();
    }

    private void init() {
        this.mInflater = LayoutInflater.from(getContext());
        setOrientation(1);
        setPrompt(StringUtils.EMPTY);
    }

    public void setPrompt(String str) {
        this.mPrompt = str;
        if (!this.mOptions.isEmpty()) {
            this.mOptions.remove(0);
        }
        this.mOptions.add(0, this.mPrompt);
    }

    public void setOptions(ArrayList<String> arrayList) {
        removeAllViews();
        this.mOptions.clear();
        setPrompt(this.mPrompt);
        this.mOptions.addAll(arrayList);
        this.mInflater.inflate(R.spinner_with_line, this, true);
        this.mSpinner = (AppCompatSpinner) getChildAt(getChildCount() - 1);
        this.mSpinner.setAdapter(new SpinnerList(getContext(), this.mOptions));
        this.mSpinner.setOnItemSelectedListener(new C10281(this));
    }

    public void setSelected(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            addItemView((String) arrayList.get(i));
        }
    }

    private void addItemView(String str) {
        LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(R.textview_with_delete, this, false);
        ((TextView) linearLayout.findViewById(R.text)).setText(str);
        linearLayout.findViewById(R.delete_button).setOnClickListener(new C10292(this, str, linearLayout));
        this.mOptions.remove(str);
        ((SpinnerList) this.mSpinner.getAdapter()).notifyDataSetChanged();
        addView(linearLayout, getChildCount() - 1);
        if (this.mOptions.size() <= 1) {
            this.mSpinner.setVisibility(8);
        }
    }

    public ArrayList<String> getSelected() {
        ArrayList<String> arrayList = new ArrayList();
        for (int i = 0; i < getChildCount() - 1; i++) {
            arrayList.add(((TextView) getChildAt(i).findViewById(R.text)).getText().toString());
        }
        return arrayList;
    }
}
