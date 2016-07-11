package com.etsy.android.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.uikit.view.ZeroSpinner;

/* renamed from: com.etsy.android.ui.cart.r */
public class CartItemListingHolder {
    public View f2563a;
    public ImageView f2564b;
    public TextView f2565c;
    public TextView f2566d;
    public Button f2567e;
    public Button f2568f;
    public TextView f2569g;
    public TextView f2570h;
    public TextView f2571i;
    public Spinner f2572j;
    public TextView f2573k;
    public ImageButton f2574l;
    public View f2575m;
    public TextView f2576n;
    public Button f2577o;
    public ZeroSpinner f2578p;
    public View f2579q;
    public View f2580r;
    public View f2581s;
    public View f2582t;
    public TextView f2583u;
    public View f2584v;
    public ViewGroup f2585w;

    public CartItemListingHolder(ViewGroup viewGroup, LayoutParams layoutParams) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2130903113, viewGroup, false);
        this.f2563a = inflate;
        this.f2566d = (TextView) inflate.findViewById(2131755440);
        this.f2572j = (Spinner) inflate.findViewById(2131755447);
        this.f2573k = (TextView) inflate.findViewById(2131755446);
        this.f2567e = (Button) inflate.findViewById(2131755443);
        this.f2568f = (Button) inflate.findViewById(2131755445);
        this.f2569g = (TextView) inflate.findViewById(2131755442);
        this.f2570h = (TextView) inflate.findViewById(2131755444);
        this.f2571i = (TextView) inflate.findViewById(2131755441);
        this.f2564b = (ImageView) inflate.findViewById(2131755429);
        this.f2564b.setLayoutParams(layoutParams);
        this.f2574l = (ImageButton) inflate.findViewById(2131755430);
        this.f2575m = inflate.findViewById(2131755433);
        this.f2576n = (TextView) this.f2575m.findViewById(2131755435);
        this.f2577o = (Button) this.f2575m.findViewById(2131755436);
        this.f2578p = (ZeroSpinner) inflate.findViewById(2131755438);
        this.f2579q = inflate.findViewById(2131755437);
        this.f2565c = (TextView) inflate.findViewById(2131755439);
        this.f2580r = inflate.findViewById(2131755434);
        this.f2581s = inflate.findViewById(2131755432);
        this.f2582t = this.f2581s.findViewById(2131755449);
        this.f2583u = (TextView) this.f2581s.findViewById(2131755416);
        this.f2584v = inflate.findViewById(2131755448);
        this.f2585w = (ViewGroup) inflate.findViewById(2131755428);
        if (this.f2585w == null) {
            this.f2585w = (ViewGroup) inflate.findViewById(2131755431);
        }
    }

    public void m3882a(boolean z) {
        if (this.f2585w == null) {
            return;
        }
        if (z) {
            this.f2585w.setPadding(this.f2585w.getPaddingLeft(), this.f2585w.getPaddingTop(), this.f2585w.getPaddingRight(), 0);
        } else {
            this.f2585w.setPadding(this.f2585w.getPaddingLeft(), this.f2585w.getPaddingTop(), this.f2585w.getPaddingRight(), this.f2585w.getPaddingTop());
        }
    }
}
