package com.etsy.android.ui.core.listingpanel;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
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
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.ShippingAddressPreference;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.apiv3.ListingShippingRequest;
import com.etsy.android.lib.requests.apiv3.ShippingCostRequest;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.core.listingpanel.h.AnonymousClass13;
import com.etsy.android.ui.core.listingpanel.h.AnonymousClass14;
import com.etsy.android.ui.core.listingpanel.h.AnonymousClass15;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.shop.policies.StructuredShopPaymentsView;
import com.etsy.android.uikit.view.shop.policies.StructuredShopRefundsView;
import com.etsy.android.uikit.view.shop.policies.StructuredShopShippingView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.core.listingpanel.h */
public class ListingPanelShippingAndPolicies extends ListingPanelBase implements CountryUtil {
    private TextView f2903A;
    private ViewStub f2904B;
    private View f2905C;
    private final boolean f2906D;
    private ListingShippingDetails f2907E;
    private boolean f2908F;
    private List<Country> f2909G;
    private Country f2910H;
    private String f2911I;
    private ShippingOption f2912J;
    private TextWatcher f2913K;
    private View f2914k;
    private TextView f2915l;
    private TextView f2916m;
    private LinearLayout f2917n;
    private TextView f2918o;
    private TextView f2919p;
    private TextView f2920q;
    private LinearLayout f2921r;
    private Button f2922s;
    private EditText f2923t;
    private TextView f2924u;
    private StructuredShopShippingView f2925v;
    private StructuredShopPaymentsView f2926w;
    private StructuredShopRefundsView f2927x;
    private View f2928y;
    private TextView f2929z;

    /* compiled from: ListingPanelShippingAndPolicies */
    /* renamed from: com.etsy.android.ui.core.listingpanel.h.13 */
    class AnonymousClass13 extends TrackingOnClickListener {
        final /* synthetic */ String f2887a;
        final /* synthetic */ ListingPanelShippingAndPolicies f2888b;

