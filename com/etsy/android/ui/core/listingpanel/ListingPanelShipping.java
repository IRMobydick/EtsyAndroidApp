package com.etsy.android.ui.core.listingpanel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.PaymentTemplate;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.ListingEtsyAsap;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.models.apiv3.ShippingAddressPreference;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.apiv3.ListingEtsyAsapRequest;
import com.etsy.android.lib.requests.apiv3.ListingShippingRequest;
import com.etsy.android.lib.requests.apiv3.ShippingCostRequest;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ai;
import com.etsy.android.ui.core.listingpanel.g.AnonymousClass12;
import com.etsy.android.ui.core.listingpanel.g.AnonymousClass13;
import com.etsy.android.ui.core.listingpanel.g.AnonymousClass14;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* renamed from: com.etsy.android.ui.core.listingpanel.g */
public class ListingPanelShipping extends ListingPanelBase {
    private ShippingOption f2865A;
    private TextWatcher f2866B;
    private TrackingOnClickListener f2867C;
    private View f2868k;
    private TextView f2869l;
    private TextView f2870m;
    private LinearLayout f2871n;
    private TextView f2872o;
    private TextView f2873p;
    private TextView f2874q;
    private LinearLayout f2875r;
    private Button f2876s;
    private EditText f2877t;
    private TextView f2878u;
    private View f2879v;
    private ListingShippingDetails f2880w;
    private List<Country> f2881x;
    private Country f2882y;
    private String f2883z;

    /* compiled from: ListingPanelShipping */
    /* renamed from: com.etsy.android.ui.core.listingpanel.g.12 */
    class AnonymousClass12 extends TrackingOnClickListener {
        final /* synthetic */ Shop f2848a;
        final /* synthetic */ ListingPanelShipping f2849b;

