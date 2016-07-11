package com.etsy.android.ui.view.viewholders;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Coupon;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

/* renamed from: com.etsy.android.ui.view.viewholders.b */
public class ReceiptTotalsViewHolder {
    public View f3862a;
    public TextView f3863b;
    public TextView f3864c;
    public TextView f3865d;
    public TextView f3866e;
    public TextView f3867f;
    public View f3868g;
    public TextView f3869h;
    public TextView f3870i;
    public TextView f3871j;
    public View f3872k;
    public TextView f3873l;
    public View f3874m;
    public TextView f3875n;
    public View f3876o;
    public TextView f3877p;
    public TextView f3878q;
    public TextView f3879r;
    public TextView f3880s;
    public ViewGroup f3881t;
    @NonNull
    private final ad f3882u;

    /* renamed from: com.etsy.android.ui.view.viewholders.b.1 */
    class ReceiptTotalsViewHolder extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f3859a;
        final /* synthetic */ String f3860b;
        final /* synthetic */ ReceiptTotalsViewHolder f3861c;

        ReceiptTotalsViewHolder(ReceiptTotalsViewHolder receiptTotalsViewHolder, FragmentActivity fragmentActivity, String str) {
            this.f3861c = receiptTotalsViewHolder;
            this.f3859a = fragmentActivity;
            this.f3860b = str;
        }

