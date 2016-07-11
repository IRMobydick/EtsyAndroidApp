package com.etsy.android.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class LegalInfoFragment extends EtsyFragment {
    private static final String TAG;
    private View mView;

    /* renamed from: com.etsy.android.ui.user.LegalInfoFragment.1 */
    class C08301 extends TrackingOnClickListener {
        final /* synthetic */ LegalInfoFragment f3467a;

        C08301(LegalInfoFragment legalInfoFragment) {
            this.f3467a = legalInfoFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3467a.mActivity).m4683a().m4496b(5);
        }
    }

    /* renamed from: com.etsy.android.ui.user.LegalInfoFragment.2 */
    class C08312 extends TrackingOnClickListener {
        final /* synthetic */ LegalInfoFragment f3468a;

        C08312(LegalInfoFragment legalInfoFragment) {
            this.f3468a = legalInfoFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3468a.mActivity).m4683a().m4496b(6);
        }
    }

    /* renamed from: com.etsy.android.ui.user.LegalInfoFragment.3 */
    class C08323 extends TrackingOnClickListener {
        final /* synthetic */ LegalInfoFragment f3469a;

        C08323(LegalInfoFragment legalInfoFragment) {
            this.f3469a = legalInfoFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3469a.mActivity).m4683a().m4496b(7);
        }
    }

    static {
        TAG = EtsyDebug.m1891a(LegalInfoFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = layoutInflater.inflate(2130903208, viewGroup, false);
        setUpCommonButtons();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).setTitle(this.mActivity.getString(R.legal));
        }
    }

    private void setUpCommonButtons() {
        this.mView.findViewById(2131755708).setOnClickListener(new C08301(this));
        this.mView.findViewById(2131755709).setOnClickListener(new C08312(this));
        this.mView.findViewById(2131755710).setOnClickListener(new C08323(this));
    }
}
