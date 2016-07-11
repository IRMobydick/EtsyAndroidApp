package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.PrivacyFlag;
import com.etsy.android.lib.models.apiv3.StructuredShopPrivacy;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class StructuredShopPrivacyView extends StructuredShopPoliciesView {
    private static final int FIELD_OTHER_VISIBILITY_LIMIT = 250;
    private View mBtnExpandPrivacyOther;
    private StructuredShopPrivacy mPrivacyPolicy;
    private TextView mTxtPrivacyInfo;
    private TextView mTxtPrivacyItems;

    /* renamed from: com.etsy.android.uikit.view.shop.policies.StructuredShopPrivacyView.1 */
    class C10411 extends TrackingOnClickListener {
        final /* synthetic */ StructuredShopPrivacyView f4280a;

        C10411(StructuredShopPrivacyView structuredShopPrivacyView) {
            this.f4280a = structuredShopPrivacyView;
        }

        public void onViewClick(View view) {
            this.f4280a.mBtnExpandPrivacyOther.setVisibility(8);
            this.f4280a.setPrivacyContent(true);
        }
    }

    public StructuredShopPrivacyView(Context context) {
        super(context);
    }

    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context, LinearLayout linearLayout) {
        inflate(context, R.view_structured_shop_privacy, linearLayout);
        this.mBtnExpandPrivacyOther = findViewById(R.btn_privacy_other_expand);
        this.mTxtPrivacyInfo = (TextView) findViewById(R.txt_privacy_info);
        this.mTxtPrivacyItems = (TextView) findViewById(R.txt_privacy_items);
    }

    public void setStructuredShopPrivacy(@NonNull StructuredShopPrivacy structuredShopPrivacy, @Nullable StructuredShopPoliciesView structuredShopPoliciesView) {
        this.mPrivacyPolicy = structuredShopPrivacy;
        if (structuredShopPrivacy.hasAnyEnabledFlags()) {
            setPrivacyContent(false);
            this.mTxtPrivacyItems.setVisibility(0);
            return;
        }
        if (isSellerMode()) {
            this.mTxtPrivacyInfo.setText(R.structured_privacy_add_privacy_info);
        } else {
            this.mTxtPrivacyInfo.setText(Html.fromHtml(getResources().getString(R.structured_privacy_no_info_alternate_text)));
            linkifyContactShopUrlSpan(this.mTxtPrivacyInfo, structuredShopPoliciesView);
        }
        this.mTxtPrivacyItems.setVisibility(8);
    }

    private void setPrivacyContent(boolean z) {
        this.mTxtPrivacyInfo.setText(getResources().getString(R.structured_privacy_message));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        PrivacyFlag communication = this.mPrivacyPolicy.getCommunication();
        if (communication.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(communication.getLabel()).append("\n");
        }
        communication = this.mPrivacyPolicy.getFulfillment();
        if (communication.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(communication.getLabel()).append("\n");
        }
        communication = this.mPrivacyPolicy.getLegal();
        if (communication.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(communication.getLabel()).append("\n");
        }
        PrivacyFlag other = this.mPrivacyPolicy.getOther();
        if (other.isEnabled()) {
            CharSequence label = other.getLabel();
            if (other.getLabel().length() <= FIELD_OTHER_VISIBILITY_LIMIT || z) {
                this.mBtnExpandPrivacyOther.setVisibility(8);
            } else {
                this.mBtnExpandPrivacyOther.setVisibility(0);
                this.mBtnExpandPrivacyOther.setOnClickListener(new C10411(this));
                label = label.substring(0, FIELD_OTHER_VISIBILITY_LIMIT).trim() + "\u2026";
            }
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(label).append("\n");
        }
        this.mTxtPrivacyItems.setText(spannableStringBuilder.toString().trim());
    }
}
