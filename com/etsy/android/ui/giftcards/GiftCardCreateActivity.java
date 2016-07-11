package com.etsy.android.ui.giftcards;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.request.EtsyApiV2RequestJob;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.DialogNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

public class GiftCardCreateActivity extends BOENavDrawerActivity implements TextWatcher, OnCheckedChangeListener {
    private static final String SAVE_AMOUNTS = "save_amounts";
    private static final String SAVE_DESIGNS = "save_designs";
    private static final String TAG;
    private Button mBtnAddToCard;
    private View mErrorView;
    private ViewGroup mFormLayout;
    private List<Integer> mGiftCardAmounts;
    private GiftCardDesignSelecterView mGiftCardDesignSelector;
    private List<GiftCardDesign> mGiftCardDesigns;
    private ViewGroup mLayoutEmail;
    private View mLoadingView;
    private RadioGroup mRadioGroupCardValue;
    private RadioGroup mRadioGroupDeliveryOption;
    private Button mRetryButton;
    TrackingOnClickListener mRetryClickListener;
    private TextView mTxtEmailConfirm;
    private TextView mTxtMessage;
    private TextView mTxtRecipientEmail;
    private TextView mTxtRecipientName;
    private TextView mTxtSenderName;

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.1 */
    class C07001 implements OnCheckedChangeListener {
        final /* synthetic */ GiftCardCreateActivity f2982a;

        C07001(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2982a = giftCardCreateActivity;
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int i2 = 0;
            int i3 = i == 2131755329 ? 1 : 0;
            ViewGroup access$000 = this.f2982a.mLayoutEmail;
            if (i3 == 0) {
                i2 = 8;
            }
            access$000.setVisibility(i2);
            this.f2982a.mLayoutEmail.animate().alpha(i3 != 0 ? FullImageView.ASPECT_RATIO_SQUARE : 0.0f);
            this.f2982a.mBtnAddToCard.setEnabled(this.f2982a.validateFormData());
        }
    }

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.2 */
    class C07012 extends TrackingOnClickListener {
        final /* synthetic */ GiftCardCreateActivity f2983a;

        C07012(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2983a = giftCardCreateActivity;
        }

        public void onViewClick(View view) {
            this.f2983a.onAddToCart();
        }
    }

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.3 */
    class C07023 extends EtsyApiV2RequestJob<GiftCardDesign> {
        final /* synthetic */ GiftCardCreateActivity f2984a;

        C07023(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2984a = giftCardCreateActivity;
        }

        public void m4257a(@NonNull List<GiftCardDesign> list, int i, @NonNull EtsyResult<GiftCardDesign> etsyResult) {
            this.f2984a.mGiftCardDesigns = list;
            this.f2984a.updateFormDisplay();
        }

        public void m4256a(int i, @Nullable String str, @NonNull EtsyResult<GiftCardDesign> etsyResult) {
            EtsyDebug.m1919e(EtsyDebug.m1891a(GiftCardCreateActivity.class), str);
            this.f2984a.showError();
        }
    }

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.4 */
    class C07034 extends EtsyApiV2RequestJob<EtsyArray> {
        final /* synthetic */ GiftCardCreateActivity f2985a;

        C07034(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2985a = giftCardCreateActivity;
        }

        public void m4259a(@NonNull List<EtsyArray> list, int i, @NonNull EtsyResult<EtsyArray> etsyResult) {
            if (list.size() > 0) {
                JSONObject data = ((EtsyArray) list.get(0)).getData();
                if (data != null) {
                    this.f2985a.mGiftCardAmounts = new ArrayList();
                    JSONArray optJSONArray = data.optJSONArray("values");
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        this.f2985a.mGiftCardAmounts.add(Integer.valueOf(optJSONArray.optInt(i2)));
                    }
                    this.f2985a.updateFormDisplay();
                }
            }
        }

