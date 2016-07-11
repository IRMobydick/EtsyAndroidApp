package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.util.ah;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.local.LocalMarketIntentLauncher;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.DateFormat;
import java.util.Locale;

public class LocalStoreInfoDetailsFragment extends EtsyFragment {
    private LinearLayout mHoursLayout;
    private LocalMarket mMarket;

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment.1 */
    class C07701 extends TrackingOnClickListener {
        final /* synthetic */ LocalStoreInfoDetailsFragment f3100a;

        C07701(LocalStoreInfoDetailsFragment localStoreInfoDetailsFragment) {
            this.f3100a = localStoreInfoDetailsFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3100a.getActivity()).m4679f();
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment.2 */
    class C07712 extends TrackingOnClickListener {
        final /* synthetic */ String f3101a;
        final /* synthetic */ LocalStoreInfoDetailsFragment f3102b;

        C07712(LocalStoreInfoDetailsFragment localStoreInfoDetailsFragment, String str) {
            this.f3102b = localStoreInfoDetailsFragment;
            this.f3101a = str;
        }

        public void onViewClick(View view) {
            LocalMarketIntentLauncher.m4395a(this.f3102b.getActivity(), this.f3102b.getAnalyticsContext(), this.f3101a);
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment.3 */
    class C07723 extends TrackingOnClickListener {
        final /* synthetic */ LocalStore f3103a;
        final /* synthetic */ LocalStoreInfoDetailsFragment f3104b;

        C07723(LocalStoreInfoDetailsFragment localStoreInfoDetailsFragment, LocalStore localStore) {
            this.f3104b = localStoreInfoDetailsFragment;
            this.f3103a = localStore;
        }

        public void onViewClick(View view) {
            LocalMarketIntentLauncher.m4398a(this.f3104b.getActivity(), this.f3103a.getPhoneNumber());
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment.4 */
    class C07734 extends TrackingOnClickListener {
        final /* synthetic */ LocalStoreInfoDetailsFragment f3105a;

        C07734(LocalStoreInfoDetailsFragment localStoreInfoDetailsFragment) {
            this.f3105a = localStoreInfoDetailsFragment;
        }

        public void onViewClick(View view) {
            ah.m3223a(this.f3105a.getActivity(), this.f3105a.mMarket.getExternalUrl());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMarket = (LocalMarket) getArguments().getSerializable(ResponseConstants.LOCAL_MARKET);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903219, viewGroup, false);
        this.mHoursLayout = (LinearLayout) inflate.findViewById(2131755727);
        inflate.findViewById(2131755726).setOnClickListener(new C07701(this));
        ((TextView) inflate.findViewById(2131755732)).setText(this.mMarket.getTitle());
        bindAddress(inflate.findViewById(2131755733), (TextView) inflate.findViewById(2131755734));
        bindStorePhoneNumber(this.mMarket.getStore(), (TextView) inflate.findViewById(2131755736), inflate.findViewById(2131755735));
        bindWebsite(inflate.findViewById(2131755737), (TextView) inflate.findViewById(2131755738));
        bindDescription(inflate.findViewById(2131755739), (TextView) inflate.findViewById(2131755740));
        return inflate;
    }

    private void bindAddress(View view, TextView textView) {
        Object a = LocalMarketFormatter.m3300a(this.mMarket);
        textView.setText(a);
        view.setOnClickListener(new C07712(this, a));
    }

    private void bindStorePhoneNumber(@NonNull LocalStore localStore, TextView textView, View view) {
        CharSequence a = LocalMarketFormatter.m3301a(localStore);
        if (bh.m3343b(localStore.getPhoneNumber())) {
            textView.setText(a);
            view.setOnClickListener(new C07723(this, localStore));
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
    }

    private void bindWebsite(View view, TextView textView) {
        if (bh.m3343b(this.mMarket.getExternalUrl())) {
            textView.setText(this.mMarket.getExternalUrl().toLowerCase(Locale.US));
            view.setOnClickListener(new C07734(this));
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
    }

    private void bindDescription(View view, TextView textView) {
        if (bh.m3343b(this.mMarket.getDescription())) {
            view.setVisibility(0);
            textView.setText(Html.fromHtml(this.mMarket.getDescription()));
            EtsyLinkify.m5483a(getActivity(), textView, false);
            return;
        }
        view.setVisibility(8);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        fillDates();
    }

    private void fillDates() {
        if (this.mMarket.getScheduleRollups().size() < 1) {
            this.mHoursLayout.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getActivity());
        for (TimeRange timeRange : this.mMarket.getScheduleRollups()) {
            View inflate = from.inflate(2130903336, this.mHoursLayout, false);
            TextView textView = (TextView) inflate.findViewById(2131755881);
            ((TextView) inflate.findViewById(2131756025)).setText(LocalMarketFormatter.m3302a(timeRange, false));
            textView.setText(String.format("%s \u2013 %s", new Object[]{timeFormat.format(timeRange.getStartTime().getTime()), timeFormat.format(timeRange.getEndTime().getTime())}));
            this.mHoursLayout.addView(inflate);
        }
    }
}
