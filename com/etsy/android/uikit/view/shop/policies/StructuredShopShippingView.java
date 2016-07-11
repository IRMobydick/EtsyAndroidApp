package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.util.CountryUtil;
import java.util.ArrayList;
import java.util.List;

public class StructuredShopShippingView extends StructuredShopPoliciesView {
    private Country mCountryFilter;
    private StructuredShopShipping mShippingPolicy;
    private boolean mShowProcessingTimeSection;
    private View mSpacer1;
    private View mSpacer2;
    private TextView mTxtCustomsFees;
    private TextView mTxtProcessingTime;
    private TextView mTxtShippingDisclaimer;
    private TextView mTxtShippingTime;
    private TextView mTxtSubheadCustomsFees;
    private TextView mTxtSubheadProcessingTime;
    private TextView mTxtSubheadShippingTime;

    public StructuredShopShippingView(Context context) {
        super(context);
    }

    public StructuredShopShippingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopShippingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopShippingView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context, LinearLayout linearLayout) {
        inflate(context, R.view_structured_shop_shipping, linearLayout);
        this.mTxtSubheadProcessingTime = (TextView) findViewById(R.txt_subhead_processing_time);
        this.mTxtProcessingTime = (TextView) findViewById(R.txt_processing_time);
        this.mSpacer1 = findViewById(R.spacer_1);
        this.mTxtSubheadShippingTime = (TextView) findViewById(R.txt_subhead_shipping_time);
        this.mTxtShippingTime = (TextView) findViewById(R.txt_shipping_time);
        this.mTxtShippingDisclaimer = (TextView) findViewById(R.txt_shipping_disclaimer);
        this.mSpacer2 = findViewById(R.spacer_2);
        this.mTxtSubheadCustomsFees = (TextView) findViewById(R.txt_subhead_customs_fees);
        this.mTxtCustomsFees = (TextView) findViewById(R.txt_customs_fees);
    }

    public void setStructuredShopShipping(@NonNull StructuredShopShipping structuredShopShipping, boolean z) {
        this.mShippingPolicy = structuredShopShipping;
        this.mShowProcessingTimeSection = z;
        setupView(this.mShippingPolicy, this.mShowProcessingTimeSection);
    }

    public void setupView(@NonNull StructuredShopShipping structuredShopShipping, boolean z) {
        int i;
        int i2 = 8;
        boolean z2 = false;
        Resources resources = getContext().getResources();
        if (z) {
            this.mTxtSubheadProcessingTime.setVisibility(0);
            this.mTxtProcessingTime.setVisibility(0);
            this.mTxtProcessingTime.setText(structuredShopShipping.getProcessingTimeText());
        } else {
            this.mTxtSubheadProcessingTime.setVisibility(8);
            this.mTxtProcessingTime.setVisibility(8);
        }
        List<StructuredShopShippingEstimate> estimates = structuredShopShipping.getEstimates();
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        List arrayList = new ArrayList();
        if (this.mCountryFilter != null) {
            String a = CountryUtil.m3022a(this.mCountryFilter.getCountryId());
            for (StructuredShopShippingEstimate structuredShopShippingEstimate : estimates) {
                if (structuredShopShippingEstimate.getCountryId() == null || structuredShopShippingEstimate.getCountryId().intValue() != this.mCountryFilter.getCountryId()) {
                    if (a != null && a.equals(structuredShopShippingEstimate.getRegionCode())) {
                        if (structuredShopShippingEstimate.isSet()) {
                            arrayList.add(structuredShopShippingEstimate);
                        }
                    }
                } else if (structuredShopShippingEstimate.isSet()) {
                    arrayList.add(structuredShopShippingEstimate);
                }
            }
        }
        List list;
        if (arrayList.isEmpty()) {
            list = estimates;
        } else {
            list = arrayList;
        }
        int i3 = 0;
        for (StructuredShopShippingEstimate structuredShopShippingEstimate2 : r0) {
            if (structuredShopShippingEstimate2.isSet()) {
                int i4 = i3 + 1;
                Object string = getResources().getString(StructuredShopShippingEstimate.UNIT_WEEKS.equals(structuredShopShippingEstimate2.getUnit()) ? R.structured_shipping_time_range_weeks : R.structured_shipping_time_range_business_days, new Object[]{Integer.valueOf(structuredShopShippingEstimate2.getMin()), Integer.valueOf(structuredShopShippingEstimate2.getMax())});
                CharSequence spannableStringBuilder2 = new SpannableStringBuilder();
                if (spannableStringBuilder.length() != 0) {
                    spannableStringBuilder2.append("\n");
                }
                spannableStringBuilder2.append(structuredShopShippingEstimate2.getDisplayName()).append(": ").append(string).setSpan(new ForegroundColorSpan(resources.getColor(R.dark_grey)), spannableStringBuilder2.toString().indexOf(string), spannableStringBuilder2.length(), 33);
                spannableStringBuilder.append(spannableStringBuilder2);
                i = i4;
            } else {
                i = i3;
            }
            i3 = i;
        }
        if (i3 > 0) {
            i3 = 1;
        } else {
            boolean z3 = false;
        }
        if (i3 != 0) {
            this.mTxtSubheadShippingTime.setVisibility(0);
            this.mTxtShippingTime.setVisibility(0);
            this.mTxtShippingTime.setText(spannableStringBuilder);
            this.mTxtShippingDisclaimer.setVisibility(0);
        } else {
            this.mTxtSubheadShippingTime.setVisibility(8);
            this.mTxtShippingTime.setVisibility(8);
            this.mTxtShippingDisclaimer.setVisibility(8);
        }
        boolean shipsInternational = structuredShopShipping.shipsInternational();
        if (shipsInternational) {
            this.mTxtSubheadCustomsFees.setVisibility(0);
            this.mTxtCustomsFees.setVisibility(0);
        } else {
            this.mTxtSubheadCustomsFees.setVisibility(8);
            this.mTxtCustomsFees.setVisibility(8);
        }
        View view = this.mSpacer1;
        if (!z || (i3 == 0 && !shipsInternational)) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        View view2 = this.mSpacer2;
        if (i3 != 0 && shipsInternational) {
            i2 = 0;
        }
        view2.setVisibility(i2);
        if (i3 != 0 || shipsInternational) {
            z2 = true;
        }
        setContentCollapsible(z2);
    }

    public void filterEstimates(@Nullable Country country) {
        this.mCountryFilter = country;
        if (this.mShippingPolicy != null) {
            setupView(this.mShippingPolicy, this.mShowProcessingTimeSection);
        }
    }
}
