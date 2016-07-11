package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.ShippingDetails;
import com.etsy.android.lib.models.apiv3.cart.ShippingOption;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.HashMap;
import java.util.List;

public class ShippingDetailsViewHolder extends BaseViewHolder<CartGroupItem> {
    private final ArrayAdapter<ShippingOption> mAdapter;
    private final CartGroupActionClickHandler mClickHandler;
    private final View mLayoutShippingOption;
    private final TextView mShippingOptionLabel;
    private final Spinner mShippingOptions;
    private final TextView mTxtProcessingTime;

    /* renamed from: com.etsy.android.ui.cart.viewholders.ShippingDetailsViewHolder.1 */
    class C06181 implements OnItemSelectedListener {
        boolean f2621a;
        final /* synthetic */ CartGroupItem f2622b;
        final /* synthetic */ List f2623c;
        final /* synthetic */ ShippingDetailsViewHolder f2624d;

        C06181(ShippingDetailsViewHolder shippingDetailsViewHolder, CartGroupItem cartGroupItem, List list) {
            this.f2624d = shippingDetailsViewHolder;
            this.f2622b = cartGroupItem;
            this.f2623c = list;
            this.f2621a = false;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (!this.f2621a) {
                this.f2621a = true;
            } else if (this.f2624d.mClickHandler != null && adapterView.getItemAtPosition(i) != null) {
                CartGroupAction action = this.f2622b.getAction(CartGroupAction.SHIPPING_OPTION);
                if (action != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("shipping_option_tuple", ((ShippingOption) this.f2623c.get(i)).getOptionId());
                    action.setParams(hashMap);
                    this.f2624d.mClickHandler.m3711a(this.f2622b.getCartAdapterPosition(), action);
                }
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public ShippingDetailsViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903351, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mLayoutShippingOption = findViewById(2131756073);
        this.mShippingOptions = (Spinner) findViewById(2131756075);
        this.mShippingOptionLabel = (TextView) findViewById(2131756074);
        this.mAdapter = new ArrayAdapter(this.itemView.getContext(), 2130903525);
        this.mAdapter.setDropDownViewResource(17367050);
        this.mShippingOptions.setAdapter(this.mAdapter);
        this.mTxtProcessingTime = (TextView) findViewById(R.txt_processing_time);
    }

    public void bind(CartGroupItem cartGroupItem) {
        ShippingDetails shippingDetails = (ShippingDetails) cartGroupItem.getData();
        this.mTxtProcessingTime.setText(shippingDetails.getProcessingTimeText());
        Object shippingOptions = shippingDetails.getShippingOptions();
        this.mAdapter.clear();
        if (shippingOptions.size() == 0) {
            this.mLayoutShippingOption.setVisibility(8);
            return;
        }
        this.mLayoutShippingOption.setVisibility(0);
        if (shippingOptions.size() == 1) {
            this.mShippingOptions.setVisibility(8);
            this.mShippingOptionLabel.setText(((ShippingOption) shippingOptions.get(0)).toString());
            return;
        }
        this.mShippingOptionLabel.setText(R.shipping);
        this.mShippingOptions.setVisibility(0);
        this.mAdapter.addAll(shippingOptions);
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (((ShippingOption) this.mAdapter.getItem(i)).getOptionId().equals(shippingDetails.getSelectedOptionId())) {
                this.mShippingOptions.setSelection(i, false);
                break;
            }
        }
        this.mShippingOptions.setOnItemSelectedListener(new C06181(this, cartGroupItem, shippingOptions));
    }
}
