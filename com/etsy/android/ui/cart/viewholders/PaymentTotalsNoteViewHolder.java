package com.etsy.android.ui.cart.viewholders;

import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class PaymentTotalsNoteViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mText;

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentTotalsNoteViewHolder.1 */
    class C06171 extends TrackingOnClickListener {
        final /* synthetic */ String f2619a;
        final /* synthetic */ PaymentTotalsNoteViewHolder f2620b;

        C06171(PaymentTotalsNoteViewHolder paymentTotalsNoteViewHolder, String str) {
            this.f2620b = paymentTotalsNoteViewHolder;
            this.f2619a = str;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) this.f2620b.itemView.getContext()).m4683a().m4528f(this.f2619a);
        }
    }

    public PaymentTotalsNoteViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903354, viewGroup, false));
        this.mText = (TextView) findViewById(2131756065);
    }

    public void bind(CartGroupItem cartGroupItem) {
        this.mText.setText(Html.fromHtml(((SimpleKVPCartGroupItem) cartGroupItem.getData()).getText()));
        linkifyText(this.mText);
    }

    private void linkifyText(TextView textView) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            EtsyLinkify.m5489a(textView, true, false, new C06171(this, urls[0].getURL()));
        }
    }
}
