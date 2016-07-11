package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import com.etsy.android.lib.R;

public class SwitchToggle extends LinearLayout implements Checkable {
    private SwitchToggle mOnCheckedChangeListener;
    private ToggleButton mToggleOff;
    private ToggleButton mToggleOn;

    /* renamed from: com.etsy.android.uikit.view.SwitchToggle.1 */
    class C10301 implements OnCheckedChangeListener {
        final /* synthetic */ SwitchToggle f4228a;

        C10301(SwitchToggle switchToggle) {
            this.f4228a = switchToggle;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            boolean z2;
            boolean z3 = true;
            if (this.f4228a.mOnCheckedChangeListener != null) {
                this.f4228a.mOnCheckedChangeListener.m5644a(this.f4228a, z);
            }
            ToggleButton access$100 = this.f4228a.mToggleOn;
            if (z) {
                z2 = false;
            } else {
                z2 = true;
            }
            access$100.setClickable(z2);
            this.f4228a.mToggleOff.setClickable(z);
            ToggleButton access$200 = this.f4228a.mToggleOff;
            if (z) {
                z3 = false;
            }
            access$200.setChecked(z3);
        }
    }

    /* renamed from: com.etsy.android.uikit.view.SwitchToggle.2 */
    class C10312 implements OnCheckedChangeListener {
        final /* synthetic */ SwitchToggle f4229a;

        C10312(SwitchToggle switchToggle) {
            this.f4229a = switchToggle;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            boolean z2;
            boolean z3 = true;
            if (this.f4229a.mOnCheckedChangeListener != null) {
                this.f4229a.mOnCheckedChangeListener.m5644a(this.f4229a, !z);
            }
            this.f4229a.mToggleOn.setClickable(z);
            ToggleButton access$200 = this.f4229a.mToggleOff;
            if (z) {
                z2 = false;
            } else {
                z2 = true;
            }
            access$200.setClickable(z2);
            ToggleButton access$100 = this.f4229a.mToggleOn;
            if (z) {
                z3 = false;
            }
            access$100.setChecked(z3);
        }
    }

    public SwitchToggle(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public SwitchToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public SwitchToggle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    @TargetApi(21)
    public SwitchToggle(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        setOrientation(0);
        inflate(getContext(), R.switch_toggle, this);
        this.mToggleOn = (ToggleButton) findViewById(R.toggle_on);
        this.mToggleOff = (ToggleButton) findViewById(R.toggle_off);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.SwitchToggle, i, i2);
            CharSequence text = obtainStyledAttributes.getText(R.SwitchToggle_android_textOn);
            this.mToggleOn.setTextOn(text);
            this.mToggleOn.setTextOff(text);
            text = obtainStyledAttributes.getText(R.SwitchToggle_android_textOff);
            this.mToggleOff.setTextOn(text);
            this.mToggleOff.setTextOff(text);
            obtainStyledAttributes.recycle();
        }
        this.mToggleOn.setOnCheckedChangeListener(new C10301(this));
        this.mToggleOff.setOnCheckedChangeListener(new C10312(this));
    }

    public void setChecked(boolean z) {
        this.mToggleOn.setChecked(z);
        this.mToggleOff.setChecked(!z);
    }

    public boolean isChecked() {
        return this.mToggleOn.isChecked();
    }

    public void toggle() {
        this.mToggleOn.toggle();
        this.mToggleOff.toggle();
    }

    public void setOnCheckedChangeListener(SwitchToggle switchToggle) {
        this.mOnCheckedChangeListener = switchToggle;
    }
}
