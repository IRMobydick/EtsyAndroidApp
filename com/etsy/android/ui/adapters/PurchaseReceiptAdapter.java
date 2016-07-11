package com.etsy.android.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.view.RatingIconView;

/* renamed from: com.etsy.android.ui.adapters.l */
class PurchaseReceiptAdapter {
    ImageView f2201a;
    TextView f2202b;
    TextView f2203c;
    TextView f2204d;
    TextView f2205e;
    TextView f2206f;
    RatingIconView f2207g;

    PurchaseReceiptAdapter(View view) {
        this.f2201a = (ImageView) view.findViewById(R.item_image);
        this.f2202b = (TextView) view.findViewById(R.listing_title);
        this.f2203c = (TextView) view.findViewById(2131756085);
        this.f2204d = (TextView) view.findViewById(2131756387);
        this.f2205e = (TextView) view.findViewById(2131756388);
        this.f2206f = (TextView) view.findViewById(2131755412);
        this.f2207g = (RatingIconView) view.findViewById(R.rating);
    }
}
