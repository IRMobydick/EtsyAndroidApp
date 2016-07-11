package com.etsy.android.ui.cart;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconSelectorDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.bl;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.GiftCardUtil;
import com.etsy.android.ui.util.RealTimeShippingCalculatorController;
import com.etsy.android.ui.util.aa;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.ui.cart.q */
public class CartItemHolder {
    private static final String f2517R;
    private static Spanned f2518T;
    public Button f2519A;
    public ImageButton f2520B;
    public ArrayList<CartItemListingHolder> f2521C;
    public View f2522D;
    public View f2523E;
    public View f2524F;
    public View f2525G;
    public View f2526H;
    public View f2527I;
    public TextView f2528J;
    public TextView f2529K;
    public LinearLayout f2530L;
    public TextView f2531M;
    public View f2532N;
    public View f2533O;
    public View f2534P;
    public TextView f2535Q;
    private TextView f2536S;
    public View f2537a;
    public ImageView f2538b;
    public TextView f2539c;
    public View f2540d;
    public PopupMenu f2541e;
    public LinearLayout f2542f;
    public TextView f2543g;
    public LinearLayout f2544h;
    public TextView f2545i;
    public TextView f2546j;
    public TextView f2547k;
    public View f2548l;
    public LinearLayout f2549m;
    public TextView f2550n;
    public TextView f2551o;
    public Spinner f2552p;
    public TextView f2553q;
    public TextView f2554r;
    public LinearLayout f2555s;
    public CheckBox f2556t;
    public LinearLayout f2557u;
    public TextView f2558v;
    public LinearLayout f2559w;
    public LinearLayout f2560x;
    public TextView f2561y;
    public TextView f2562z;

    /* renamed from: com.etsy.android.ui.cart.q.1 */
    class CartItemHolder extends TrackingOnClickListener {
        final /* synthetic */ CartItemHolder f2513a;

        CartItemHolder(CartItemHolder cartItemHolder) {
            this.f2513a = cartItemHolder;
        }

        public void onViewClick(View view) {
            this.f2513a.f2556t.setChecked(!this.f2513a.f2556t.isChecked());
        }
    }

    /* renamed from: com.etsy.android.ui.cart.q.2 */
    class CartItemHolder extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f2514a;
        final /* synthetic */ String f2515b;
        final /* synthetic */ CartItemHolder f2516c;

        CartItemHolder(CartItemHolder cartItemHolder, FragmentActivity fragmentActivity, String str) {
            this.f2516c = cartItemHolder;
            this.f2514a = fragmentActivity;
            this.f2515b = str;
        }