        public void onViewClick(View view) {
            if (this.f3859a != null) {
                Nav.m4682a(this.f3859a).m4683a().m4528f(this.f3860b);
            }
        }
    }

    public ReceiptTotalsViewHolder(View view, @NonNull ad adVar) {
        this.f3882u = adVar;
        this.f3862a = view.findViewById(2131756446);
        this.f3864c = (TextView) view.findViewById(2131755399);
        this.f3863b = (TextView) view.findViewById(2131755398);
        this.f3865d = (TextView) view.findViewById(2131755400);
        this.f3866e = (TextView) view.findViewById(2131755411);
        this.f3867f = (TextView) view.findViewById(2131756457);
        this.f3868g = view.findViewById(2131756447);
        this.f3869h = (TextView) view.findViewById(2131756448);
        this.f3870i = (TextView) view.findViewById(2131756449);
        this.f3871j = (TextView) view.findViewById(2131756463);
        this.f3872k = view.findViewById(2131756450);
        this.f3873l = (TextView) view.findViewById(2131755402);
        this.f3876o = view.findViewById(2131756452);
        this.f3877p = (TextView) view.findViewById(2131756454);
        this.f3878q = (TextView) view.findViewById(2131756459);
        this.f3879r = (TextView) view.findViewById(2131756458);
        this.f3880s = (TextView) view.findViewById(2131755412);
        this.f3874m = view.findViewById(2131756455);
        this.f3875n = (TextView) view.findViewById(2131756456);
        this.f3881t = (ViewGroup) view.findViewById(2131756460);
    }

    public void m5280a(FragmentActivity fragmentActivity, Receipt receipt) {
        this.f3863b.setText(R.item_total);
        this.f3864c.setText(receipt.getTotalPrice().format());
        this.f3867f.setText(R.order_total);
        this.f3866e.setText(receipt.getFormattedGrandTotal());
        m5274a(fragmentActivity.getResources(), receipt);
        m5281a(receipt);
        m5278c(receipt);
        m5279d(receipt);
        m5276a(receipt, fragmentActivity);
        m5277b(receipt);
    }

    private void m5274a(Resources resources, Receipt receipt) {
        Coupon coupon = receipt.getCoupon();
        int i = this.f3871j == null ? 1 : 0;
        if (coupon == null || receipt.isInPerson()) {
            this.f3868g.setVisibility(8);
            if (this.f3871j != null) {
                this.f3871j.setVisibility(8);
                return;
            }
            return;
        }
        CharSequence string;
        CharSequence format;
        if (coupon.isFreeShipping()) {
            string = resources.getString(R.coupon_free_shipping);
            this.f3865d.setPaintFlags(this.f3865d.getPaintFlags() | 16);
            if (i != 0) {
                format = String.format(resources.getString(R.coupon_with_code), new Object[]{coupon.getCouponCode()});
            } else {
                format = String.format(resources.getString(R.coupon_details_text_freeshipping), new Object[]{coupon.getCouponCode()});
            }
        } else if (coupon.isPercentDiscount()) {
            string = "- " + receipt.getDiscountAmt().format();
            if (i != 0) {
                format = String.format(resources.getString(R.coupon_with_code_and_percentage), new Object[]{coupon.getCouponCode(), String.valueOf(coupon.getPercentDiscount())});
            } else {
                format = String.format(resources.getString(R.coupon_details_text_discount), new Object[]{coupon.getCouponCode(), Integer.valueOf(coupon.getPercentDiscount())});
            }
        } else {
            String format2 = receipt.getDiscountAmt().format();
            String str = "- " + format2;
            Object obj;
            if (i != 0) {
                format = String.format(resources.getString(R.coupon_with_code), new Object[]{coupon.getCouponCode()});
                obj = str;
            } else {
                String formattedFixedDiscount = coupon.getFormattedFixedDiscount();
                if (bh.m3340a(formattedFixedDiscount)) {
                    format2 = formattedFixedDiscount;
                }
                format = String.format(resources.getString(R.coupon_details_text_fixed_discount), new Object[]{coupon.getCouponCode(), format2});
                obj = str;
            }
        }
        this.f3870i.setText(string);
        if (i != 0) {
            this.f3869h.setText(format);
        } else {
            this.f3871j.setText(format);
        }
    }

    private void m5277b(Receipt receipt) {
        if (receipt.isInPerson() && receipt.getDiscountAmt().compareTo(0) == 1) {
            this.f3875n.setText("- " + receipt.getDiscountAmt().format());
            this.f3874m.setVisibility(0);
            return;
        }
        this.f3874m.setVisibility(8);
    }

    private void m5278c(Receipt receipt) {
        if (receipt.getTotalTaxCost().compareTo(0) == 0) {
            this.f3872k.setVisibility(8);
        } else {
            this.f3873l.setText(receipt.getTotalTaxCost().format());
        }
    }

    private void m5279d(Receipt receipt) {
        if (this.f3876o != null) {
            if (receipt.getTotalVatCost().compareTo(0) == 0) {
                this.f3878q.setVisibility(8);
                this.f3876o.setVisibility(8);
                this.f3879r.setVisibility(8);
                return;
            }
            this.f3877p.setText(receipt.getTotalVatCost().format());
            this.f3863b.setText(R.item_total_excluding_vat);
            this.f3867f.setText(R.order_total_including_vat);
            if (this.f3882u.m1864f().m883b(EtsyConfigKeys.f1202U) != null) {
                this.f3878q.setText(Html.fromHtml(this.f3878q.getResources().getString(R.vat_desc, new Object[]{r0})));
                this.f3878q.setVisibility(0);
                this.f3878q.setMovementMethod(LinkMovementMethod.getInstance());
            }
            if (this.f3882u.m1864f().m883b(EtsyConfigKeys.f1203V) != null) {
                this.f3879r.setText(Html.fromHtml(String.format(this.f3879r.getResources().getString(R.vat_view_invoice, new Object[]{r0}), new Object[]{receipt.getReceiptId().getId()})));
                this.f3879r.setVisibility(0);
                this.f3879r.setMovementMethod(LinkMovementMethod.getInstance());
            }
            if (this.f3882u.m1864f().m883b(EtsyConfigKeys.f1204W) != null && this.f3881t != null) {
                List<String> vatCreditNoteIds = receipt.getVatCreditNoteIds();
                if (vatCreditNoteIds != null && vatCreditNoteIds.size() > 0) {
                    LayoutInflater layoutInflater = (LayoutInflater) this.f3881t.getContext().getSystemService("layout_inflater");
                    Resources resources = this.f3881t.getResources();
                    String id = receipt.getReceiptId().getId();
                    for (String str : vatCreditNoteIds) {
                        TextView textView = (TextView) layoutInflater.inflate(2130903541, null);
                        Object[] objArr = new Object[]{id, str};
                        textView.setText(Html.fromHtml(resources.getString(R.vat_credit_note, new Object[]{String.format(r3, objArr), str})));
                        textView.setMovementMethod(LinkMovementMethod.getInstance());
                        this.f3881t.addView(textView);
                    }
                }
            }
        }
    }

    private void m5276a(Receipt receipt, FragmentActivity fragmentActivity) {
        if (this.f3882u.m1864f().m885c(EtsyConfigKeys.cf) && receipt.hasTransparentPriceMessage()) {
            this.f3880s.setText(Html.fromHtml(receipt.getTransparentPriceMessage()));
            m5275a(this.f3880s, fragmentActivity);
            this.f3880s.setVisibility(0);
            return;
        }
        this.f3880s.setVisibility(8);
    }

    public void m5281a(Receipt receipt) {
        if (receipt.isInPerson()) {
            this.f3862a.setVisibility(8);
        } else {
            this.f3865d.setText(receipt.getTotalShippingCost().format());
        }
    }

    private void m5275a(TextView textView, FragmentActivity fragmentActivity) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            EtsyLinkify.m5489a(textView, true, false, new ReceiptTotalsViewHolder(this, fragmentActivity, urls[0].getURL()));
        }
    }
}
