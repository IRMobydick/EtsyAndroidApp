package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class PaymentShippingDestinationViewHolder extends CartGroupItemViewHolder {
    private final View mBtnCalcShipping;
    private final Spinner mCountries;
    private final TextView mShipToLabel;
    private final TextView mTxtZip;

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentShippingDestinationViewHolder.1 */
    class C06141 extends ArrayAdapter {
        final /* synthetic */ PaymentShippingDestinationViewHolder f2615a;

        C06141(PaymentShippingDestinationViewHolder paymentShippingDestinationViewHolder, Context context, int i, Object[] objArr) {
            this.f2615a = paymentShippingDestinationViewHolder;
            super(context, i, objArr);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            m3900a(view2, (Locale) getItem(i));
            return view2;
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View dropDownView = super.getDropDownView(i, view, viewGroup);
            m3900a(dropDownView, (Locale) getItem(i));
            return dropDownView;
        }

        void m3900a(View view, Locale locale) {
            TextView textView = (TextView) view;
            if (textView != null) {
                textView.setText(locale.getDisplayCountry());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentShippingDestinationViewHolder.2 */
    class C06152 implements OnItemSelectedListener {
        boolean f2616a;
        final /* synthetic */ PaymentShippingDestinationViewHolder f2617b;

        C06152(PaymentShippingDestinationViewHolder paymentShippingDestinationViewHolder) {
            this.f2617b = paymentShippingDestinationViewHolder;
            this.f2616a = false;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f2616a) {
                Locale locale = (Locale) adapterView.getItemAtPosition(i);
                if (locale != null) {
                    if (Locale.US.getISO3Country().equals(locale.getISO3Country())) {
                        this.f2617b.mTxtZip.setVisibility(0);
                        return;
                    } else {
                        this.f2617b.mTxtZip.setVisibility(8);
                        return;
                    }
                }
                return;
            }
            this.f2616a = true;
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentShippingDestinationViewHolder.3 */
    class C06163 implements OnClickListener {
        final /* synthetic */ PaymentShippingDestinationViewHolder f2618a;

        C06163(PaymentShippingDestinationViewHolder paymentShippingDestinationViewHolder) {
            this.f2618a = paymentShippingDestinationViewHolder;
        }

        public void onClick(View view) {
            this.f2618a.mBtnCalcShipping.setVisibility(8);
            this.f2618a.mShipToLabel.setVisibility(0);
            this.f2618a.mCountries.setVisibility(0);
            this.f2618a.mTxtZip.setVisibility(8);
        }
    }

    public PaymentShippingDestinationViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903350, viewGroup, false), cartGroupActionClickHandler);
        this.mBtnCalcShipping = findViewById(2131756069);
        this.mShipToLabel = (TextView) findViewById(2131756070);
        this.mTxtZip = (TextView) findViewById(2131756072);
        this.mCountries = (Spinner) findViewById(2131756071);
        String[] iSOCountries = Locale.getISOCountries();
        ArrayList arrayList = new ArrayList();
        for (String locale : iSOCountries) {
            Locale locale2 = new Locale(StringUtils.EMPTY, locale);
            if (!TextUtils.isEmpty(locale2.getDisplayCountry())) {
                arrayList.add(locale2);
            }
        }
        SpinnerAdapter c06141 = new C06141(this, this.itemView.getContext(), 2130903525, arrayList.toArray());
        c06141.setDropDownViewResource(17367050);
        this.mCountries.setAdapter(c06141);
        this.mCountries.setOnItemSelectedListener(new C06152(this));
    }

    public void bind(CartGroupItem cartGroupItem) {
        this.mBtnCalcShipping.setOnClickListener(new C06163(this));
    }
}