        AnonymousClass12(ListingPanelShipping listingPanelShipping, ITrackedObject[] iTrackedObjectArr, Shop shop) {
            this.f2849b = listingPanelShipping;
            this.f2848a = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f2849b.a).m4529g().m4512c(this.f2848a.getShopId(), null);
        }
    }

    /* compiled from: ListingPanelShipping */
    /* renamed from: com.etsy.android.ui.core.listingpanel.g.13 */
    class AnonymousClass13 extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShipping f2850a;

        AnonymousClass13(ListingPanelShipping listingPanelShipping, ITrackedObject... iTrackedObjectArr) {
            this.f2850a = listingPanelShipping;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2850a.m4141z();
        }
    }

    /* compiled from: ListingPanelShipping */
    /* renamed from: com.etsy.android.ui.core.listingpanel.g.14 */
    class AnonymousClass14 extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShipping f2851a;

        AnonymousClass14(ListingPanelShipping listingPanelShipping, ITrackedObject... iTrackedObjectArr) {
            this.f2851a = listingPanelShipping;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2851a.m4084A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.1 */
    class ListingPanelShipping extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShipping f2856a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2856a = listingPanelShipping;
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case 2131756329:
                    this.f2856a.m4093J();
                default:
            }
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.2 */
    class ListingPanelShipping implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShipping f2857a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2857a = listingPanelShipping;
        }

        public void m4076a(EtsyResult etsyResult) {
            this.f2857a.m4095L();
            this.f2857a.m4137v();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.3 */
    class ListingPanelShipping implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShipping f2858a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2858a = listingPanelShipping;
        }

        public void m4077a(int i, String str, EtsyResult etsyResult) {
            new AdHocEventCompatBuilder("shipping_cost_retreival_error").m5515a("view_listing").m5516a(new ListingPanelShipping$11$1(this, str)).m5511a(AnalyticsLogAttribute.LISTING_ID, this.f2858a.b.getListingId()).m5510a(this.f2858a.c).m5517a();
            this.f2858a.m4094K();
            this.f2858a.m4137v();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.4 */
    class ListingPanelShipping implements EtsyJobBuilder {
        final /* synthetic */ ListingPanelShipping f2859a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2859a = listingPanelShipping;
        }

        public void m4078a() {
            SharedPreferencesUtility.m3143f(this.f2859a.a, this.f2859a.f2882y.getIsoCountryCode());
            SharedPreferencesUtility.m3145g(this.f2859a.a, this.f2859a.f2883z);
            this.f2859a.m4106W();
            this.f2859a.m4136u();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.5 */
    class ListingPanelShipping implements EtsyJobResponse<ListingEtsyAsap> {
        final /* synthetic */ ListingPanelShipping f2860a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2860a = listingPanelShipping;
        }

        public void m4079a(List<ListingEtsyAsap> list, int i, EtsyResult<ListingEtsyAsap> etsyResult) {
            if (this.f2860a.f2880w != null) {
                this.f2860a.f2880w.setOffersEtsyAsap(((ListingEtsyAsap) list.get(0)).offersEtsyAsap());
                this.f2860a.f2880w.setOffersEtsyAsapToday(((ListingEtsyAsap) list.get(0)).offersEtsyAsapToday());
            }
            this.f2860a.m4139x();
            this.f2860a.m4140y();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.6 */
    class ListingPanelShipping implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShipping f2861a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2861a = listingPanelShipping;
        }

        public void m4080a(EtsyResult etsyResult) {
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.7 */
    class ListingPanelShipping implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShipping f2862a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2862a = listingPanelShipping;
        }

        public void m4081a(int i, String str, EtsyResult etsyResult) {
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.8 */
    class ListingPanelShipping implements EtsyJobResponse<ListingShippingDetails> {
        final /* synthetic */ ListingPanelShipping f2863a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2863a = listingPanelShipping;
        }

        public void m4082a(List<ListingShippingDetails> list, int i, EtsyResult<ListingShippingDetails> etsyResult) {
            this.f2863a.m4109a((ListingShippingDetails) list.get(0));
            this.f2863a.m4137v();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.g.9 */
    class ListingPanelShipping implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShipping f2864a;

        ListingPanelShipping(ListingPanelShipping listingPanelShipping) {
            this.f2864a = listingPanelShipping;
        }

        public void m4083a(EtsyResult etsyResult) {
            this.f2864a.m4137v();
        }
    }

    public ListingPanelShipping(Listing listing, BaseActivity baseActivity, @NonNull ad adVar) {
        super(listing, baseActivity, adVar);
        this.f2867C = new ListingPanelShipping(this);
        m4025a(2131756150, 2131756147, 2131756148, 2131756149);
    }

    protected void m4143c() {
        m4130o();
        m4131p();
        m4132q();
        m4134s();
        m4133r();
        if (this.b.isSoldOut()) {
            this.c.findViewById(2131756189).setVisibility(8);
            m4137v();
            m4139x();
            m4140y();
        } else if (this.f2880w == null) {
            m4102S();
        } else {
            m4109a(this.f2880w);
            m4137v();
        }
    }

    private void m4130o() {
        this.f2868k = this.d.findViewById(2131756218);
        this.f2869l = (TextView) this.c.findViewById(2131756208);
        this.f2870m = (TextView) this.c.findViewById(2131756209);
        this.f2871n = (LinearLayout) this.c.findViewById(2131756210);
        this.f2872o = (TextView) this.c.findViewById(2131756211);
        this.f2873p = (TextView) this.c.findViewById(2131756212);
        this.f2874q = (TextView) this.c.findViewById(2131756213);
        this.f2875r = (LinearLayout) this.c.findViewById(2131756214);
        this.f2876s = (Button) this.c.findViewById(2131756215);
        this.f2877t = (EditText) this.c.findViewById(2131756216);
        this.f2878u = (TextView) this.c.findViewById(2131756217);
        this.f2879v = this.c.findViewById(2131756231);
        View findViewById = this.c.findViewById(2131756326);
        View findViewById2 = this.c.findViewById(2131756329);
        if (findViewById != null) {
            findViewById.setOnClickListener(this.f2867C);
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(this.f2867C);
        }
        if (this.b.isDigitalDownload()) {
            ((TextView) this.f).setText(R.shop_policies);
            return;
        }
        this.c.findViewById(2131756189).setVisibility(0);
        m4087D();
        m4090G();
        m4135t();
    }

    private void m4131p() {
        Shop shop = this.b.getShop();
        if (shop == null || !shop.hasPolicies()) {
            this.f2879v.setVisibility(8);
            return;
        }
        this.f2879v.setVisibility(0);
        this.f2879v.setOnClickListener(new AnonymousClass12(this, new ITrackedObject[]{shop, this.b}, shop));
    }

    private void m4132q() {
        TextView textView = (TextView) this.c.findViewById(2131756190);
        textView.setVisibility(0);
        if (this.b.isDigitalDownload()) {
            textView.setText(R.file_delivery_message);
        } else if (this.b.getProcessingDaysMin() > 0 || this.b.getProcessingDaysMax() > 0) {
            Object valueOf;
            if (this.b.getProcessingDaysMin() == this.b.getProcessingDaysMax()) {
                valueOf = String.valueOf(this.b.getProcessingDaysMin());
            } else {
                valueOf = this.b.getProcessingDaysMin() + "-" + this.b.getProcessingDaysMax();
            }
            if ("1".equals(valueOf)) {
                textView.setText(String.format(this.c.getResources().getString(R.shipping_processing_singular), new Object[]{valueOf}));
                return;
            }
            textView.setText(String.format(this.c.getResources().getString(R.shipping_processing), new Object[]{valueOf}));
        } else {
            textView.setVisibility(8);
        }
    }

    private void m4133r() {
        if (!this.b.isDigitalDownload() && this.b.getShippingInfo().size() > 0) {
            TextView textView = (TextView) this.c.findViewById(2131756191);
            textView.setVisibility(0);
            textView.setText(String.format(this.c.getResources().getString(R.shipping_ships_from), new Object[]{((ShippingInfo) this.b.getShippingInfo().get(0)).getOriginCountryName()}));
        }
    }

    private void m4134s() {
        PaymentTemplate paymentInfo = this.b.getPaymentInfo();
        if (paymentInfo != null) {
            View findViewById = this.c.findViewById(2131756187);
            View findViewById2 = this.c.findViewById(2131756186);
            if (paymentInfo.getAllowCc()) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(0);
            }
            if (paymentInfo.getAllowPaypal()) {
                findViewById2.setVisibility(0);
            }
            TextView textView = (TextView) this.c.findViewById(2131756188);
            if (paymentInfo.getAllowOther() || paymentInfo.getAllowCheck() || paymentInfo.getAllowMo() || paymentInfo.getAllowBt()) {
                ArrayList arrayList = new ArrayList();
                if (paymentInfo.getAllowOther()) {
                    arrayList.add(this.c.getResources().getString(R.payment_method_label_other));
                }
                if (paymentInfo.getAllowCheck()) {
                    arrayList.add(this.c.getResources().getString(R.payment_method_label_check));
                }
                if (paymentInfo.getAllowMo()) {
                    arrayList.add(this.c.getResources().getString(R.payment_method_label_money_order));
                }
                if (paymentInfo.getAllowBt()) {
                    arrayList.add(this.c.getResources().getString(R.payment_method_label_bank_transfer));
                }
                textView.setText(TextUtils.join(", ", arrayList.toArray()));
                return;
            }
            textView.setVisibility(8);
            return;
        }
        this.c.findViewById(2131756185).setVisibility(4);
    }

    private void m4135t() {
        OnClickListener anonymousClass13 = new AnonymousClass13(this, this.b);
        this.f2873p.setOnClickListener(new AnonymousClass14(this, this.b));
        this.f2870m.setOnClickListener(anonymousClass13);
        this.f2876s.setOnClickListener(anonymousClass13);
        m4103T();
        this.f2866B = new TextWatcher() {
            final /* synthetic */ ListingPanelShipping f2852a;

            {
                this.f2852a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.f2852a.f2883z = editable.toString();
            }
        };
        this.f2877t.addTextChangedListener(this.f2866B);
        this.f2877t.setOnEditorActionListener(new OnEditorActionListener() {
            final /* synthetic */ ListingPanelShipping f2853a;

            {
                this.f2853a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null && i != 6) {
                    return false;
                }
                this.f2853a.m4115a(textView.getText().toString());
                ai.m3225a(this.f2853a.a, textView);
                return true;
            }
        });
    }

    @CallSuper
    public void m4142b() {
        super.m4033b();
        m4103T();
    }

    private void m4136u() {
        this.f2868k.setBackgroundColor(this.f2868k.getResources().getColor(2131624265));
        this.f2868k.setVisibility(0);
    }

    private void m4137v() {
        this.f2868k.setVisibility(8);
    }

    protected void m4144i() {
        m4104U();
        m4138w();
    }

    protected void m4145j() {
        m4138w();
    }

    protected void m4146k() {
        ai.m3225a(this.a, this.f2877t);
    }

    private void m4138w() {
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        if (m4118b(false) && this.f2865A != null) {
            m4089F();
            m4085B();
            m4092I();
        } else if (m4118b(false) && this.f2880w != null && this.f2865A == null) {
            m4095L();
        } else if (this.f2882y == null && TextUtils.isEmpty(this.f2883z)) {
            m4089F();
            m4086C();
            m4091H();
        } else {
            m4088E();
            m4086C();
            m4092I();
        }
        m4031a(false);
        m4139x();
        m4140y();
    }

    private void m4139x() {
        View findViewById = this.c.findViewById(2131756115);
        if (findViewById != null) {
            if (!m4045n().m885c(EtsyConfigKeys.bj)) {
                findViewById.setVisibility(8);
            } else if (this.f2880w == null || !this.f2880w.offersEtsyAsap()) {
                findViewById.setVisibility(8);
            } else {
                TextView textView = (TextView) this.c.findViewById(2131756116);
                if (this.f2880w.offersEtsyAsapToday()) {
                    textView.setText(this.a.getString(R.listing_etsy_asap_available_today));
                } else {
                    textView.setText(this.a.getString(R.listing_etsy_asap_available_tomorrow));
                }
                findViewById.setVisibility(0);
            }
        }
    }

    private void m4140y() {
        View findViewById = this.c.findViewById(2131756326);
        if (findViewById != null) {
            if (!m4045n().m885c(EtsyConfigKeys.bj)) {
                findViewById.setVisibility(8);
            } else if (this.f2880w != null && this.f2880w.offersEtsyAsap() && SharedPreferencesUtility.m3127a(this.a, "show_etsy_asap_banner")) {
                TextView textView = (TextView) this.c.findViewById(2131756328);
                if (this.f2880w.offersEtsyAsapToday()) {
                    textView.setText(this.a.getString(R.etsy_asap_get_it_today));
                } else {
                    textView.setText(this.a.getString(R.etsy_asap_get_it_tomorrow));
                }
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
        }
    }

    private void m4141z() {
        ArrayList arrayList;
        if (this.f2881x == null || this.f2881x.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.f2881x.size());
            arrayList.addAll(this.f2881x);
        }
        Nav.m4682a(this.a).m4686d().m4411a(new CountryUtil() {
            final /* synthetic */ ListingPanelShipping f2854a;

            {
                this.f2854a = r1;
            }

            public void m4074a(Country country) {
                this.f2854a.m4108a(country);
            }
        }, arrayList, null, "view_listing");
    }

    private void m4084A() {
        if (m4097N()) {
            m4086C();
            m4088E();
            return;
        }
        m4141z();
    }

    private void m4085B() {
        m4087D();
        this.f2871n.setVisibility(0);
    }

    private void m4086C() {
        this.f2871n.setVisibility(8);
    }

    private void m4087D() {
        if (this.f2882y != null) {
            if (TextUtils.isEmpty(this.f2883z) || !m4097N()) {
                this.f2872o.setText(this.f2882y.getName());
            } else {
                this.f2872o.setText(this.f2882y.getName() + ", " + this.f2883z);
            }
        }
        if (this.f2865A == null || StringUtils.EMPTY.equals(this.f2865A.getOptionId())) {
            this.f2874q.setText(StringUtils.EMPTY);
            return;
        }
        this.f2874q.setText(this.f2865A.getCost().format());
        m4105V();
    }

    private void m4088E() {
        m4090G();
        this.f2875r.setVisibility(0);
        this.f2876s.setVisibility(0);
    }

    private void m4089F() {
        this.f2875r.setVisibility(8);
        this.f2876s.setVisibility(8);
    }

    private void m4090G() {
        if (this.f2882y != null) {
            this.f2876s.setText(this.f2882y.getName());
        } else {
            this.f2876s.setText(R.shipping_to_default);
        }
        if (this.f2882y == null || !m4097N()) {
            this.f2877t.setVisibility(8);
        } else {
            this.f2877t.setVisibility(0);
        }
        this.f2877t.setText(this.f2883z);
    }

    private void m4091H() {
        this.f2870m.setVisibility(0);
        this.f2869l.setText(R.shipping_panel_cost_label);
    }

    private void m4092I() {
        this.f2870m.setVisibility(8);
        this.f2869l.setText(R.shipping_panel_to_label);
    }

    private void m4093J() {
        SharedPreferencesUtility.m3123a(this.a, "show_etsy_asap_banner", false);
        m4140y();
    }

    private void m4094K() {
        m4086C();
        m4088E();
        Toast.makeText(this.a, R.shipping_error_cost, 0).show();
    }

    private void m4095L() {
        m4086C();
        m4089F();
        m4091H();
        this.f2870m.setText(R.shipping_cost_action_update);
        this.f2878u.setText(this.f2878u.getResources().getString(R.shipping_error_destination, new Object[]{this.f2882y.getName()}));
        this.f2878u.setVisibility(0);
    }

    private void m4096M() {
        this.f2878u.setText(StringUtils.EMPTY);
        this.f2878u.setVisibility(8);
        this.f2870m.setText(R.shipping_cost_action_calculate);
        this.f2876s.setError(null);
    }

    private boolean m4118b(boolean z) {
        if (this.f2882y != null) {
            if (!m4097N() || (!TextUtils.isEmpty(this.f2883z) && this.f2883z.matches("^(\\d{5})(-\\d{4})?$"))) {
                return true;
            }
            if (!z) {
                return false;
            }
            new AdHocEventCompatBuilder("invalid_listing_destination").m5515a("view_listing").m5516a(new ListingPanelShipping$8(this)).m5514a(this.b).m5517a();
            Toast.makeText(this.a, R.shipping_error_zip, 0).show();
            return false;
        } else if (!z) {
            return false;
        } else {
            this.f2876s.setError(this.a.getResources().getString(R.shipping_error_country));
            return false;
        }
    }

    private void m4109a(ListingShippingDetails listingShippingDetails) {
        this.f2880w = listingShippingDetails;
        ShippingAddressPreference shippingAddress = listingShippingDetails.getShippingAddress();
        if (shippingAddress != null) {
            this.f2882y = shippingAddress.getCountry();
            this.f2883z = shippingAddress.getPostalCode();
        }
        this.f2865A = listingShippingDetails.getShippingOption();
        this.f2881x = listingShippingDetails.getCountries();
        m4138w();
    }

    private void m4108a(Country country) {
        this.f2882y = country;
        this.f2865A = null;
        if (this.f2880w != null) {
            this.f2880w.getShippingAddress().setCountry(this.f2882y);
        }
        m4090G();
        m4099P();
    }

    private void m4115a(String str) {
        this.f2883z = str;
        this.f2865A = null;
        if (this.f2880w != null) {
            this.f2880w.getShippingAddress().setPostalCode(this.f2883z);
        }
        m4090G();
        m4098O();
    }

    private void m4110a(ShippingOption shippingOption) {
        this.f2865A = shippingOption;
        m4138w();
    }

    private boolean m4097N() {
        return this.f2882y != null && Locale.US.getISO3Country().equals(this.f2882y.getWorldBankCode());
    }

    private void m4098O() {
        m4096M();
        if (m4118b(true)) {
            m4100Q();
            m4101R();
        }
    }

    private void m4099P() {
        m4096M();
        if (m4118b(false)) {
            m4100Q();
            m4101R();
            return;
        }
        this.f2865A = null;
        m4138w();
    }

    private void m4100Q() {
        String str;
        EtsyId listingId = this.b.getListingId();
        String isoCountryCode = this.f2882y.getIsoCountryCode();
        if (m4097N()) {
            str = this.f2883z;
        } else {
            str = null;
        }
        m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(ShippingCostRequest.getStandardShippingOptionForListing(listingId, isoCountryCode, str)).m1318a(new ListingPanelShipping(this)).m1320a(new ListingPanelShipping(this)).m1319a(new ListingPanelShipping(this)).m1321a(new EtsyJobResponse<ShippingOption>() {
            final /* synthetic */ ListingPanelShipping f2855a;

            {
                this.f2855a = r1;
            }

            public void m4075a(List<ShippingOption> list, int i, EtsyResult<ShippingOption> etsyResult) {
                this.f2855a.m4110a((ShippingOption) list.get(0));
                this.f2855a.m4137v();
            }
        }).m1324a());
    }

    private void m4101R() {
        String str;
        EtsyId listingId = this.b.getListingId();
        String isoCountryCode = this.f2882y.getIsoCountryCode();
        if (m4097N()) {
            str = this.f2883z;
        } else {
            str = null;
        }
        m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(ListingEtsyAsapRequest.getListingOffersEtsyAsap(listingId, isoCountryCode, str)).m1320a(new ListingPanelShipping(this)).m1319a(new ListingPanelShipping(this)).m1321a(new ListingPanelShipping(this)).m1324a());
    }

    private void m4102S() {
        m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(ListingShippingRequest.getListingShippingDetails(this.a, this.b.getListingId())).m1318a(new EtsyJobBuilder() {
            final /* synthetic */ ListingPanelShipping f2847a;

            {
                this.f2847a = r1;
            }

            public void m4073a() {
                this.f2847a.m4136u();
            }
        }).m1320a(new EtsyJobResponse() {
            final /* synthetic */ ListingPanelShipping f2846a;

            {
                this.f2846a = r1;
            }

            public void m4072a(int i, String str, EtsyResult etsyResult) {
                this.f2846a.m4137v();
            }
        }).m1319a(new ListingPanelShipping(this)).m1321a(new ListingPanelShipping(this)).m1324a());
    }

    private void m4103T() {
        if (this.f2877t != null && this.f2866B != null) {
            this.f2877t.removeTextChangedListener(this.f2866B);
            this.f2866B = null;
        }
    }

    private void m4104U() {
        EtsyLogger.m1966a().m1997b("shipping_tab_click", "view_listing", new ListingPanelShipping$20(this));
    }

    private void m4105V() {
        new AdHocEventCompatBuilder("shipping_costs_view").m5515a("view_listing").m5516a(new ListingPanelShipping$21(this)).m5510a(this.c).m5517a();
    }

    private void m4106W() {
        new AdHocEventCompatBuilder("shipping_costs_request").m5515a("view_listing").m5516a(new ListingPanelShipping$22(this)).m5510a(this.c).m5517a();
    }
}
