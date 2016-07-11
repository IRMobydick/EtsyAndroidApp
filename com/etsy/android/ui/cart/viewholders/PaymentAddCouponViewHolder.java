package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentAddCoupon;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.HashMap;

public class PaymentAddCouponViewHolder extends BaseViewHolder<CartGroupItem> {
    private final Button mBtnAddCouponCode;
    private final CartGroupActionClickHandler mClickHandler;
    private final View mLabelCouponCode;
    private final EditText mTxtCouponCode;
    private final TextView mTxtError;

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentAddCouponViewHolder.1 */
    class C06101 extends TrackingOnClickListener {
        final /* synthetic */ PaymentAddCoupon f2608a;
        final /* synthetic */ PaymentAddCouponViewHolder f2609b;

        C06101(PaymentAddCouponViewHolder paymentAddCouponViewHolder, PaymentAddCoupon paymentAddCoupon) {
            this.f2609b = paymentAddCouponViewHolder;
            this.f2608a = paymentAddCoupon;
        }

        public void onViewClick(View view) {
            this.f2609b.showCouponFields(this.f2608a);
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentAddCouponViewHolder.2 */
    class C06112 implements OnEditorActionListener {
        final /* synthetic */ CartGroupItem f2610a;
        final /* synthetic */ PaymentAddCouponViewHolder f2611b;

        C06112(PaymentAddCouponViewHolder paymentAddCouponViewHolder, CartGroupItem cartGroupItem) {
            this.f2611b = paymentAddCouponViewHolder;
            this.f2610a = cartGroupItem;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            CartGroupAction action = this.f2610a.getAction(CartGroupAction.APPLY_COUPON);
            if (action != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(CartsRequest.PARAM_COUPON_CODE, this.f2611b.mTxtCouponCode.getText().toString());
                action.setParams(hashMap);
                this.f2611b.mClickHandler.m3711a(this.f2610a.getCartAdapterPosition(), action);
            }
            return false;
        }
    }

    public PaymentAddCouponViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903337, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mBtnAddCouponCode = (Button) findViewById(2131756035);
        this.mLabelCouponCode = findViewById(2131756036);
        this.mTxtCouponCode = (EditText) findViewById(2131756037);
        this.mTxtError = (TextView) findViewById(2131756038);
    }

    public void bind(CartGroupItem cartGroupItem) {
        PaymentAddCoupon paymentAddCoupon = (PaymentAddCoupon) cartGroupItem.getData();
        if (TextUtils.isEmpty(paymentAddCoupon.getCode())) {
            hideCouponFields();
        } else {
            showCouponFields(paymentAddCoupon);
        }
        if (this.mClickHandler != null) {
            this.mBtnAddCouponCode.setOnClickListener(new C06101(this, paymentAddCoupon));
            this.mTxtCouponCode.setOnEditorActionListener(new C06112(this, cartGroupItem));
        }
    }

    private void hideCouponFields() {
        this.mBtnAddCouponCode.setVisibility(0);
        this.mLabelCouponCode.setVisibility(8);
        this.mTxtCouponCode.setVisibility(8);
        this.mTxtError.setVisibility(8);
    }

    protected void showCouponFields(PaymentAddCoupon paymentAddCoupon) {
        this.mBtnAddCouponCode.setVisibility(8);
        this.mLabelCouponCode.setVisibility(0);
        this.mTxtCouponCode.setVisibility(0);
        this.mTxtCouponCode.setText(paymentAddCoupon.getCode());
        CharSequence error = paymentAddCoupon.getError();
        if (TextUtils.isEmpty(paymentAddCoupon.getError())) {
            this.mTxtError.setVisibility(8);
            return;
        }
        this.mTxtError.setVisibility(0);
        this.mTxtError.setText(error);
    }
}
