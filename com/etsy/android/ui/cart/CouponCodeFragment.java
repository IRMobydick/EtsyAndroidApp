package com.etsy.android.ui.cart;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ProgressButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class CouponCodeFragment extends EtsyFragment implements TextWatcher, OnEditorActionListener {
    private static final String TAG;
    private InputFilter mAlphanumericCharsFilter;
    private Cart mCart;
    private CouponCodeFragment mCouponCallback;
    private String mCouponCode;
    private EditText mCouponEditText;
    private ProgressButton mSubmitButton;

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.1 */
    class C05891 implements InputFilter {
        final /* synthetic */ CouponCodeFragment f2375a;

        C05891(CouponCodeFragment couponCodeFragment) {
            this.f2375a = couponCodeFragment;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            while (i < i2) {
                String ch = Character.toString(charSequence.charAt(i));
                if (ch.matches("[a-z]")) {
                    return ch.toUpperCase();
                }
                if (!ch.matches("[A-Z0-9]")) {
                    return StringUtils.EMPTY;
                }
                i++;
            }
            return null;
        }
    }

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.2 */
    class C05902 extends TrackingOnClickListener {
        final /* synthetic */ CouponCodeFragment f2376a;

        C05902(CouponCodeFragment couponCodeFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2376a = couponCodeFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case 2131755637:
                    this.f2376a.submitCoupon();
                default:
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.3 */
    class C05913 implements EtsyJobResponse<Cart> {
        final /* synthetic */ CouponCodeFragment f2377a;

        C05913(CouponCodeFragment couponCodeFragment) {
            this.f2377a = couponCodeFragment;
        }

        public void m3691a(List<Cart> list, int i, EtsyResult<Cart> etsyResult) {
            this.f2377a.onSuccessfulSave((Cart) list.get(0));
        }
    }

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.4 */
    class C05924 implements EtsyJobResponse {
        final /* synthetic */ CouponCodeFragment f2378a;

        C05924(CouponCodeFragment couponCodeFragment) {
            this.f2378a = couponCodeFragment;
        }

        public void m3692a(EtsyResult etsyResult) {
            this.f2378a.onSuccessfulSave(null);
        }
    }

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.5 */
    class C05935 implements EtsyJobResponse {
        final /* synthetic */ CouponCodeFragment f2379a;

        C05935(CouponCodeFragment couponCodeFragment) {
            this.f2379a = couponCodeFragment;
        }

        public void m3693a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1920e(CouponCodeFragment.TAG, "Error saving coupon code. Error code: %d. Error message: %s", Integer.valueOf(i), str);
            this.f2379a.onFailedToSave(i);
        }
    }

    /* renamed from: com.etsy.android.ui.cart.CouponCodeFragment.6 */
    class C05946 implements EtsyJobBuilder {
        final /* synthetic */ CouponCodeFragment f2380a;

        C05946(CouponCodeFragment couponCodeFragment) {
            this.f2380a = couponCodeFragment;
        }

        public void m3694a() {
            this.f2380a.startLoading();
        }
    }

    public CouponCodeFragment() {
        this.mAlphanumericCharsFilter = new C05891(this);
    }

    static {
        TAG = EtsyDebug.m1891a(CouponCodeFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mCart = (Cart) arguments.getSerializable("cart");
        }
    }

    public static CouponCodeFragment newInstance() {
        return new CouponCodeFragment();
    }

    public void setCouponListener(CouponCodeFragment couponCodeFragment) {
        this.mCouponCallback = couponCodeFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903194, viewGroup, false);
        this.mCouponEditText = (EditText) inflate.findViewById(2131755636);
        this.mCouponEditText.setOnEditorActionListener(this);
        this.mCouponEditText.addTextChangedListener(this);
        this.mSubmitButton = (ProgressButton) inflate.findViewById(2131755637);
        this.mSubmitButton.setOnClickListener(new C05902(this, this.mCart));
        this.mSubmitButton.setEnabled(false);
        setCouponEditTextFilters();
        if (this.mCart != null) {
            setupCouponEditText(this.mCart.getCouponCode());
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mCart == null && (getParentFragment() instanceof IDialogFragment)) {
            ((IDialogFragment) getParentFragment()).dismissAllowingStateLoss();
        }
    }

    private void setupCouponEditText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mCouponEditText.setText(str);
            this.mCouponEditText.setSelection(str.length());
        }
        ai.m3227b(this.mContext, this.mCouponEditText);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (editable.length() > 0) {
            this.mSubmitButton.setEnabled(true);
        } else {
            this.mSubmitButton.setEnabled(false);
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        submitCoupon();
        return true;
    }

    private void submitCoupon() {
        this.mCouponCode = this.mCouponEditText.getText().toString();
        getRequestQueue().m1697a((Object) this, EtsyJobBuilder.m1307a(CartsRequest.updateCart(this.mCart.getCartId())).m1323a(CartsRequest.PARAM_COUPON_CODE, this.mCouponCode).m1326b(CartsRequest.CART_INCLUDES).m1318a(new C05946(this)).m1320a(new C05935(this)).m1319a(new C05924(this)).m1321a(new C05913(this)).m1324a());
    }

    private void onFailedToSave(int i) {
        endLoading();
        if (i == 400) {
            CartEventTracker.m3721a(getAnalyticsContext(), this.mCart, "cart_view", this.mCouponCode);
            this.mCouponEditText.setError(getString(R.invalid_coupon));
            return;
        }
        bl.m3356a(this.mContext, getResources().getString(R.coupon_code_error_saving));
    }

    private void onSuccessfulSave(Cart cart) {
        if (!(this.mCouponCallback == null || cart == null)) {
            this.mCouponCallback.m3747a(cart);
        }
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).dismissAllowingStateLoss();
        }
        CartEventTracker.m3725b(getAnalyticsContext(), this.mCart, "cart_view", this.mCouponCode);
    }

    private void startLoading() {
        this.mCouponEditText.setEnabled(false);
        this.mSubmitButton.showLoading();
    }

    private void endLoading() {
        this.mCouponEditText.setEnabled(true);
        this.mSubmitButton.hideLoading();
    }

    private void setCouponEditTextFilters() {
        InputFilter[] filters = this.mCouponEditText.getFilters();
        List arrayList = filters == null ? new ArrayList() : new ArrayList(Arrays.asList(filters));
        if (getConfigMap().m885c(EtsyConfigKeys.f1225r)) {
            arrayList.add(this.mAlphanumericCharsFilter);
        }
        this.mCouponEditText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
    }
}
