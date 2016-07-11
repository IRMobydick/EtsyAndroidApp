package com.etsy.android.ui.view.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.PaymentAdjustment;

/* renamed from: com.etsy.android.ui.view.viewholders.a */
public class ReceiptRefundViewHolder {
    public View f3853a;
    public LinearLayout f3854b;
    public TextView f3855c;
    public int f3856d;
    public int f3857e;
    public int f3858f;

    public ReceiptRefundViewHolder(View view) {
        this.f3853a = view.findViewById(2131756460);
        this.f3854b = (LinearLayout) view.findViewById(2131756461);
        this.f3855c = (TextView) view.findViewById(2131756462);
        this.f3856d = 2130903360;
        this.f3857e = 2131756088;
        this.f3858f = 2131756089;
    }

    public void m5273a(Payment payment) {
        this.f3854b.removeAllViews();
        if (payment == null || !payment.hasRefund()) {
            this.f3853a.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(this.f3854b.getContext());
        for (PaymentAdjustment paymentAdjustment : payment.getAdjustments()) {
            View inflate = from.inflate(this.f3856d, this.f3854b, false);
            ((TextView) inflate.findViewById(this.f3857e)).setText("- " + paymentAdjustment.getRefundAmount());
            ((TextView) inflate.findViewById(this.f3858f)).setText(paymentAdjustment.getFormattedReason());
            this.f3854b.addView(inflate);
        }
        this.f3855c.setText(payment.getFormattedAdjustedTotal());
        this.f3853a.setVisibility(0);
    }
}
