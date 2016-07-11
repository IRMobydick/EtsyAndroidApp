package com.etsy.android.ui.cart.googlewallet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.cart.CartEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.f;
import com.google.android.gms.wallet.j;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.cart.googlewallet.a */
public class GoogleWalletCartHelper extends GoogleWalletHelperBase {

    /* renamed from: com.etsy.android.ui.cart.googlewallet.a.1 */
    class GoogleWalletCartHelper implements OnCancelListener {
        final /* synthetic */ GoogleWalletCartHelper f2475a;

        GoogleWalletCartHelper(GoogleWalletCartHelper googleWalletCartHelper) {
            this.f2475a = googleWalletCartHelper;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f2475a.m3809b();
        }
    }

    public GoogleWalletCartHelper(Context context) {
        super(context);
    }

    public void m3820a(Cart cart, int i) {
        CartEventTracker.m3726b(cart, "cart_view");
        f a = MaskedWalletRequest.newBuilder().c("Etsy - " + cart.getShopName()).b(cart.getSubtotal().getCurrency().getCurrencyCode()).a(cart.getTotal().getAmount().toString());
        if (!cart.isGiftCardCart()) {
            a.a(true);
        }
        j.b.a(this.b, a.a(), m3813a(44032, i));
    }

    public void m3821a(ResultCallback<BooleanResult> resultCallback) {
        j.b.a(this.b).setResultCallback(resultCallback);
    }

    public boolean m3822a() {
        return this.c == null && this.b.isConnected();
    }

    public void m3818a(Activity activity, int i) {
        if (this.c != null) {
            m3823b(activity, i);
        } else if (this.b.isConnected()) {
            bl.m3365b((Context) activity, (int) R.whoops_somethings_wrong);
        } else {
            bl.m3365b((Context) activity, (int) R.google_wallet_error_unavailable);
        }
    }

    private int m3813a(int i, int i2) {
        return i | i2;
    }

    public static int m3812a(int i) {
        return MotionEventCompat.ACTION_POINTER_INDEX_MASK & i;
    }

    public static int m3816b(int i) {
        return i & ValidationUtils.APPBOY_STRING_MAX_LENGTH;
    }

    public static boolean m3817c(int i) {
        int a = GoogleWalletCartHelper.m3812a(i);
        return a == 43776 || a == 44032;
    }

    protected void m3823b(Activity activity, int i) {
        int a = m3813a(43776, i);
        if (this.c.hasResolution()) {
            try {
                this.c.startResolutionForResult(activity, a);
            } catch (SendIntentException e) {
                m3807a(402, (Context) activity);
            }
        } else {
            int errorCode = this.c.getErrorCode();
            if (GooglePlayServicesUtil.isUserRecoverableError(errorCode)) {
                GooglePlayServicesUtil.getErrorDialog(errorCode, activity, a, new GoogleWalletCartHelper(this)).show();
            } else {
                m3807a(errorCode, (Context) activity);
            }
        }
        this.c = null;
    }

    public void m3819a(FragmentActivity fragmentActivity, Cart cart, int i, int i2, Intent intent) {
        switch (GoogleWalletCartHelper.m3812a(i)) {
            case 43776:
                m3814a(fragmentActivity, i2, intent);
            case 44032:
                m3815a(fragmentActivity, cart, i2, intent);
            default:
        }
    }

    private void m3815a(FragmentActivity fragmentActivity, Cart cart, int i, Intent intent) {
        CartEventTracker.m3724b(fragmentActivity instanceof ITrackingView ? ((ITrackingView) fragmentActivity).getAnalyticsContext() : null, cart, "cart_view", i);
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
                MaskedWallet a = GoogleWalletHelperBase.m3802a(intent);
                if (a != null) {
                    Nav.m4682a(fragmentActivity).m4683a().m4457a(cart, a.getGoogleTransactionId(), GoogleWalletHelperBase.m3803a(a));
                }
            case Task.NETWORK_STATE_CONNECTED /*0*/:
            default:
                m3807a(m3808b(intent), (Context) fragmentActivity);
        }
    }

    private void m3814a(Context context, int i, Intent intent) {
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (!this.b.isConnected() && !this.b.isConnecting()) {
                    m3809b();
                }
            default:
                m3807a(m3808b(intent), context);
        }
    }
}
