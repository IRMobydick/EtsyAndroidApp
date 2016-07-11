package com.etsy.android.ui.nav;

import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.ui.cart.CouponCodeFragment;
import com.etsy.android.ui.cart.NoteToSellerFragment;
import com.etsy.android.ui.cart.ac;
import com.etsy.android.ui.convos.ConvoComposeFragment;
import com.etsy.android.ui.dialog.BOEPermissionDeniedDialogFragment;
import com.etsy.android.ui.dialog.CountryDialogFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.dialog.PromoDialogFragment;
import com.etsy.android.ui.dialog.ShopShareOnboarding;
import com.etsy.android.ui.dialog.VariationsListDialog;
import com.etsy.android.ui.favorites.ListingCollectionsFragment;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment;
import com.etsy.android.ui.user.LeaveFeedbackFragment;
import com.etsy.android.ui.user.SupportFeedbackFragment;
import com.etsy.android.ui.user.UserSettingsFragment;
import com.etsy.android.ui.user.auth.RegisterFragment;
import com.etsy.android.ui.user.auth.SignInFragment;
import com.etsy.android.ui.user.auth.SignInNagFragment;
import com.etsy.android.ui.user.auth.SignInTwoFactorFragment;
import com.etsy.android.uikit.share.ShareBrokerFragment;
import com.etsy.android.uikit.ui.dialog.text.TextInfoDialog;
import com.etsy.android.uikit.ui.user.auth.ForgotPasswordFragment;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.nav.a */
public class DialogNavigator {
    private static final String f3169a;
    private final FragmentActivity f3170b;
    private final NavTracker f3171c;
    private Bundle f3172d;
    private OnDismissListener f3173e;
    private String f3174f;

    static {
        f3169a = EtsyDebug.m1891a(DialogNavigator.class);
    }

    DialogNavigator(FragmentActivity fragmentActivity) {
        this.f3170b = fragmentActivity;
        this.f3171c = new NavTracker();
        this.f3172d = new Bundle();
    }

    public DialogNavigator m4420a(Bundle bundle) {
        this.f3172d = bundle;
        return this;
    }

    public DialogNavigator m4419a(OnDismissListener onDismissListener) {
        this.f3173e = onDismissListener;
        return this;
    }

    public EtsyDialogFragment m4404a(int i, String str, OnClickListener onClickListener) {
        Fragment newInstance = PromoDialogFragment.newInstance(i, str, onClickListener);
        newInstance.setArguments(this.f3172d);
        return m4401a(null, newInstance, EtsyDialogFragment.OPT_X_BUTTON, newInstance.getOnClickListener(), true);
    }

    public EtsyDialogFragment m4403a() {
        Fragment userSettingsFragment = new UserSettingsFragment();
        userSettingsFragment.setArguments(this.f3172d);
        String string = this.f3170b.getString(R.settings);
        this.f3171c.m4723v();
        return m4401a(string, userSettingsFragment, EtsyDialogFragment.OPT_X_BUTTON, userSettingsFragment.getOnClickListener(), true);
    }

    public EtsyDialogFragment m4421b() {
        Fragment signInNagFragment = new SignInNagFragment();
        signInNagFragment.setArguments(this.f3172d);
        this.f3171c.m4724w();
        return m4401a(StringUtils.EMPTY, signInNagFragment, StringUtils.EMPTY, null, true);
    }

    public EtsyDialogFragment m4410a(SignInFlow signInFlow) {
        Fragment signInFragment = new SignInFragment();
        signInFragment.setArguments(this.f3172d);
        this.f3171c.m2078a(signInFlow);
        this.f3174f = "signIn";
        return m4401a(this.f3170b.getString(R.sign_in), signInFragment, StringUtils.EMPTY, null, true);
    }

    public EtsyDialogFragment m4423b(SignInFlow signInFlow) {
        Fragment registerFragment = new RegisterFragment();
        registerFragment.setArguments(this.f3172d);
        this.f3171c.m2082b(signInFlow);
        this.f3174f = "register";
        return m4401a(this.f3170b.getString(R.register), registerFragment, StringUtils.EMPTY, null, true);
    }

    public EtsyDialogFragment m4418a(String str, String str2, SignInFlow signInFlow) {
        Fragment signInTwoFactorFragment = new SignInTwoFactorFragment();
        this.f3172d.putString("username", str);
        this.f3172d.putString("workflow_key", str2);
        String string = this.f3170b.getString(R.sign_in);
        signInTwoFactorFragment.setArguments(this.f3172d);
        this.f3171c.m4703c(signInFlow);
        return m4401a(string, signInTwoFactorFragment, EtsyDialogFragment.OPT_X_BUTTON, null, true);
    }