        public void m4258a(int i, @Nullable String str, @NonNull EtsyResult<EtsyArray> etsyResult) {
            EtsyDebug.m1919e(EtsyDebug.m1891a(GiftCardCreateActivity.class), str);
            this.f2985a.showError();
        }
    }

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.5 */
    class C07045 extends EtsyApiV2RequestJob<EmptyResult> {
        final /* synthetic */ GiftCardCreateActivity f2986a;

        C07045(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2986a = giftCardCreateActivity;
        }

        public void m4261a(@NonNull List<EmptyResult> list, int i, @NonNull EtsyResult<EmptyResult> etsyResult) {
            new AdHocEventCompatBuilder("gift_card_created").m5515a("create_gift_card").m5513a(this.f2986a.getAnalyticsContext()).m5517a();
            Toast.makeText(this.f2986a.getApplicationContext(), this.f2986a.getString(R.gift_card_create_success), 0).show();
            this.f2986a.setResult(-1);
            this.f2986a.finish();
        }

        public void m4260a(int i, @Nullable String str, @NonNull EtsyResult<EmptyResult> etsyResult) {
            String string;
            new AdHocEventCompatBuilder("gift_card_create_error").m5515a("create_gift_card").m5513a(this.f2986a.getAnalyticsContext()).m5517a();
            EtsyDebug.m1919e(EtsyDebug.m1891a(GiftCardCreateActivity.class), str);
            this.f2986a.mLoadingView.setVisibility(8);
            DialogNavigator d = Nav.m4682a(this.f2986a).m4686d();
            if (TextUtils.isEmpty(str)) {
                string = this.f2986a.getString(R.gift_card_create_submit_error);
            } else {
                string = str;
            }
            d.m4412a(null, (int) R.ok, 0, 0, string);
        }
    }

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardCreateActivity.6 */
    class C07056 extends TrackingOnClickListener {
        final /* synthetic */ GiftCardCreateActivity f2987a;

        C07056(GiftCardCreateActivity giftCardCreateActivity) {
            this.f2987a = giftCardCreateActivity;
        }

        public void onViewClick(View view) {
            this.f2987a.startLoading();
        }
    }

    public GiftCardCreateActivity() {
        this.mRetryClickListener = new C07056(this);
    }

    static {
        TAG = EtsyDebug.m1891a(GiftCardCreateActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903074);
        setTitle((int) R.create_gift_card);
        this.mFormLayout = (ViewGroup) findViewById(2131755321);
        this.mLoadingView = findViewById(R.loading_view);
        this.mLoadingView.setBackgroundColor(getResources().getColor(R.white_transparent));
        this.mErrorView = findViewById(R.error_view);
        this.mRetryButton = (Button) findViewById(R.btn_retry_internet);
        this.mRetryButton.setOnClickListener(this.mRetryClickListener);
        this.mTxtSenderName = (TextView) findViewById(2131755324);
        this.mTxtSenderName.addTextChangedListener(this);
        this.mTxtRecipientName = (TextView) findViewById(2131755323);
        this.mTxtRecipientName.addTextChangedListener(this);
        this.mTxtRecipientEmail = (TextView) findViewById(2131755332);
        this.mTxtRecipientEmail.addTextChangedListener(this);
        this.mTxtEmailConfirm = (TextView) findViewById(2131755333);
        this.mTxtEmailConfirm.addTextChangedListener(this);
        this.mLayoutEmail = (ViewGroup) findViewById(2131755331);
        this.mTxtMessage = (TextView) findViewById(2131755325);
        this.mGiftCardDesignSelector = (GiftCardDesignSelecterView) findViewById(2131755322);
        this.mRadioGroupDeliveryOption = (RadioGroup) findViewById(2131755328);
        this.mRadioGroupDeliveryOption.setOnCheckedChangeListener(new C07001(this));
        this.mRadioGroupCardValue = (RadioGroup) findViewById(2131755326);
        this.mRadioGroupCardValue.setOnCheckedChangeListener(this);
        this.mBtnAddToCard = (Button) findViewById(2131755334);
        this.mBtnAddToCard.setOnClickListener(new C07012(this));
        if (bundle != null) {
            this.mGiftCardDesigns = (List) Parcels.m7495a(bundle.getParcelable(SAVE_DESIGNS));
            this.mGiftCardAmounts = (List) Parcels.m7495a(bundle.getParcelable(SAVE_AMOUNTS));
            if (this.mGiftCardDesigns == null || this.mGiftCardAmounts == null) {
                showError();
                return;
            } else {
                updateFormDisplay();
                return;
            }
        }
        startLoading();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(SAVE_DESIGNS, Parcels.m7494a(this.mGiftCardDesigns));
        bundle.putParcelable(SAVE_AMOUNTS, Parcels.m7494a(this.mGiftCardAmounts));
    }

    private void requestGiftCardDesigns() {
        EtsyApiV2RequestJob a = EtsyApiV2RequestJob.m1449a(new GiftCardRequestFactory().m4262a());
        a.m1422a(new C07023(this), (Activity) this);
        getRequestQueue().m1696a((Object) this, a.m1426c());
    }

    private void requestGiftCardAmounts() {
        EtsyApiV2RequestJob a = EtsyApiV2RequestJob.m1449a(new GiftCardRequestFactory().m4264b());
        a.m1422a(new C07034(this), (Activity) this);
        getRequestQueue().m1696a((Object) this, a.m1426c());
    }

    private void updateFormDisplay() {
        if (this.mGiftCardDesigns != null && this.mGiftCardAmounts != null) {
            this.mGiftCardDesignSelector.setGiftCardDesigns(this.mGiftCardDesigns);
            addGiftCardAmountButtons(this.mGiftCardAmounts);
            stopLoading();
        }
    }

    private void onAddToCart() {
        if (validateFormData()) {
            int checkedRadioButtonId = this.mRadioGroupCardValue.getCheckedRadioButtonId();
            if (checkedRadioButtonId == 0) {
                EtsyDebug.m1919e(TAG, "No Gift Card Value was retrieved");
                return;
            }
            EtsyApiV2RequestJob a = EtsyApiV2RequestJob.m1449a(new GiftCardRequestFactory().m4263a(checkedRadioButtonId, this.mRadioGroupDeliveryOption.getCheckedRadioButtonId() == 2131755329 ? this.mTxtRecipientEmail.getText().toString().trim() : StringUtils.EMPTY, this.mTxtRecipientName.getText().toString(), this.mTxtSenderName.getText().toString(), this.mGiftCardDesignSelector.getSelectedGiftCardId(), this.mTxtMessage.getText().toString()));
            a.m1422a(new C07045(this), (Activity) this);
            this.mLoadingView.setVisibility(0);
            getRequestQueue().m1696a((Object) this, a.m1426c());
        }
    }

    private boolean validateFormData() {
        int i = 1;
        CharSequence trim = this.mTxtSenderName.getText().toString().trim();
        CharSequence trim2 = this.mTxtRecipientName.getText().toString().trim();
        CharSequence text = this.mTxtRecipientEmail.getText();
        CharSequence text2 = this.mTxtEmailConfirm.getText();
        boolean z = (TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2) || this.mRadioGroupCardValue.getCheckedRadioButtonId() == -1) ? false : true;
        if (this.mRadioGroupDeliveryOption.getCheckedRadioButtonId() != 2131755329) {
            return z;
        }
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text2) || !Patterns.EMAIL_ADDRESS.matcher(text).matches() || !TextUtils.equals(text, text2)) {
            i = 0;
        }
        return z & i;
    }

    private void startLoading() {
        this.mGiftCardDesigns = null;
        this.mGiftCardAmounts = null;
        this.mFormLayout.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        requestGiftCardDesigns();
        requestGiftCardAmounts();
    }

    private void stopLoading() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mFormLayout.setVisibility(0);
    }

    private void showError() {
        this.mLoadingView.setVisibility(8);
        this.mFormLayout.setVisibility(8);
        this.mErrorView.setVisibility(0);
    }

    private void addGiftCardAmountButtons(List<Integer> list) {
        this.mRadioGroupCardValue.setWeightSum((float) list.size());
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = (RadioButton) layoutInflater.inflate(2130903557, this.mRadioGroupCardValue, false);
            int intValue = ((Integer) list.get(i)).intValue();
            radioButton.setId(intValue);
            radioButton.setText(CurrencyUtil.m3055a((double) intValue));
            this.mRadioGroupCardValue.addView(radioButton);
        }
        ((RadioButton) this.mRadioGroupCardValue.getChildAt(0)).setChecked(true);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.mBtnAddToCard.setEnabled(validateFormData());
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.mBtnAddToCard.setEnabled(validateFormData());
    }

    @NonNull
    public String getTrackingName() {
        return "create_gift_card";
    }
}
