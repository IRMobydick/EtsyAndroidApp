package com.etsy.android.ui.util;

import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.ShippingDestination;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.util.Closure;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ai;
import com.etsy.android.ui.cart.ShippingOptionArrayAdapter;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.etsy.android.ui.util.z */
public class RealTimeShippingCalculatorController {
    private static final String f3817c;
    protected BaseActivity f3818a;
    protected final List<aa> f3819b;
    private Cart f3820d;
    private View f3821e;
    private View f3822f;
    private Button f3823g;
    private EditText f3824h;
    private TextView f3825i;
    private View f3826j;
    private View f3827k;
    private Spinner f3828l;
    private View f3829m;
    private TextView f3830n;
    private TextView f3831o;
    private TextView f3832p;
    private ShippingDestination f3833q;
    private Country f3834r;
    private String f3835s;
    private String f3836t;
    private ShippingOption f3837u;
    private List<ShippingOption> f3838v;

    /* renamed from: com.etsy.android.ui.util.z.1 */
    class RealTimeShippingCalculatorController extends TrackingOnClickListener {
        final /* synthetic */ RealTimeShippingCalculatorController f3808a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController, ITrackedObject... iTrackedObjectArr) {
            this.f3808a = realTimeShippingCalculatorController;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f3808a.f3820d.getViewState().setEditingShippingCosts(true);
            this.f3808a.m5249c();
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.2 */
    class RealTimeShippingCalculatorController extends Closure {
        final /* synthetic */ RealTimeShippingCalculatorController f3809a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3809a = realTimeShippingCalculatorController;
        }

        public void m5236a() {
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.3 */
    class RealTimeShippingCalculatorController extends Closure {
        final /* synthetic */ RealTimeShippingCalculatorController f3810a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3810a = realTimeShippingCalculatorController;
        }

        public void m5237a() {
            for (aa a : this.f3810a.f3819b) {
                a.m3744a(this.f3810a.f3834r, this.f3810a.f3835s, this.f3810a.f3837u);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.4 */
    class RealTimeShippingCalculatorController extends TrackingOnClickListener {
        final /* synthetic */ RealTimeShippingCalculatorController f3811a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController, ITrackedObject... iTrackedObjectArr) {
            this.f3811a = realTimeShippingCalculatorController;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f3811a.f3820d.getViewState().setViewingShippingCosts(false);
            this.f3811a.f3820d.getViewState().setEditingShippingCosts(true);
            this.f3811a.m5249c();
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.5 */
    class RealTimeShippingCalculatorController extends TrackingOnClickListener {
        final /* synthetic */ RealTimeShippingCalculatorController f3812a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController, ITrackedObject... iTrackedObjectArr) {
            this.f3812a = realTimeShippingCalculatorController;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f3812a.m5262k();
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.6 */
    class RealTimeShippingCalculatorController implements TextWatcher {
        final /* synthetic */ RealTimeShippingCalculatorController f3813a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3813a = realTimeShippingCalculatorController;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.f3813a.f3835s = editable.toString();
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.7 */
    class RealTimeShippingCalculatorController implements OnEditorActionListener {
        final /* synthetic */ RealTimeShippingCalculatorController f3814a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3814a = realTimeShippingCalculatorController;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (keyEvent == null && i != 6) {
                return false;
            }
            this.f3814a.m5244a(textView.getText().toString());
            ai.m3225a(this.f3814a.f3818a, textView);
            return true;
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.8 */
    class RealTimeShippingCalculatorController implements OnItemSelectedListener {
        final /* synthetic */ RealTimeShippingCalculatorController f3815a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3815a = realTimeShippingCalculatorController;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            ShippingOption shippingOption = (ShippingOption) this.f3815a.f3828l.getSelectedItem();
            if (this.f3815a.f3837u != null && !this.f3815a.f3837u.equals(shippingOption)) {
                this.f3815a.m5241a(shippingOption);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.ui.util.z.9 */
    class RealTimeShippingCalculatorController implements CountryUtil {
        final /* synthetic */ RealTimeShippingCalculatorController f3816a;

        RealTimeShippingCalculatorController(RealTimeShippingCalculatorController realTimeShippingCalculatorController) {
            this.f3816a = realTimeShippingCalculatorController;
        }

        public void m5238a(Country country) {
            this.f3816a.m5246b(country);
        }
    }

    static {
        f3817c = RealTimeShippingCalculatorController.class.toString();
    }

    public RealTimeShippingCalculatorController(BaseActivity baseActivity, Cart cart, View view, View view2, aa aaVar) {
        this.f3819b = new ArrayList();
        m5268a(baseActivity);
        m5265a(cart);
        m5264a(view, view2);
        m5267a(aaVar);
    }

    public void m5268a(BaseActivity baseActivity) {
        this.f3818a = baseActivity;
    }

    public void m5265a(Cart cart) {
        this.f3820d = cart;
    }

    public void m5264a(View view, View view2) {
        this.f3821e = view;
        this.f3822f = view2;
        View view3 = (View) this.f3821e.getParent();
        this.f3823g = (Button) this.f3821e.findViewById(2131755380);
        this.f3824h = (EditText) this.f3821e.findViewById(2131755381);
        this.f3825i = (TextView) this.f3821e.findViewById(2131755378);
        this.f3826j = this.f3821e.findViewById(2131755379);
        this.f3827k = this.f3821e.findViewById(2131755382);
        this.f3828l = (Spinner) this.f3821e.findViewById(2131755383);
        this.f3830n = (TextView) this.f3822f.findViewById(2131755386);
        this.f3831o = (TextView) this.f3822f.findViewById(2131755387);
        this.f3832p = (TextView) this.f3822f.findViewById(2131755388);
        this.f3829m = view3.findViewById(2131755389);
        this.f3833q = this.f3820d.getCartShippingDetails().getShippingDestination();
        this.f3838v = this.f3820d.getCartShippingDetails().getShippingOptionList();
        this.f3837u = this.f3820d.getShippingOption();
        this.f3834r = this.f3833q.getCountry();
        this.f3835s = this.f3833q.getPostalCode();
        this.f3836t = this.f3833q.getPostalCode();
        m5249c();
        m5258g();
        m5259h();
        m5260i();
        m5261j();
    }

    private void m5249c() {
        if (this.f3820d.getViewState().isViewingShippingCosts()) {
            this.f3821e.setVisibility(8);
            this.f3822f.setVisibility(0);
            this.f3829m.setVisibility(8);
            this.f3830n.setText(this.f3837u.getName() + " (" + this.f3837u.getCost().format() + ")");
            if (TextUtils.isEmpty(this.f3835s) || !m5269a(this.f3834r)) {
                this.f3831o.setText(this.f3834r.getName());
            } else {
                this.f3831o.setText(this.f3834r.getName() + ", " + this.f3835s);
            }
            m5252d();
            this.f3832p.setEnabled(true);
            return;
        }
        this.f3821e.setVisibility(0);
        this.f3822f.setVisibility(8);
        this.f3825i.setEnabled(false);
        this.f3826j.setVisibility(8);
        this.f3827k.setVisibility(8);
        this.f3829m.setVisibility(8);
        if (this.f3820d.getViewState().isEditingShippingCosts()) {
            if (this.f3834r != null) {
                this.f3823g.setText(this.f3834r.getName());
            } else {
                this.f3823g.setText(R.shipping_to_default);
            }
            if (this.f3834r == null || !m5269a(this.f3834r)) {
                this.f3824h.setVisibility(8);
            } else {
                this.f3824h.setVisibility(0);
            }
            this.f3824h.setText(this.f3835s);
            this.f3826j.setVisibility(0);
            this.f3825i.setVisibility(8);
            if (this.f3838v != null && this.f3838v.size() > 1) {
                this.f3828l.setAdapter(new ShippingOptionArrayAdapter(this.f3828l.getContext(), R.spinner_item_blue, this.f3838v));
                if (this.f3837u != null) {
                    this.f3828l.setSelection(this.f3838v.indexOf(this.f3837u));
                }
                m5252d();
                this.f3827k.setVisibility(0);
                return;
            }
            return;
        }
        this.f3825i.setEnabled(true);
    }

    private void m5252d() {
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bj) && m5254e()) {
            this.f3829m.setVisibility(0);
            View findViewById = this.f3829m.findViewById(2131755414);
            View findViewById2 = this.f3829m.findViewById(2131755415);
            if (m5256f()) {
                ((TextView) this.f3829m.findViewById(2131755416)).setText(R.local_delivery_options);
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
                return;
            }
            findViewById.setVisibility(0);
            findViewById2.setVisibility(8);
            TextView textView = (TextView) this.f3829m.findViewById(2131755416);
            if (this.f3820d.isEarliestLocalDeliveryToday()) {
                textView.setText(Html.fromHtml(textView.getContext().getString(R.local_delivery_today)));
            } else {
                textView.setText(Html.fromHtml(textView.getContext().getString(R.local_delivery_tomorrow)));
            }
        }
    }

    private boolean m5254e() {
        return this.f3820d.offersLocalDelivery();
    }

    private boolean m5256f() {
        return this.f3837u != null && this.f3837u.isLocalDelivery();
    }

    private void m5258g() {
        this.f3825i.setOnClickListener(new RealTimeShippingCalculatorController(this, this.f3820d));
        this.f3832p.setOnClickListener(new RealTimeShippingCalculatorController(this, this.f3820d));
    }

    private void m5259h() {
        this.f3823g.setOnClickListener(new RealTimeShippingCalculatorController(this, this.f3820d));
    }

    private void m5260i() {
        this.f3824h.addTextChangedListener(new RealTimeShippingCalculatorController(this));
        this.f3824h.setOnEditorActionListener(new RealTimeShippingCalculatorController(this));
    }

    private void m5261j() {
        this.f3828l.setOnItemSelectedListener(new RealTimeShippingCalculatorController(this));
    }

    private void m5262k() {
        ArrayList arrayList;
        Collection shippingCountryList = this.f3820d.getCartShippingDetails().getShippingCountryList();
        if (shippingCountryList == null || shippingCountryList.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(shippingCountryList.size());
            arrayList.addAll(shippingCountryList);
        }
        Nav.m4682a(this.f3818a).m4686d().m4411a(new RealTimeShippingCalculatorController(this), arrayList, null, "cart_view");
    }

    private void m5246b(Country country) {
        if (!country.equals(this.f3834r)) {
            this.f3834r = country;
            this.f3823g.setText(country.getName());
            SharedPreferencesUtility.m3143f(this.f3818a, this.f3834r.getIsoCountryCode());
            m5263a();
        }
    }

    private void m5244a(String str) {
        if (!str.equals(this.f3836t)) {
            this.f3835s = str;
            this.f3836t = str;
            this.f3824h.setText(str);
            SharedPreferencesUtility.m3145g(this.f3818a, this.f3835s);
            m5263a();
        }
    }

    private void m5241a(ShippingOption shippingOption) {
        if (!shippingOption.equals(this.f3837u)) {
            this.f3837u = shippingOption;
            m5270b();
        }
    }

    public void m5267a(aa aaVar) {
        this.f3819b.add(aaVar);
    }

    protected void m5263a() {
        this.f3833q = new ShippingDestination(this.f3834r, this.f3835s, this.f3834r.getIsoCountryCode());
        m5249c();
        m5266a(this.f3834r, this.f3835s, new Closure() {
            final /* synthetic */ RealTimeShippingCalculatorController f3806a;

            {
                this.f3806a = r1;
            }

            public void m5234a() {
                Toast.makeText(this.f3806a.f3818a, R.shipping_error_zip, 0).show();
            }
        }, new Closure() {
            final /* synthetic */ RealTimeShippingCalculatorController f3807a;

            {
                this.f3807a = r1;
            }

            public void m5235a() {
                for (aa a : this.f3807a.f3819b) {
                    a.m3744a(this.f3807a.f3834r, this.f3807a.f3835s, null);
                }
            }
        });
    }

    protected void m5270b() {
        m5249c();
        m5266a(this.f3834r, this.f3835s, new RealTimeShippingCalculatorController(this), new RealTimeShippingCalculatorController(this));
    }

    public void m5266a(Country country, String str, Closure closure, Closure closure2) {
        if (m5245a(country, str)) {
            closure2.m3420a();
            return;
        }
        Object valueOf;
        AdHocEventCompatBuilder a = new AdHocEventCompatBuilder("invalid_cart_destination").m5515a("cart_view").m5509a((long) this.f3820d.getCartId()).m5516a(new RealTimeShippingCalculatorController$12(this, country, str)).m5514a(this.f3820d);
        AnalyticsLogAttribute analyticsLogAttribute = AnalyticsLogAttribute.COUNTRY_ID;
        if (country != null) {
            valueOf = Integer.valueOf(country.getCountryId());
        } else {
            valueOf = null;
        }
        a.m5512a(analyticsLogAttribute, valueOf).m5512a(AnalyticsLogAttribute.POSTAL_CODE, (Object) str).m5510a(this.f3821e).m5517a();
        closure.m3420a();
    }

    private boolean m5245a(Country country, String str) {
        if (country == null) {
            return false;
        }
        if (!m5269a(country) || (!TextUtils.isEmpty(str) && str.matches("^(\\d{5})(-\\d{4})?$"))) {
            return true;
        }
        return false;
    }

    protected boolean m5269a(Country country) {
        return country != null && country.isUs();
    }
}