    public EtsyDialogFragment m4412a(EtsyTrioDialogFragment etsyTrioDialogFragment, int i, int i2, int i3, String str) {
        Fragment newInstance = EtsyTrioDialogFragment.newInstance(etsyTrioDialogFragment, i, i2, i3);
        this.f3172d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.f3172d);
        return m4401a(str, newInstance, StringUtils.EMPTY, null, true);
    }

    public EtsyDialogFragment m4413a(EtsyTrioDialogFragment etsyTrioDialogFragment, int i, int i2, String str, OnDismissListener onDismissListener) {
        Fragment newInstance = EtsyTrioDialogFragment.newInstance(etsyTrioDialogFragment, i, i2, 0);
        this.f3172d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.f3172d);
        this.f3173e = onDismissListener;
        return m4401a(str, newInstance, StringUtils.EMPTY, null, true);
    }

    public EtsyDialogFragment m4416a(String str, VariationsListDialog variationsListDialog, EtsyId etsyId, long j) {
        Fragment newInstance = VariationsListDialog.newInstance(etsyId, j, variationsListDialog);
        this.f3172d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.f3172d);
        this.f3171c.m4718q();
        return m4401a(str, newInstance, EtsyDialogFragment.OPT_X_BUTTON, null, true);
    }

    public EtsyDialogFragment m4411a(CountryUtil countryUtil, ArrayList<Country> arrayList, ArrayList<Country> arrayList2, String str) {
        Fragment newInstance = CountryDialogFragment.newInstance(countryUtil, arrayList, arrayList2);
        this.f3172d.putBoolean("USE_DIM", true);
        String string = this.f3170b.getResources().getString(R.countries);
        this.f3171c.m4695a(str);
        return m4401a(string, newInstance, EtsyDialogFragment.OPT_X_BUTTON, null, true);
    }

    public EtsyDialogFragment m4424c() {
        Fragment newInstance = ConvoComposeFragment.newInstance();
        if (this.f3172d.containsKey("convo_id")) {
            this.f3172d.putString("TRACKING_NAME", "conversations_thread_reply");
        } else {
            this.f3172d.putString("TRACKING_NAME", "conversations_new");
        }
        newInstance.setArguments(this.f3172d);
        String string = this.f3170b.getString(R.convo_compose_new_title);
        this.f3174f = "convoCompose";
        EtsyDialogFragment a = m4401a(string, newInstance, EtsyDialogFragment.OPT_X_BUTTON, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        this.f3171c.m4698b(this.f3172d);
        return a;
    }

    public EtsyDialogFragment m4414a(SupportFeedbackFragment supportFeedbackFragment) {
        Fragment newInstance = SupportFeedbackFragment.newInstance();
        newInstance.setArguments(this.f3172d);
        String string = this.f3170b.getString(R.supportfeedback_compose_new_title);
        this.f3174f = "supportFeedbackCompose";
        EtsyDialogFragment a = m4401a(string, newInstance, EtsyDialogFragment.OPT_X_BUTTON, newInstance.getOnClickListener(), true);
        a.setWindowAnimation(R.DialogAnimBottom);
        this.f3171c.m2076a(this.f3172d);
        return a;
    }

    public EtsyDialogFragment m4407a(Cart cart, CouponCodeFragment couponCodeFragment) {
        Fragment newInstance = CouponCodeFragment.newInstance();
        newInstance.setCouponListener(couponCodeFragment);
        this.f3172d.putSerializable("cart", cart);
        this.f3172d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.f3172d);
        EtsyDialogFragment a = m4401a(this.f3170b.getString(R.coupon_code_title, new Object[]{cart.getShopName()}), newInstance, EtsyDialogFragment.OPT_X_BUTTON, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        this.f3171c.m4708g();
        return a;
    }

    public EtsyDialogFragment m4406a(Cart cart, ac acVar) {
        Fragment newInstance = NoteToSellerFragment.newInstance();
        newInstance.setNoteListener(acVar);
        this.f3172d.putSerializable("cart", cart);
        this.f3172d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.f3172d);
        EtsyDialogFragment a = m4401a(this.f3170b.getString(R.note_to_seller_title, new Object[]{cart.getShopName()}), newInstance, EtsyDialogFragment.OPT_X_BUTTON, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        this.f3171c.m4709h();
        return a;
    }

    public EtsyDialogFragment m4422b(OnDismissListener onDismissListener) {
        Fragment leaveFeedbackFragment = new LeaveFeedbackFragment();
        this.f3173e = onDismissListener;
        leaveFeedbackFragment.setArguments(this.f3172d);
        EtsyDialogFragment a = m4401a(this.f3170b.getString(R.write_a_review), leaveFeedbackFragment, EtsyDialogFragment.OPT_X_BUTTON, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        return a;
    }

    @Deprecated
    public EtsyDialogFragment m4408a(EtsyId etsyId, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ResponseConstants.LISTING_ID, etsyId);
        bundle.putString(ResponseConstants.LISTING_IMAGE_URL, str);
        return m4402b(bundle);
    }

    public EtsyDialogFragment m4409a(ListingLike listingLike) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityFeedEntity.LISTING, listingLike);
        return m4402b(bundle);
    }

    private EtsyDialogFragment m4402b(Bundle bundle) {
        Fragment listingCollectionsFragment = new ListingCollectionsFragment();
        this.f3172d.putAll(bundle);
        listingCollectionsFragment.setArguments(this.f3172d);
        String string = this.f3170b.getString(R.add_listing_to_collection_title);
        String string2 = this.f3170b.getString(R.done);
        this.f3171c.m4720s();
        return m4401a(string, listingCollectionsFragment, string2, listingCollectionsFragment.getOnClickListener(), false);
    }

    public EtsyDialogFragment m4425c(SignInFlow signInFlow) {
        Fragment forgotPasswordFragment = new ForgotPasswordFragment();
        forgotPasswordFragment.setArguments(this.f3172d);
        EtsyDialogFragment a = m4401a(this.f3170b.getString(R.forgot_password_title), forgotPasswordFragment, EtsyDialogFragment.OPT_X_BUTTON, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        this.f3171c.m4705d(signInFlow);
        return a;
    }

    public EtsyDialogFragment m4405a(Attendee attendee, LocalMarket localMarket) {
        Fragment localDatesAttendingFragment = new LocalDatesAttendingFragment();
        this.f3172d.putSerializable("attendee", attendee);
        this.f3172d.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        localDatesAttendingFragment.setArguments(this.f3172d);
        return m4401a(StringUtils.EMPTY, localDatesAttendingFragment, StringUtils.EMPTY, null, false);
    }

    public EtsyDialogFragment m4426d() {
        Fragment shareBrokerFragment = new ShareBrokerFragment();
        shareBrokerFragment.setArguments(this.f3172d);
        EtsyDialogFragment a = m4401a(this.f3170b.getString(R.share_with), shareBrokerFragment, null, null, true);
        a.setWindowAnimation(R.DialogAnimBottom);
        return a;
    }

    public EtsyDialogFragment m4417a(String str, String str2) {
        Fragment textInfoDialog = new TextInfoDialog();
        this.f3172d.putString(FindsModule.FIELD_TEXT, str2);
        textInfoDialog.setArguments(this.f3172d);
        this.f3171c.m2086f();
        return m4401a(str, textInfoDialog, EtsyDialogFragment.OPT_X_BUTTON, null, true);
    }

    public EtsyDialogFragment m4427e() {
        this.f3172d.putBoolean("USE_DIM", true);
        Fragment shopShareOnboarding = new ShopShareOnboarding();
        shopShareOnboarding.setArguments(this.f3172d);
        this.f3174f = "shopShareOnboarding";
        return m4401a(StringUtils.EMPTY, shopShareOnboarding, null, shopShareOnboarding.getOnClickListener(), true);
    }

    public EtsyDialogFragment m4415a(@NonNull String str) {
        Fragment bOEPermissionDeniedDialogFragment = new BOEPermissionDeniedDialogFragment();
        this.f3172d.putString("denied_permission", str);
        bOEPermissionDeniedDialogFragment.setArguments(this.f3172d);
        return m4401a(null, bOEPermissionDeniedDialogFragment, StringUtils.EMPTY, null, true);
    }

    private EtsyDialogFragment m4401a(String str, Fragment fragment, String str2, OnClickListener onClickListener, boolean z) {
        EtsyDialogFragment newInstance = EtsyDialogFragment.newInstance(fragment);
        newInstance.setArguments(this.f3172d);
        newInstance.setTitle(str);
        newInstance.setOkButton(str2, onClickListener, z);
        newInstance.setOnDismissListener(this.f3173e);
        newInstance.setCancelable(false);
        FragmentManager supportFragmentManager = this.f3170b.getSupportFragmentManager();
        String str3 = TextUtils.isEmpty(this.f3174f) ? newInstance.getClass().getSimpleName() + newInstance.hashCode() : this.f3174f;
        if (supportFragmentManager.findFragmentByTag(str3) == null) {
            newInstance.show(supportFragmentManager, str3);
        }
        return newInstance;
    }
}