        AnonymousClass13(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies, String str) {
            this.f2888b = listingPanelShippingAndPolicies;
            this.f2887a = str;
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f2888b.a).m4534h(this.f2887a);
        }
    }

    /* compiled from: ListingPanelShippingAndPolicies */
    /* renamed from: com.etsy.android.ui.core.listingpanel.h.14 */
    class AnonymousClass14 extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShippingAndPolicies f2889a;

        AnonymousClass14(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies, ITrackedObject... iTrackedObjectArr) {
            this.f2889a = listingPanelShippingAndPolicies;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2889a.m4158C();
        }
    }

    /* compiled from: ListingPanelShippingAndPolicies */
    /* renamed from: com.etsy.android.ui.core.listingpanel.h.15 */
    class AnonymousClass15 extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShippingAndPolicies f2890a;

        AnonymousClass15(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies, ITrackedObject... iTrackedObjectArr) {
            this.f2890a = listingPanelShippingAndPolicies;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2890a.m4159D();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.1 */
    class ListingPanelShippingAndPolicies extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelShippingAndPolicies f2894a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2894a = listingPanelShippingAndPolicies;
        }

        public void onViewClick(View view) {
            this.f2894a.m4218o();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.2 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse<ShippingOption> {
        final /* synthetic */ ListingPanelShippingAndPolicies f2895a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2895a = listingPanelShippingAndPolicies;
        }

        public void m4148a(List<ShippingOption> list, int i, EtsyResult<ShippingOption> etsyResult) {
            this.f2895a.m4183a((ShippingOption) list.get(0));
            this.f2895a.m4156A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.3 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShippingAndPolicies f2896a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2896a = listingPanelShippingAndPolicies;
        }

        public void m4149a(EtsyResult etsyResult) {
            this.f2896a.m4169N();
            this.f2896a.m4156A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.4 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShippingAndPolicies f2897a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2897a = listingPanelShippingAndPolicies;
        }

        public void m4150a(int i, String str, EtsyResult etsyResult) {
            new AdHocEventCompatBuilder("shipping_cost_retreival_error").m5515a("view_listing").m5516a(new ListingPanelShippingAndPolicies$12$1(this, str)).m5511a(AnalyticsLogAttribute.LISTING_ID, this.f2897a.b.getListingId()).m5510a(this.f2897a.c).m5517a();
            this.f2897a.m4168M();
            this.f2897a.m4156A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.5 */
    class ListingPanelShippingAndPolicies implements EtsyJobBuilder {
        final /* synthetic */ ListingPanelShippingAndPolicies f2898a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2898a = listingPanelShippingAndPolicies;
        }

        public void m4151a() {
            SharedPreferencesUtility.m3143f(this.f2898a.a, this.f2898a.f2910H.getIsoCountryCode());
            SharedPreferencesUtility.m3145g(this.f2898a.a, this.f2898a.f2911I);
            this.f2898a.m4179X();
            this.f2898a.m4210z();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.6 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse<ListingShippingDetails> {
        final /* synthetic */ ListingPanelShippingAndPolicies f2899a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2899a = listingPanelShippingAndPolicies;
        }

        public void m4152a(List<ListingShippingDetails> list, int i, EtsyResult<ListingShippingDetails> etsyResult) {
            this.f2899a.m4182a((ListingShippingDetails) list.get(0));
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.7 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShippingAndPolicies f2900a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2900a = listingPanelShippingAndPolicies;
        }

        public void m4153a(EtsyResult etsyResult) {
            this.f2900a.m4156A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.8 */
    class ListingPanelShippingAndPolicies implements EtsyJobResponse {
        final /* synthetic */ ListingPanelShippingAndPolicies f2901a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2901a = listingPanelShippingAndPolicies;
        }

        public void m4154a(int i, String str, EtsyResult etsyResult) {
            this.f2901a.m4156A();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.h.9 */
    class ListingPanelShippingAndPolicies implements EtsyJobBuilder {
        final /* synthetic */ ListingPanelShippingAndPolicies f2902a;

        ListingPanelShippingAndPolicies(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
            this.f2902a = listingPanelShippingAndPolicies;
        }

        public void m4155a() {
            this.f2902a.m4210z();
        }
    }

    public ListingPanelShippingAndPolicies(Listing listing, BaseActivity baseActivity, @NonNull ad adVar, boolean z) {
        super(listing, baseActivity, adVar);
        this.f2906D = z;
        m4025a(2131756154, 2131756151, 2131756152, 2131756153);
    }

    protected void m4214c() {
        m4200p();
        m4202r();
        m4201q();
        m4203s();
        m4205u();
        m4206v();
        m4207w();
        m4208x();
        if (this.b.isSoldOut()) {
            this.c.findViewById(2131756193).setVisibility(8);
            m4156A();
        } else if (this.f2907E == null) {
            m4175T();
            CountryUtil.m3026a((CountryUtil) this, true);
        } else {
            m4182a(this.f2907E);
            m4156A();
        }
    }

    private void m4200p() {
        this.f2914k = this.d.findViewById(2131756230);
        this.f2915l = (TextView) this.c.findViewById(2131756220);
        this.f2916m = (TextView) this.c.findViewById(2131756221);
        this.f2917n = (LinearLayout) this.c.findViewById(2131756222);
        this.f2918o = (TextView) this.c.findViewById(2131756223);
        this.f2919p = (TextView) this.c.findViewById(2131756224);
        this.f2920q = (TextView) this.c.findViewById(2131756225);
        this.f2921r = (LinearLayout) this.c.findViewById(2131756226);
        this.f2922s = (Button) this.c.findViewById(2131756227);
        this.f2923t = (EditText) this.c.findViewById(2131756228);
        this.f2924u = (TextView) this.c.findViewById(2131756229);
        this.f2925v = (StructuredShopShippingView) this.c.findViewById(2131756197);
        this.f2926w = (StructuredShopPaymentsView) this.c.findViewById(2131756199);
        this.f2927x = (StructuredShopRefundsView) this.c.findViewById(2131756201);
        this.f2928y = this.c.findViewById(2131756203);
        this.f2929z = (TextView) this.c.findViewById(2131756205);
        this.f2903A = (TextView) this.c.findViewById(2131756206);
        this.f2904B = (ViewStub) this.c.findViewById(2131756202);
        if (this.b.isDigitalDownload()) {
            ((TextView) this.f).setText(R.shop_policies);
            return;
        }
        this.c.findViewById(2131756193).setVisibility(0);
        m4162G();
        m4165J();
        m4209y();
    }

    private void m4201q() {
        int i;
        int i2 = 8;
        TextView textView = (TextView) this.c.findViewById(2131756194);
        TextView textView2 = (TextView) this.c.findViewById(R.txt_shipping_time);
        TextView textView3 = (TextView) this.c.findViewById(2131756196);
        textView.setVisibility(this.b.isDigitalDownload() ? 8 : 0);
        if (this.b.isDigitalDownload()) {
            i = 8;
        } else {
            i = 0;
        }
        textView2.setVisibility(i);
        if (this.b.isDigitalDownload()) {
            i2 = 0;
        }
        textView3.setVisibility(i2);
        if (this.b.isDigitalDownload()) {
            String b = m4045n().m883b(EtsyConfigKeys.f1255a);
            textView3.setText(Html.fromHtml(this.c.getResources().getString(R.structured_shipping_digital_message, new Object[]{b})));
            textView3.setMovementMethod(LinkMovementMethod.getInstance());
        } else if (this.b.getProcessingDaysMin() > 0 || this.b.getProcessingDaysMax() > 0) {
            CharSequence quantityString;
            int processingDaysMin;
            if (this.b.getProcessingDaysMin() == this.b.getProcessingDaysMax()) {
                if (this.b.getProcessingDaysMin() < 5 || this.b.getProcessingDaysMin() % 5 != 0) {
                    quantityString = this.c.getResources().getQuantityString(R.shipping_processing_days, this.b.getProcessingDaysMin(), new Object[]{Integer.valueOf(this.b.getProcessingDaysMin())});
                } else {
                    quantityString = this.c.getResources().getQuantityString(R.weeks_count, processingDaysMin, new Object[]{Integer.valueOf(this.b.getProcessingDaysMin() / 5)});
                }
            } else if (this.b.getProcessingDaysMax() < 5 || this.b.getProcessingDaysMax() % 5 != 0 || this.b.getProcessingDaysMin() < 5 || this.b.getProcessingDaysMin() % 5 != 0) {
                quantityString = this.c.getResources().getString(R.shipping_processing_days_range, new Object[]{Integer.valueOf(this.b.getProcessingDaysMin()), Integer.valueOf(this.b.getProcessingDaysMax())});
            } else {
                processingDaysMin = this.b.getProcessingDaysMin() / 5;
                int processingDaysMax = this.b.getProcessingDaysMax() / 5;
                quantityString = this.c.getResources().getString(R.weeks_range, new Object[]{Integer.valueOf(processingDaysMin), Integer.valueOf(processingDaysMax)});
            }
            textView2.setText(quantityString);
        } else {
            textView.setText(R.structured_shipping_processing_time);
            textView2.setText(Html.fromHtml(this.c.getResources().getString(R.structured_shipping_listing_no_processing_time)));
            EtsyLinkify.m5488a(textView2, false, new ListingPanelShippingAndPolicies(this));
        }
    }

    private void m4202r() {
        TextView textView = (TextView) this.c.findViewById(2131756192);
        if (this.b.isDigitalDownload()) {
            textView.setText(R.structured_shipping_digital_title);
        } else if (this.b.getShippingInfo().size() > 0) {
            textView.setText(String.format(this.c.getResources().getString(R.shipping_ships_from), new Object[]{((ShippingInfo) this.b.getShippingInfo().get(0)).getOriginCountryName()}));
        } else {
            textView.setText(R.structured_shipping);
        }
    }

    private void m4203s() {
        m4204t();
        this.f2925v.setExpanded(false);
    }

    private void m4204t() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.isDigitalDownload() || this.b.getShop().getStructuredPolicies() == null) {
            this.f2925v.setVisibility(8);
            return;
        }
        StructuredShopShipping shipping = this.b.getShop().getStructuredPolicies().getShipping();
        if (shipping.hasSetEstimates() || shipping.shipsInternational()) {
            this.f2925v.setVisibility(0);
            this.f2925v.setStructuredShopShipping(shipping, false);
            return;
        }
        this.f2925v.setVisibility(8);
    }

    private void m4205u() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.getShop().getStructuredPolicies() == null) {
            this.f2926w.setVisibility(8);
            return;
        }
        this.f2926w.setStructuredShopPayments(this.b.getShop().getStructuredPolicies().getPayments(), new ListingPanelShippingAndPolicies(this));
        this.f2926w.setExpanded(false);
    }

    private void m4206v() {
        if (this.b.getShop() == null || !this.b.getShop().isUsingStructuredPolicies() || this.b.getShop().getStructuredPolicies() == null) {
            this.f2927x.setVisibility(8);
            return;
        }
        this.f2927x.setStructuredShopRefunds(this.b.getShop().getStructuredPolicies().getRefunds(), new ListingPanelShippingAndPolicies(this));
        this.f2927x.setExpanded(false);
    }

    private void m4207w() {
        if (this.b.getShop() == null) {
            this.f2928y.setVisibility(8);
            return;
        }
        SellerDetails sellerDetails = this.b.getShop().getSellerDetails();
        if (sellerDetails == null || !sellerDetails.hasDetails()) {
            this.f2928y.setVisibility(8);
            return;
        }
        this.f2928y.setVisibility(0);
        this.f2929z.setText(sellerDetails.getFormattedDetails());
        this.f2903A.setText(this.c.getResources().getString(R.seller_details_contact, new Object[]{this.b.getShopName()}));
        this.f2903A.setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ ListingPanelShippingAndPolicies f2886a;

            {
                this.f2886a = r1;
            }

            public void onViewClick(View view) {
                this.f2886a.m4218o();
            }
        });
    }

    private void m4208x() {
        if (this.f2906D) {
            String termsAndConditions;
            TextView textView;
            Shop shop = this.b.getShop();
            if (shop != null) {
                StructuredShopPolicies structuredPolicies = shop.getStructuredPolicies();
                if (shop.isUsingStructuredPolicies() && structuredPolicies != null) {
                    termsAndConditions = structuredPolicies.getTermsAndConditions();
                    if (bh.m3343b(termsAndConditions)) {
                        this.f2905C = this.f2904B.inflate();
                        textView = (TextView) this.f2905C.findViewById(2131755342);
                        textView.setText(this.c.getResources().getString(R.terms_and_conditions_link, new Object[]{this.b.getShopName()}));
                        textView.setOnClickListener(new AnonymousClass13(this, termsAndConditions));
                    } else if (this.f2905C != null) {
                        this.f2905C.setVisibility(8);
                    }
                }
            }
            termsAndConditions = null;
            if (bh.m3343b(termsAndConditions)) {
                this.f2905C = this.f2904B.inflate();
                textView = (TextView) this.f2905C.findViewById(2131755342);
                textView.setText(this.c.getResources().getString(R.terms_and_conditions_link, new Object[]{this.b.getShopName()}));
                textView.setOnClickListener(new AnonymousClass13(this, termsAndConditions));
            } else if (this.f2905C != null) {
                this.f2905C.setVisibility(8);
            }
        }
    }

    private void m4209y() {
        OnClickListener anonymousClass14 = new AnonymousClass14(this, this.b);
        this.f2919p.setOnClickListener(new AnonymousClass15(this, this.b));
        this.f2916m.setOnClickListener(anonymousClass14);
        this.f2922s.setOnClickListener(anonymousClass14);
        m4176U();
        this.f2913K = new TextWatcher() {
            final /* synthetic */ ListingPanelShippingAndPolicies f2891a;

            {
                this.f2891a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.f2891a.f2911I = editable.toString();
            }
        };
        this.f2923t.addTextChangedListener(this.f2913K);
        this.f2923t.setOnEditorActionListener(new OnEditorActionListener() {
            final /* synthetic */ ListingPanelShippingAndPolicies f2892a;

            {
                this.f2892a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null && i != 6) {
                    return false;
                }
                this.f2892a.m4190b(textView.getText().toString());
                ai.m3225a(this.f2892a.a, textView);
                return true;
            }
        });
    }

    @CallSuper
    public void m4213b() {
        super.m4033b();
        m4176U();
    }

    private void m4210z() {
        this.f2914k.setBackgroundColor(this.f2914k.getResources().getColor(2131624265));
        this.f2914k.setVisibility(0);
    }

    private void m4156A() {
        this.f2914k.setVisibility(8);
    }

    protected void m4215i() {
        m4177V();
        m4157B();
    }

    protected void m4216j() {
        m4157B();
    }

    protected void m4217k() {
        ai.m3225a(this.a, this.f2923t);
    }

    private void m4157B() {
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        if (m4191b(false) && this.f2912J != null) {
            m4164I();
            m4160E();
            m4167L();
        } else if (m4191b(false) && this.f2907E != null && this.f2912J == null) {
            m4169N();
        } else if (this.f2910H == null && TextUtils.isEmpty(this.f2911I)) {
            m4164I();
            m4161F();
            m4166K();
        } else {
            m4163H();
            m4161F();
            m4167L();
        }
        m4031a(false);
    }

    private void m4158C() {
        ArrayList arrayList;
        if (this.f2909G == null || this.f2909G.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.f2909G.size());
            arrayList.addAll(this.f2909G);
        }
        Nav.m4682a(this.a).m4686d().m4411a(new CountryUtil() {
            final /* synthetic */ ListingPanelShippingAndPolicies f2893a;

            {
                this.f2893a = r1;
            }

            public void m4147a(Country country) {
                this.f2893a.m4181a(country);
            }
        }, arrayList, null, "view_listing");
    }

    private void m4159D() {
        if (m4171P()) {
            m4161F();
            m4163H();
            return;
        }
        m4158C();
    }

    private void m4160E() {
        m4162G();
        this.f2917n.setVisibility(0);
    }

    private void m4161F() {
        this.f2917n.setVisibility(8);
    }

    private void m4162G() {
        if (this.f2910H != null) {
            if (TextUtils.isEmpty(this.f2911I) || !m4171P()) {
                this.f2918o.setText(this.f2910H.getName());
            } else {
                this.f2918o.setText(this.f2910H.getName() + ", " + this.f2911I);
            }
        }
        if (this.f2912J == null || StringUtils.EMPTY.equals(this.f2912J.getOptionId())) {
            this.f2920q.setText(StringUtils.EMPTY);
            return;
        }
        this.f2920q.setText(this.f2912J.getCost().format());
        m4178W();
    }

    private void m4163H() {
        m4165J();
        this.f2921r.setVisibility(0);
        this.f2922s.setVisibility(0);
    }

    private void m4164I() {
        this.f2921r.setVisibility(8);
        this.f2922s.setVisibility(8);
    }

    private void m4165J() {
        if (this.f2910H != null) {
            this.f2922s.setText(this.f2910H.getName());
        } else {
            this.f2922s.setText(R.shipping_to_default);
        }
        if (this.f2910H == null || !m4171P()) {
            this.f2923t.setVisibility(8);
        } else {
            this.f2923t.setVisibility(0);
        }
        this.f2923t.setText(this.f2911I);
    }

    private void m4166K() {
        this.f2916m.setVisibility(0);
        this.f2915l.setText(R.shipping_panel_cost_label);
    }

    private void m4167L() {
        this.f2916m.setVisibility(8);
        this.f2915l.setText(R.shipping_panel_to_label);
    }

    private void m4168M() {
        m4161F();
        m4163H();
        Toast.makeText(this.a, R.shipping_error_cost, 0).show();
    }

    private void m4169N() {
        m4161F();
        m4164I();
        m4166K();
        this.f2916m.setText(R.shipping_cost_action_update);
        this.f2924u.setText(this.f2924u.getResources().getString(R.shipping_error_destination, new Object[]{this.f2910H.getName()}));
        this.f2924u.setVisibility(0);
        this.f2925v.setVisibility(8);
    }

    private void m4170O() {
        this.f2924u.setText(StringUtils.EMPTY);
        this.f2924u.setVisibility(8);
        this.f2916m.setText(R.shipping_cost_action_calculate);
        this.f2922s.setError(null);
        m4204t();
    }

    private boolean m4191b(boolean z) {
        if (this.f2910H != null) {
            if (!m4171P() || (!TextUtils.isEmpty(this.f2911I) && this.f2911I.matches("^(\\d{5})(-\\d{4})?$"))) {
                return true;
            }
            if (!z) {
                return false;
            }
            new AdHocEventCompatBuilder("invalid_listing_destination").m5515a("view_listing").m5516a(new ListingPanelShippingAndPolicies$9(this)).m5514a(this.b).m5517a();
            Toast.makeText(this.a, R.shipping_error_zip, 0).show();
            return false;
        } else if (!z) {
            return false;
        } else {
            this.f2922s.setError(this.a.getResources().getString(R.shipping_error_country));
            return false;
        }
    }

    private void m4182a(ListingShippingDetails listingShippingDetails) {
        this.f2907E = listingShippingDetails;
        if (this.f2908F && this.f2907E != null) {
            m4156A();
            ShippingAddressPreference shippingAddress = listingShippingDetails.getShippingAddress();
            if (shippingAddress != null) {
                this.f2910H = shippingAddress.getCountry();
                this.f2911I = shippingAddress.getPostalCode();
            }
            this.f2912J = listingShippingDetails.getShippingOption();
            this.f2909G = listingShippingDetails.getCountries();
            m4157B();
            this.f2925v.filterEstimates(this.f2910H);
        }
    }

    private void m4181a(Country country) {
        this.f2910H = country;
        this.f2912J = null;
        if (this.f2907E != null) {
            this.f2907E.getShippingAddress().setCountry(this.f2910H);
        }
        m4165J();
        m4173R();
        this.f2925v.filterEstimates(this.f2910H);
    }

    private void m4190b(String str) {
        this.f2911I = str;
        this.f2912J = null;
        if (this.f2907E != null) {
            this.f2907E.getShippingAddress().setPostalCode(this.f2911I);
        }
        m4165J();
        m4172Q();
    }

    private void m4183a(ShippingOption shippingOption) {
        this.f2912J = shippingOption;
        m4157B();
    }

    private boolean m4171P() {
        return this.f2910H != null && Locale.US.getISO3Country().equals(this.f2910H.getWorldBankCode());
    }

    private void m4172Q() {
        m4170O();
        if (m4191b(true)) {
            m4174S();
        }
    }

    private void m4173R() {
        m4170O();
        if (m4191b(false)) {
            m4174S();
            return;
        }
        this.f2912J = null;
        m4157B();
    }

    private void m4174S() {
        String str;
        EtsyId listingId = this.b.getListingId();
        String isoCountryCode = this.f2910H.getIsoCountryCode();
        if (m4171P()) {
            str = this.f2911I;
        } else {
            str = null;
        }
        m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(ShippingCostRequest.getStandardShippingOptionForListing(listingId, isoCountryCode, str)).m1318a(new ListingPanelShippingAndPolicies(this)).m1320a(new ListingPanelShippingAndPolicies(this)).m1319a(new ListingPanelShippingAndPolicies(this)).m1321a(new ListingPanelShippingAndPolicies(this)).m1324a());
    }

    private void m4175T() {
        m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(ListingShippingRequest.getListingShippingDetails(this.a, this.b.getListingId())).m1318a(new ListingPanelShippingAndPolicies(this)).m1320a(new ListingPanelShippingAndPolicies(this)).m1319a(new ListingPanelShippingAndPolicies(this)).m1321a(new ListingPanelShippingAndPolicies(this)).m1324a());
    }

    public void m4212a(List<Region> list) {
        this.f2908F = true;
        if (this.a != null) {
            this.a.runOnUiThread(new Runnable() {
                final /* synthetic */ ListingPanelShippingAndPolicies f2884a;

                {
                    this.f2884a = r1;
                }

                public void run() {
                    this.f2884a.m4182a(this.f2884a.f2907E);
                }
            });
        }
    }

    public void m4211a(String str) {
        this.f2908F = true;
        if (this.a != null) {
            this.a.runOnUiThread(new Runnable() {
                final /* synthetic */ ListingPanelShippingAndPolicies f2885a;

                {
                    this.f2885a = r1;
                }

                public void run() {
                    this.f2885a.m4182a(this.f2885a.f2907E);
                }
            });
        }
    }

    public void m4218o() {
        if (this.b.getShop() != null) {
            String loginName = this.b.getShop().getLoginName();
            Bundle bundle = new Bundle();
            if (aj.m1101a().m1118d()) {
                bundle.putString("username", loginName);
                ((EtsyActivityNavigator) Nav.m4682a(this.a).m4683a().m3012a(this.c)).m4516d(bundle);
                return;
            }
            bundle.putString("username", loginName);
            ((EtsyActivityNavigator) Nav.m4682a(this.a).m4683a().m3012a(this.c)).m4452a(EtsyAction.CONTACT_USER, bundle);
        }
    }

    private void m4176U() {
        if (this.f2923t != null && this.f2913K != null) {
            this.f2923t.removeTextChangedListener(this.f2913K);
            this.f2913K = null;
        }
    }

    private void m4177V() {
        EtsyLogger.m1966a().m1997b("shipping_tab_click", "view_listing", new ListingPanelShippingAndPolicies$20(this));
    }

    private void m4178W() {
        new AdHocEventCompatBuilder("shipping_costs_view").m5515a("view_listing").m5516a(new ListingPanelShippingAndPolicies$21(this)).m5510a(this.c).m5517a();
    }

    private void m4179X() {
        new AdHocEventCompatBuilder("shipping_costs_request").m5515a("view_listing").m5516a(new ListingPanelShippingAndPolicies$22(this)).m5510a(this.c).m5517a();
    }
}