        public void onViewClick(View view) {
            if (this.f2514a != null) {
                Nav.m4682a(this.f2514a).m4683a().m4528f(this.f2515b);
            }
        }
    }

    static {
        f2517R = EtsyDebug.m1891a(CartItemHolder.class);
    }

    public CartItemHolder(View view) {
        this.f2521C = new ArrayList();
        this.f2537a = view.findViewById(2131755371);
        this.f2544h = (LinearLayout) view.findViewById(2131755426);
        this.f2542f = (LinearLayout) view.findViewById(2131755424);
        this.f2543g = (TextView) view.findViewById(2131755422);
        this.f2524F = view.findViewById(2131755421);
        this.f2525G = view.findViewById(2131755423);
        this.f2526H = view.findViewById(2131755425);
        this.f2539c = (TextView) view.findViewById(2131755419);
        this.f2538b = (ImageView) view.findViewById(2131755418);
        this.f2540d = view.findViewById(2131755420);
        this.f2545i = (TextView) view.findViewById(2131755395);
        this.f2546j = (TextView) view.findViewById(2131755394);
        this.f2549m = (LinearLayout) view.findViewById(2131755403);
        this.f2550n = (TextView) this.f2549m.findViewById(2131755405);
        this.f2551o = (TextView) this.f2549m.findViewById(2131755406);
        this.f2532N = view.findViewById(2131755375);
        this.f2533O = view.findViewById(2131755376);
        this.f2534P = view.findViewById(2131755385);
        this.f2529K = (TextView) view.findViewById(2131755374);
        this.f2527I = view.findViewById(2131755401);
        this.f2528J = (TextView) this.f2527I.findViewById(2131755402);
        this.f2522D = view.findViewById(2131755390);
        this.f2554r = (TextView) this.f2522D.findViewById(2131755391);
        this.f2552p = (Spinner) this.f2522D.findViewById(2131755393);
        this.f2553q = (TextView) this.f2522D.findViewById(2131755392);
        this.f2555s = (LinearLayout) view.findViewById(2131755368);
        this.f2548l = view.findViewById(2131755372);
        this.f2547k = (TextView) view.findViewById(2131755373);
        this.f2556t = (CheckBox) this.f2555s.findViewById(2131755370);
        if (GiftCardUtil.m5198a()) {
            m3875c(view.getResources());
            m3861a(view, view.getResources(), this);
        }
        this.f2557u = (LinearLayout) view.findViewById(2131755397);
        this.f2535Q = (TextView) this.f2557u.findViewById(2131755398);
        this.f2558v = (TextView) this.f2557u.findViewById(2131755399);
        this.f2530L = (LinearLayout) view.findViewById(2131755016);
        this.f2531M = (TextView) view.findViewById(2131755400);
        this.f2560x = (LinearLayout) view.findViewById(2131755409);
        this.f2561y = (TextView) this.f2560x.findViewById(2131755411);
        this.f2562z = (TextView) view.findViewById(2131755412);
        this.f2559w = (LinearLayout) view.findViewById(2131755407);
        this.f2536S = (TextView) this.f2559w.findViewById(2131755408);
        this.f2523E = view.findViewById(2131755015);
        this.f2519A = (Button) view.findViewById(2131755413);
        this.f2520B = (ImageButton) view.findViewById(2131755427);
    }

    public void m3863a() {
        this.f2542f.setVisibility(8);
        this.f2543g.setVisibility(8);
        this.f2524F.setVisibility(8);
        this.f2525G.setVisibility(8);
    }

    public void m3867a(Resources resources) {
        this.f2544h.setVisibility(0);
        if (this.f2532N != null) {
            this.f2532N.setVisibility(8);
            this.f2533O.setVisibility(8);
            this.f2534P.setVisibility(8);
        }
        this.f2529K.setVisibility(8);
        this.f2556t.setVisibility(8);
        if (this.f2527I != null) {
            this.f2527I.setVisibility(0);
        }
        this.f2522D.setVisibility(0);
        this.f2548l.setVisibility(8);
        this.f2549m.setVisibility(8);
        this.f2555s.setVisibility(8);
        this.f2559w.setVisibility(8);
        this.f2557u.setVisibility(0);
        this.f2545i.setVisibility(8);
        this.f2546j.setVisibility(8);
        if (this.f2530L != null) {
            this.f2530L.setVisibility(8);
        }
        this.f2560x.setVisibility(0);
        if (resources.getBoolean(2131296261)) {
            this.f2523E.setVisibility(0);
        }
        this.f2519A.setVisibility(0);
        if (this.f2526H != null) {
            this.f2526H.setVisibility(0);
        }
    }

    public void m3873b(Resources resources) {
        this.f2544h.setVisibility(0);
        if (this.f2532N != null) {
            this.f2532N.setVisibility(0);
            this.f2533O.setVisibility(0);
            this.f2534P.setVisibility(8);
        }
        this.f2529K.setVisibility(0);
        this.f2556t.setVisibility(0);
        if (this.f2527I != null) {
            this.f2527I.setVisibility(0);
        }
        this.f2522D.setVisibility(0);
        this.f2549m.setVisibility(0);
        this.f2559w.setVisibility(0);
        this.f2557u.setVisibility(0);
        this.f2545i.setVisibility(0);
        if (resources.getBoolean(2131296260)) {
            this.f2546j.setVisibility(0);
        }
        if (this.f2530L != null) {
            this.f2530L.setVisibility(0);
        }
        this.f2560x.setVisibility(0);
        if (resources.getBoolean(2131296261)) {
            this.f2523E.setVisibility(0);
        }
        this.f2519A.setVisibility(0);
        if (this.f2526H != null) {
            this.f2526H.setVisibility(0);
        }
    }

    public void m3872b() {
        this.f2542f.setVisibility(0);
        this.f2543g.setVisibility(0);
        this.f2525G.setVisibility(0);
        this.f2524F.setVisibility(0);
    }

    public void m3874c() {
        this.f2544h.setVisibility(8);
        if (this.f2532N != null) {
            this.f2532N.setVisibility(8);
        }
        this.f2529K.setVisibility(8);
        this.f2556t.setVisibility(8);
        this.f2527I.setVisibility(8);
        this.f2522D.setVisibility(8);
        this.f2549m.setVisibility(8);
        this.f2557u.setVisibility(8);
        this.f2559w.setVisibility(8);
        this.f2545i.setVisibility(8);
        this.f2546j.setVisibility(8);
        if (this.f2530L != null) {
            this.f2530L.setVisibility(8);
        }
        this.f2560x.setVisibility(8);
        this.f2523E.setVisibility(8);
        this.f2519A.setVisibility(8);
        this.f2520B.setVisibility(8);
        if (this.f2526H != null) {
            this.f2526H.setVisibility(8);
        }
    }

    public void m3876d() {
        this.f2519A.setVisibility(8);
        this.f2520B.setVisibility(0);
    }

    public void m3877e() {
        this.f2519A.setVisibility(0);
        this.f2520B.setVisibility(8);
    }

    public void m3878f() {
        bl.m3357a(this.f2519A);
        bl.m3357a(this.f2520B);
        bl.m3357a(this.f2552p);
        bl.m3357a(this.f2553q);
        this.f2532N.setVisibility(8);
        this.f2530L.setVisibility(8);
    }

    public void m3871a(boolean z) {
        bl.m3366b(this.f2519A);
        bl.m3366b(this.f2520B);
        bl.m3366b(this.f2552p);
        bl.m3366b(this.f2553q);
        if (z) {
            this.f2532N.setVisibility(0);
            this.f2530L.setVisibility(0);
        }
    }

    public void m3879g() {
        this.f2559w.setVisibility(8);
        this.f2555s.setVisibility(8);
    }

    public void m3880h() {
        if (GiftCardUtil.m5198a()) {
            this.f2555s.setVisibility(0);
        }
    }

    public void m3868a(Cart cart) {
        if (cart.hasGiftCardApplied() && GiftCardUtil.m5198a()) {
            this.f2559w.setVisibility(0);
            if (CurrencyUtil.m3072a()) {
                this.f2536S.setText(cart.getGiftCardCreditAmount().multiply(-1).formatWithConditionalCurrencyCode());
                return;
            } else {
                this.f2536S.setText("-" + CurrencyUtil.m3060a(cart.getGiftCardCreditAmount().getAmount().toString(), cart.getGiftCardCreditAmount().getCurrency().getCurrencyCode()));
                return;
            }
        }
        this.f2559w.setVisibility(8);
    }

    private void m3861a(View view, Resources resources, CartItemHolder cartItemHolder) {
        TextView textView = (TextView) view.findViewById(2131755369);
        textView.setCompoundDrawablesWithIntrinsicBounds(IconDrawable.m775a(resources).m779a(resources.getColor(R.green)).m780a(EtsyFontIcons.GIFT_CARD).m778a(resources.getDimension(2131362137)).m777a(), null, null, null);
        textView.setCompoundDrawablePadding(resources.getDimensionPixelOffset(R.padding_small));
        textView.setOnClickListener(new CartItemHolder(this));
    }

    public void m3875c(Resources resources) {
        this.f2556t.setButtonDrawable(IconSelectorDrawable.createChecked(IconDrawable.m775a(resources).m779a(resources.getColor(R.green)).m778a((float) resources.getDimensionPixelOffset(2131362136)).m780a(EtsyFontIcons.UNCHECKED), EtsyFontIcons.CHECKED));
    }

    public void m3881i() {
        this.f2552p.setVisibility(8);
        this.f2553q.setVisibility(8);
    }

    public void m3869a(Cart cart, FragmentActivity fragmentActivity) {
        this.f2558v.setText(cart.getSubtotal().formatWithConditionalCurrencyCode());
        this.f2531M.setText(cart.getShippingCost().formatWithConditionalCurrencyCode());
        this.f2561y.setText(cart.calculateOrderTotal(GiftCardUtil.m5198a()).formatWithConditionalCurrencyCode());
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cf) && cart.hasTransparentPriceMessage()) {
            this.f2562z.setText(Html.fromHtml(cart.getTransparentPriceMessage()));
            m3862a(this.f2562z, fragmentActivity);
            this.f2562z.setVisibility(0);
            return;
        }
        this.f2562z.setVisibility(8);
    }

    public void m3866a(Context context, Cart cart, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (cart.getCoupon() == null) {
            if (cart.getSellerHasActiveCoupons()) {
                this.f2548l.setVisibility(0);
                this.f2547k.setText(R.coupon_field_hint);
                this.f2547k.setTextColor(context.getResources().getColor(R.light_grey));
                this.f2547k.setOnClickListener(onClickListener);
            } else {
                this.f2548l.setVisibility(8);
            }
            this.f2549m.setVisibility(8);
            return;
        }
        this.f2548l.setVisibility(8);
        this.f2549m.setVisibility(0);
        this.f2550n.setText(cart.getCouponCode());
        if (CurrencyUtil.m3072a()) {
            this.f2551o.setText(cart.calculateDiscountAmount().multiply(-1).formatWithConditionalCurrencyCode());
        } else {
            this.f2551o.setText("-" + CurrencyUtil.m3060a(cart.calculateDiscountAmount().getAmount().toString(), cart.calculateDiscountAmount().getCurrency().getCurrencyCode()));
        }
        this.f2550n.setOnClickListener(onClickListener2);
    }

    public void m3864a(Context context, Cart cart) {
        EtsyMoney taxCost = cart.getTaxCost();
        if (taxCost.getAmount().equals(new BigDecimal(0))) {
            this.f2527I.setVisibility(8);
            return;
        }
        this.f2528J.setText(taxCost.formatWithConditionalCurrencyCode());
        this.f2527I.setVisibility(0);
    }

    public void m3870a(BaseActivity baseActivity, Cart cart, aa aaVar) {
        if (!cart.isDownloadOnly() && cart.getCartShippingDetails() != null) {
            if (cart.getShippingOption() != null) {
                cart.getViewState().setEditingShippingCosts(true);
                List shippingOptionList = cart.getCartShippingDetails().getShippingOptionList();
                if (!(cart.getViewState().isRequestingShippingCosts() || cart.getViewState().isViewingShippingCosts() || shippingOptionList == null || shippingOptionList.size() != 1)) {
                    cart.getViewState().setViewingShippingCosts(true);
                }
            }
            RealTimeShippingCalculatorController realTimeShippingCalculatorController = new RealTimeShippingCalculatorController(baseActivity, cart, this.f2533O, this.f2534P, aaVar);
        }
    }

    public void m3865a(Context context, Cart cart, OnClickListener onClickListener) {
        if (TextUtils.isEmpty(cart.getMessageToSeller())) {
            this.f2545i.setText(R.optional_note_hint);
            this.f2545i.setTextColor(context.getResources().getColor(R.light_grey));
        } else {
            this.f2545i.setText(cart.getMessageToSeller());
            this.f2545i.setTextColor(context.getResources().getColor(R.grey));
        }
        this.f2545i.setOnClickListener(onClickListener);
        this.f2546j.setText(m3860a(context));
    }

    private Spanned m3860a(Context context) {
        if (f2518T == null) {
            f2518T = Html.fromHtml(context.getString(R.cart_add_a_note_label));
        }
        return f2518T;
    }

    private void m3862a(TextView textView, FragmentActivity fragmentActivity) {
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            EtsyLinkify.m5489a(textView, true, false, new CartItemHolder(this, fragmentActivity, urls[0].getURL()));
        }
    }
}
