package com.etsy.android.ui.cart;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.lib.R;
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
import java.util.List;

public class NoteToSellerFragment extends EtsyFragment implements OnEditorActionListener {
    private static final String TAG;
    private Cart mCart;
    private ac mNoteCallback;
    private EditText mNoteEditText;
    private ProgressButton mSubmitButton;

    /* renamed from: com.etsy.android.ui.cart.NoteToSellerFragment.1 */
    class C05971 extends TrackingOnClickListener {
        final /* synthetic */ NoteToSellerFragment f2385a;

        C05971(NoteToSellerFragment noteToSellerFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2385a = noteToSellerFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2385a.submitNote();
        }
    }

    /* renamed from: com.etsy.android.ui.cart.NoteToSellerFragment.2 */
    class C05982 implements EtsyJobResponse<Cart> {
        final /* synthetic */ NoteToSellerFragment f2386a;

        C05982(NoteToSellerFragment noteToSellerFragment) {
            this.f2386a = noteToSellerFragment;
        }

        public void m3703a(List<Cart> list, int i, EtsyResult<Cart> etsyResult) {
            this.f2386a.onSuccessfulSave((Cart) list.get(0));
        }
    }

    /* renamed from: com.etsy.android.ui.cart.NoteToSellerFragment.3 */
    class C05993 implements EtsyJobResponse {
        final /* synthetic */ NoteToSellerFragment f2387a;

        C05993(NoteToSellerFragment noteToSellerFragment) {
            this.f2387a = noteToSellerFragment;
        }

        public void m3704a(EtsyResult etsyResult) {
            this.f2387a.onSuccessfulSave(null);
        }
    }

    /* renamed from: com.etsy.android.ui.cart.NoteToSellerFragment.4 */
    class C06004 implements EtsyJobResponse {
        final /* synthetic */ NoteToSellerFragment f2388a;

        C06004(NoteToSellerFragment noteToSellerFragment) {
            this.f2388a = noteToSellerFragment;
        }

        public void m3705a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1920e(NoteToSellerFragment.TAG, "Error saving cart note. Error code: %d. Error message: %s", Integer.valueOf(i), str);
            this.f2388a.onFailedToSave();
        }
    }

    /* renamed from: com.etsy.android.ui.cart.NoteToSellerFragment.5 */
    class C06015 implements EtsyJobBuilder {
        final /* synthetic */ NoteToSellerFragment f2389a;

        C06015(NoteToSellerFragment noteToSellerFragment) {
            this.f2389a = noteToSellerFragment;
        }

        public void m3706a() {
            this.f2389a.startLoading();
        }
    }

    static {
        TAG = EtsyDebug.m1891a(NoteToSellerFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mCart = (Cart) arguments.getSerializable("cart");
        }
    }

    public static NoteToSellerFragment newInstance() {
        return new NoteToSellerFragment();
    }

    public void setNoteListener(ac acVar) {
        this.mNoteCallback = acVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903227, viewGroup, false);
        this.mNoteEditText = (EditText) inflate.findViewById(2131755788);
        this.mNoteEditText.setOnEditorActionListener(this);
        this.mSubmitButton = (ProgressButton) inflate.findViewById(2131755637);
        this.mSubmitButton.setOnClickListener(new C05971(this, this.mCart));
        if (this.mCart != null) {
            setupNoteEditText(this.mCart.getMessageToSeller());
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mCart == null && (getParentFragment() instanceof IDialogFragment)) {
            ((IDialogFragment) getParentFragment()).dismissAllowingStateLoss();
        }
    }

    private void setupNoteEditText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mNoteEditText.setText(str);
            this.mNoteEditText.setSelection(str.length());
        }
        ai.m3227b(this.mContext, this.mNoteEditText);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        submitNote();
        return true;
    }

    private void submitNote() {
        getRequestQueue().m1697a((Object) this, EtsyJobBuilder.m1307a(CartsRequest.updateCart(this.mCart.getCartId())).m1323a(CartsRequest.PARAM_NOTE_TO_SELLER, this.mNoteEditText.getText().toString()).m1326b(CartsRequest.CART_INCLUDES).m1318a(new C06015(this)).m1320a(new C06004(this)).m1319a(new C05993(this)).m1321a(new C05982(this)).m1324a());
    }

    private void onFailedToSave() {
        endLoading();
        bl.m3365b(this.mContext, (int) R.note_to_seller_error_saving);
    }

    private void onSuccessfulSave(Cart cart) {
        if (!(this.mNoteCallback == null || cart == null)) {
            this.mNoteCallback.m3738a(cart);
        }
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).dismissAllowingStateLoss();
        }
        CartEventTracker.m3718a(getAnalyticsContext(), cart, "cart_view");
    }

    private void startLoading() {
        this.mNoteEditText.setEnabled(false);
        this.mSubmitButton.showLoading();
    }

    private void endLoading() {
        this.mNoteEditText.setEnabled(true);
        this.mSubmitButton.hideLoading();
    }
}
