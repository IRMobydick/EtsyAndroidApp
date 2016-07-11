package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.NonRefundableItem;
import com.etsy.android.lib.models.apiv3.StructuredShopRefunds;
import com.google.android.gms.gcm.Task;
import java.util.List;

public class StructuredShopRefundsView extends StructuredShopPoliciesView {
    private View mSpacer;
    private TextView mTxtCancelWithin;
    private TextView mTxtContactWithin;
    private TextView mTxtNonreturnableInfo;
    private TextView mTxtNonreturnableItems;
    private TextView mTxtNotAcceptedInfo;
    private TextView mTxtQuestionsInfo;
    private TextView mTxtReturnConditionsInfo;
    private TextView mTxtReturnWithin;
    private TextView mTxtSubheadAcceptedSummary;
    private TextView mTxtSubheadNonreturnable;
    private TextView mTxtSubheadNotAcceptedSummary;
    private TextView mTxtSubheadQuestions;
    private TextView mTxtSubheadReturnConditions;

    public StructuredShopRefundsView(Context context) {
        super(context);
    }

    public StructuredShopRefundsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopRefundsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopRefundsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context, LinearLayout linearLayout) {
        inflate(context, R.view_structured_shop_refunds, linearLayout);
        this.mTxtSubheadAcceptedSummary = (TextView) findViewById(R.txt_subhead_accepted_summary);
        this.mTxtContactWithin = (TextView) findViewById(R.txt_contact_within);
        this.mTxtReturnWithin = (TextView) findViewById(R.txt_return_within);
        this.mTxtCancelWithin = (TextView) findViewById(R.txt_cancel_within);
        this.mSpacer = findViewById(R.spacer);
        this.mTxtSubheadNotAcceptedSummary = (TextView) findViewById(R.txt_subhead_not_accepted_summary);
        this.mTxtNotAcceptedInfo = (TextView) findViewById(R.txt_not_accepted_info);
        this.mTxtSubheadNonreturnable = (TextView) findViewById(R.txt_subhead_nonreturnable);
        this.mTxtNonreturnableInfo = (TextView) findViewById(R.txt_nonreturnable_info);
        this.mTxtNonreturnableItems = (TextView) findViewById(R.txt_nonreturnable_items);
        this.mTxtSubheadReturnConditions = (TextView) findViewById(R.txt_subhead_return_conditions);
        this.mTxtReturnConditionsInfo = (TextView) findViewById(R.txt_return_conditions_info);
        this.mTxtSubheadQuestions = (TextView) findViewById(R.txt_subhead_questions);
        this.mTxtQuestionsInfo = (TextView) findViewById(R.txt_questions_info);
    }

    public void setStructuredShopRefunds(@NonNull StructuredShopRefunds structuredShopRefunds, @Nullable StructuredShopPoliciesView structuredShopPoliciesView) {
        boolean z;
        int i;
        int cancelWithinHours;
        int i2;
        Resources resources = getContext().getResources();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i3 = (structuredShopRefunds.acceptsReturns() || structuredShopRefunds.acceptsExchanges() || structuredShopRefunds.acceptsCancellations()) ? 1 : 0;
        if (structuredShopRefunds.acceptsReturns() || structuredShopRefunds.acceptsExchanges()) {
            z = true;
        } else {
            z = false;
        }
        if (structuredShopRefunds.acceptsReturns() && structuredShopRefunds.acceptsExchanges() && structuredShopRefunds.acceptsCancellations()) {
            i = 1;
        } else {
            i = 0;
        }
        this.mTxtSubheadAcceptedSummary.setVisibility(8);
        this.mTxtContactWithin.setVisibility(8);
        this.mTxtReturnWithin.setVisibility(8);
        this.mTxtCancelWithin.setVisibility(8);
        this.mTxtSubheadNotAcceptedSummary.setVisibility(8);
        this.mTxtNotAcceptedInfo.setVisibility(8);
        this.mTxtSubheadNonreturnable.setVisibility(8);
        this.mTxtNonreturnableInfo.setVisibility(8);
        this.mTxtNonreturnableItems.setVisibility(8);
        this.mTxtSubheadReturnConditions.setVisibility(8);
        this.mTxtReturnConditionsInfo.setVisibility(8);
        this.mTxtSubheadQuestions.setVisibility(8);
        this.mTxtQuestionsInfo.setVisibility(8);
        if (i3 != 0) {
            this.mTxtSubheadAcceptedSummary.setVisibility(0);
            this.mTxtSubheadAcceptedSummary.setText(structuredShopRefunds.getAcceptedSummaryString());
        }
        if (z) {
            this.mTxtContactWithin.setVisibility(0);
            this.mTxtReturnWithin.setVisibility(0);
            Object quantityString = resources.getQuantityString(R.structured_refunds_days, cancelWithinHours, new Object[]{Integer.valueOf(structuredShopRefunds.contactSellerWithinDays())});
            CharSequence spannableStringBuilder2 = new SpannableStringBuilder(resources.getString(R.structured_refunds_contact_within));
            spannableStringBuilder2.append(" ").append(quantityString).setSpan(new ForegroundColorSpan(resources.getColor(R.dark_grey)), spannableStringBuilder2.toString().indexOf(quantityString), spannableStringBuilder2.length(), 33);
            this.mTxtContactWithin.setText(spannableStringBuilder2);
            quantityString = resources.getQuantityString(R.structured_refunds_days, cancelWithinHours, new Object[]{Integer.valueOf(structuredShopRefunds.returnItemsWithinDays())});
            spannableStringBuilder2 = new SpannableStringBuilder(resources.getString(R.structured_refunds_return_within));
            spannableStringBuilder2.append(" ").append(quantityString).setSpan(new ForegroundColorSpan(resources.getColor(R.dark_grey)), spannableStringBuilder2.toString().indexOf(quantityString), spannableStringBuilder2.length(), 33);
            this.mTxtReturnWithin.setText(spannableStringBuilder2);
            List<NonRefundableItem> nonRefundableItems = structuredShopRefunds.getNonRefundableItems();
            CharSequence spannableStringBuilder3 = new SpannableStringBuilder();
            i2 = 0;
            for (NonRefundableItem nonRefundableItem : nonRefundableItems) {
                if (nonRefundableItem.isNonRefundable()) {
                    i2++;
                    if (spannableStringBuilder3.length() != 0) {
                        spannableStringBuilder3.append("\n");
                    }
                    spannableStringBuilder3.append(Html.fromHtml("&#8226; ")).append(nonRefundableItem.getName());
                }
                i2 = i2;
            }
            if (i2 > 0) {
                this.mTxtSubheadNonreturnable.setVisibility(0);
                this.mTxtNonreturnableInfo.setVisibility(0);
                this.mTxtNonreturnableItems.setVisibility(0);
                this.mTxtNonreturnableItems.setText(spannableStringBuilder3);
            }
        }
        if (structuredShopRefunds.acceptsCancellations()) {
            quantityString = null;
            String cancellationType = structuredShopRefunds.getCancellationType();
            i2 = -1;
            switch (cancellationType.hashCode()) {
                case -1907964558:
                    if (cancellationType.equals(StructuredShopRefunds.TYPE_CANCEL_BEFORE_SHIPPED)) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 99469071:
                    if (cancellationType.equals(StructuredShopRefunds.TYPE_CANCEL_WITHIN_HOURS)) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    cancelWithinHours = structuredShopRefunds.cancelWithinHours();
                    if (cancelWithinHours <= 24) {
                        quantityString = resources.getQuantityString(R.structured_refunds_hours_of_purchase, cancelWithinHours, new Object[]{Integer.valueOf(cancelWithinHours)});
                        break;
                    } else {
                        quantityString = resources.getQuantityString(R.structured_refunds_days_of_purchase, cancelWithinHours / 24, new Object[]{Integer.valueOf(cancelWithinHours / 24)});
                        break;
                    }
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    quantityString = resources.getString(R.structured_refunds_before_shipped);
                    break;
            }
            if (quantityString != null) {
                this.mTxtCancelWithin.setVisibility(0);
                CharSequence spannableStringBuilder4 = new SpannableStringBuilder(resources.getString(R.structured_refunds_accept_cancellations_within));
                spannableStringBuilder4.append(" ").append(quantityString).setSpan(new ForegroundColorSpan(resources.getColor(R.dark_grey)), spannableStringBuilder4.toString().indexOf(quantityString), spannableStringBuilder4.length(), 33);
                this.mTxtCancelWithin.setText(spannableStringBuilder4);
            }
        }
        View view = this.mSpacer;
        cancelWithinHours = (i3 == 0 || i != 0) ? 8 : 0;
        view.setVisibility(cancelWithinHours);
        if (i == 0) {
            this.mTxtSubheadNotAcceptedSummary.setVisibility(0);
            this.mTxtNotAcceptedInfo.setVisibility(0);
            this.mTxtSubheadNotAcceptedSummary.setText(structuredShopRefunds.getNotAcceptedSummaryString());
            this.mTxtNotAcceptedInfo.setText(Html.fromHtml(resources.getString(R.structured_refunds_cancellation_message)));
            linkifyContactShopUrlSpan(this.mTxtNotAcceptedInfo, structuredShopPoliciesView);
        }
        if (z) {
            this.mTxtSubheadReturnConditions.setVisibility(0);
            this.mTxtReturnConditionsInfo.setVisibility(0);
        }
        if (i != 0) {
            this.mTxtSubheadQuestions.setVisibility(0);
            this.mTxtQuestionsInfo.setVisibility(0);
            this.mTxtQuestionsInfo.setText(Html.fromHtml(resources.getString(R.structured_refunds_questions_message)));
            linkifyContactShopUrlSpan(this.mTxtQuestionsInfo, structuredShopPoliciesView);
        }
        setContentCollapsible(z);
    }
}
